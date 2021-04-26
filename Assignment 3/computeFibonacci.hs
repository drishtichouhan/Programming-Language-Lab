
--function that captures fibonacci numbers from the infinite list(fibonacci_seq)
fibonacci num = fibonacci_seq!!num

--function that generates list of fibonacci numbers in O(n)
fibonacci_seq = 0 : 1 : zipWith (+) fibonacci_seq (tail fibonacci_seq)

--main function that runs 10 test cases 
main =  do
    putStrLn("The 1 fibonacci number is:")
    print(fibonacci 1)
    putStrLn("The 2 fibonacci number is:")
    print(fibonacci 2)
    putStrLn("The 3 fibonacci number is:")
    print(fibonacci 3)
    putStrLn("The 4 fibonacci number is:")
    print(fibonacci 4)
    putStrLn("The 5 fibonacci number is:")
    print(fibonacci 5)
    putStrLn("The 10 fibonacci number is:")
    print(fibonacci 10)
    putStrLn("The 50 fibonacci number is:")
    print(fibonacci 50)
    putStrLn("The 100 fibonacci number is:")
    print(fibonacci 100)
    putStrLn("The 120 fibonacci number is:")
    print(fibonacci 120)
    putStrLn("The 150 fibonacci number is:")
    print(fibonacci 150)
    