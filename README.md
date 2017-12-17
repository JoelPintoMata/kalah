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
Descriotion | URI | Example
----------- | --- | -------
 | | http://localhost:8080/setup/level/6
 | | http://localhost:8080/setup/level/6/strategy/someStrategy
 | | http://localhost:8080/play/player/1/position/3

