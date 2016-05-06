Joseph Lilien
jrl48

### SLOGO Feature Addition

*** Note:  During the completion of this project, I had implemented a window to display all turtle images, but did not allow for editing of that image by clicking within that table.  As a result, I decided for this extension to add that functionality, but also add to a completely new view to the GUI workspace displaying the coordinates, heading, and general information about each turtle as well to fully display true feature extension of an older project.

#### Estimation

* I do not think that it will take me very long at all to implement this feature extension.  All of the framework is already well in place for the features to be added.  In order to add the image selection functionality to the turtle window display, I will only need to make a new custom cell class that is used in the cell factory for the table view.  For the full new window addition, I will need to create a new class (under the extensible ModuleView hierarchy) and only add that new class to the ArrayList of Module views to be displayed in the GUI (located withing the UserInterface class).

* My frontend class structure and design for this project has made it such that I should only have to modify one line in an existing class and add two new relatively small classes to completely achieve the desired extended functionality.  This modified existing code (adding the new class to an ArrayList of ModuleView objects to display) is one that would not even be necessary at all if I had incorperated reflection and properties files, which could have been nicely incorperated in the design given more time (it was done in my Masterpiece in a different area of the project).

#### Review

* In order to completely implement these two additional features, it took me a total of about 20 minutes.  This includes the time that it took me to get reaquainted with the project's details as well as the actual implementation.  

* Complete implementation of the new features required the modification of code within 6 classes and the addition of 4.  Although these numbers are higher than my initial estimates, they are very justifiable and for the most part do not indicate poor design.  For example, modifications in the TurtlePreferences class, SingleTurtle class, and Turtle interface included the simple addition of a single getter method for information that was necessary for the extension.  Modifications in the Turtle Display Cell class included only the one line name change of the custom cell to be used for the cell factory of the Table View display and in the UserInterface class included only the addition of the instantiation of the new class (new turtle information view) and its addition to the ArrayList of ModuleView objects to be displayed in the GUI.  Slightly heavier refactoring came within the ImageDisplayCell class in which I extracted a method to an abstract class and used the Template method to create a more extensible hierarchy.

* I got the implementation mostly right on my first try.  One issue that I ran into that required some debugging was that I instantiated a new TurtlePreferences object at first instead of calling a method in the correct existing one, which led to some unsuspected behaivior.  However, I quickly realized my error and made the necessary change.



#### Analysis

* Even returning to my project some weeks later, I was still able to relatively quickly and easily locate the important classes and make the necessary changes and additions for the new feature implementation without any major difficulties.  This showed that my design, especially the modular construction of the UI and the ModuleView hierarchy, held up fairly well to future extension, as I had hoped.  One thing that I did notice is that the documentation for the classes was honestly probably not as clear as it could have been, and I did most of the extensions based on my own prior knowledge of the project's design rather than basing it off of the documentation provided

* As mentioned above, the documentation in my SLOGO project could definitely have been a little more clear and descriptive.  Although I often described the purpose or function of a class or method in my comments, I wrote very little about how to extend each one, or how they interact with any other dependent classes in the project.  Additionally, as mentioned in my actual SLOGO analysis, the generation of the ArrayList of components to display in the GUI in the UserInterface class would have been done via reflection and properties files in an ideal world, but there was not enough time during the project.

* If I were not familiar with the code at all, it would have been daunting at first to make the extensions, only because of the lack of sufficient documentation in some of the classes and the need to create some getter methods such as those in the TurtlePreferences class and the Turtle Interface.  However, given even a short briefing on the project's design, I imagine I would have been able to implement the extension fairly quickly and easily.