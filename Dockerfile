FROM openjdk:8
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY ./build/libs/* /usr/app/app.jar
CMD ["java","-jar","app.jar"]
