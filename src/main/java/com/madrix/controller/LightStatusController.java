package com.madrix.controller;

import com.madrix.service.LightStatusService;
import com.madrix.util.MadrixUtil;
import com.madrix.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * 用户处理灯光状态的控制器test
 * Created by sdc on 2018/2/28.
 */
@Controller
public class LightStatusController {

    @Autowired
    LightStatusService lightStatusService;

    @RequestMapping("/gc")
    public ResponseEntity<byte[]> getColor(@RequestParam("iName") String imageName) throws IOException {
        byte[] b = MadrixUtil.getStoragePlaceLeftByte(imageName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<byte[]>(b, headers, HttpStatus.OK);
    }

    /**
     * 返回远程的灯光状态给前台页面
     * @return
     */
    @RequestMapping("/fls")
    @ResponseBody
    public String farLightStatus(){
        try{
            return MessageUtil.mapToJsonString("success",lightStatusService.findMaxId());
        }catch (Exception ex){
            ex.printStackTrace();
            return MessageUtil.serviceError();
        }
    }
}
