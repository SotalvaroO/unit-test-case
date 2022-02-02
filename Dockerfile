FROM openjdk:11
EXPOSE 8080
ADD target/unit-test-case.jar unit-test-case.jar
ENTRYPOINT ["java","-jar","/unit-test-case.jar"]