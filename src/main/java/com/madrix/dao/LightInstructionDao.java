package com.madrix.dao;

import com.madrix.pojo.LightInstruction;
import com.madrix.pojo.LightStatus;

import java.util.List;

/**
 * 灯光控制指令dao类
 * Created by sdc on 2018/2/28.
 */
public interface LightInstructionDao extends BaseDao<LightInstruction> {
    /**
     * 根据条件查询未执行的指令
     * @return
     */
    List<LightInstruction> findNoExec(LightInstruction lightInstruction);

    /**
     * 查询未执行的指令
     * @return
     */
    List<LightInstruction> findNoExecNoProperty();
}
