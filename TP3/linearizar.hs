linearizar :: [[Int]]->[Int]
linearizar [[]] = []
linearizar ([]:xs) = linearizar xs
linearizar ((x:xs):ys) = x:linearizar (xs:ys)