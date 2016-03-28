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

### Conclusions
