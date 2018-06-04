package com.neo.util;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.Document;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DBObjectUtil {

    private static final DateFormat secondFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final DateFormat minuteFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final DateFormat hourFormat = new SimpleDateFormat("yyyy-MM-dd HH");
    private static final DateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateFormat monthFormat = new SimpleDateFormat("yyyy-MM");

    /**
     * 支持@Field注解，对应mongodb真实属性名
     * 支持区间查询，属性名以Begin/End 结尾
     * 支持模糊查詢，屬性名字以Like結尾
     * 支持时间格式字符串，属性名包含Date/Time
     * 支持数组查询，mongodb数组属性需以Array结尾
     * 支持批量查询，查询参数需要以"," 分割
     *
     * @param model 请求参数实体
     * @return
     */
    public static Document generateDBObject(Object model) {
        Document dbObject = new Document();
        try {
            Field[] field = model.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
            for (int j = 0; j < field.length; j++) { // 遍历所有属性
                String name = field[j].getName(); // 获取属性的名字
                //分页参数跳过
                org.springframework.data.mongodb.core.mapping.Field f = field[j].getAnnotation(org.springframework.data.mongodb.core.mapping.Field.class);
                String realField = f == null ? null : f.value();
                if (realField != null && realField.equals("-")) {
                    continue;
                }
                String getMethodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
                String type = field[j].getGenericType().toString(); // 获取属性的类型
                Method m = model.getClass().getMethod(getMethodName);
                Object valueInit = m.invoke(model); // 调用getter方法获取属性值
                //空值跳过
                if (valueInit == null) {
                    continue;
                }
                if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class
                    // "，后面跟类名
                    String value = (String) valueInit; // 调用getter方法获取属性值
                    //空值參數處理
                    if (value.equals("")) {
                        continue;
                    }
                    //区间判断
                    if (name.contains("Date") || name.contains("Time")) {
                        generateDateOption(dbObject, realField, name, value);
                    } else {
                        generateOption(dbObject, realField, name, value);
                    }
                } else {
                    generateOption(dbObject, realField, name, valueInit);
                }
            }
            return dbObject;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    //字符串加区间默认格式化成时间
    private static void generateDateOption(Document dbObject, String realField, String key, String value) throws ParseException {
        //时间区间开始
        if (key.endsWith("Begin") || key.endsWith("Start")) {
            Date d;
            if (value.length() == 19) {
                d = secondFormat.parse(value);
            } else if (value.length() == 16) {
                d = minuteFormat.parse(value);
            } else if (value.length() == 13) {
                d = hourFormat.parse(value);
            } else if (value.length() == 10) {
                d = dayFormat.parse(value);
            } else if (value.length() == 7) {
                d = monthFormat.parse(value);
            } else {
                return;
            }
            String field = realField == null ? key.substring(0, key.length() - 5) : realField;
            if (dbObject.containsKey(field)) {
                DBObject obj = (DBObject) dbObject.get(field);
                obj.put("$gte", d);
                dbObject.put(field, obj);
            } else {
                DBObject obj = new BasicDBObject();
                obj.put("$gte", d);
                dbObject.put(field, obj);
            }
            return;
        } else if (key.endsWith("End")) {
            Date d;
            if (value.length() == 19) {
                //时间+1s
                d = secondFormat.parse(value);
                Calendar c = Calendar.getInstance();
                c.setTime(d);
                c.add(Calendar.SECOND, 1);
                d = c.getTime();
            } else if (value.length() == 16) {
                //时间+1m
                d = minuteFormat.parse(value);
                Calendar c = Calendar.getInstance();
                c.setTime(d);
                c.add(Calendar.MINUTE, 1);
                d = c.getTime();
            } else if (value.length() == 13) {
                d = hourFormat.parse(value);
                Calendar c = Calendar.getInstance();
                c.setTime(d);
                c.add(Calendar.HOUR, 1);
                d = c.getTime();
            } else if (value.length() == 10) {
                d = dayFormat.parse(value);
                Calendar c = Calendar.getInstance();
                c.setTime(d);
                c.add(Calendar.DAY_OF_MONTH, 1);
                d = c.getTime();
            } else if (value.length() == 7) {
                d = monthFormat.parse(value);
                Calendar c = Calendar.getInstance();
                c.setTime(d);
                c.add(Calendar.MONTH, 1);
                d = c.getTime();
            } else {
                return;
            }
            String field = realField == null ? key.substring(0, key.length() - 3) : realField;
            if (dbObject.containsKey(field)) {
                DBObject obj = (DBObject) dbObject.get(field);
                obj.put("$lt", d);
                dbObject.put(field, obj);
            } else {
                DBObject gtObject = new BasicDBObject();
                gtObject.put("$lt", d);
                dbObject.put(field, gtObject);
            }
        }
    }

    //非时间格式操作
    private static void generateOption(Document dbObject, String realField, String key, Object value) throws ParseException {
        if (key.endsWith("Begin") || key.endsWith("Start")) {
            String field = realField == null ? key.substring(0, key.length() - 5) : realField;
            if (dbObject.containsKey(field)) {
                DBObject obj = (DBObject) dbObject.get(field);
                obj.put("$gte", value);
                dbObject.put(field, obj);
            } else {
                DBObject gtObject = new BasicDBObject();
                gtObject.put("$gte", value);
                dbObject.put(field, gtObject);
            }
        } else if (key.endsWith("End")) {
            String field = realField == null ? key.substring(0, key.length() - 3) : realField;
            if (dbObject.containsKey(field)) {
                DBObject obj = (DBObject) dbObject.get(field);
                obj.put("$lte", value);
                dbObject.put(field, obj);
            } else {
                DBObject gtObject = new BasicDBObject();
                gtObject.put("$lte", value);
                dbObject.put(field, gtObject);
            }
        } else if (key.endsWith("Like")) {
            String field = realField == null ? key.substring(0, key.length() - 4) : realField;
            DBObject gtObject = new BasicDBObject();
            gtObject.put("$regex", value);
            dbObject.put(field, gtObject);
        } else {
            String field = realField == null ? key : realField;
            //包含field注解且注解值为List结尾认为是查询数据内包含的数据
            if (realField != null && realField.endsWith("Array")) {
                //查询数据
                DBObject aObject = new BasicDBObject();
                aObject.put("$all", new String[]{value.toString()});
                dbObject.put(field, aObject);
            } else if (value.toString().contains(",")) {
                //批量查询
                String[] arr = value.toString().split(",");
                DBObject aObject = new BasicDBObject();
                aObject.put("$in", arr);
                dbObject.put(field, aObject);
            } else {
                dbObject.put(field, value);
            }
        }
        return;

    }
}
