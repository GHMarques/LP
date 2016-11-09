nelementos([],0).
nelementos([_|L], C) :- nelementos(L, AUX), C is AUX+1.

soma([],0).
soma([X|L], S) :- soma(L, AUX), S is X+AUX.

medio([], 0).
medio(L, M) :- soma(L, S), nelementos(L, N), M is S / N.