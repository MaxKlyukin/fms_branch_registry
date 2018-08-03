FROM openjdk:8

RUN apt-get update && \
    apt-get install -y wget maven mysql-client && \
    useradd fmsapp && \
    mkdir -p /home/fmsapp/source && \
    mkdir -p /home/fmsapp/app

COPY . /home/fmsapp/source
WORKDIR /home/fmsapp/source

RUN mvn clean package && \
    cp /home/fmsapp/source/target/fms_branch_registry-0.0.1-SNAPSHOT.jar /home/fmsapp/app/fms_branch_registry.jar && \
    rm -rf /home/fmsapp/source/target

RUN cp /home/fmsapp/source/wait-db.sh /home/fmsapp/app/wait-db.sh && \
    cp /home/fmsapp/source/run.sh /home/fmsapp/app/run.sh && \
    chown -R fmsapp:fmsapp /home/fmsapp/app && \
    chmod +x /home/fmsapp/app/wait-db.sh && \
    chmod +x /home/fmsapp/app/run.sh

USER fmsapp

WORKDIR /home/fmsapp/app

EXPOSE 8080

CMD ["sh", "./run.sh"]