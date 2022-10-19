package com.neo.web;


import com.neo.result.MailResult;
import com.neo.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MailController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MailService mailService;

    @RequestMapping("/sendSimpleMail")
    public MailResult sendSimpleMail(String to, String subject, String content) {
        MailResult result=new MailResult();
        if(StringUtils.isEmpty(to) || !to.contains("@")){
            result.setRspCode("01");
            result.setRspCode("手机人邮件格式不正确");
        }
        if(StringUtils.isEmpty(content) ){
            result.setRspCode("03");
            result.setRspCode("邮件正文不能为空");
        }
        try {
            mailService.sendSimpleMail(to,subject,content);
            logger.info("简单邮件已经发送。");
        } catch (Exception e) {
            result.setRspCode("04");
            result.setRspCode("邮件发送出现异常");
            logger.error("sendSimpleMail Exception ", e);
        }
        return result;
    }


}
