set MVN_HOME=C:\Prog\apache-maven-3.3.9
set JBOSS_PATH=C:\Prog\wildfly-9.0.2.Final
cd /d %~dp0
"%MVN_HOME%\bin\mvn" deploy:deploy-file -Dfile="%JBOSS_PATH%\bin\client\jboss-client.jar" -DpomFile=jboss-client.pom    -DrepositoryId=local    -Durl=file:///C:\Users\didier\.m2\repository
pause