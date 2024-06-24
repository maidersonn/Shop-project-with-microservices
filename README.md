# SHOP PROJECT

This project implemets a CRUD API with authentication and authorization and a messaging system with Kafka. 
The project includes two microservices. Both are API Rest and Spring Boot projects.   
One mciroservice is a sing-in / login service.   
The other one is a CRUD of articles.  

## Database
Each service has got its own database.   
The databases run into docker containers.   
To run them it is necesary to have Docker in your machine and Daemon running.   
Then, run in the terminal: 

```docker compose up```  
(with sudo if you need it)

## Kafka

Download the latest Kafka realease and extract it.   
Run Kafka broker (my choice is via docker image). 

```
docker pull apache/kafka:3.7.0
docker run -p 9092:9092 apache/kafka:3.7.0

```   
Go to the kafka folder created in your directory system.  
Create this topic :

```
bin/kafka-topics.sh --create --topic newUser --bootstrap-server localhost:9092
```

## Start application

Go to each springboot aplication and run the main of each, the authentication-service and the article-service.



