package com.madrix.service;
/*
*
* COPYRIGHT. Shenzhen Qianhai Dianjiang Financial Tech Co., Ltd. 2017. 
* ALL RIGHTS RESERVED.
** No part of this publication may be reproduced, stored in a retrieval system, or transmitted,
* on any form or by any means, electronic, mechanical, photocopying, recording, 
 * or otherwise, without the prior written permission of Shenzhen Qianhai Dianjiang Financial Tech Co., Ltd.
*
* Amendment History:
* 
* Date                   By              Description
* -------------------    -----------     -------------------------------------------
* 2021/5/4    stan.c         Create the class
*/

import com.madrix.pojo.Operator;

/**
 *@ClassName RemoteService
 *@Description 同步信息到远程服务器
 *@Author stan.c
 *@Date2021/5/4
 **/
public interface RemoteInfoSynService {
    
    
    /**
     * @Description 远程同步用户数据
     * @param operator 
     * @author stan.c
     * @date 2021/5/4
     */
    void remoteInsertUserInfo(Operator operator);
    
    /**
     * @Description 修改远程用户信息
     * @param email
     * @param password 
     * @author stan.c
     * @date 2021/5/4
     */
    void remoteUpdateUserInfo(String email, String password);
}
