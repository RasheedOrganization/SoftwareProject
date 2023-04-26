package com.example;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class Mail
{

    Session newSession = null;
    MimeMessage mimeMessage = null;




    public void RasheedEmail(ArrayList<String> names) throws MessagingException, IOException {
    Mail mail = new Mail();
    mail.setupServerProperties();
    mail.draftEmail(names);
    mail.sendEmail();
}
    private void sendEmail() throws MessagingException {
        String fromUser = "rrash22875@gmail.com";
        String fromUserPassword = "shxtkncbtduzbpub";
        String emailHost = "smtp.gmail.com";
        Transport transport = newSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserPassword);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
        System.out.println("Email successfully sent!!!");
    }

    private MimeMessage draftEmail(ArrayList<String> names) throws AddressException, MessagingException, IOException {
        String emailSubject = "Your product is completed";
        String emailBody = "Come to the store and take the product as possible as you can";
        mimeMessage = new MimeMessage(newSession);

        for (int i =0 ;i<names.size();i++)
        {
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(names.get(i)));
        }
        mimeMessage.setSubject(emailSubject);

        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(emailBody,"html/text");
        MimeMultipart multiPart = new MimeMultipart();
        multiPart.addBodyPart(bodyPart);
        mimeMessage.setContent(multiPart);
        return mimeMessage;
    }

    private void setupServerProperties() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        newSession = Session.getDefaultInstance(properties,null);
    }

}