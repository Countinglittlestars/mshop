package com.skymall.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class WrapperUtil {

    public static QueryWrapper wrap(QueryWrapper entityWrapper, Object object){
        Field[] fields = object.getClass().getDeclaredFields();
        for(Field field : fields){
            if("serialVersionUID".equals(field.getName())){
                continue;
            }
            Object obj = getFieldValueByName(field.getName(), object);
            if(obj != null){
                String hump = field.getName();
                String line = StringUtils.humpToLine(hump);
                entityWrapper.eq(line, obj);
            }
        }
        return entityWrapper;
    }


    public static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value =method.invoke(o, new Object[] {});
            return value;}
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
