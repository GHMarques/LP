ordenar([], []).
ordenar(L, R):- quick(L, [], R).

quick([], X, X).
quick([X|Xs], Y, R):-
	pivo(X, Xs, E, D),
	quick(E, Y, R1), quick(D,[X|R1],R).
   
pivo(_, [], [], []).
pivo(P, [X|Xs], [X|Xs2], Y):- X >= P, pivo(P, Xs,Xs2, Y).
pivo(P, [X|Xs], Y, [X|Xs2]):- X < P, pivo(P, Xs, Y, Xs2).