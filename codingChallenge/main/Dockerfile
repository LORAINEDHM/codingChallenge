
############################
##        BUILD           ##
############################

FROM amazoncorretto:11-alpine-jdk as builder

# set working directory inside the container
WORKDIR /usr/src/app

# copy source code to the working directory
COPY src /usr/src/app
COPY resources /usr/src/app/

# compile
RUN javac $(find . -name "*.java")

# create executable JAR file
# 'c' : create new archive
# 'f' : specify archive file name (codingChallenge.jar)
# 'e' : specify entrypoint
RUN jar cfe codingChallenge.jar Main $(find . -name "*.class") user_events.csv
############################
##        TESTS           ##
############################

RUN echo "Testing..."
FROM amazoncorretto:11-alpine-jdk as tester

WORKDIR /usr/src/app
COPY --from=builder /usr/src/app /usr/src/app

ENTRYPOINT ["java", "-classpath", ".", "test.TestRunner"]

############################
##          RUN           ##
############################

# use JRE image for execution
FROM openjdk:11-jre-slim as runtime

WORKDIR /usr/app

# expose the port the app listens to
EXPOSE 8080

# copy the executable from the 'build' step
COPY --from=builder /usr/src/app/codingChallenge.jar /usr/app/codingChallenge.jar

CMD ["java", "-jar", "/usr/app/codingChallenge.jar"]