# Usa una imagen base de Java que incluya un JDK para compilar y ejecutar tu aplicación.
FROM openjdk:21-jdk-slim

# Establece el directorio de trabajo dentro del contenedor.
WORKDIR /app

# Copia los archivos necesarios del proyecto.
COPY pom.xml .
COPY src ./src

# Construye el proyecto usando Maven.
RUN mvn package -DskipTests

# Expone el puerto que usa tu aplicación Spring Boot.
EXPOSE 8080

# Define el comando para ejecutar tu aplicación. El nombre del archivo JAR se basa en tu pom.xml.
CMD ["java", "-jar", "target/restaurante-0.0.1-SNAPSHOT.jar"]