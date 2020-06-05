package com.techky.sonar.report;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import org.apache.commons.lang.StringUtils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
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
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static void main(final String[] args) throws ExportConfigurationException, DocumentException, IOException {
		final String sonarQubeServerURL = PluginProperties.getProperty(ReportPluginConstant.SONAR_QUBE_SERVER_URL);
		final File inputXMLFile = new File(ReportPluginConstant.REPORT_XML_INPUT_FILE);
		final File xslFile = new File(PluginProperties.getProperty(ReportPluginConstant.REPORT_XSLT_TEMPLATE));
		final File filterMapper = new File(ReportPluginConstant.REPORT_FILTER_FILE);
		final SonarQubeFilter sonarQubeFilter = XMLUtility.loadConfiguration(filterMapper);
		final String jsonString = HttpTools.getSonarResult(sonarQubeServerURL + "/api/issues/search", sonarQubeFilter);
		String xmlString = XMLUtility.jsonToxmlConverter(jsonString);
		xmlString = XMLUtility.format(xmlString);
		FileUtility.writeData(xmlString, inputXMLFile, false);
		final String reportFormat = PluginProperties.getProperty(ReportPluginConstant.REPORT_OUTPUT_FORMAT);
		if (StringUtils.isNotBlank(reportFormat) && "PDF".equalsIgnoreCase(reportFormat)) {
			writePDFReport(inputXMLFile, xslFile);
		} else {
			final File outputReportFile = new File(
					PluginProperties.getProperty(ReportPluginConstant.REPORT_OUTPUT_FILE));
			XMLUtility.transformXML(inputXMLFile, xslFile, outputReportFile);
		}
	}

	/**
	 * Write PDF report.
	 *
	 * @param inputXMLFile the input XML file
	 * @param xslFile      the xsl file
	 * @throws ExportConfigurationException the export configuration exception
	 * @throws DocumentException            the document exception
	 * @throws IOException                  Signals that an I/O exception has
	 *                                      occurred.
	 */
	private static void writePDFReport(final File inputXMLFile, final File xslFile)
			throws ExportConfigurationException, DocumentException, IOException {
		final String pdfFileOutput = PluginProperties.getProperty(ReportPluginConstant.REPORT_OUTPUT_FILE);
		final File outputReportFile = new File("./temp");
		XMLUtility.transformXML(inputXMLFile, xslFile, outputReportFile);
		try (final BufferedReader bufferedReader = new BufferedReader(new FileReader(outputReportFile));) {
			final StringBuilder htmlStringBuilder = new StringBuilder();
			final Document document = new Document();
			final PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfFileOutput));
			document.open();
			while (true) {
				final String str = bufferedReader.readLine();
				if (str == null) {
					break;
				}
				if (!str.contains("META http-equiv=")) {
					htmlStringBuilder.append(str + "\n");
				}
			}
			XMLWorkerHelper.getInstance().parseXHtml(writer, document, new StringReader(htmlStringBuilder.toString()));
			document.close();

		}
	}
}