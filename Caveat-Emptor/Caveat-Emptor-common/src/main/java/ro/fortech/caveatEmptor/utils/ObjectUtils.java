package ro.fortech.caveatEmptor.utils;

public class ObjectUtils {

	public static boolean isNullOrEmpty(String string) {
		return string == null || string.trim().isEmpty();
	}

	public static String capitalizeFirstLetter(String original) {
		if (original == null || original.length() == 0) {
			return original;
		}
		return original.substring(0, 1).toUpperCase() + original.substring(1);
	}

}
