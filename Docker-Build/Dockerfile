# Temel Image olarak OpenJDK Image'ını kullanma
FROM openjdk:17-jdk-slim 

# Jar dosyamızın çalıştırılabilir olmasını sağlama
ARG JAR_FILE=drema-ws.jar
COPY ${JAR_FILE} app.jar

# Uygulama Portu
EXPOSE 8090

# Uygulamanın Çalıştırılması
ENTRYPOINT ["java","-jar","/app.jar"]
