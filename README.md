# Progression

## A songwriting and improvisation progress software suite.

For modern musicians, the selection of available practice exercises, toolsets,
and methodologies has never been wider. Along with the widespread use of software,
large chord progression datasets, such as iRealBook, have been made readily accessible
through mobile phone applications. However, a common bottleneck in the path to improvement
as a musician lies in the ability to be self analyse the technical nature of our playing. Music and
music preference is inherently bias. While practicing to become objectively more skilled at an instrument,
biases can help focus one's effort in a specific area but can also lead to ignorance of
certain techniques and ideas. In this regard, third-party instrument improvement tracking and analysis
that offers users a comprehensive platform to **write and save new songs** (chord progressions), **transcribe**
their own performance, and have **useful analytics** about their own playing. All together, the app could 
provide musicians with deeper objective insight on their playing without necessarily having to study with 
a tutor or pay for lessons.

To focus on improvisation, users should be able to transcribe their playing into the application and be able to
**compare transcriptions overtime**. Long term analytical data will likely be a defining feature as musicians will be able
to quantify their progression instead of subjectively "sounding better", ie *more diverse chord use,
ability to play faster, longer phrasing, outlining progressions better*.



## User Stories

- As a user, I want to be able to create a progression with a title, key, tempo, and time signature.
- As a user, I want to be able to save new progressions into a list of saved progressions(playlist).
- As a user, I want to be able to view, remove, and modify old progressions.
- As a user, I want to be able to add notes and chord numbers to new or existing progressions. 
- As a user, I want to be able to save my progressions and playlists to file.
  As a user, when I start the application, I want to be given the option to load my playlist from file.

# Instructions for Grader

- You can generate the first required event related to adding Xs to a Y by pressing new progression and follow prompts to set fields.
- You can generate the second required event related to adding Xs to a Y by pressing the 
  remove progression button and following prompts to remove an existing progression from the current playlist. 
- You can locate my visual component by starting from main (keyboard image in the middle of the frame).
- You can save the state of my application by pressing the save playlist button and following prompts. 
- You can reload the state of my application by pressing saved progressions when opening the application. 


# Phase 4: Task 2
Thu Dec 01 12:18:09 PST 2022
Set progression name set to Blackbird

Thu Dec 01 12:18:09 PST 2022
Progression Blackbird has key set to D

Thu Dec 01 12:18:09 PST 2022
Progression Blackbird has tempo set to 210

Thu Dec 01 12:18:09 PST 2022
Progression Blackbird has set time signature to THREE_FOUR

Thu Dec 01 12:18:09 PST 2022
Progression Blackbird added to Playlist

Thu Dec 01 12:18:24 PST 2022
Set progression name set to My Favourite Things

Thu Dec 01 12:18:24 PST 2022
Progression My Favourite Things has key set to D

Thu Dec 01 12:18:24 PST 2022
Progression My Favourite Things has tempo set to 120

Thu Dec 01 12:18:24 PST 2022
Progression My Favourite Things has set time signature to SEVEN_FOUR

Thu Dec 01 12:18:24 PST 2022
Progression My Favourite Things added to Playlist

Thu Dec 01 12:18:45 PST 2022
Set progression name set to I'll Be Seeing You

Thu Dec 01 12:18:45 PST 2022
Progression I'll Be Seeing You has key set to F

Thu Dec 01 12:18:45 PST 2022
Progression I'll Be Seeing You has tempo set to 200

Thu Dec 01 12:18:45 PST 2022
Progression I'll Be Seeing You has set time signature to FOUR_FOUR

Thu Dec 01 12:18:45 PST 2022
Progression I'll Be Seeing You added to Playlist

Process finished with exit code 0

# Phase 4: Task 3
Based on my current UML Design Diagram, the TextFieldEvents are an extra class I created on my own 
represent a frame that opens on a button click event, and provides text fields that submit their inputs
to be set as values for progressions or playlists. This extra class introduces "complexity" that could
have been avoided utilizing other classes from the SWING library, such as instantiating JFrames instead of 
creating new TextFieldEvent classes. This would reduce the need to create separate classes and would simplify 
the UML diagram. 

