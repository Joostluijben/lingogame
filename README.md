[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=Joostluijben_lingogame&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=Joostluijben_lingowords)
[![codecov](https://codecov.io/gh/Joostluijben/lingogame/branch/master/graph/badge.svg)](https://codecov.io/gh/Joostluijben/lingowords)
[![Build Status](https://dev.azure.com/joostluijben/lingo/_apis/build/status/Joostluijben.lingogame?branchName=master)](https://dev.azure.com/joostluijben/lingo/_build/latest?definitionId=10&branchName=master)

This is an application with an API to play Lingo

To create a new player call <br>
curl --location --request POST 'https://joostluijben-lingogame.herokuapp.com/lingo/players' --header 'Content-Type: text/plain' --data-raw 'PLAYERNAME'<br>

To create a new game with that player<br>
curl --location --request POST 'https://joostluijben-lingogame.herokuapp.com/lingo/game/PLAYERNAME/startGame'

To create a round with the game. If wordLength is empty it will pick a random word<br>
curl --location --request POST 'https://joostluijben-lingogame.herokuapp.com/lingo/rounds/GAMEID/startRound?wordLength=WORDLENGTH'

To make a turn on that round<br>
curl --location --request POST 'https://joostluijben-lingogame.herokuapp.com/lingo/turns/GAMEID/ROUNDID/makeTurn' --header 'Content-Type: text/plain' --data-raw 'GUESSWORD'

To stop the game and calculate a highscore<br>
curl --location --request GET 'https://joostluijben-lingogame.herokuapp.com/lingo/players/PLAYERNAME/highscore'
