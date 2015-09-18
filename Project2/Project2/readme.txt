Dave Studin

I used one unique solver which takes in a Position object. The Position class is abstract and will be the basis
for all my games. The Position class specifies that all games using this solver will share the following
methods, getStart(), getFinish(), getScore(), setScore(), isFinished(), getNeighbors(), computerTurn(), and
playerTurn(). The solver for these types of games uses the same recursive evaluative algorithm the website states.
The solver is able to be used for multiple games, becuase it only accesses the methods used in the Position class.