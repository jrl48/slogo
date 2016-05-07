## Estimation

I estimate that this will take approximately half an hour. I will need to add the commands to the properties file so they can be parsed, and I will need to add the new commands to the Commands class. Then, I will need to create two new simple classes that extend the display commands interface, and I will need to add a method to the Display class to account for these new functionalities.

## Review

This actually took me about an hour to complete. I was correct about the properties file and the Commands class, but I also needed to add to the ParametersMap class. Furthermore, I had to edit the SingleTurtle class, not the Display class, to add the functionality that was required. What really made me take longer than expected and not get it on the first try was the problem of adding the update in coordinates to the movement as well as the animation portions of the SingleTurtle, as they are separated and I was not aware of that fact. Once I figured that out, however, I fixed the issue rather easily.

## Analysis

I feel as though our design was very capable of supporting this feature, but I now see small tweaks that would have allowed it to be even better. By connecting the display and actual positions of the turtle, a more efficient frontend design could have been in play. Furthermore, reducing the dependency between the frontend and backend in regards to turtle position would have been a good idea. Had I not been familiar with the code at all, making this addition would have been a struggle, as frontend as well as backend classes needed editing.
