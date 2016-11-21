contar(_, [], 0).
contar(X, [X|T], N) :- contar(X,T,NN), N is NN + 1.
contar(X, [H|T], N) :- contar(X,T,N), X \= H.

deletar(_, [], []).
deletar(X, [X|Xs], Y) :- deletar(X, Xs, Y).
deletar(X, [Y|Ys], [Y|R]) :- X \= Y, deletar(X, Ys, R).

agrupar(X, L, R, N) :- contar(X, L, N), deletar(X, L, R).

compactar([], []).
compactar([X|Xs], [[N,X]|Ys]) :- agrupar(X, [X|Xs], L, N), compactar(L, Ys).
