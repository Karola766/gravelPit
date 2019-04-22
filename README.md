# GravelPitSystem

I have decided to use Scala, both because of the pseudocode's visual suggestion and needing a more functional approach.
I haven't had too much experience with Scala, so there was certainly a lot of learning involved - I've generally tried to work with the language, 
instead of against it.

After implementing the simplest approach, I have identified a few issues to correct. 
First things first, if I wanted to improve order allocation, I needed to find a clear, concise way to store data about available productions, 
their number and assigned orders. I've experimented for a while in the Scala worksheet, trying to find a way that would be comfortable enough. 
Finally, I have decided not to change the production's identification system - I didn't want to make the whole thing more murky with unnecessary data - 
and so, settled for my simplest functional idea of a Map with productions as keys and Arrays of fixed value, containing allocated orders, as values. 
(I have assumed the amount of orders will be of an Integer type. This might not necessarily be true.)   

To allocate and relocate the orders, I've decided to use a recursive function - shuffle_order, which checks first whether there are any suitable free spots left, 
and later if you can relocate any of the already placed orders to create a free spot. Also, I've decided to use Option[] to properly deal with the recursion.

I've decided to add print_productions() to, well, print productions in a mostly readable way - and ship_order to realize the right order. 


#Basic Documentation

 **type scope** - basically a Tuple2 of Double. Added to simplify the code and maximize clarity. 

 **type assigned** - a mutable Map with keys of type scope and values of Array[scope]
 
 **val productions** - structure of type assigned, filled with orders' and productions' data
 
 **def production(scope, Int): Unit** - adds a new production (or adds more units of an existing one)

 **def shuffle_order(scope, scope): Option[(scope, Array[scope])]** - allocates or relocates an order
 
 **def order(scope, Int):String** - places an order and returns a string with the chosen production
 
 **def ship_order(scope):Unit** - realizes an order and removes it's amount from the list of productions (or removes a production)
 
 **def print_productions():Unit** - prints the productions in a clean way
 
 **def test():Unit** - a simple test of the system's capabilities

#How To Use

For a simple test, just run: 

GravelPitSystem.test()