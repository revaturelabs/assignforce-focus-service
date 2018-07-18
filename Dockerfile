FROM java:8
ADD target/focus-service.jar .
EXPOSE 8005
CMD java -jar -Xmx512M focus-service.jar