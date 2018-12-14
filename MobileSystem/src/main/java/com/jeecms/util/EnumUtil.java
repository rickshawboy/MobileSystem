package com.jeecms.util;

public enum EnumUtil {

   /**枚举第一种用法：常量**/
    RED(1,"sd"), GREEN(1,"sd"), BLANK(1,"sd"), YELLOW(1,"sd");
    
    EnumUtil(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
    
    private Integer key;
    
    private String value;
    
    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static void main(String[] args) {
       
        System.out.println(EnumUtil.BLANK.getKey());
    }
}

