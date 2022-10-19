package com.neo.service;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.mongodb.client.result.UpdateResult;
import com.neo.canal.config.Schema;
import com.neo.canal.config.Table;
import com.neo.model.User;
import com.neo.model.UserDetail;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import javax.annotation.Resource;

@Schema("test")
public class UserService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    MongoTemplate mongoTemplate;

    @Table(value = "user_info", event = CanalEntry.EventType.INSERT)
    public void saveUser(Document userInfo) {
        User user=new User();
        user.setUserId((long) userInfo.get("_id"));
        user.setName((String) userInfo.get("name"));

        Query query = new Query(Criteria.where("userId").is(userInfo.get("_id")));
        UserDetail userDetail= mongoTemplate.findOne(query,UserDetail.class);
        if(userDetail!=null){
            user.setAddress((String) userDetail.getAddress());
        }
        mongoTemplate.save(user);
    }

    @Table(value = "user_info", event = CanalEntry.EventType.UPDATE)
    public void updateUser(Document userNew) {
        Query query = new Query(Criteria.where("userId").is(userNew.get("_id")));
        Update update= new Update().set("name", (String) userNew.get("name"));
        //更新查询返回结果集的第一条
        UpdateResult result =mongoTemplate.updateFirst(query,update,User.class);
    }

    @Table(value = "user_info", event = CanalEntry.EventType.DELETE)
    public void deleteUser(Document user) {
        Query query=new Query(Criteria.where("_id").is(user.get("id")));
        mongoTemplate.remove(query,User.class);
    }

    @Table(value = "user_detail", event = CanalEntry.EventType.UPDATE)
    public void updateUserDetail(Document userDetail) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userDetail.get("userId")));
        Update update= new Update().set("address", (String) userDetail.get("address"));
        UpdateResult result =mongoTemplate.updateFirst(query,update,User.class);
    }
}