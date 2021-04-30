package com.madrix.dao;

import com.madrix.pojo.Operator;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sdc on 2018/2/26.
 */
public interface OperatorDao extends BaseDao<Operator> {

    /**
     * 判断用户的用户名或者邮箱是否存在
     * @param userName
     * @param email
     * @return
     */
    List<Operator> userNameOrEmailIsExist(String userName,String email);

    /**
     * 查询所有记录
     * @return
     */
    int count();

    /**
     * 分页查询用户信息
     * @param startRow 开始的行数
     * @param showNumber 显示的个数
     * @return
     */
    List<Operator> findUserByPage(int startRow,int showNumber);
}
