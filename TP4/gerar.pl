gerar(X, Y, X).
gerar(X, Y, L) :- AUX is X+1, AUX =< Y, gerar(AUX, Y, L).