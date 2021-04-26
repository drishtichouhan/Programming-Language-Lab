testCases :-
    sqrtFunc(1100,Y0,0.001),
    format("Test Case #1 : The square root of 1100 is: ~w\n",[Y0]),
    sqrtFunc(36.25,Y1,0.001),
    format("Test Case #2 : The square root of 36.25 is: ~w\n",[Y1]),
    sqrtFunc(1.21,Y2,0.001),
    format("Test Case #3 : The square root of 1.21 is: ~w\n",[Y2]),
    sqrtFunc(645523,Y3,0.001),
    format("Test Case #4 : The square root of 645523 is: ~w\n",[Y3]),
    sqrtFunc(1600.04,Y4,0.001),
    format("Test Case #5 : The square root of 1600.04 is: ~w\n",[Y4]).

sqrtFunc(Number,Result,Accuracy) :-
    X is Number * 0.5,
    Y0 is (((Number/2)*(Number/2)) + Number)/(2*X),
    once(sqrtRecursive(Accuracy, Number, X, Y0, Result)).

sqrtRecursive(Accuracy, Number, X, Y0, Result) :-
    abs(X - Y0) > Accuracy,
    X0 is Y0,
    Y1 is (X0*X0 + Number)/(2*X0),
    sqrtRecursive(Accuracy, Number, X0, Y1, Result).

sqrtRecursive(Accuracy, _, X, Y0, Result) :-
    abs(X - Y0) < Accuracy,
	Result is Y0.



