# Utilizar una imagen base de OpenJDK para la compilación
FROM adoptopenjdk:11-jdk-hotspot AS build

# Establecer el directorio de trabajo en el proyecto Spring Boot
WORKDIR /app

# Copiar el archivo pom.xml al directorio de trabajo
COPY pom.xml .

RUN mvn --version
# Descargar las dependencias de Maven (esto mejorará el tiempo de compilación si el archivo pom no ha cambiado)
RUN mvn dependency:go-offline -B

# Copiar todos los archivos del proyecto Spring Boot al directorio de trabajo
COPY src ./src

# Compilar la aplicación Spring Boot
RUN mvn package -DskipTests

# Utilizar una imagen base de OpenJDK y JRE para la ejecución de la aplicación
FROM adoptopenjdk:11-jre-hotspot

# Establecer el directorio de trabajo en la aplicación Spring Boot
WORKDIR /app

# Copiar el archivo JAR generado en la etapa anterior al directorio de trabajo
COPY --from=build /app/target/spring-skeleton-1.0-SNAPSHOT.jar ./spring-skeleton-1.0-SNAPSHOT.jar

# Exponer el puerto en el que se ejecuta la aplicación Spring Boot (puerto predeterminado de Spring Boot es 8080)
EXPOSE 8080

# Comando para ejecutar la aplicación Spring Boot
CMD ["java", "-jar", "spring-skeleton-1.0-SNAPSHOT.jar"]
