package ro.fortech.caveatEmptor.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EmailDto implements Serializable {

	private static final long serialVersionUID = 5869247280291799348L;

	private List<String> recipients;
	private String body;
	private String subject;

	public EmailDto() {
		initializeMembers();
	}

	private void initializeMembers() {
		recipients = new ArrayList<>();
	}

	public List<String> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}
