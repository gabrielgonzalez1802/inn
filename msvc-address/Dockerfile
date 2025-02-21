FROM openjdk:17-alpine
VOLUME /tmp
ADD ./target/msvc-address-0.0.1-SNAPSHOT.jar msvc-address.jar
ENTRYPOINT [ "java","-jar","/msvc-address.jar" ]