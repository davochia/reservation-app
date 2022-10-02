FROM gradle:7-jdk8 AS builder
WORKDIR /app
COPY --chown=gradle:gradle . /app
RUN gradle build --no-daemon 


FROM openjdk:8-jdk-alpine
COPY --from=builder /app/build/libs/*.jar app.jar 

ENV SW_AGENT_COLLECTOR_BACKEND_SERVICES="host.docker.internal:11800" \
    SW_AGENT_NAME="appointment-app"

COPY skywalking-agent /usr/local/agent    

EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -javaagent:/usr/local/agent/skywalking-agent.jar=agent.service_name=appointment-app -jar app.jar"]
