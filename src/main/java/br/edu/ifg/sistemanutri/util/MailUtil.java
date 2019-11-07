
package br.edu.ifg.sistemanutri.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author danilo
 */
public class MailUtil {

    public static void sendMail(String[] loginsDest, String nomeDest, String loginRemet, String senhaEmail, String nomeRemet, String assunto, String corpo) throws Exception{
        try {
            //Passo 1 - Configuar login
            Properties props = System.getProperties();
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.socketFactory.fallback", "true");

            //Passo 2 - recuperar sessao do login
            Session mailSession = Session.getDefaultInstance(props, null);
            MimeMessage mailMessage = new MimeMessage(mailSession);
            mailMessage.setFrom(new InternetAddress(loginRemet, nomeRemet));
            Address[] addresses = new Address[loginsDest.length];
            for (int i = 0; i < loginsDest.length; i++) {
                addresses[i] = new InternetAddress(loginsDest[i].toLowerCase().trim());
            }
            mailMessage.addRecipients(Message.RecipientType.TO, addresses);
            mailMessage.setSubject(assunto);
            mailMessage.setContent(corpo, "text/html");

            //Passo 3 - Pegar sessao enviar login
            Transport transport = mailSession.getTransport("smtp");

            // Enter your correct gmail UserID and Password
            // if you have 2FA enabled then provide App Specific Password
            transport.connect("smtp.gmail.com", loginRemet, senhaEmail);
            transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
            transport.close();

        } catch (UnsupportedEncodingException ex) {
            throw new Exception("Erro ao enviar o login. Desculpe, mas parece que estamos com alguns problemas!");
        } catch (MessagingException ex) {
            throw new Exception("Erro ao enviar o login. Desculpe, mas parece que estamos com alguns problemas!");
        }
    }

}


