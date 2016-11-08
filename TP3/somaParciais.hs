calda :: [Int]->Int
calda (x:xs)
	| xs == [] = x
	| otherwise = calda xs


magic::[Int]->[Int]->[Int]
magic [] b = b --retorno esperado apos recursao
magic (a:as) [] = magic (as) [a] --caso de entrada
magic (c:cs) d = magic (cs) ( d ++ [ (calda d)+(c) ] )

somaParciais::[Int]->[Int]
somaParciais (x:xs) = magic (x:xs) []
