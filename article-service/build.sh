#!/usr/bin/env bash

./mvnw clean -DskipTests package
docker build --tag "imagen-app-article-microservice" .
