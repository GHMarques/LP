shift :: Int->[Int]->[Int]
shift x (a:as)
	| x/=0 =  shift (x-1) (as ++ [a])
	| x==0 = (a:as)
