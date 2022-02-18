FROM openjdk:8-jdk-slim
ADD resources/entrypoint.sh entrypoint.sh
RUN chmod a+x entrypoint.sh
ENTRYPOINT ["./entrypoint.sh"]
ADD resources/qamanager.jar qamanager.jar