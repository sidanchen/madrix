package com.madrix.service.impl;

import com.madrix.dao.LightInstructionDao;
import com.madrix.pojo.LightInstruction;
import com.madrix.service.LightInstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sdc on 2018/2/28.
 */
@Service
public class LightInstructionServiceImpl implements LightInstructionService {
    @Autowired
    LightInstructionDao lightInstructionDao;
    @Override
    public int insert(LightInstruction lightInstruction) {
        return lightInstructionDao.insert(lightInstruction);
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
    public int update(LightInstruction lightInstruction) {
        return lightInstructionDao.update(lightInstruction);
    }

    @Override
    public LightInstruction findById(Integer id) {
        return null;
    }

    @Override
    public List<LightInstruction> findByProperty(LightInstruction lightInstruction) {
        return lightInstructionDao.findByProperty(lightInstruction);
    }

    @Override
    public List<LightInstruction> findNoExec(LightInstruction lightInstruction) {
        return lightInstructionDao.findNoExec(lightInstruction);
    }

    @Override
    public List<LightInstruction> findNoExecNoProperty() {
        return lightInstructionDao.findNoExecNoProperty();
    }
}
