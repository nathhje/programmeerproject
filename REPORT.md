# Final Report

This app is a combination between a wikia and a forum. On the wikia people can find Pokémon related information and on the forum 
people can discuss Pokémon related topics. People can also play the Pokémon Naming Challenge in this app.

<img src="https://github.com/nathhje/programmeerproject/blob/master/doc/wikia.png" width="216" height="384"/>

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
with this ID. A post that has been retrieved will be put in a ForumPost class, which contains email of poster, timestamp and post
itself. A list of these ForumPosts is then set to a ListView using a custom ArrayAdapter. TopicActivity also contains a Back button
that takes the user back to TabActivity, and a button to create a new post in the topic.

*PostActivity*




