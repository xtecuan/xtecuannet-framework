/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.model.services.impl;

import com.xtesoft.xtecuannet.framework.model.services.MailerService;
import java.util.Date;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author xtecuan
 */
public class MailerServiceImpl implements MailerService {

    private static final Logger logger = Logger.getLogger(MailerServiceImpl.class);
    private Session mailSession;

    public Session getMailSession() {
        return mailSession;
    }

    public void setMailSession(Session mailSession) {
        this.mailSession = mailSession;
    }

    /**
     * 
     * @param from
     * @param subject
     * @param to
     * @param bodyText
     * @param filename 
     */
    public void sendEmail(String from, String subject, String to, String bodyText, String filename) {
        try {
            MimeMessage message = new MimeMessage(this.getMailSession());
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setSentDate(new Date());

            //
            // Set the email message text.
            //
            MimeBodyPart messagePart = new MimeBodyPart();
            messagePart.setText(bodyText);

            //
            // Set the email attachment file
            //
            MimeBodyPart attachmentPart = new MimeBodyPart();
            FileDataSource fileDataSource = new FileDataSource(filename) {

                @Override
                public String getContentType() {
                    return "application/octet-stream";
                }
            };
            attachmentPart.setDataHandler(new DataHandler(fileDataSource));
            attachmentPart.setFileName(FilenameUtils.getName(filename));

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messagePart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            Transport.send(message);
        } catch (MessagingException e) {
            logger.error("Error al enviar mensaje de correo con archivo Adjunto: ", e);
        }
    }

    /**
     * 
     * @param email
     * @param subject
     * @param body 
     */
    public void sendEmail(String email, String subject, String body) {

        try {
            MimeMessage message = new MimeMessage(this.getMailSession());
            message.setSubject(subject);
            message.setRecipients(RecipientType.TO, InternetAddress.parse(email, false));
            message.setText(body);
            Transport.send(message);
        } catch (Exception ex) {
            logger.error("Error al enviar mensaje de correo: ", ex);
        }
    }

    /**
     * 
     * @param from
     * @param subject
     * @param to
     * @param bodyText
     * @param data (byte[])
     * @param filename 
     */
    public void sendEmail(String from, String subject, String to, String bodyText, byte[] data, String filename) {
        try {
            MimeMessage message = new MimeMessage(this.getMailSession());
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setSentDate(new Date());

            //
            // Set the email message text.
            //
            MimeBodyPart messagePart = new MimeBodyPart();
            messagePart.setText(bodyText);

            //
            // Set the email attachment file
            //
            MimeBodyPart attachmentPart = new MimeBodyPart();
            ByteArrayDataSource byteDataSource = new ByteArrayDataSource(data, "application/octet-stream");
            attachmentPart.setDataHandler(new DataHandler(byteDataSource));
            attachmentPart.setFileName(filename);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messagePart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            Transport.send(message);
        } catch (MessagingException e) {
            logger.error("Error al enviar mensaje de correo con archivo Adjunto (arreglo de bytes): ", e);
        }
    }
}
