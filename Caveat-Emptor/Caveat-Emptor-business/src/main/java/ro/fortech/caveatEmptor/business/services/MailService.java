package ro.fortech.caveatEmptor.business.services;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.fortech.caveatEmptor.dto.RegistrationDto;
import ro.fortech.caveatEmptor.dto.UserDto;

@Service
public class MailService {

	@Autowired
	private RegistrationService registrationService;

	private static String EMAIL_BODY;

	@PostConstruct
	public void init() {
		StringBuilder sb = new StringBuilder();
		sb.append("<h3>Hello %s,</h3>");
		sb.append("<p>We are glad you have registered to Caveat Emptor.</p></br>");
		sb.append(
				"<p>Please, activate your account by clicking this <a href=\"%s\">link</a> or use the following one in your browser: </p></br>");
		sb.append("<p>%s</p></br>");
		sb.append("<p>The activation link will expire after 24 hours.<p></br>");
		sb.append("<p>Best regards,<p></br>");
		sb.append("<p>The Caveat Emptor team<p></br>");

		EMAIL_BODY = sb.toString();
	}

	public void sendEmail(UserDto userDto) throws Exception {

		RegistrationDto registration = registrationService.createRegistration(userDto);
		String activationLink = "http://localhost:8080/Caveat-Emptor-webservices/ws/activation/" + registration.getId();

		String emailBody = String.format(EMAIL_BODY, userDto.getFullName(), activationLink, activationLink);

		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.fallback", "true");

		Session mailSession = Session.getDefaultInstance(props);
		mailSession.setDebug(true);

		Message mailMessage = new MimeMessage(mailSession);
		mailMessage.setFrom(new InternetAddress("caveat.emptor17@gmail.com"));
		mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(userDto.getEmail()));
		mailMessage.setContent(emailBody, "text/html");
		mailMessage.setSubject("Account activation");

		Transport transport = mailSession.getTransport("smtp");
		transport.connect("smtp.gmail.com", "caveat.emptor17", "caveatEmptor17");
		transport.sendMessage(mailMessage, mailMessage.getAllRecipients());

	}
}
