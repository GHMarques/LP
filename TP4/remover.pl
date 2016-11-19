concatenar([], L, L).
concatenar([X|L1], L2, [X|L3]) :- concatenar(L1, L2, L3).

compara(X,X).

remover(N,[],[]).
remover(N,[X|Xs],L):- compara(N,X) -> remover(N,Xs,L) ; concatenar([X],Y,L), remover(N,Xs,Y).