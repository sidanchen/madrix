package com.madrix.pojo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sdc on 2018/2/28.
 */
public class LightInstruction {


    private int id;
    private String order;
    private String value;
    private String operator;
    private Date execTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getExecTime() {
        return execTime;
    }

    public void setExecTime(Date execTime) {
        this.execTime = execTime;
    }
}
