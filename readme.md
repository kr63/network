# Network application demo

## Setup google as oauth2 provider:
Set environment variables clientId & clientSecret.

In intellij ide you can do it as follows:
  ```
  Edit Configuration -> DemoApplication -> Configuration tab -> Environment variables
  ```

## How to run:

1. Install docker
1. Start up postgres database:
    ```
    docker-compose up -d
    ```
1. Run database migrations:
    ```
    ./gradlew flywayClean
    ./gradlew flywayMigrate
    ```
1. Build jar file:
    ```
    ./gradlew bootJar
    ```
1. Run jar file:
    ```
    java -jar build/libs/network-0.0.1.jar
    ```
