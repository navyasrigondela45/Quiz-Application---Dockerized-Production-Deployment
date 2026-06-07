FROM mysql:8.0
MAINTAINER Navya_Sri 07/05/2026
LABEL MySQL deployment
EXPOSE 3306
ENV MYSQL_DATABASE=quiz_db
ENV MYSQL_USER=navya
COPY init.sql /docker-entrypoint-initdb.d/
COPY sample_data.sql /docker-entrypoint-initdb.d/
