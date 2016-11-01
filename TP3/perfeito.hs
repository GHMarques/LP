obter_divisores :: Int -> [Int]
obter_divisores n = [ x | x <- [1 .. n-1], ((n `mod` x) == 0)]

somatorio :: [Int]->Int
somatorio [] = 0
somatorio (x:xs) =
	x + somatorio xs

perfeito :: Int->Bool
perfeito n = 
				if(somatorio (obter_divisores n)) == n
				then True
				else False