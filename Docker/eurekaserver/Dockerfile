FROM openjdk:11
#WORKDIR /usr/app
COPY . /app
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/app/eurekaserver.jar"]
#ENTRYPOINT ["java", "-jar", "/usr/app/eurekaserver.jar"]