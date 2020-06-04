## Sonar Report Plugins
Sonar Plugins help to download sonar scanning reports in the form of HTML / PDF which we can share with audience.

### Overview:
Sonar Report Plugin help to download sonar scanning reports with the help of filters such as,- Types, Resolutions, 
Severities, Projects and DateRange. We can give sonarqube server url in *report-plugin.properties* file. With the 
help of filters and sonarqube server configuration tool fetch JSON response from server. Once tool receive JSON 
response from server it convert json response to XML format and apply XSLT to generate HTML / PDF report.

### Project Layers:

| Layers | Available | Technology |
| ------ | ------ | ------ |
| Client side layer | No | Not Applicable |
| Cascade style layer | Yes | XSLT |
| Server Web layer | No | Not Applicable |
| Service layer | Yes | Core Java |
| Database layer | No | Not Applicable |

### Technology Stack :

| Technology | Version |
| ------- | ------- |
| Core Java | Adopt Open JDK (11.0.7) |
| Maven | Apache Maven 3.5.0 |
| XSLT | 2.0 |
| SonarQube Server | 7.7 Community Edition |

### Benifits:

- Reporting
  - Download sonar scanning reports in the form of HTML / PDF which we can share with audience
  - Keep track of sonar scanning issue as we have list of all open / resolved / closed issue.

### Architecture Overview:

  ![Overview](images/sonar-report-blockdiagram.png)

### Tool configuration:
 - Edit **_report-plugin.properties**_ file
  ```markdown
#sonar qube server URL.
sonar.qube.server.url=http://SONAR_QUBE_SERVER_HOSTNAME:PORT/
#report xml stylesheet template.
report.xslt.template=../conf/Report.xsl
#report output file name.
report.output.file=../conf/OutputXMLReport.html
#supported formats - html, xml, pdf
report.output.format=html
 ```
 - Edit **_FilterMapper.xml**_ file
 <pre><code>
&lt;!-- This Filter used to fetch requirement specific data from SonarQube server. --&gt;
&lt;Filter&gt;
  &lt;!-- Types node value contains comma separated issue type. 
       BUG indicate sonar reported bug. 
       VULNERABILITY indicate sonar reported vulnerabilities. 
       If we keep Types node value is blank then it will show all types of sonar reported issue.
  --&gt;
	&lt;Types&gt;BUG,VULNERABILITY&lt;/Types&gt;

  &lt;!-- Resolutions node value contains comma separated resolutions of sonar reported issue. 
       OPEN indicates sonar reported issue whose implementation status is currently Open. 
       FALSE-POSITIVE indicates sonar reported issue's implementation status is currently marked as false positive by developer. 
       WONTFIX indicates sonar reported issue is as per designed and we can't able to fix this.
       FIXED indicates sonar reported issue is Fixed.
       CLOSED indicates sonar reported issue is Fixed and after sonar scann it gets validated and marked as Closed. 
       If we keep Resolutions node value is blank then it will show all resolutions sonar reported issue.
  --&gt;
	&lt;Resolutions&gt;FALSE-POSITIVE,WONTFIX,FIXED,REMOVED&lt;/Resolutions&gt;

  &lt;!-- 
  	Severities node value contains comma separated severity.
	BLOCKER indicates critical and blocker sonar reported issue.
	MAJOR indicates high level sonar reported issue.
	MINOR indicates low level sonar reported issue.
  --&gt;
	&lt;Severities&gt;BLOCKER&lt;/Severities&gt;

  &lt;!-- 
  	Projects node value contains project name mentioned in sonarqube. This value is dervied from maven project group and artifact id.
  --&gt;
	&lt;Projects&gt;&lt;/Projects&gt;

  &lt;!-- 
  	DateRange node value contains start date and end date to find out sonar reported issue with this range.
	CreatedAfter format is YYYY-mm-dd example, - 2019-01-15
	CreatedBefore format is YYYY-mm-dd example, - 2019-02-15
	CreatedAfter date should be older than CreatedBefore
  --&gt;
	&lt;DateRange&gt;
		&lt;CreatedAfter&gt;2019-02-03&lt;/CreatedAfter&gt;
		&lt;CreatedBefore&gt;2019-02-15&lt;/CreatedBefore&gt;
	&lt;/DateRange&gt;
&lt;/Filter&gt;
</code></pre>

- Edit **_Report.xsl**_ file
 ```markdown
This is XSLT file which use to generate HTML report. Same HTML format is used in PDF file.

Sample XSLT template is available in ${project.directory}/conf/Report.xsl

```

### Important Links:

| Description | Link |
| -------- | -------- |
| Source Code | <span style="color: green;font-weight: bold;"><a href="https://github.com/ravaan-techky/sonar-plugins/archive/master.zip"><i class="fa fa-download"></i></a></span> |
| Circle CI <If Available> | Not Available |
| Documentation | <span style="color: green;font-weight: bold;"><a href="https://ravaan-techky.github.io/sonar-plugins/index"><i class="fa fa-folder-open"></i></a></span> |
  
### Sample Reports:
| Report Type | Link |
| -------- | -------- |
| HTML | <span style="color: green;font-weight: bold;"><a href="https://ravaan-techky.github.io/sonar-plugins/sample/html_report.html"><i class="fa fa-info"></i></a></span> |
| PDF | <span style="color: green;font-weight: bold;"><a href="https://ravaan-techky.github.io/sonar-plugins/sample/pdf_report.pdf"><i class="fa fa-info"></i></a></span> |
| XML | <span style="color: green;font-weight: bold;"><a href="https://ravaan-techky.github.io/sonar-plugins/sample/xml_report.xml"><i class="fa fa-info"></i></a></span> |


### Developer Information:

| Description | Github Profile Link  | LinkedIn Profile Link | Email Address
| -------- | -------- | -------- | -------- |
| Vaishali Patil | [<i class="fa fa-external-link"></i>](https://github.com/ravaan-techky/) | [<i class="fa fa-external-link"></i>](#) | [ravaan.techky@gmail.com](mailto:ravaan.techky@gmail.com) |

<br/><br/>
[<i class="fa fa-arrow-left"></i> **Back**](/documentation/)
