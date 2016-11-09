percorre :: Int->[Int]->Bool
percorre _ [] = False
percorre y (x:xs)
 | y == x = True
 | otherwise = percorre y xs

distintos :: [Int]->Bool
distintos [] = True
distintos(x:xs)
 | percorre x xs == True = False
 | otherwise = distintos xs