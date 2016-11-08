binario :: Int -> [Int]
binario 0 = [0]
binario 1 = [1]
binario b
  | (b `mod` 2 ) == 0 = binario (b `div` 2) ++ [0]
  | otherwise = binario (b `div` 2) ++ [1]
