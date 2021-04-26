/*
---------------------------Outputs-----------------
?- ['maze_solver.pl'].
true.

?- ['Mazedata1.pl'].
true.

?- shortestPathFinder(11,[99],99,X).
X = [11, 12, 13, 14, 15, 16, 17, 18, 19, 29, 39, 49, 59, 69, 79, 89, 99] 


?- ['Mazedata2.pl'].
true.

?- shortestPathFinder(43,[169],169,X).
X = [43, 44, 45, 46, 47, 48, 49, 69, 89, 109, 129, 149, 169] 

?- ['Mazedata3.pl'].
true.

?- shortestPathFinder(16,[96],96,X).
X = [16, 17, 18, 19, 20, 21, 36, 51, 66, 81, 96] 

?- ['Mazedata4.pl'].
true.

?- shortestPathFinder(42,[63],63,X).
X = [42, 43, 63] 

?- ['Mazedata5.pl'].
true.

?- shortestPathFinder(6,[12],12,X).
X = [6, 7, 12] 
----------------------------------------------
*/

:- dynamic(faultynode/1).

faultyNodeRemoval([A|Rest]):-
    faultynode(A),
    retract(faultynode(A)),
    !,
    faultyNodeRemoval(Rest).
faultyNodeRemoval([]).

faultyNodeAddition([A|Rest]):-
    assertz(faultynode(A)),
    faultyNodeAddition(Rest).

faultyNodeAddition([]).

beginningVertice([], []).
beginningVertice([[First|_]|Xs], [First|Ys]) :- beginningVertice(Xs, Ys).

makechild(N0, P0, N, P,V):-
  mazelink(N0, N),
  \+ member(N,V),
  append(P0, [N], P).

breadthFirstSearch(Destinations, [[Target,P]|_], Target, _,P):- member(Target, Destinations).
breadthFirstSearch(Destinations, [[N,P]|Tail], Target, V, Path):-
  (setof([Child,Pp], makechild(N, P, Child, Pp,V), Nodes);true),
  beginningVertice(Nodes,Temp),
  append(V,Temp,V2),
  append(Tail, Nodes, NextQueue),
  breadthFirstSearch(Destinations, NextQueue, Target, V2, Path).

% Picks only the shortest path to the closest destination.
shortestPathFinder(A,Destinations, Target,P):- breadthFirstSearch(Destinations, [[A,[A]]], Target,[A],P).