package com.lan.interest.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.lan.interest.service.MsmService;
import com.lan.interest.utils.EmailUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class MsmServiceImpl implements MsmService {
    @Override
    public Boolean send(Map<String, Object> param, String email) {
        if (StringUtils.isEmpty(email)) return false;
        try {
            SimpleEmail mail = new SimpleEmail();

            System.out.println(mail.toString());
            System.out.println(EmailUtils.PORT);
            System.out.println(EmailUtils.HOST);
            System.out.println(EmailUtils.EMAIL);
            System.out.println(EmailUtils.PASSWORD);

            // mail.setSmtpPort(Integer.valueOf(EmailUtils.PORT));
            mail.setSslSmtpPort(EmailUtils.PORT);
            mail.setHostName(EmailUtils.HOST);
            // 设置密码验证器
            mail.setAuthentication(EmailUtils.EMAIL, EmailUtils.PASSWORD);
            // 设置邮件发送者
            mail.setFrom(EmailUtils.EMAIL);
            mail.addTo(email);
            // 设置邮件编码
            mail.setCharset("UTF-8");
            // 设置邮件主题
            mail.setSubject("叮咚，你点的外卖到了");
            // 设置邮件内容
            mail.setMsg("10分钟内有效,您的验证码为：" + param.get("code"));
            // 设置邮件发送时间
            mail.setSentDate(new Date());
            // 发送邮件


            System.out.println(mail.getSmtpPort());

            mail.send();
            return true;
        }catch (EmailException e) {
            e.printStackTrace();
        }
        return null;
    }
}
