#Fantasy Baseball App v.1.0


#####This is a basic app that will contain a database of baseball players and display attributes (statistics) about the particular player.

##App Characteristics
#####1. User will be able to search for their favorite player.
#####2. User will be shown stats about their searched player.
#####3. User will have an option to favorite their player of choice. 



##Project Specifications
###User Stories:




#####1. As a user, 	when I open the app, I want to be shown what to do when I get to the main screen so I can interact with the app.
#####2. As a user, 	when I click the search toolbar I want to type my favorite baseball player	so I can find information about this particular player.
#####3. As a user, when I'm on the player's page I want to see the stats displayed	so I can see data about this particular player.
#####4. As a user, when I'm on the player's page I want to be able to favorite this player	so I can reference this player at a later time (or share it with friends ---- EXTRA FEATURE).


###Black Marker Prototype:
![img1](/images/img1.jpg)
#####1. User will navigate the app starting with the first page. Which will provide instructions on how to interact with the app.

![img2](/images/img2.jpg)
#####2. As player types in his/her query, there will be displayed auto-populated search results. To trigger this event, they can do so by inputting the following query varieties:
#######	1. [first name]
#######	2. [last name]
#######	3. [last name, first name]
#######	4. [first name, last name]

![img3](/images/img3.jpg)
#####3. User is taken to player page and is given a display of statistics about that player. User will also be able to favorite the player. 


###Entity-Relationship Diagram:

![erd](/images/erd-1.png)

#####The given ERD describes each of the player's attributes. They are given a name*, age**, team, rbi (runs batted in) and ba (batting average).


######*attribute:name will be segmented into first and last name -- so two string values of fName and lName will be used. 

######**age is of type INTEGER, not STRING.

