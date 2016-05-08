##Estimation
before looking at the old code:
how long do you think it will take you to complete this new feature?
how many files will you need to add or update? Why?

I think the new feature will probably take around 1 - 3 hours. I will have to update the properties files to include the new edge cases, as well as changing the ParametersMap to include those edge cases and the number of parameters they all need (which should be 0). New classes that extend TurtleInterfaces will also need to be created and added to the , like TurtleWrap, TurtleFence, and TurtleWrapped. The MultipleTurtles class will probably needed to be updated with the actual implementation of how the edges should be taken.

##Review
It took about 1 - 2 hours for me to actual complete the feature. I needed to update 4 files (the ParametersMap class, the English.properties resource file, the SingleTurtle class, and the MultipleTurtles class). I also added 7 classes: 1 ITurtleMove interface and then 3 classes that implemented that interface (one for wrap, one for fence, one for window) along with 3 other classes that implemented the TurtleInterface (again: one for wrap, one for fence, one for window). I needed to update the ParametersMap class so that the parsing tree would know how many parameters to expect for each case. I needed to update the resource file so that the new instructions would be recognized, and I had to change the SingleTurtle and MultipleTurtle classes to add the new interfaces to their respective maps. The TurtleInterfaces were needed to set the edge type (1, 2, or 3) and the ITurtleMove interface was used to set the turtle coordinates to the correct ones.

I kind of got it right the first time, minus some little bugs. The first time I completely messed up my variable names for the edge map, so it would not work at all. Once, I fixed that it was pretty easy to manage.

##Analysis
what do you feel this exercise reveals about your project's design and documentation?
was it as good (or bad) as you remembered?
what could be improved?
what would it have been like if you were not familiar with the code at all?

I think that this exercise reveals that even though the addition is so small, a lot of classes needed to be changed. But, it was better than I remembered our project being. I think something that could be improved is the organization of our method interfaces, there are a lot of them and its difficult to find the one you want. Also, it would help to keep all the interface/parameter maps in a centralized location that way the user does not have to hunt through all the files to find the one they are looking for. When I was trying to implement these extensions, I honestly opened way too many classes while trying to find the turtleInstructions map. If I was unfamiliar with the code at all, it would have been difficult but possible for me to implement these changes. One of the big problems I think is the organization of the code. In the future, I would want to organize the packages better so that anyone looking at the code can easily find what they want, which is also why it would be easier to have the maps in a centralized location so that an user would know where to change things.
