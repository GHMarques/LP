{-
binario 20 -> 10  [0]
binario 10 -> 5   [0,0]
binario 5  -> 2   [1,0,0]
binario 2  -> 1   [0,1,0,0]
binario 1  -> 0   [1,0,1,0,0]
-}

magic :: Integer -> [Integer] -> [Integer]
magic a all@(_)
  | a == 0 = all
  | otherwise = (magic (truncate ( fromIntegral a / fromIntegral 2)) ([ceiling ( fromIntegral(a `mod` 2) )] ++ all))


binario :: Integer -> [Integer]
binario b
  | b == 0 = [0]
  | otherwise = magic b []


