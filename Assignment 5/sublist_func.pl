sublistFunction( [], _ ).
sublistFunction( [X|XS], [X|XSS] ) :- 
    sublistFunction( XS, XSS ).
sublistFunction( [X|XS], [_|XSS] ) :- 
    sublistFunction( [X|XS], XSS ).  

/*==================== Test cases: ========================== */
/*
?- sublistFunction([1,2,3,4,5],[5,4,3,2,1]).
false.

?- sublistFunction([a,a,a],[a,b,a,c,a,d]).
true .

?- sublistFunction([a,a,a],[]).
false.

?- sublistFunction([],[1,2,3,4]).
true.

?- sublistFunction([],[]).
true. 

======================================
*/


  
    