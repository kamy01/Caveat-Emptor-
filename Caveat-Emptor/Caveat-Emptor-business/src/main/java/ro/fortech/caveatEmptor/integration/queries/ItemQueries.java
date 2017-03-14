package ro.fortech.caveatEmptor.integration.queries;

import ro.fortech.caveatEmptor.integration.entities.Item;

public class ItemQueries {

    public static String GET_ITEMS_BOUGHT_BY_USER;
    static {
	StringBuilder sb = new StringBuilder();
	// sb.append("SELECT i ");
	sb.append(" FROM ");
	sb.append(Item.class.getName() + " i");
	sb.append(" JOIN i.buyers b");
	sb.append(" WHERE b.id = :userId ");

	GET_ITEMS_BOUGHT_BY_USER = sb.toString();
    }

}
