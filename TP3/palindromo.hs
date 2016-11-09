inverte :: [Int]->[Int]  
inverte (x:xs) = inverte (xs) ++ [x]  
inverte [] = []

palindromo :: [Int]->Bool
palindromo(x:xs) 
 | (x:xs) == inverte (x:xs) = True
 | otherwise = False