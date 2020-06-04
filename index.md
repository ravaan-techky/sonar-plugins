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

- Module 1
  - Benifit 1
  - Benifit 2
  - ....
- Module 2
  - Benifit 1
  - Benifit 2
  - ....

### Architecture Overview:

  ![Overview](images/sample_architect_blocks.png)

### Important Links:

| Description | Link |
| -------- | -------- |
| Source Code | <span style="color: green;font-weight: bold;"><i class="fa fa-download"></i></span> |
| Circle CI <If Available> | <span style="color: green;font-weight: bold;"><i class="fa fa-external-link"></i></span> |
| Documentation | <span style="color: green;font-weight: bold;"><i class="fa fa-folder-open"></i></span> |
  
### Developer Information:

| Description | Github Profile Link  | LinkedIn Profile Link | Email Address
| -------- | -------- | -------- | -------- |
| First_Name1 Last_Name1 | [<i class="fa fa-external-link"></i>](#) | [<i class="fa fa-external-link"></i>](#) | [abc@gmail.com](mailto:abc@gmail.com) |
| First_Name2 Last_Name2 | [<i class="fa fa-external-link"></i>](#) | [<i class="fa fa-external-link"></i>](#) | [abc@gmail.com](mailto:abc@gmail.com) |
| First_Name3 Last_Name3 | [<i class="fa fa-external-link"></i>](#) | [<i class="fa fa-external-link"></i>](#) | [abc@gmail.com](mailto:abc@gmail.com) |

<br/><br/>
[<i class="fa fa-arrow-left"></i> **Back**](/documentation/)
