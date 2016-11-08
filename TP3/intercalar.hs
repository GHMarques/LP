intercalar :: [Int]->[Int]->[Int]
intercalar [] b = b
intercalar a [] = a
intercalar (a:as) (b:bs)
	| (a<b) = [a] ++ (intercalar as (b:bs))
	| otherwise = [b] ++ (intercalar (a:as) bs) 
