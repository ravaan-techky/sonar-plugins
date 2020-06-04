## Sonar Plugins
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

### Benifits:

- Reporting
  - Download sonar scanning reports in the form of HTML / PDF which we can share with audience
  - Keep track of sonar scanning issue as we have list of all open / resolved / closed issue.

### Architecture Overview:

  ![Overview](images/sonar-report-blockdiagram.png)

### Important Links:

| Description | Link |
| -------- | -------- |
| Source Code | <span style="color: green;font-weight: bold;"><a href="https://github.com/ravaan-techky/sonar-plugins/archive/master.zip"><i class="fa fa-download"></i></a></span> |
| Circle CI <If Available> | Not Available |
| Documentation | <span style="color: green;font-weight: bold;"><a href="https://ravaan-techky.github.io/sonar-plugins/"><i class="fa fa-folder-open"></i></a></span> |
  
### Developer Information:

| Description | Github Profile Link  | LinkedIn Profile Link | Email Address
| -------- | -------- | -------- | -------- |
| Vaishali Patil | [<i class="fa fa-external-link"></i>](https://github.com/ravaan-techky/) | [<i class="fa fa-external-link"></i>](#) | [ravaan.techky@gmail.com](mailto:ravaan.techky@gmail.com) |

<br/><br/>
[<i class="fa fa-arrow-left"></i> **Back**](/documentation/)
