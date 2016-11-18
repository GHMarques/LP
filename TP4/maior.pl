maior([V],V).
maior([X|Xs],M):-maior(Xs,K),(X>K -> M=X;M=K).