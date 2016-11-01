somaImpares :: [Int]->Int
somaImpares [] = 0
somaImpares (x:xs) =
	if x `mod` 2 /= 0
	then x + somaImpares xs
	else somaImpares xs
