package pl.semantyk.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * Singleton - loads system properties from config file.
 * 
 * @author Sebastian Fabisz.
 */
public class PropertyProvider {

	//private static final Logger LOG = Logger.getLogger(PropertyProvider.class);

	private static Properties properties = null;

	private PropertyProvider() {
	}

	private static void initalizeProperty() {
		FileInputStream in;
		try {
			in = new FileInputStream("resources/config.properties");
			properties = new Properties();
			properties.load(in);
			in.close();
			//LOG.info("Application configuration file loaded.");
		} catch (FileNotFoundException e) {
			//LOG.debug(e);
		} catch (IOException e) {
			//LOG.debug(e);
		}
	}

	public static String getProperty(String propertyName) {
		if (properties == null)
			initalizeProperty();

		if (StringUtils.isEmpty(propertyName))
			return "";

		return properties.getProperty(propertyName);
	}

	public static Map<String, String> getProperties(
			Collection<String> propertiesName) {
		if (properties == null)
			initalizeProperty();

		Map<String, String> result = new HashMap<>();

		if (propertiesName.isEmpty())
			return result;

		for (String name : propertiesName) {
			result.put(name, properties.getProperty(name));
		}

		return result;
	}
}
