inserir([X|Y], Z, [X|W]) :- inserir(Y, Z, W).
inserir([], X, X).

rotacionar([X|Xs], L) :- inserir(Xs, [X], L).
rotacionar([],[]).