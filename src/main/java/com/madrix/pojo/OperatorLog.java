package com.madrix.pojo;

import java.util.Date;

/**
 * 操作员日志实体类
 * Created by sdc on 2018/3/1.
 */
public class OperatorLog {
    private int id;
    private String operator;
    private String order;
    private String value;
    private Date operatingTime;

    public OperatorLog() {
    }

    public OperatorLog(String operator, String order, String value, Date operatingTime) {
        this.operator = operator;
        this.order = order;
        this.value = value;
        this.operatingTime = operatingTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
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

    public Date getOperatingTime() {
        return operatingTime;
    }

    public void setOperatingTime(Date operatingTime) {
        this.operatingTime = operatingTime;
    }
}
