# Usa una imagen base con JDK
FROM maven:3.8-openjdk-11 AS build

# Establece el directorio de trabajo
WORKDIR /app

# Copia el código fuente al contenedor
COPY . /app

# Ejecuta Maven para compilar y empaquetar la aplicación
RUN mvn clean install -DskipTests

# Usa una imagen más ligera para la ejecución
FROM openjdk:11-jre-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia el artefacto generado desde la fase de construcción
COPY --from=build /app/target/code-quality-testing.jar /app/code-quality-testing.jar

# Expone el puerto en el que la aplicación corre
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/code-quality-testing.jar"]
