FROM amazoncorretto:11-alpine

MAINTAINER Aidynuly Dias, aidynuly@magnum.kz

ENV FILE_NAME=dev-fastman-service.jar
ENV APP_PORT=8086
ARG JAR_FILE=target/*.jar

WORKDIR /app

COPY ${JAR_FILE} /app/
EXPOSE ${APP_PORT}
RUN ln -snf /usr/share/zoneinfo/Asia/Aqtau /etc/localtime && echo Asia/Aqtau > /etc/timezone

CMD ["java", "-jar", "fastman-service.jar"]