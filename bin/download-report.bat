title "Sonar Report Plugin"
java -cp ".;../lib/commons-codec-1.10.jar;../lib/commons-configuration-1.10.jar;../lib/commons-lang-2.6.jar;../lib/commons-logging-1.1.1.jar;../lib/httpclient-4.5.6.jar;../lib/httpcore-4.4.10.jar;../lib/json-20160212.jar;../lib/report-0.0.1.jar" -DPLUGIN_CONF_DIRECTORY=../conf com.techky.sonar.report.ReportGenerator
pause;
