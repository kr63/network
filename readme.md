#Project pattern

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
