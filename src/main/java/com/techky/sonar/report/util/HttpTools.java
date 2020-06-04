package com.techky.sonar.report.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.techky.sonar.report.bo.SonarQubeFilter;

/**
 * The Class HttpTools.
 */
public class HttpTools {

	/**
	 * Gets the sonar result.
	 *
	 * @param url             the url
	 * @param sonarQubeFilter the sonar qube filter
	 * @return the sonar result
	 */
	public static String getSonarResult(final String url, final SonarQubeFilter sonarQubeFilter) {
		final StringBuilder responseString = new StringBuilder();
		final StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(url);
		urlBuilder.append(getQueryParameter(sonarQubeFilter));
		try {
			final HttpClient client = HttpClientBuilder.create().build();
			System.out.println("Sonar API URL : " + urlBuilder.toString());
			final HttpGet request = new HttpGet(urlBuilder.toString());
			request.addHeader("User-Agent", "Mozilla/5.0");
			request.addHeader("Accept", "application/json");
			final HttpResponse response = client.execute(request);
			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
			final BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				responseString.append(line + "\n");
			}
		} catch (final Exception exception) {
			System.err.println("Failed to download SonarQube response. Reason - " + exception.getLocalizedMessage());
		}
		return responseString.toString();
	}

	/**
	 * Gets the query parameter.
	 *
	 * @param sonarQubeFilter the sonar qube filter
	 * @return the query parameter
	 */
	private static String getQueryParameter(final SonarQubeFilter sonarQubeFilter) {
		final StringBuilder queryParameter = new StringBuilder();
		queryParameter.append("?");
		if (sonarQubeFilter.getTypes() != null && !sonarQubeFilter.getTypes().isEmpty()) {
			queryParameter.append("types=").append(getParameterValue(sonarQubeFilter.getTypes())).append("&");
		}
		if (sonarQubeFilter.getResolutions() != null && !sonarQubeFilter.getResolutions().isEmpty()) {
			queryParameter.append("resolutions=").append(getParameterValue(sonarQubeFilter.getResolutions()))
					.append("&");
		}
		if (sonarQubeFilter.getProjects() != null && !sonarQubeFilter.getProjects().isEmpty()) {
			queryParameter.append("id=").append(getParameterValue(sonarQubeFilter.getProjects())).append("&");
		}
		if (sonarQubeFilter.getSeverities() != null && !sonarQubeFilter.getSeverities().isEmpty()) {
			queryParameter.append("severities=").append(getParameterValue(sonarQubeFilter.getSeverities())).append("&");
		}
		if (sonarQubeFilter.getStartDate() != null && !sonarQubeFilter.getStartDate().isEmpty()) {
			queryParameter.append("createdAfter=").append(sonarQubeFilter.getStartDate()).append("&");
		}
		if (sonarQubeFilter.getEndDate() != null && !sonarQubeFilter.getEndDate().isEmpty()) {
			queryParameter.append("createdBefore=").append(sonarQubeFilter.getEndDate()).append("&");
		}
		return queryParameter.toString();
	}

	/**
	 * Gets the parameter value.
	 *
	 * @param valueList the value list
	 * @return the parameter value
	 */
	private static String getParameterValue(final List<String> valueList) {
		StringBuilder parameterValue = new StringBuilder();
		for (final String value : valueList) {
			parameterValue.append(value + ",");
		}
		if (parameterValue.lastIndexOf(",") == parameterValue.length() - 1) {
			parameterValue = new StringBuilder(parameterValue.substring(0, parameterValue.length() - 1));
		}
		return parameterValue.toString();
	}
}
