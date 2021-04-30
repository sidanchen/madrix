package com.madrix.service.impl;

import com.madrix.dao.OperatorLogDao;
import com.madrix.pojo.OperatorLog;
import com.madrix.service.OperatorLogService;
import com.madrix.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sdc on 2018/3/1.
 */
@Service
public class OperatorLogServiceImpl implements OperatorLogService {
    @Autowired
    OperatorLogDao operatorLogDao;
    @Override
    public int insert(OperatorLog operatorLog) {
        return operatorLogDao.insert(operatorLog);
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public int delete(int[] id) {
        return 0;
    }

    @Override
    public int update(OperatorLog operatorLog) {
        return 0;
    }

    @Override
    public OperatorLog findById(Integer id) {
        return null;
    }

    @Override
    public List<OperatorLog> findByProperty(OperatorLog operatorLog) {
        return null;
    }

    @Override
    public List<OperatorLog> adminFindLogByPage(int page, int showNumber) {
        return operatorLogDao.adminFindLogByPage(PageUtil.startRow(PageUtil.checkPage(page)),showNumber);
    }

    @Override
    public List<OperatorLog> operatorFindLogByPage(int page, int showNumber, String operName) {
        return operatorLogDao.operatorFindLogByPage(PageUtil.startRow(PageUtil.checkPage(page)),showNumber,operName);
    }

    @Override
    public int adminLogTotalPage() {
        return PageUtil.countPage(operatorLogDao.adminLogTotal());
    }

    @Override
    public int operatorLogTotalPage(String operName) {
        return PageUtil.countPage(operatorLogDao.operatorLogTotal(operName));
    }
}
