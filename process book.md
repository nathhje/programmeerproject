# Process book
**Week 1**

*Monday*

Today I had to come up with an idea for my app. The only thing I’d really thought of before today was a survey app, but today I decided that that idea just doesn’t cut it for this project. I did find a great API on Pokémon yesterday, so I decided that I could base my new idea on that. What I came up with was a combination of a wikia and a forum with a Pokémon theme.

*Tuesday*

Today I worked out most of my design document, because I have to leave a bit early tomorrow. I also reconsidered the survey idea, but it doesn’t use enough resources and it’s hard to justify why my app is useful when there are others out there, so it won’t work.

*Wednesday*

I finished the design document. I mainly had to work out the extra game feature and I had to redo the sketches. I got a tip for a better way to work out the game feature and it was actually a good idea. So now instead of saving the Pokémon in a database, they will just be saved in a .txt file in JSON format.

*Thursday*

Today was the first standup meeting. I actually got some useful tips, like creating a menu with tabs instead of navigating with buttons. I tried to implement something like this, but there were some bugs in it and I had to make my prototype for tomorrow, so I gave up for now and I will return to it tomorrow or next week. Instead I made a prototype with button navigation instead. There were some bugs in that as well and some things that needed updating and that’s why it took me a long time to finish. After that I tried writing some code to retrieve info from my API, but it did not work. The getRespondCode() functions crashes the app, but it’s 23:30 right now so I’ll ask for assistance tomorrow.

*Friday*

I forgot to push my project to Github so I had to do it this morning. But I was struggling with Git for a bit and ended up deleting my repository and uploading it again. Looking back it wasn’t necessary, but it was the best thing I knew to do. It still shows which files I changed in what commit though, so that’s good. It also turns out that my AsyncTask wasn’t working because the line for internet access wasn’t in my manifest. Now it works fine and I can retrieve info from the API.

**Week 2**

*Monday*

Today I figured out how Tabbed Activities work and I began implementing it in my own project. But of course there were a lot of bugs, and it took me all day to figure out the basics. Halfway through I had to decide if I actually wanted to continue implementing a Tabbed Activity, or whether I would just use the action bar instead. In the end I decided to keep at the Tabbed Activity and by the end it worked, so tomorrow I'll continue implenting features into the Tabbed Activity.

*Tuesday*

After dealing with even more bugs, I got the tabbed activity working properly. I can now add just add fragments, so I should be able to focus on getting some functionality into my app. Tonight I set up the Firebase Authentication, which seems to be working fine so far. Later on I'll see if I can add usernames, because I think that looks better in a forum and because it allows for more privacy. I'm not going to do it now though, not as long as I'm behind on schedule.

*Wednesday*

Today I started to work on the forum. It turns out that it's a bit mor difficult than expected, but I'm on my way to finishing the ForumFragment, and I think that once that's done, the other three Fragments in the forum part will be way easier. Today I also had to decide if I want the forum to update instantly or only on refresh, but with the way Firebase Database works it's easier to updat right away so that's what I'm going with. My new goal for this week is to finish the forum, I won't be able to start working on the Wikia. Today I realized that android studio has only been committing my changes for the past two days, but not pushing them, so my repository isn't up to date. I can't figure out what exactly is wrong, so I'll ask about it tomorrow.
