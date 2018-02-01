# Final Report

This app is a combination between a wikia and a forum. On the wikia people can find Pokémon related information and on the forum 
people can discuss Pokémon related topics. People can also play the Pokémon Naming Challenge in this app.

<img src="https://github.com/nathhje/programmeerproject/blob/master/doc/wikiasocial.png" width="216" height="384"/>

**Technical Design**

The app starts out with a main activity that leads into a tab activity where the user can follow three paths. The first is the 
wikia, the second the forum and the third the game. I will now describe in details how each path is build and how they are connected
together.

*MainActivity*

The app starts in MainActivity. This is where a user signs in or signs up. The screen starts with a login screen and the user can 
go to the register screen by clicking a button. When the user does this, the visibility of the widgets will be changed around to 
show the register screen. From here a user can go back to the login screen by clicking another button. When a user clicks on 
the sign in button, the email and password will be verified in Firebase and the user will be signed in or told that email or
password is incorrect. When a user clicks on Create Account, Firebase will attempt to create the user and if succesful, sign the 
new user in. If unsuccesful, the user will be notified.In all activities except for MainActivity itself, their is a logout button
in the action bar that bring the user back to the login/register MainActivity.

*TabActivity*

Once the user is signed in, they will be send to TabActivity. The activity itself only holds the tablayout and the fragments put in 
the tablayout. Each fragment provides the basis for one of the three paths in the app. The TabActivity functions as a starting 
point in the app, you navigate between features through the tablayout in TabActivity.

*WikiaFragment*

First of the three paths is the wikia path, starting out with WikiaFragment. It contains a short introduction to the wikia and 
navigation to other parts of the wikia. Buttons to go to ListActivity and SearchActivity, and a ListView of "Famous Pokémon" brings
the user to InfoActivity, where they can find more info on the Pokémon.

*ListActivity*

ListActivity contains a list with all Pokémon that are in the API in it. This includes different forms of the same Pokémon such as 
mega's or totems. There's a total of 954 Pokémon in this list. If the user clicks on a Pokémon in the list, they will be send to 
InfoActivity where they can find more info about this Pokémon. There's also a button that leads back to TabActivity.

*SearchActivity*

In SearchActivity the user can select if they want to search for a Pokémon, an ability or a type. They can then type something in
the EditText and hit enter. Upon hitting enter the app will check what kind of search is being done and check for each String in 
the appropriate ArrayList (the app holds .txt files with all Pokémon, abilities and types and the files are put into ArrayList) 
whether it contains the string that user is searching for. All hits will be put into a list and shown to the user. If the user
clicks on one of the results, they will be taken to InfoActivity where they can get more information about the thing they clicked
on. SearchActivity also has a button that takes the user back to TabActivity.

*InfoActivity*

In InfoActivity the user can see information about a Pokémon, ability or type. Each intent that starts InfoActivity will have
two extra's, one with the type of thing that has to be shown and one with the actual thing, so Pokémon Pikachu for instance, or
ability Intimidate, or type Fire. InfoActivity will then execute an InfoAsyncTask to retrieve the right information. The 
InfoAsyncTask sends back a class of the right type filled with the right information for that type. The possible classes are
Pokemon(), Ability() and Type(). For each class there is a function in InfoActivity that sets the right information to the right
widgets, and the appriopriate one is called once the InfoAsyncTask is done. Most information that is shown, is clickable, so 
OnClickListener's are set to them. When they are clicked, they will start a new InfoActivity. So for example, a user is looking
at the page on Charizard and sees Charizard is a fire type. The user can then click on fire to be taken to the page on fire. From
InfoActivity the user can also navigate back to TabActivity, ListActivity and SearchActivity.

*ForumFragment*

That was the first path, ForumFragment is the start of the second. It contains a list with topics (and emails of the user that
started the topic) that are in the forum. These topics are retrieved from Firebase, where each topic title is stored under an ID
together with the email of the creator. The posts in each topic are stored in another child, but with the same ID, which can be
used to find the right posts. Each title, email and ID are put in a custom class TopicTitle and a custom ArrayAdapter is used to
put a list of TopicTitles in a ListView. So the topic titles are shown in a list, and if the user clicks on one of them, 
they will be taken to TopicActivity, where they can see the posts in the topic. ForumFragment also contains a button that takes 
the user to NewActivity, where they can create a new topic.

*NewActivity*

