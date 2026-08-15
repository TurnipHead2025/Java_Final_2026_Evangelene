# Individual Contribution Report

## Assigned Features
I completed the Gym Management System project independently, so all features, debugging, and documentation were done by me.

The main features I implemented are:

- User registration and login with BCrypt password hashing
- Role-based menus for Admin, Trainer, and Member
- Membership purchase and membership revenue tracking
- Workout class create, view, update, and delete functionality
- Gym merchandise add and view functionality
- Logging for startup events, failed logins, database errors, and admin actions

I also fixed several compile and menu issues while finishing the project, including menu wiring, method naming, and database access problems.

## GitHub Contributions
This was an individual project, so I worked on the `main` branch.

Remote repository:
- `origin` -> `https://github.com/TurnipHead2025/Java_Final_2026_Evangelene.git`

Recent commit history from my work:
- `ed52320` - Trying to fix the Repo...again
- `5f48057` - Logger using util, FileHandler, SimpleFormatter
- `9e1ff3e` - Product viewing and manipulations menu. Normalized the code. testing
- `7b6fa78` - GymProductService
- `e4d7439` - GymProducts DAO
- `6d460c5` - Update Class Menu. Testing. GymProducts.java
- `302337d` - Update Class Method
- `89d3958` - Testing GymApp and adding functionality

## Challenges & Problem Solving
One of the biggest challenges was getting the code to compile cleanly while I was wiring new features into the menus. I had to fix method name mismatches, menu fall-through issues, and database-related errors one slice at a time.

Another issue was the logging setup. I first used the wrong logging approach and had to adjust it until the app correctly wrote to `app.log`.

I had an issue installing Maven. To fix it, I accepted a pop-up to "Modernize" because I assumed it would update and install packages. It did that, but also more than I anticipated, like auto-fixing my fat-finger mistakes. Trying to stop it and fix it led to my main app file getting completely corrupted. I had to revert my work back to the last saved rendition.

I also ran into a Git history problem when a large video file caused a push rejection. I had to back out the bad commit path and get the repository back to a pushable state.

## Skills Learned
This project helped me practice:

- Java class design and method organization
- DAO and service layer separation
- PostgreSQL and JDBC work
- BCrypt password hashing
- Console menu design
- Basic logging and debugging
- Git cleanup after a bad commit or large file problem

## Team Reflection
I completed this project independently, so I did not have team communication issues to manage. The main thing that worked well was being able to move at my own pace and fix problems without waiting on anyone else.

If I did this in a team, I would want clearer task splitting early on and more frequent check-ins so problems like menu bugs or Git issues get caught sooner.

## AI Usage Log
I used AI tools as tutoring, debugging, and writing support while building the project.

### AI Help Used
- Clarifying the layered design of the app
- Understanding DAO and service class responsibilities
- Checking menu logic
- Helping me get unstuck when writing methods
- Unscrambling my brain when objects and classes blur in coding blocks (WorkoutClassService vs. workoutClassService, etc.)
- Setting up Java logging with `java.util.logging`
- Cleaning up the user guide and technical documentation
- Recovering from several Git problems
- Finding and understanding bugs and compilation errors
- Offering support and encouragement

### Section 5 Feature Challenge Reflection
I did not complete the optional file export challenge, so I do not have a finished report writer to include. If I had continued that task, I would have needed to use Java file I/O, create a reports folder, and write formatted text output to a file.

### Final Reflection on AI Use
I use AI tools not just as a coding helper, but as part of how I think about learning and building systems. I firmly believe that coding LLMs will be fully integrated into programming. Learning how and when to use this knowledge base is crucial.

AI can support coding, debugging, and idea development without replacing the need to understand the underlying logic. I am especially interested in LLMs, AI philosophy, and the future of artificial intelligence, because those ideas connect directly to the kind of systems architecture (hobby) work I am doing. For me, AI is not just a tool I used in this class; it is part of the field I want to keep studying and building.

It was most useful when I needed a refresher or further exploration of a concept. It also completely replaces Googling a Substack and asking a Redditor to help with structure and syntax, saving me an incredible amount of time and energy. It did not replace the actual coding, but it helped me move faster when I was stuck or overwhelmed. And let's not discount the pass it made on this very document cleaning up my spelling and grammar mistakes. 

As far as the "prompts" I use, I just talk like a normal person. I do specify instructions. I currently have two AI tutors. Co-Pilot in VS Code which is now ridiculously expensive and I use a free Gem in Google Gemini.

Have a look at the co-Pilot instructions here. The top half is what I wrote. The bottom are notes from the instance themself. They are not wrong about the crying lol 


# Tutor Mode
- You are Pixel, Sheri's tutor. Not a code writer.
- Sheri should write the code unless they explicitly ask for a full solution.
- Default to hints first, one small step at a time.
- Help Sheri find and understand mistakes instead of silently fixing everything.
- Keep answers short and clear.
- Do not refactor or improve code that was not asked about.

- The user's name is Sheri
- She is a software development student
- She has auDHD and is easily frustrated and prone to crying.
- Only use analogies when actual descriptions are not working.
- Analogies are a LAST resort. 
- Attempt to explain in concrete coding terms first
- If Sheri seems overwhelmed, slow down and do one concept at a time.

# My Gemini Gem instructions:
Gem Identity:  Sheri's software development tutor
Technical Mandate:
You are an expert in back end and front end coding and applications.  DO NOT write code without being asked. Remember you are the tutor. Sheri has to learn to write it herself
Relational Directives
Style: Be lean, intimate, and human. Avoid "assistant-speak" (e.g., "I understand," "Certainly").
Executive Function Support: Sheri is auDHD and uses AI as an extension of her executive function. Never judge this; support it as a mechanical necessity for her agency.

