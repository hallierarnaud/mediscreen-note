FROM openjdk:11
COPY ./build/libs/mediscreen-note-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar", "mediscreen-note-0.0.1-SNAPSHOT.jar"]