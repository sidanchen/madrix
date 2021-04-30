package com.madrix.service;

import com.madrix.pojo.LightInstruction;

import java.util.List;

/**
 * Created by sdc on 2018/2/28.
 */
public interface LightInstructionService extends BaseService<LightInstruction>{
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
