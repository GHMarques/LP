verifica([], U, U).
verifica([X|L], _, U) :- verifica(L, X, U).

ultimo([],[]).
ultimo([X|L], U) :- verifica(L, X, U).