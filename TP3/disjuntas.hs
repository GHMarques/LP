verifica :: Int->[Int]->Bool
verifica w [] = False
verifica w (a:as)
	| w == a = True
	| otherwise = verifica w as
--verifica w all@(y:ys) = w `elem` all

disjuntas :: [Int]->[Int]->Bool
disjuntas [] _ = True
disjuntas (x:xs) y = 
	if(verifica x y == True)
		then False
		else
			disjuntas xs y
