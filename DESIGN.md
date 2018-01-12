
# Design Document

- **Advanced Sketches.**
![](https://github.com/nathhje/programmeerproject/blob/master/doc/design%20drawing%201.png)
![](https://github.com/nathhje/programmeerproject/blob/master/doc/design%20drawing%202.png)
![](https://github.com/nathhje/programmeerproject/blob/master/doc/design%20drawing%203.png)

- **Diagram of classes, attributes and functions.**
![](https://github.com/nathhje/programmeerproject/blob/master/doc/classes%2C%20attributes%20and%20functions%201.png)
![](https://github.com/nathhje/programmeerproject/blob/master/doc/classes%2C%20attributes%20and%20functions%202.png)
![](https://github.com/nathhje/programmeerproject/blob/master/doc/classes%2C%20attributes%20and%20functions%203.png)
![](https://github.com/nathhje/programmeerproject/blob/master/doc/classes%2C%20attributes%20and%20functions%204.png)

- **Used APIs.** 
  - I will be using one API to make my app, the Pokéapi: the RESTful Pokémon API (https://pokeapi.co/). This is an open source API. The API will provide the information in the wiki part of the app.
  - On the info page for each Pokémon, a sprite of that Pokémon will be available. To correctly display the sprites I will be using some code from Android Developer (http://stackoverflow.com/users/1196072/android-developer). The code is available through this link: https://stackoverflow.com/questions/2471935/how-to-load-an-imageview-by-url-in-android/9288544#9288544.

- **How will the data be filtered and transformed.** 
  - The data from the API is in JSON. There are multiple things you can search for in the API, with a lot of information that comes with it. In the MVP for my app, people will be able to look for Pokémon, types and abilities. The relevant information from each thing will be retrieved and filtered by using an AsyncTask and HTTPRequestHelper and a package to parse JSON.
  - To play the naming game, I need a list of all Pokémon. I downloaded such a list in JSON format by sindresorhus (https://github.com/sindresorhus) from this location: https://github.com/sindresorhus/pokemon/blob/master/data/en.json. I saved it as a .txt file and I can directly use it in my program by putting all elements of the file in an array.

- **Form of the database.** 
  - I will be using a Firebase database. In this database the forumtopics and forumposts will be stored. This will be done as shown in the screenshot below.

![](https://github.com/nathhje/programmeerproject/blob/master/doc/database%20structure.png)


