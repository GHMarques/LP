nelementos([],0).
nelementos([X|L], C) :- nelementos(L, AUX), C is AUX+1.