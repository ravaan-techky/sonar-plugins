package com.techky.sonar.report.util;

import java.io.File;
import java.io.StringReader;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;

import com.techky.sonar.report.bo.SonarQubeFilter;
import com.techky.sonar.report.constants.ExportErrorCodes;
import com.techky.sonar.report.exception.ExportConfigurationException;

/**
 * The Class XMLUtility.
 */
public class XMLUtility {

	/** The Constant COMMA_SEPARATOR. */
	private static final String COMMA_SEPARATOR = ",";

	/**
	 * Load configuration.
	 *
	 * @param filterMapper the filter mapper
	 * @return the sonar qube filter
	 * @throws ExportConfigurationException the export configuration exception
	 */
	public static SonarQubeFilter loadConfiguration(final File filterMapper) throws ExportConfigurationException {
		final SonarQubeFilter sonarQubeFilter = new SonarQubeFilter();
		try {
			final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			final DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			final Document document = documentBuilder.parse(filterMapper);
			document.getDocumentElement().normalize();

			loadFilterConfigurationForTypes(sonarQubeFilter, document);
			loadFilterConfigurationForServerities(sonarQubeFilter, document);
			loadFilterConfigurationForResolutions(sonarQubeFilter, document);
			loadFilterConfigurationForProjects(sonarQubeFilter, document);
			loadFilterConfigurationForDateRange(sonarQubeFilter, document);

		} catch (final Exception exception) {
			throw new ExportConfigurationException(ExportErrorCodes.SYSTEM_ERROR, exception);
		}
		return sonarQubeFilter;
	}

	/**
	 * Json toxml converter.
	 *
	 * @param jsonString the json string
	 * @return the string
	 */
	public static String jsonToxmlConverter(final String jsonString) {
		final StringBuilder responseString = new StringBuilder();
		final JSONObject json = new JSONObject(jsonString);
		final JSONArray jsonArray = json.getJSONArray("issues");
		responseString.append("<root>");
		responseString.append(XML.toString(jsonArray));
		responseString.append("</root>");
		return responseString.toString();
	}

	/**
	 * Format.
	 *
	 * @param xml the xml
	 * @return the string
	 * @throws ExportConfigurationException the export configuration exception
	 */
	public static String format(final String xml) throws ExportConfigurationException {
		try {
			final InputSource src = new InputSource(new StringReader(xml));
			final Node document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(src)
					.getDocumentElement();
			final Boolean keepDeclaration = xml.startsWith("<?xml");
			final DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
			final DOMImplementationLS impl = (DOMImplementationLS) registry.getDOMImplementation("LS");
			final LSSerializer writer = impl.createLSSerializer();
			writer.getDomConfig().setParameter("format-pretty-print", Boolean.TRUE);
			writer.getDomConfig().setParameter("xml-declaration", keepDeclaration);
			return writer.writeToString(document);
		} catch (final Exception exception) {
			throw new ExportConfigurationException(ExportErrorCodes.SYSTEM_ERROR, exception);
		}
	}

	/**
	 * Transform XML.
	 *
	 * @param inputFile  the input file
	 * @param xsltFile   the xslt file
	 * @param outputFile the output file
	 * @throws ExportConfigurationException the export configuration exception
	 */
	public static void transformXML(final File inputFile, final File xsltFile, final File outputFile)
			throws ExportConfigurationException {
		final TransformerFactory factory = TransformerFactory.newInstance();
		if (xsltFile.isFile()) {
			final Source xslt = new StreamSource(xsltFile);
			Transformer transformer;
			try {
				transformer = factory.newTransformer(xslt);
				final Source text = new StreamSource(inputFile);
				transformer.transform(text, new StreamResult(outputFile));
			} catch (final TransformerException exception) {
				throw new ExportConfigurationException(ExportErrorCodes.SYSTEM_ERROR, exception);
			}
		} else {
			throw new ExportConfigurationException(ExportErrorCodes.INTERNAL_SERVER_ERROR,
					"Failed to get XSLT file - " + xsltFile.getAbsolutePath());
		}
	}

