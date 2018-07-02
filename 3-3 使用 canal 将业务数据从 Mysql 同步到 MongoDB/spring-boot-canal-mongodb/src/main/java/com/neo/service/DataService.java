package com.neo.service;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.mongodb.*;
import com.neo.util.DBConvertUtil;
import com.neo.util.SpringUtil;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


@Component
public class DataService {
    protected final static Logger logger = LoggerFactory.getLogger(DataService.class);
    @Resource
    MongoTemplate mongoTemplate;

    public void insert(List<CanalEntry.Column> data, String schemaName, String tableName) {
        Document obj = DBConvertUtil.columnToJson(data);
        if (obj.containsKey("id")) {
            obj.put("_id", obj.get("id"));
            obj.remove("id");
        }
        insertData(schemaName, tableName, obj);
    }

    public void delete(List<CanalEntry.Column> data, String schemaName, String tableName) {
        Document obj = DBConvertUtil.columnToJson(data);
        logger.debug("delete:{}", obj.toString());
        deleteData(schemaName, tableName, obj);
    }

    public void update(List<CanalEntry.Column> data, String schemaName, String tableName) {
        Document obj = DBConvertUtil.columnToJson(data);
        logger.debug("update:{}", obj.toString());
        if (obj.containsKey("id")) {
            updateData(schemaName, tableName, new BasicDBObject("_id", obj.get("id")), obj);
        } else {
            logger.info("unknown data structure");
        }
    }

    public void drop(String tableName) {
        logger.warn("drop table {} from naive", tableName);
        System.out.println("drop table " + tableName + " from naive");
        try {
            mongoTemplate.dropCollection(tableName.trim());
        } catch (Exception e) {
            logger.error("drop tableName error "+ tableName,  e);
        }
    }

    public void insertData(String schemaName, String tableName,  Document obj) {
        try {
            String path = "/" + schemaName + "/" + tableName + "/" + CanalEntry.EventType.INSERT.getNumber();
            mongoTemplate.save(obj,tableName);
        } catch (MongoClientException | MongoSocketException clientException) {
            //客户端连接异常抛出，阻塞同步，防止mongodb宕机
            throw clientException;
        } catch (DuplicateKeyException dke) {
            //主键冲突异常，跳过
            logger.warn("DuplicateKeyException:", dke);
        } catch (Exception e) {
            logger.error(schemaName, tableName, obj, e);
        }
    }

    public void updateData(String schemaName, String tableName, DBObject query, Document obj) {
        String path = "/" + schemaName + "/" + tableName + "/" + CanalEntry.EventType.UPDATE.getNumber();
        Document options = new Document(query.toMap());
        try {
            obj.remove("id");
            mongoTemplate.getCollection(tableName).replaceOne(options,obj);
            obj.putAll(query.toMap());
        } catch (MongoClientException | MongoSocketException clientException) {
            //客户端连接异常抛出，阻塞同步，防止mongodb宕机
            throw clientException;
        } catch (Exception e) {
            logger.error(schemaName, tableName, obj, e);
        }
    }


    public void deleteData(String schemaName, String tableName, Document obj) {
        String path = "/" + schemaName + "/" + tableName + "/" + CanalEntry.EventType.DELETE.getNumber();
        //保存原始数据
        try {
            if (obj.containsKey("id")) {
                obj.put("_id", obj.get("id"));
                obj.remove("id");
                mongoTemplate.remove(new BasicQuery(obj),tableName);
            }
        } catch (MongoClientException | MongoSocketException clientException) {
            //客户端连接异常抛出，阻塞同步，防止mongodb宕机
            throw clientException;
        } catch (Exception e) {
            logger.error(schemaName, tableName, obj, e);
        }
    }

}
