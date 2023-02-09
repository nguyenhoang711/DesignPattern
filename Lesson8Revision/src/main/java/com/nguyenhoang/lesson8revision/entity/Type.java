package com.nguyenhoang.lesson8revision.entity;

public enum Type {
    DEV("Dev"), TEST("Test"), SCRUMMASTER("ScrumMaster");

    private String value;

    Type(String data){
        this.value = data;
    }

    public String getValue() {
        return value;
    }

    public static Type toEnum(String data){
        for (Type type: Type.values()) {
            if(type.getValue().equals(data))
                return type;
        }
        return null;
    }
}
