# API PRODUCTS
Api RestFUl Games with Spring boot (version 3.2.1) which provides endpoints for you,CRUD (Create, Read, Update, Delete) operations on user data.



## Use requirements
``Java version 17`` <br>
``Postman or insomnia to test the requests``

## Used dependencies
- ``Lombok``<br>
- ``Spring data JPA``<br>
- ``H2``<br>
- ``Spring validation``<br>



## How to run the project?
- Make sure you have Java installed on your machine.
- Clone this repository into your local system
- Open the project in a Java IDE like Visual Studio Code or IntelliJ.
- Run the project's main class.

# HOW TO ACCESS THE H2 DATABASE
- ``http://localhost:8080/h2-console ``
> See the file: `` aplication.properties`` for more info


# RESTful resource routing for User

- ## Get One Game
``Method : GET`` <br>
``http://localhost:8080/game/{id}``
- ## Get All games
``Method : GET`` <br>
``http://localhost:8080/games``
- ## Create a new game
``Method : POST`` <br>
``http://localhost:8080/game`` <br>
- ## Update a game
``Method : PUT`` <br>
``http://localhost:8080/game{id}`` <br>
- ## Delete a game
``Method : DELETE`` <br>
``http://localhost:8080/game/{id}``

# 
##  Important Note

you must replace {id} for the real id when you test the route
