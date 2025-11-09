FROM eclipse-temurin:17-jdk-jammy
WORKDIR /appContainer
COPY cert.txt /appContainer/cert.txt
COPY target/jenkinsCICD.jar /appContainer
EXPOSE 7072
CMD ["java", "-jar", "jenkinsCICD.jar"]
