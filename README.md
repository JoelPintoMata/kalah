# kalah
Kalah game implementation (2 human players, no AI)

## Hiring rational
1. The game is played with a strategy.
2. The strategy as a board and a set of players.
3. The strategy defines the board game generation parameters and the playing rules against its board.

## Rules
Position indexes are zero based

## Further reading
[Kalah rules on Wikipedia](https://en.wikipedia.org/wiki/Kalah)

## Controllers

### URI(s)
Description | URI | Example
----------- | --- | -------
Default initialization of Kalah | | http://localhost:8080/setup
Initialization of Kalah with a given level | http://<host>:<server port>/setup/level/<level> | http://localhost:8080/setup/level/6
Initialization of Kalah with a given level and strategy | | http://localhost:8080/setup/level/6/strategy/someStrategy
A player performs a plays on a given position | | http://localhost:8080/play/player/1/position/3