In NewActivity the user can create a new topic. It contains an EditText for the title of the topic, and an EditText for the first
post. The user can type something here and if they hit Create Topic, the topic will be assigned a unique ID and the title and the
first post will be put into the database, the title in the "titles" child, together with it's ID and email of the creator. And 
the post will be stored in the "posts" child, under the right topic ID. The post itself will get a post ID of 0, and it will be
stored together with this ID, the email of the creator and the timestamp. The user will be send to TopicActivity, where they can
see their brand new topic. In NewActivity there is also a button Back, which takes the user back to ForumFragment.

*TopicActivity*

In TopicActivity the user can see a topic. All the posts in the topic are placed in a list. Each intent that starts a TopicActivity
has two extra's, the title and the title ID. The posts are stored under this ID in Firebase, and will also be able to be retrieved
with this ID. A post that has been retrieved will be put in a ForumPost class, which contains email of the user who posted it, timestamp and post
itself. A list of these ForumPosts is then set to a ListView using a custom ArrayAdapter. TopicActivity also contains a Back button
that takes the user back to TabActivity, and a button to create a new post in the topic.

*PostActivity*

In PostActivity the user can create a new post. Each intent that starts PostActivity has an extra that contains the title ID, so the app knows where in Firebase to store the post. The activity contains an EditText for the new post. The user can type something here and if they hit Create Post, the post will be stored in the "posts" child of the database, under the title ID, the post will also receive a unique post ID and the content of the post will be stored together with this ID, the email of the user who posted it and the timestamp. The user will then be taken back to TopicActivity where they can see their new post. PostActivity also contains a Back button that brings the user back to TopicActivity without adding anything to the database.

*GameFragment*

This fragment is what makes up the third path. It's only one fragment but a lot is going on. When the fragment is created, the progress of the user will be retreived from Firebase and stored in an ArrayList. This ArrayList is then put into a ListView that will be invisible at the time. The only things that are shown are an explanation and a button to start the game. When the user starts the game, the button and the explanation will become invisble and the widgets used to play the game will become visible. Also, a .txt file containing all 807 Pokémon will be read and stored into an ArrayList. This list will then be compared to the list with the progress and every Pokémon that's in the progress will be removed from the list with all Pokémon. The list with all Pokémon then becomes a list with all Pokémon that have to be guessed, which can be used to validate an entry from the user. Now the user can start the game. When they hit enter, the app will check if the string in the EditText is in the list with Pokémon that have to be guessed, if so, the entry will be deleted from list of Pokémon that have to be guessed and the progress will go up. If the entry is correct, the function also checks if this was the last Pokémon and thus if the user has won. If the user has indeed won, a message appears. If at any point the user wants to give up or restart, there are buttons that allow them to do so. In either case a popup will come up that asks them if they really want to proceed. If no, nothing happens. If yes, in the case of give up, all Pokémon that are still in the list of Pokémon that have to be guessed, will be put in the database and the list will be cleared. The user can now see a list of all 807 Pokémon, but no winning message will appear. If the user wants to restart and answers yes on the popup, all progress will be deleted from the database and the list with progress will be emptied. Every widget except the explanation and the start game button will be set to invisible. The user can start again.

*InfoAsyncTasks*

Those were the activities and fragments. Up next are help classes. InfoAsyncTask is called by InfoActivity. The InfoAsyncTask prepares a request to the PokéAPI and also handles the result of the API request. After it has prepared the request the InfoAsyncTasks sends it to the HttpRequestHelper. When it gets back the result, the InfoAsyncTask creates a Pokémon(), an Ability() and a Type() class and let's the appropriate one parse the JSON and store the information, depending on what type of search it was. It then sends this class back to the InfoActivity.

*HttpRequestHelper*

Gets a request to the PokéAPI from InfoAsyncTask and executes it, then sends back the result to the InfoAsyncTask.

*Pokemon*

This class stores all information on a Pokémon that will be displayed in InfoActivity. It also has a function that parses a JSON object to get to the information it has to store.

*Ability*

This class stores all information on a ability that will be displayed in InfoActivity. It also has a function that parses a JSON object to get to the information it has to store.

*Type*

This class stores all information on a type that will be displayed in InfoActivity. It also has a function that parses a JSON object to get to the information it has to store.

*TopicTitle*

Class that holds information that is saved in the "titles" child in Firebase. Makes it easier to make a custom ArrayAdapter with multiple elements and is easy for storing information together in Firebase.

*ForumPost*

