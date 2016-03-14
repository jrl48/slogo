# slogo
A development environment that helps users write SLogo programs.

#### Contributors

* Joe Lilien (frontend)
* Alan Cavalcanti (frontend)
* Virginia Cheng (backend)
* Guarav Kumar (backend)

#### Date Started 

* 3/22

#### Total Time

* 60-70 hours

#### Contributions/Roles

* Joe: Implemented most frontend User Interface funcionality and design

* Alan: Implemented Turtle movement/behaivior and animation

* Virginia: Implemented backend parsing tree design, variable scope, and most functionality

* Guarav: Worked on implementing backend loop/user defined command functionality

#### Resources Used

* Java Documentation about many built in javaFX functions and objects 

* JavaFX Pane tutorial: http://docs.oracle.com/javafx/2/layout/builtin_layouts.htm

* JavaFX binding tutorial: https://docs.oracle.com/javafx/2/binding/jfxpub-binding.htm

#### Starting File

* Main.java

#### Resource Files

* scene.properties

* pref.properties

* language files

* custom.css

* help.html

* Image Files:

	- turtle.png
	- turtleTest.png
	- turtle_gif.gif
	- red_square.jpg
	- green_circle.png
	- blue_triangle.png

#### Program Features:

* Hover over turtle to see info/stats

* Right Click turtle to change preferences

* Click turtle to change active/not active

* Hover over color in palette to see RGB value

* Click color in palette to change display color

* Hit shift/enter to run command

* Click variable in table to edit

#### Known Bugs

* Saved Workspaces are displayed in a combobox, which responds to SetOnAction, which is a change in selection model.  As a result, if only one workspace is saved, it can only be reopened once, unless a second workspace is saved so the selection model can be changed effectively.  This bug would have been fixed but for a nullpointer error that is thrown due to a bug inherint to the Java Fx combobox object





