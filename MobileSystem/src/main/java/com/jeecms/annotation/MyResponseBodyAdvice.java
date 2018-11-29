package com.jeecms.annotation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@SuppressWarnings("rawtypes")
@Order(1)
@ControllerAdvice
public class MyResponseBodyAdvice implements ResponseBodyAdvice{

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType selectedContentType,
        Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        //判断返回的对象是单个对象，还是list，或者是map
        if(o==null){
            return null;
        }
        String[] includes = {};
        String[] excludes = {};
        Class returnObjectType = o.getClass();
        Object retObj = null;
        // 默认过滤掉page对象中的sort、number/numberOfelements属性
//        jsonFilterObject.getExcludes().put(Page.class,
//                new HashSet<String>(Arrays.asList("sort", "number", "numberOfElements", "pageable")));
//
//        jsonFilterObject.setJsonObject(o);
        //检查返回对象是否加入注解
        if (methodParameter.getMethod().isAnnotationPresent(Json.class)) {
            //获取注解配置的包含和去除字段
            Json serializedField = methodParameter.getMethodAnnotation(Json.class);
            includes = serializedField.includes();
            excludes = serializedField.excludes();
        }
      
        if (returnObjectType.isAssignableFrom(ArrayList.class)){
            //List
            List list = (List)o;
            retObj = handleList(list, includes, excludes);
        }else{
            //Single Object
            retObj = handleSingleObject(o, includes, excludes);
        }
        return retObj;
    }
    
    /** * 处理返回值是单个enity对象 * * @param o * @param includes * @param excludes * @param encode * @return */
    private Object handleSingleObject(Object o, String[] includes, String[] excludes){
        Map<String,Object> map = new HashMap<String, Object>();

        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field:fields){
            //如果未配置表示全部的都返回
            if(includes.length==0 && excludes.length==0){
                String newVal = getNewVal(o, field);
                map.put(field.getName(), newVal);
            }else if(includes.length>0){
                //考虑包含字段
                if(isStringInArray(field.getName(), includes)){
                    String newVal = getNewVal(o, field);
                    map.put(field.getName(), newVal);
                }
            }else{
                //去除字段
                if(excludes.length>0){
                    if(!isStringInArray(field.getName(), excludes)){
                        String newVal = getNewVal(o, field);
                        map.put(field.getName(), newVal);
                    }
                }
            }

        }
        return map;
    }

    /** * 处理返回值是列表 * * @param list * @param includes * @param excludes * @param encode * @return */
    @SuppressWarnings("unchecked")
    private List handleList(List list, String[] includes, String[] excludes){
        List retList = new ArrayList();
        for (Object o:list){
            Map map = (Map) handleSingleObject(o, includes, excludes);
            retList.add(map);
        }
        return retList;
    }
    
    /** * 获取新值 * * @param o * @param field * @param encode * @return */
    private String getNewVal(Object o, Field field){
        String newVal = "";
        try {
            field.setAccessible(true);
            Object val = field.get(o);
            if(val!=null){
             newVal = val.toString();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return newVal;
    }
    
    public static boolean isStringInArray(String str, String[] array){
        for (String val:array){
            if(str.equals(val)){
                return true;
            }
        }
        return false;
    }
}
