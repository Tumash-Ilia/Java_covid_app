FROM registry.gitlab.com/sti-group/sti-app/openjdk:8-jdk

ENV JAVA_OPTS="-Xmx1000m -XX:MaxMetaspaceSize=128m"

RUN mkdir -p /home/sti/appdata /home/sti/logs

WORKDIR /home/sti
COPY build/libs/backend.jar backend.jar
COPY docker/entrypoint.sh entrypoint.sh

EXPOSE 8080

RUN chmod +x /home/sti/entrypoint.sh
ENTRYPOINT ["/home/sti/entrypoint.sh"]