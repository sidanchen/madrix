package com.madrix.service.impl;

import com.madrix.dao.OperatorDao;
import com.madrix.pojo.Operator;
import com.madrix.service.OperatorService;
import com.madrix.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sdc on 2018/2/26.
 */
@Service
public class OperatorServiceImpl implements OperatorService {

    @Autowired
    OperatorDao operatorDao;


    @Override
    public int insert(Operator operator) {
        return operatorDao.insert(operator);
    }

    @Override
    public int delete(int id) {
        return operatorDao.delete(id);
    }

    @Override
    public int delete(int[] id) {
        return 0;
    }

    @Override
    public int update(Operator operator) {
        return operatorDao.update(operator);
    }

    @Override
    public Operator findById(Integer id) {
        return operatorDao.findById(id);
    }

    @Override
    public List<Operator> findByProperty(Operator operator) {
        return operatorDao.findByProperty(operator);
    }

    @Override
    public List<Operator> userNameOrEmailIsExist(String userName, String email){
        return operatorDao.userNameOrEmailIsExist(userName,email);
    }

    @Override
    public int count() {
        return operatorDao.count();
    }

    @Override
    public List<Operator> findUserByPage(int page, int showNumber) {
        return operatorDao.findUserByPage(PageUtil.startRow(PageUtil.checkPage(page)),showNumber);
    }
}
