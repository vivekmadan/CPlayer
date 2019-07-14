FROM java:8-jre
WORKDIR usr/src
ENV MONGO_DATABASENAME=cpplayerdb
ENV MONGO_URL=mongodb://localhost:27017/cpplayerdb
ADD ./target/playerrecommendationservice-1.0.jar /usr/src/playerrecommendationservice-1.0.jar
ENTRYPOINT ["java","-jar","playerrecommendationservice-1.0.jar"]