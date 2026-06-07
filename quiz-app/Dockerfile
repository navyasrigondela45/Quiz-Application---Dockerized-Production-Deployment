FROM tomcat:9.0-jdk11-openjdk
MAINTAINER Navya_Sri 06/05/2026
LABEL Java Deployment
EXPOSE 8080
COPY target/*.war /usr/local/tomcat/webapps/ROOT.war
COPY tomcat-users.xml /usr/local/tomcat/conf/
