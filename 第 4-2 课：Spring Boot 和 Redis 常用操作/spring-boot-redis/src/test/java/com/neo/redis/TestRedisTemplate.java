package com.neo.redis;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import com.neo.model.User;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedisTemplate {
	@Autowired
	private RedisTemplate redisTemplate;

    @Test
    public void testString()  {
        redisTemplate.opsForValue().set("neo", "ityouknow");
        redisTemplate.opsForValue().set("neo", "hello");
        Assert.assertEquals("hello", redisTemplate.opsForValue().get("neo"));
    }
    
    @Test
    public void testObj(){
        User user=new User("ityouknow@126.com", "smile", "youknow", "know","2020");
        ValueOperations<String, User> operations=redisTemplate.opsForValue();
        operations.set("com.neo", user);
        User u=operations.get("com.neo");
        System.out.println("user: "+u.toString());
    }

    @Test
    public void testExpire() throws InterruptedException {
        User user=new User("ityouknow@126.com", "expire", "youknow", "expire","2020");
        ValueOperations<String, User> operations=redisTemplate.opsForValue();
        operations.set("expire", user,100,TimeUnit.MILLISECONDS);
        Thread.sleep(1000);
        boolean exists=redisTemplate.hasKey("expire");
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
    }

    @Test
    public void testDelete() {
        ValueOperations<String, User> operations=redisTemplate.opsForValue();
        redisTemplate.opsForValue().set("deletekey", "ityouknow");
        redisTemplate.delete("deletekey");
        boolean exists=redisTemplate.hasKey("deletekey");
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
    }

    @Test
    public void testHash() {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put("hash","you","you");
        String value=(String) hash.get("hash","you");
        System.out.println("hash value :"+value);
    }


    @Test
    public void testList() {
        String key="list";
        redisTemplate.delete(key);
        ListOperations<String, String> list = redisTemplate.opsForList();
        list.leftPush(key,"it");
        list.leftPush(key,"you");
        list.leftPush(key,"know");
        String value=(String)list.leftPop(key);
        System.out.println("list value :"+value.toString());

       List<String> values=list.range(key,0,3);
       for (String v:values){
           System.out.println("list range :"+v);
       }
    }


    @Test
    public void testSet() {
        String key="set";
        redisTemplate.delete(key);
        SetOperations<String, String> set = redisTemplate.opsForSet();
        set.add(key,"it");
        set.add(key,"you");
        set.add(key,"you");
        set.add(key,"know");
        Set<String> values=set.members(key);
        for (String v:values){
            System.out.println("set value :"+v);
        }
    }

    @Test
    public void testSetMore() {
        SetOperations<String, String> set = redisTemplate.opsForSet();
        String key1="setMore1";
        String key2="setMore2";
        set.add(key1,"it");
        set.add(key1,"you");
        set.add(key1,"you");
        set.add(key1,"know");
        set.add(key2,"xx");
        set.add(key2,"know");
        Set<String> diffs=set.difference(key1,key2);
        for (String v:diffs){
            System.out.println("diffs set value :"+v);
        }

        String key3="setMore3";
        String key4="setMore4";
        set.add(key3,"it");
        set.add(key3,"you");
        set.add(key3,"xx");
        set.add(key4,"aa");
        set.add(key4,"bb");
        set.add(key4,"know");
        Set<String> unions=set.union(key3,key4);
        for (String v:unions){
            System.out.println("unions value :"+v);
        }
    }


    @Test
    public void testZset(){
        String key="zset";
        redisTemplate.delete(key);
        ZSetOperations<String, String> zset = redisTemplate.opsForZSet();
        zset.add(key,"it",1);
        zset.add(key,"you",6);
        zset.add(key,"know",4);
        zset.add(key,"neo",3);

        Set<String> zsets=zset.range(key,0,3);
        for (String v:zsets){
            System.out.println("zset value :"+v);
        }

        Set<String> zsetB=zset.rangeByScore(key,0,3);
        for (String v:zsetB){
            System.out.println("zsetB value :"+v);
        }
    }

}