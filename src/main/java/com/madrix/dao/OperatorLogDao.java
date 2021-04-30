package com.madrix.dao;

import com.madrix.pojo.OperatorLog;

import java.util.List;

/**
 * Created by sdc on 2018/3/1.
 */
public interface OperatorLogDao extends BaseDao<OperatorLog>{

    /**
     * 管理员查看日志
     * @param startRow
     * @param showNumber
     * @return
     */
    List<OperatorLog> adminFindLogByPage(int startRow,int showNumber);

    /**
     * 操作员查看日志
     * @param startRow
     * @param showNumber
     * @param operName
     * @return
     */
    List<OperatorLog> operatorFindLogByPage(int startRow,int showNumber,String operName);

    /**
     * 管理员能查看日志的总行数
     * @return
     */
    int adminLogTotal();

    /**
     * 操作人员能查看日志的总行数
     * @return
     */
    int operatorLogTotal(String operName);
}
