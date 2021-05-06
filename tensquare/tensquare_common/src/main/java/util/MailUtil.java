package util;


import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class MailUtil {
    //修改为自己的qq邮箱账号
    private static String myEmailAccount = "85306972@qq.com";

    //修改为自己的邮箱授权码  发送邮件协议的授权码  geblqucpwmfxbfbe
    // sgeeqnuytjxwbifd
    private static String myEmailPassword = "scvcvamcxtukbifd";

    //如果用qq邮箱不用修改，如果用163邮箱，修改为smtp.163.com
    private static String myEmailSMTPHost = "smtp.qq.com"; //qq的发邮件服务器

    //用户名
    private static String sendUser = "小菜鸡";
    /**
     * @Title: send
     * @Description: TODO
     * @param @param 收件人邮箱
     * @param @param 标题
     * @param @param 内容
     * @param @throws Exception void
     * @throws
     */
    public static String send(String receive,String title,String body) throws Exception {
// 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();
// 使用的协议（JavaMail规范要求）
        props.setProperty("mail.transport.protocol", "smtp");
// 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.host", myEmailSMTPHost);
// 需要请求认证
        props.setProperty("mail.smtp.auth", "true");
/*
* 需要SSL安全配置的需要以下配置
* SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
* 需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
* QQ邮箱的SMTP(SLL)端口为465或587,其他邮箱自行去查看)
*/
        final String smtpPort = "465";
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getDefaultInstance(props);
// 设置为debug模式, 可以查看详细的发送 log
        session.setDebug(true);
// 3. 创建一封邮件
        MimeMessage message = createMimeMessage(session, myEmailAccount, receive,title,body);
//4. 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();
//5.使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
        transport.connect(myEmailAccount,myEmailPassword);
// 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());
// 7. 关闭连接
        transport.close();
        return body;
    }
    /**
     * @Title: createMimeMessage* @Description: TODO
     * @param @param session 和邮件服务器互的会话对象
     * @param @param sendMail 发件人的邮箱帐号
     * @param @param receiveMail 收件人的邮箱帐号
     * @param @param title 邮件主题
     * @param @param body 邮件内容
     * @param @return 创建的邮件对象
     * @param @throws Exception MimeMessag
    e
     * @throws
     */
    private static MimeMessage createMimeMessage(Session session, String sendMail,String receiveMail,String title,String body) throws Exception {
// 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);
// 2. From: 发件人(发件人邮箱地址,发件人名称,编码格式)
        message.setFrom(new InternetAddress(sendMail, sendUser, "UTF-8"));
// 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "尊敬的用户", "UTF-8"));
// 4. Subject: 邮件主题
        message.setSubject(title, "UTF-8");
// 5. Content: 邮件正文（可以使用html标签）
        message.setContent(body, "text/html;charset=UTF-8");
// 6. 设置发件时间
        message.setSentDate(new Date());
// 7. 保存设置
        message.saveChanges();
        return message;
    }



    public static String randomCode(){
        Random random = new Random();
        String num= "";
        for (int i = 0; i < 6; i++) {
            int j = random.nextInt(10);
            num+=j;
        }
        return num;
    }
}