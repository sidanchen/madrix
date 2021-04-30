package com.madrix.service;

import com.madrix.pojo.Operator;

import java.util.List;

/**
 * Created by sdc on 2018/2/26.
 */
public interface OperatorService extends BaseService<Operator> {
    /**
     * 判断用户的用户名或者邮箱是否存在
     * @param userName
     * @param email
     * @return
     */
    List<Operator> userNameOrEmailIsExist(String userName, String email);
    /**
     * 查询所有记录
     * @return
     */
    int count();

    /**
     * 分页查询用户信息
     * @param page 页码
     * @param showNumber 显示的个数
     * @return
     */
    List<Operator> findUserByPage(int page,int showNumber);
}
