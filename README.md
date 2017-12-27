# kalah
Kalah game implementation (2 human players, no AI)

## Tech Stack
* Java 8
* Spring Boot
* Junit
* [Lombok](https://projectlombok.org)

## Wiring rational
1. The game is played with a strategy.
2. The strategy as a board and a set of players.
3. The strategy defines the board game generation parameters and the playing rules against its board.

## Controllers

### URI(s)
Description | URI | Example | Note
----------- | --- | ------- | ----
Default initialization of Kalah | http://<host>:<server port>/setup | http://localhost:8080/setup | |
Initialization of Kalah with a given level | http://<host>:<server port>/setup/level/<level> | http://localhost:8080/setup/level/6 | Not fully covered
Initialization of Kalah with a given level and strategy | http://<host>:<server port>/setup/level/<level>/strategy/<strategy-name> | http://localhost:8080/setup/level/6/strategy/ | Not fully covered
A player performs a plays on a given position | http://<host>:<server port>/play/player/>player>/position/<position> | http://localhost:8080/play/player/1/position/3 |

## Usage
```
mvn clean spring-boot:run
```

## Test

### Maven
```
mvn clean test
```
### Coverage (Jacoco)
```
mvn clean test jacoco:report
```

## Lombok
This project uses [Lombok](https://projectlombok.org) for reducing boiler plate code. Check the link for specific IDE coonfiguration.

## Further reading
[Kalah rules on Wikipedia](https://en.wikipedia.org/wiki/Kalah)