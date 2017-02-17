package ro.fortech.caveatEmptor.integration.queries;

import ro.fortech.caveatEmptor.integration.entities.User;
import ro.fortech.caveatEmptor.integration.entities.fields.UserFields;

public class UserQueries {

    public static final String GET_USER_BY_USERNAME;
    static {
	StringBuilder sb = new StringBuilder();
	sb.append("FROM ");
	sb.append(User.class.getName());
	sb.append(" WHERE ");
	sb.append(UserFields.USER_NAME + " = :username ");

	GET_USER_BY_USERNAME = sb.toString();
    }

    public static final String GET_ALL_USERS;
    static {
	StringBuilder sb = new StringBuilder();
	sb.append("FROM ");
	sb.append(User.class.getName());

	GET_ALL_USERS = sb.toString();
    }

}
