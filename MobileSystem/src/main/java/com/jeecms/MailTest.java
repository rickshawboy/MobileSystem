package com.jeecms;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class MailTest {

    public static void main(String[] args) {
        SimpleEmail email = new SimpleEmail();  
        email.setHostName("smtp.qq.com");
        try {
            // 收件人邮箱
            email.addTo("3158655288@qq.com");
            // 邮箱服务器身份验证
            email.setAuthentication("664592270@qq.com", "zppwuwqfttpobfai");
            // 发件人邮箱
            email.setFrom("664592270@qq.com");
            // 邮件主题
            email.setSubject("zhipeng-JavaMail");
            // 邮件内容
            email.setMsg("Kobe Bryante Never Stop Trying");
            // 发送邮件
            email.send();
        } catch (EmailException ex) {
            ex.printStackTrace();
        }
    }
}
