gerar(X,X,L) :- !, L = [X].
gerar(Y,X,[Y|Ys]) :- Y =< X, AUX is Y+1, gerar(AUX, X, Ys).