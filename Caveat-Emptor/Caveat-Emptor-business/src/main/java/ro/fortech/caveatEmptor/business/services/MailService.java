package ro.fortech.caveatEmptor.business.services;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import ro.fortech.caveatEmptor.dto.EmailDto;

@Service
public class MailService {

    public void sendEmail(EmailDto emailDto) throws Exception {

	Properties props = System.getProperties();
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.port", "465");
	props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	props.put("mail.smtp.socketFactory.port", "465");
	props.put("mail.smtp.socketFactory.fallback", "true");

	Session mailSession = Session.getDefaultInstance(props);
	mailSession.setDebug(false);

	Message mailMessage = new MimeMessage(mailSession);
	mailMessage.setFrom(new InternetAddress("caveat.emptor17@gmail.com"));

	List<InternetAddress> addresses = emailDto.getRecipients().stream().map(email -> {
	    try {
		return new InternetAddress(email);
	    } catch (AddressException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    return null;
	}).collect(Collectors.toList());

	mailMessage.addRecipients(Message.RecipientType.TO, addresses.toArray(new InternetAddress[] {}));
	mailMessage.setContent(emailDto.getBody(), "text/html");
	mailMessage.setSubject(emailDto.getSubject());

	Transport transport = mailSession.getTransport("smtp");
	transport.connect("smtp.gmail.com", "caveat.emptor17", "caveatEmptor17");
	transport.sendMessage(mailMessage, mailMessage.getAllRecipients());

    }
}
