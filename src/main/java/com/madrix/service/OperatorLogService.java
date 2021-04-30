package com.madrix.service;

import com.madrix.pojo.OperatorLog;

import java.util.List;

/**
 * Created by sdc on 2018/3/1.
 */
public interface OperatorLogService extends BaseService<OperatorLog> {
    /**
     * 管理员查看日志
     * @param startRow
     * @param showNumber
     * @return
     */
    List<OperatorLog> adminFindLogByPage(int page, int showNumber);

    /**
     * 操作员查看日志
     * @param startRow
     * @param showNumber
     * @param operName
     * @return
     */
    List<OperatorLog> operatorFindLogByPage(int page,int showNumber,String operName);

    /**
     * 管理员能查看日志的总页数
     * @return
     */
    int adminLogTotalPage();

    /**
     * 操作人员能查看日志的总行数
     * @return
     */
    int operatorLogTotalPage(String operName);
}
