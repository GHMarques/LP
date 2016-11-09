nelementos([],0).
nelementos([_|L], C) :- nelementos(L, AUX), C is AUX+1.