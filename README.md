# Project Proposal Programmeerproject
**Problem Statement**

Sometimes I'm playing Pokémon and I want to look something up on a wiki or a forum and I just can't find an app that doesn't provide an overload of information, like bulbapedia does. And websites with this information are usually very slow. And I also want a simple forum where I can discuss the things I just read or ask small questions about them. And I know there are more people like me that casually play Pokémon games and would occasionally like some additional, factual information.

**Solution**

My app will provide a combination of a wikia with key information about Pokémon and a forum to discuss Pokémon, where a community of casual Pokémon fans can be formed.

Here is a sketch of what it will look like:
![](https://github.com/nathhje/programmeerproject/blob/master/doc/design%20deel%201.jpg)
![](https://github.com/nathhje/programmeerproject/blob/master/doc/design%20deel%202.jpg)
![](https://github.com/nathhje/programmeerproject/blob/master/doc/design%20deel%203.jpg)

*main features (MVP)*
- registration and sign in
- forum where anyone can start topics or post to an existing topic and you can see who posted what at what time
- a wikia with basic information on Pokémon
  - home screen with instructions and quick links to well known Pokémon
  - search for Pokémon by name or national dex number
  - For each Pokémon info like type, sprite, moves, etc.
- basic information on other things, like
  - types
  - items
  - locations
- links to other pages in the basic information of something (example: You have the page of Bulbasaur open and you click on it's ability to go to the page with information on the ability.)
  
*additional features*
- subforums in the forum
- feature to be taken to a random page
- maybe as an extra: a game where you have to type in all 807 Pokémon names at the top of your head (Pokémon naming challenge). Each Pokémon you guess successfully will appear in a list and you win if you guess all 807.

*prerequisites*
- Pokéapi: the RESTful Pokémon API (https://pokeapi.co/)
- Firebase authentication and Firebase Database
- **Comparison to similar apps.** There are actually quite a few similar apps to this one, but what will make my app unique is the forum function and the very basic, factual information. Forum functions are often only implemented in websites, but in my experience these websites are usually quite slow. The forum function will make it possible for a community to be created. Other apps also tend to have a lot of additional information available that I'm not interested in when I'm just playing through the game and I have to scroll through all of it, this app will not have that. The setup of the wikia will however be the same as in similar apps, with quick links to well known Pokémon and a search function, but mainly with a page for each Pokémon that lists everything the app has to offer on that Pokémon.
- **What will be hard to implement.** I don't foresee any huge technical problems or limitations, but I think the forum will be the hardest part. I have to figure out how to store each topic in a database and keep them all neatly sorted and retrieve them the right way. All I can really do about that is use my database carefully.

[![BCH compliance](https://bettercodehub.com/edge/badge/nathhje/programmeerproject?branch=master)](https://bettercodehub.com/)
