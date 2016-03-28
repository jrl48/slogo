### Status
* This code is definitely consistent in its naming conventions, descriptiveness, and style, as our group often programmed together. 
However, the layout between the front and the backend was different.
* The code is readable due to its clear method names and goals for individual methods; everything does what is expected.
* There are many dependencies due to the use of reflection, as that process involves many classes that each rely on an interface. 
These dependencies are clear and easy to find.
* Most of the program proves to be somewhat challenging to extend, as it serves a specific purpose, but some of the classes that contain 
individual methods are extendable.
* Review of classes I did not write or refactor:
  1. The UserInterface code is interesting because it is the primary front end class; it controls what the user sees. 
One way to improve this code is to put more comments to make Java FX methods and actions more understandable, but a lot of frontend 
programming can be learned by observing this code. This code could be used in a different project so long as that project involved using 
a command line to operate on an object on the screen.
  2. The ParseNode class is used to create the nodes in the parsing tree for a command, and that is essential to the function of the 
program. This code can be improved by adding comments to some methods to outline their purposes, and it can easily be used in another
project when making a general node class for any sort of tree like data structure.
  
### Design
* Every command entered in the frontend will call the backend through the CommandParser class, which will parse the command and based 
on the result of that operation will call a certain method from the UserInterface class in the frontend. That will update what the user
sees with the appropriate result.
* Adding a new command to a language would require editing the properties file for that particular language and adding the command that
the user will enter and what that command induces in the same format as the rest of the properties file has those command entered.
* My code is the backend, which is designed to take in a command and parse it into a command from the properties file. Based on the 
result from the properties file, the Commands class is accessed, and using reflection, the appropriate method is called. This method
uses the front end class UserInterface to update the screen based on this command that the user input.
* The design of my code was based on the avoidance of brute force methods such as case-switch statements or if-else loops to sort through
every possible command that the user could enter. This was not a hard decision to make, as the aforementioned alternatives are very poor
coding tricks. The major dependency of reflection is its reliance on the properties file; for every new method added to that file, a new
class with a new executeCommand method would have to be created.
* Two features from the assignment specification:
  1. We did not implement the recursion feature, which would have required the editing of the Commands class to take in the CommandParser,
as to implement recursion, the method would need to call on the CommandParser to enter a new level of recursion. The design for this feature
would not be very extensible, as it has a specific purpose and there is not a true "extension" of recursion in programming.
  2. We did implement the grouping of commands feature, which required a regex to parse parenthesis, edited the command within the 
parenthesis to fit a format that the CommandParser could understand, and fed that command back into the CommandParser. Extending this 
feature to include not only basic grouped commands but also complicated ones would be quite easy, as the functionality would just need to
be added to multiple classes rather than just classes with two parameters.

### Alternate Designs

* The original design handled the projects extensions extremely well, as it allowed for a simple, repetitive process to add more commands and generally followed good coding conventions by reducing the dependencies between the frontend and the backend.
* The original APIs only altered slightly over the course of the project, as we realized that there were a handful of methods in the CommandParser class that needed to be accessed by other backend classes, so those needed to be added to the API.
* Two design decisions that were discussed:
  1. The main design decision we discussed both as a group and with our TAs was the use of reflection to execute commands from the backend versus if-else loops or case-switch statements, and all parties came to the easy conclusion that reflection was preferable because it created more efficient code by using one HashMap rather than iterating through an entire chunky statement to find the correct command; the use of reflection was better in every way.
  2. Another design discussion I had with my TA was about how to implement commands for multiple turtles, and the discussion was whether to go through all Turtle commands and add a for loop to each one (which would be bad design because it creates a huge dependency on all Turtle commands and the MultipleTurtles commands) or to create a MultipleTurtles class that stores instances of Turtles and changing the Turtle commands to operate on a MultipleTurtles object (which made more sense because only the HashMap in the Commands class had to be edited to create this functionality). After we figured out the alternative to adding for loops everywhere, we realized that this newfound alternative was definitely preferable.

### Conclusions
* The two best features of this design:
  1. One of the best features of this design is the use of reflection to turn the command input by the user into a workable command. It uses a HashMap that maps strings to classes, so a string from the properties file maps to a particular class that has the executeCommand
method. Since the CommandParser access the Commands class that contains this map and calls executeCommand on whatever string the input
corresponds to within the properties file, this functionality works out beautifully. It looks cleaner and is more efficient than the alternatives.
  2. Another design feature that is important is the dearth of dependencies between the frontend and the backend. The only contact that the two spheres have is the passing of EntryManagers (to update the UserInterface) and the frontend's use of the parse command within CommandParser. This is good design convention because it reduces the amount of changes that the frontend would have to endure if the backend were to alter its design.
* The two worst features of this design:
  1. One of the worst features of this design is the use of two different classes for the command HashMap for the user defined commands and for the rest of the commands. If we were to spend more time on the project, we would like to find a way to merge these classes into one so that the same map can be accessed by all commands rather than the use of a single if-else loop to differentiate between user 
defined commands and other commands.
  2. Another of the worst features in this design is the sheer mass of the CommandParser class. With the amount of methods that this class contains, it could definitely be split into a few smaller entities with the main CommandParser class acting as the umbrella over all of these methods.
* To become a better designer, I need to focus on refactoring classes in smaller chunks rather than writing all of my code and then circling back to the beginning to refactor from top to bottom. Refactoring in smaller pieces would definitely benefit the quality of my code. I should also continue to think about efficiency and the avoidance of CodeSmells as I code, because I believe that this has greatly augmented the quality of my code.
