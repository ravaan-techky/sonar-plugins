package com.techky.sonar.report.util;

import java.io.File;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.techky.sonar.report.constants.ExportErrorCodes;
import com.techky.sonar.report.exception.ExportConfigurationException;

/**
 * The Class PluginProperties.
 */
public final class PluginProperties {

	/** The plugin properties. */
	private static PluginProperties pluginProperties;

	/** The configuration. */
	private final Configuration configuration;

	/** The property file name. */
	private final String propertyFileName = System.getProperty("PLUGIN_CONF_DIRECTORY") + File.separator
			+ "report-plugin.properties";

	/**
	 * Instantiates a new plugin properties.
	 *
	 * @throws ConfigurationException the configuration exception
	 */
	private PluginProperties() throws ConfigurationException {
		configuration = new PropertiesConfiguration(propertyFileName);
	}

	/**
	 * Gets the single instance of PluginProperties.
	 *
	 * @return single instance of PluginProperties
	 * @throws ExportConfigurationException the export configuration exception
	 */
	private static PluginProperties getInstance() throws ExportConfigurationException {
		try {
			if (pluginProperties == null) {
				pluginProperties = new PluginProperties();
			}
		} catch (final ConfigurationException exception) {
			throw new ExportConfigurationException(ExportErrorCodes.INTERNAL_SERVER_ERROR, exception);
		}
		return pluginProperties;
	}

	/**
	 * Gets the property.
	 *
	 * @param propertyKey the property key
	 * @return the property
	 * @throws ExportConfigurationException the export configuration exception
	 */
	public static String getProperty(final String propertyKey) throws ExportConfigurationException {
		return PluginProperties.getInstance().configuration.getString(propertyKey);
	}
}
