incrementar([], []).
incrementar([X|L1] , [Y|L2] ) :- adicionar(X,Y) , incrementar(L1, L2).

adicionar(X,Y) :- !, Y is X+1.
adicionar(X,X) .