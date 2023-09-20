# java 17을 기반으로 Docker 이미지를 사용한다.
FROM openjdk:17-jdk
# Springboot JAR 파일을 컨테이너 내로 복사한다.
COPY build/libs/EcologyMap-0.0.1-SNAPSHOT.jar app.jar
# 컨테이너의 포트 8080을 외부에 노출시킨다.
EXPOSE 8080
# Springboot 애플리케이션을 실행한다.
ENTRYPOINT ["java", "-jar", "/app.jar"]