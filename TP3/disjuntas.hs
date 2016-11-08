verifica :: Int->[Int]->Bool
verifica w all@(y:ys) = w `elem` all

disjuntas :: [Int]->[Int]->Bool
disjuntas [] b@(_) = True
disjuntas (x:xs) [] = False
disjuntas (x:xs) b@(_) = 
	if(verifica x b == True)
		then False
		else
			disjuntas xs b