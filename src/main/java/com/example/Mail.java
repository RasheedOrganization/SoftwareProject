package com.example;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Mail
{
    private static final Logger loggER = Logger.getLogger(Mail.class.getName());
    Session newSession = null;
    MimeMessage mimeMessage = null;




    public static void rasheedEmail(String names) throws MessagingException {
    Mail mail = new Mail();
    mail.setupServerProperties();
    mail.draftEmail(names);//new ArrayList<>(names)
    mail.sendEmail();
}
    private void sendEmail() throws MessagingException {
        String fromUser = "rrash22875@gmail.com";
        String fromUserPassword = "xwyrsjoamtfjtcvs";
        String emailHost = "smtp.gmail.com";
        Transport transport = newSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserPassword);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
        loggER.log(Level.WARNING,"emailL successfully sent!!!");
    }

    private MimeMessage draftEmail(String names) throws MessagingException {
        String emailSubject = "Product Ready for Delivery";
        String emailBody = "Dear Customer...\n" +
                "\n" +
                "I am pleased to inform you that your product is now ready for delivery. The product has been thoroughly inspected to ensure that it meets our high quality standards.\n" +
                "\n" +
                "Please let us know when you would like the product delivered, and provide us with any special delivery instructions that we should be aware of. We will do our best to accommodate your schedule and ensure that the product is delivered safely and on time.\n" +
                "\n" +
                "Thank you for choosing our store for your cleaning needs. We appreciate your business and look forward to serving you in the future.\n" +
                "\n" +
                "Best regards,\n" +
                "\n" +
                "Bubble Cleaning";
        mimeMessage = new MimeMessage(newSession);

        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(names));
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