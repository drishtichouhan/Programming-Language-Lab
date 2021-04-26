--squareRootFuntion computes squareRoot by the Babylonian method 
squareRootFunction :: Int -> Float -> Float
squareRootFunction 0 s = 1
squareRootFunction num s = (x+s/x)/2 where x = squareRootFunction (num-1) s

--main function that runs 10 test cases 
main = do
    putStrLn("1. The square root of 23.56 is:")
    print(squareRootFunction 5 23.56)
    putStrLn("2. The square root of 106.78 is:")
    print(squareRootFunction 5 106.78)
    putStrLn("3. The square root of 3500.1234 is:")
    print(squareRootFunction 5 3500.1234)
    putStrLn("4. The square root of 987898.34562 is:")
    print(squareRootFunction 5 987898.34562)
    putStrLn("5. The square root of 64 is:")
    print(squareRootFunction 5 64)
    putStrLn("6. The square root of 670.76 is:")
    print(squareRootFunction 5 670.76)
    putStrLn("7. The square root of 91.09 is:")
    print(squareRootFunction 5 91.09)
    putStrLn("8. The square root of 6.25 is:")
    print(squareRootFunction 5 6.25)
    putStrLn("9. The square root of 25.00 is:")
    print(squareRootFunction 5 25.00)
    putStrLn("10. The square root of 1.00 is:")
    print(squareRootFunction 5 1.00)
       
