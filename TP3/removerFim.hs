formataLista :: [Int] -> [Int]
formataLista [] = []
formataLista [x] = []
formataLista (x:xs) = x : formataLista xs

removerFim :: Int->[Int]->[Int]
removerFim _ [] = []
removerFim 0 xs = xs
removerFim x xs = removerFim (x-1) (formataLista xs)