/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author WIN8.1X64
 */
public class Mail {
    public static void sendmail(String smtpServer, String to, String from, String psw,
            String subject, String body) throws MessagingException
    {
        // Code provide by congdongjava.com
        Properties props = System.getProperties();
        props.put("mail.smtp.host", smtpServer);
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.enable", "true");
//        props.put("mail.transport.protocol","smtp");
        props.setProperty("mail.smtp.**ssl.enable", "true");
        props.setProperty("mail.smtp.**ssl.required", "true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.debug", "true");
        final String login = from;
        final String pwd = psw;
        javax.mail.Authenticator pa = null;
        if (login != null && pwd != null) {
            props.put("mail.smtp.auth", "true");
            pa = new javax.mail.Authenticator() {

                public javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication(login, pwd);
                }
            };
        }//else: no authentication
        Session session = Session.getInstance(props, pa);
// — Create a new message –
        Message msg = new MimeMessage(session);
// — Set the FROM and TO fields –
        msg.setFrom(new InternetAddress(from));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(
                to, false));

// — Set the subject and body text –
        msg.setSubject(subject);
        msg.setText(body);//Để gởi nội dung dạng utf-8 các bạn dùng msg.setContent(body, "text/html; charset=UTF-8");
// — Set some other header information –
        msg.setHeader("X-Mailer", "LOTONtechEmail");
        msg.setSentDate(new Date());
        msg.saveChanges();
// — Send the message –
//        Transport trnsport;
//        trnsport = session.getTransport("smtps");
//        trnsport.connect();
//        msg.saveChanges(); 
//        trnsport.sendMessage(msg, msg.getAllRecipients());
//        trnsport.close();
        Transport.send(msg);
        System.out.println("Mail da duoc gui");
    }
}