	/**
	 * Load filter configuration for types.
	 *
	 * @param sonarQubeFilter the sonar qube filter
	 * @param document        the document
	 */
	private static void loadFilterConfigurationForTypes(final SonarQubeFilter sonarQubeFilter,
			final Document document) {
		final NodeList typeNodeList = document.getElementsByTagName("Types");
		if (typeNodeList != null && typeNodeList.getLength() > 0) {
			final String types = typeNodeList.item(0).getTextContent().trim();
			if (StringUtils.isNotBlank(types)) {
				sonarQubeFilter.setTypes(Arrays.asList(types.split(COMMA_SEPARATOR)));
			}
		}
	}

	/**
	 * Load filter configuration for resolutions.
	 *
	 * @param sonarQubeFilter the sonar qube filter
	 * @param document        the document
	 */
	private static void loadFilterConfigurationForResolutions(final SonarQubeFilter sonarQubeFilter,
			final Document document) {
		final NodeList resolutionNodeList = document.getElementsByTagName("Resolutions");
		if (resolutionNodeList != null && resolutionNodeList.getLength() > 0) {
			final String resolutions = resolutionNodeList.item(0).getTextContent().trim();
			if (StringUtils.isNotBlank(resolutions)) {
				sonarQubeFilter.setResolutions(Arrays.asList(resolutions.split(COMMA_SEPARATOR)));
			}
		}
	}

	/**
	 * Load filter configuration for serverities.
	 *
	 * @param sonarQubeFilter the sonar qube filter
	 * @param document        the document
	 */
	private static void loadFilterConfigurationForServerities(final SonarQubeFilter sonarQubeFilter,
			final Document document) {
		final NodeList serveritiesNodeList = document.getElementsByTagName("Severities");
		if (serveritiesNodeList != null && serveritiesNodeList.getLength() > 0) {
			final String severities = serveritiesNodeList.item(0).getTextContent().trim();
			if (StringUtils.isNotBlank(severities)) {
				sonarQubeFilter.setSeverities(Arrays.asList(severities.split(COMMA_SEPARATOR)));
			}
		}
	}

	/**
	 * Load filter configuration for projects.
	 *
	 * @param sonarQubeFilter the sonar qube filter
	 * @param document        the document
	 */
	private static void loadFilterConfigurationForProjects(final SonarQubeFilter sonarQubeFilter,
			final Document document) {
		final NodeList projectNodeList = document.getElementsByTagName("Projects");
		if (projectNodeList != null && projectNodeList.getLength() > 0) {
			final String projects = projectNodeList.item(0).getTextContent().trim();
			if (StringUtils.isNotBlank(projects)) {
				sonarQubeFilter.setProjects(Arrays.asList(projects.split(COMMA_SEPARATOR)));
			}
		}
	}

	/**
	 * Load filter configuration for date range.
	 *
	 * @param sonarQubeFilter the sonar qube filter
	 * @param document        the document
	 */
	private static void loadFilterConfigurationForDateRange(final SonarQubeFilter sonarQubeFilter,
			final Document document) {
		final NodeList createdAfterNodeList = document.getElementsByTagName("CreatedAfter");
		if (createdAfterNodeList != null && createdAfterNodeList.getLength() > 0) {
			final String createdAfter = createdAfterNodeList.item(0).getTextContent().trim();
			if (StringUtils.isNotBlank(createdAfter)) {
				sonarQubeFilter.setStartDate(createdAfter);
			}
		}

		final NodeList createdBeforeNodeList = document.getElementsByTagName("CreatedBefore");
		if (createdBeforeNodeList != null && createdBeforeNodeList.getLength() > 0) {
			final String createdBefore = createdBeforeNodeList.item(0).getTextContent().trim();
			if (StringUtils.isNotBlank(createdBefore)) {
				sonarQubeFilter.setEndDate(createdBefore);
			}
		}
	}

	/**
	 * Instantiates a new XML utility.
	 */
	private XMLUtility() {
		// NO_PMD DUMMY Constructor.
	}
}
