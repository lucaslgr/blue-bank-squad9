FROM openjdk:11

ARG PROFILE=${PROFILE}
ARG ADDITIONAL_OPTS=${ADDITIONAL_OPTS}

WORKDIR /opt/blue_bank

COPY /target/*.jar blue-bank-api.jar

SHELL [ "/bin/sh", "-c" ]

EXPOSE 5005
EXPOSE 8000

CMD java ${ADDITIONAL_OPTS} -jar blue-bank-api.jar --spring.profiles.active=${PROFILE}