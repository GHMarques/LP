inserirfim(N,[],[N]).
inserirfim(N,[X | Xs], [X | Ys]) :- inserirfim(N,Xs,Ys).

% inserirfim( 5, [1,2,3,4], L )
% inserirfim( 5, [1,2,3,4], [1,2,3,4,5])
% inserirfim( 5, [2,3,4], [2,3,4,5])
% inserirfim( 5, [3,4], [3,4,5])
% inserirfim( 5, [4], [4,5]) 
% inserirfim( 5, [], [5]) base