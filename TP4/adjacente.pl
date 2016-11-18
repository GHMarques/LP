adjacente(A,B,[ A,B |_]).	%base
adjacente(A,B,[ B,A |_]).	%base
adjacente(A,B,[X|Xs]) :- adjacente(A,B,Xs).