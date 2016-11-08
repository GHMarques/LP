magic::[Int]->[Int]->[Int]
magic [] secondList@(_) = secondList
magic firstList@(_) [] = magic (tail firstList) ( [ head firstList ] )
magic firstList@(_) secondList@(_) = magic (tail firstList) ( secondList ++ [ (last secondList)+(head firstList) ] )

somaParciais::[Int]->[Int]
somaParciais (x:xs) = magic (x:xs) []