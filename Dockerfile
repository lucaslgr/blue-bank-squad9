FROM openjdk:11-jre
ARG PROFILE=${PROFILE}
ARG ADDITIONAL_OPTS=${ADDITIONAL_OPTS}
COPY target/*.jar blue-bank-service.jar

CMD java ${ADDITIONAL_OPTS} -jar blue-bank-service.jar --spring.profiles.active=${PROFILE}