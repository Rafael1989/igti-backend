FROM openjdk:8

WORKDIR /app

COPY target/users-mysql.jar /app/users-mysql.jar

ENTRYPOINT ["java", "-jar", "users-mysql.jar"]