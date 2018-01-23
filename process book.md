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

*Thursday*

Today the roof blew of Science Park and although I got some programming done, it was way less than I planned to do. I did set up the ForumFragment and NewFragment. I managed to retrieve and process information from Firebase. Putting information in there shouldn't be a problem and I'll figure it out tomorrow. The TopicFragment and PostFragment should work in much the same way as the ForumFragment and NewFragment respectively so they shouldn't be too big of a problem. A problem that I do have is that the buttons of a fragment don't disappear when I open a new fragment, not even with a non-transparent background. At this point my best solution is to manually adjust the visibility of each button to match the right activity, but this doesn't seem like a good way to do it. (It sounds horrible actually.) I'll ask for tips tomorrow if I can. My repository hasn't been updated today (again) because I had to download my project from Github and build it anew, and now for some reason it isn't connected to my repository and I'm not sure what to do about it without messing it all up again.

*Friday*

Today I made the NewFragment functional and I added the TopicActivity and I also reconnected my repository to my project so hopefully I will be able to push regularly from now on. I do still have quite a few problems though, the issue with the buttons that don't disappear is still there. Also, when I go from NewFragment to TopicFragment, I do go to the new fragment, but the screen stays the same. The screen of TopicFragment is nowhere to be found, it's not even like it's layered on top of NewFragment. And another thing, to index my topics I'm calling getChildCount() in the childEventListener() for the database, but this always returns 2, instead of the number of topics like it's supposed. I currently don't have a solution for any of these issues. I may just start working on the wikia part the next time I open my project, and only continue working on the forum with some assistance on Monday. Most of these issues are being caused because I'm using fragments instead of activities. They make it very difficult but I do think it's the kind of challenge I need in this project, so I'll just keep on figuring out those fragments.

*Sunday*

Today I thought about how to implement the AsyncTasks into fragments. I think because I don't have to deal with different acitivities, I will only need two Asynctasks instead of four. I do, however, have to figure out how to correctly close a fragment and start a new one after the AsyncTasks is done and I think I'll have to figure out exactly how this goes as I go along. Today I made three classes for the three different pages that will be available (Pokemon, types and abilities). Each class takes the result from an AsyncTask and puts the right information into variables that can be used to set up the InfoFragment.

**Week 3**

*Monday*
Today went horrible. The bug where my buttons don't disappear is probably rooted pretty deep in my program and I fixed a few things but it's still not working and the TA couldn't help me either. That's really about all I did today. Completely wasted it. If I don't find anything early tomorrow, I'll try again with the hardcoding of the buttons, and if that doesn't work I'll add more activities. I am going to work really hard to get all my stuff done because of this one stupid bug, though. 

*Tuesday*
After trying to get my fragments working properly for half the day, I decided to give up on them. I'm keeping the tab layout and fragments for the initial three screens, but they will open up to other activities instead of other fragments. After this decision it went quickly. In the remainder of the afternoon I made the forum and in the evening I took out the minor bugs, typo's and stuff. Tomorrow I think I'll be able to implement most of the wikia and I'll be able to finish it Thursday. Then after that I can implement the game. I may have to work Thursday evening to accomplish this, but I'm willing to do that. Using activities instead of fragments does mean that I'll need more AsyncTasks since you need a different one for each activity. This is unfortunate but it's preferable to the bugs I had with the fragments.