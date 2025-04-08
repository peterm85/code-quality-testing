# Etapa de build con Maven y JDK 17
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Establece el directorio de trabajo
WORKDIR /app

# Copia el código fuente al contenedor
COPY . /app

# Ejecuta Maven para compilar y empaquetar la aplicación
RUN mvn clean install -DskipTests

# Imagen final con JDK 17 slim
FROM eclipse-temurin:17-jre

# Establece el directorio de trabajo
WORKDIR /app

# Copia el artefacto generado desde la fase de construcción
COPY --from=build /app/target/code-quality-testing.jar /app/code-quality-testing.jar

# Expone el puerto en el que la aplicación corre
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/code-quality-testing.jar"]
