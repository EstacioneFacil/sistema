package controller;

import java.io.IOException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.log4j.Logger;

/**
 *
 * @author Douglas
 */
public class EmailController {
    
    private final Logger logger = Logger.getLogger(getClass().getName());
    
    public boolean enviarEmail(String destinatario, String assunto, String texto, String anexo) {
        try {
            Properties properties = new Properties();
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("mail.properties"));
            
            final String username = properties.getProperty("mail.username");
            final String password = properties.getProperty("mail.password");

            Session session = Session.getInstance(properties,
              new javax.mail.Authenticator() {
                   public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("jonasdiel@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(assunto);
            message.setText(texto);
                        
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(texto);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            String filename = anexo;
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);
            logger.info("Enviando email...");
            Transport.send(message);
            logger.info("Email enviado com sucesso");
            return true;
        } catch (IOException | MessagingException ex) {
            logger.error("Erro ao gerar e enviar email", ex);
            return false;
        }
    }
}
