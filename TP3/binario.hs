binario :: Int->[Int]
x = []
binario n = 
			if ((n > 0) && (n `mod` 2 == 1))
			then x <- 1:[] 
			else  x <- 0:[]
			binario (n-1)