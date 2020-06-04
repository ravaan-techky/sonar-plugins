package com.techky.sonar.report;

import java.io.File;

import com.techky.sonar.report.bo.SonarQubeFilter;
import com.techky.sonar.report.constants.ReportPluginConstant;
import com.techky.sonar.report.exception.ExportConfigurationException;
import com.techky.sonar.report.util.FileUtility;
import com.techky.sonar.report.util.HttpTools;
import com.techky.sonar.report.util.PluginProperties;
import com.techky.sonar.report.util.XMLUtility;

/**
 * Main Application Class.
 *
 * @author Lavanya
 */
public class ReportGenerator {

	/**
	 * Main method.
	 *
	 * @param args string array.
	 * @throws ExportConfigurationException
	 */
	public static void main(final String[] args) throws ExportConfigurationException {
		final String sonarQubeServerURL = PluginProperties.getProperty(ReportPluginConstant.SONAR_QUBE_SERVER_URL);
		final File inputXMLFile = new File(ReportPluginConstant.REPORT_XML_INPUT_FILE);
		final File xslFile = new File(PluginProperties.getProperty(ReportPluginConstant.REPORT_XSLT_TEMPLATE));
		final File outputReportFile = new File(PluginProperties.getProperty(ReportPluginConstant.REPORT_OUTPUT_FILE));
		final File filterMapper = new File(ReportPluginConstant.REPORT_FILTER_FILE);
		final SonarQubeFilter sonarQubeFilter = XMLUtility.loadConfiguration(filterMapper);
		final String jsonString = HttpTools.getSonarResult(sonarQubeServerURL + "/api/issues/search", sonarQubeFilter);
		String xmlString = XMLUtility.jsonToxmlConverter(jsonString);
		xmlString = XMLUtility.format(xmlString);
		FileUtility.writeData(xmlString, inputXMLFile, false);
		XMLUtility.transformXML(inputXMLFile, xslFile, outputReportFile);
	}
}