title "Sonar Report Plugin"

@echo off
setLocal EnableDelayedExpansion

set CLASSPATH="

for /R ../lib %%a in (*.jar) do (
	set CLASSPATH=!CLASSPATH!;%%a
)
 
set CLASSPATH=!CLASSPATH!"
 
echo !CLASSPATH!
 
java -DPLUGIN_CONF_DIRECTORY=../conf com.techky.sonar.report.ReportGenerator