Class that holds information that is saved in the "posts" child in Firebase. Makes it easier to make a custom ArrayAdapter with multiple elements and is easy for storing information together in Firebase.

*DownloadImageTask*

Takes an url with an image and puts it into an ImageView. Used to set Pokémon sprites in ImageView.

*NonScrollListView*

A class created by CabezasGonzalezJavier, it makes a widget of a ListView that isn't scrollable. Used to put lists in a ScrollView in InfoActivity.

**Decisions and Difficulties**

Probably both the biggest difficulty and the biggest decision revolve around Fragments. I was made aware of them shortly after I'd made my DESIGN.md. I had never worked with them before and I saw them as a good way to really turn this app into a challenge. And that turned out to be true, and it almost became to much of a challenge. I wanted a tablayout with three tabs, and each tab would have its own set of fragments between which the user could navigate. It took me a little while to set up the tablayout with one fragment in each tab, but after that I didn't get anywhere. I was stuck on the same bug for over two days trying to replace one fragment with another in the same tab. Unfortunately I didn't manage to find a solution and I really had to get moving so I decided to keep the tablayout with three tabs and three fragments and turn the rest into activities. It looks slightly less convenient, but it still works just fine. I also thought I'd need more AyncTasks because of this, but it turned out that wasn't true. And because I did have the three Fragments in the tablayout, I already managed to have way less buttons in my app, which is good. Looking back I could have used more fragments, even if I wasn't able to get them into my tablayout. In the current version, I'm setting the visibility of a lot of widgets to create two screens in one activity, which is of course what Fragments should be for. But I got delayed by TabFragments pretty badly, so I decided to go with what I know, setting visibilities, instead of messing with something I didn't quite understand, Fragments. And I think it was the right choice with the amount of time I had. If I were to ever develop this app further, I would definitely look into the Fragments some more.

After I made the switch from Fragments to Activities it went a lot quicker. I did have difficulties, but they were mainly bugs that turned out to be caused by a typo, or because I forgot to put internet access in my manifest, or one time I couldn't compile for a whole day because my "aapt.exe was missing" which turned out to be because my antivirus protection had deemed the file unsafe and stored it away. But there really haven't been any other issues big enough to force me to make major app changing decisions. One thing I did change quite early on was the way I structured my Firebase. Firebase just works easiest when all children look the same and so having one post with a title added, or a separate child with a title in it, is just not that easy to work with. (Or maybe I'm not thinking out of the box enough). Anyway, I ended up storing my titles separately from the post, and linking them through an ID. That is one of the reasons though that I wasn't able to sort my topics by order of last posted, which is unfortunate and definitely something I would change if I had more time, but at the same time it's also not that important.

One thing that wasn't really an issue, but is more of a personal challenge, was design. I have absolutely zero sense of style. I don't know what looks good or what people would like. I ended up going with a red-pink-white theme and I hope it looks somewhat okay, but really I don't have clue. Naming the app is also something that's always difficult. I ended up with "Pokédex Social" because of the wikia/forum combination. Again, I hope it isn't completely lame.

I did add one small feature I didn't plan to do, which was the Pokémon list where you can click on any Pokémon to get more information. I hadn't really thought about adding it but somebody mentioned it at some point and it really was a good idea, in case people just want to scroll through what Pokémon there are.

I just realized what I forgot to add, the index number for each Pokémon in InfoActivity. It never even crossed my mind to add it even though it's the most basic characteristic a Pokémon has. Now I feel stupid. Oh well, in terms of functionality it doesn't really change anything. It would just show I can get one more object out of a JSONObject and I could put that information into one more widget in InfoActivity (which, if you've seen my InfoActivity, you know I have enough of).

There are some things that I would definitely still like to add if I had more time. First of all I would play with the layout some more, maybe make a fancy background or custom buttons or stuff like that. And as I've mentioned earlier, I would add more Fragments and I would sort the topics by last post. I would also make usernames, because on a forum that gives more privacy. I would create settings, where the user can see their information, like what email adres is signed in at the moment. And in those settings users would also be able to edit their information. There's also the thing that when you go back from TopicActivity to TabActivity, you automatically start in WikiaFragment, but it would be better if you would start in ForumFragment. The last thing I would change is the format of the text in the wikia. I get a lot of my lists from the API, and the text in those lists aren't capitalized, and spaces are replaced with - . And just to be consistent, I formatted my own .txt files in the same way. I would like to change this.



