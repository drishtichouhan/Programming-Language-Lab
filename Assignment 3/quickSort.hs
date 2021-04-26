--recursive quicksort function to sort list of numbers
quicksort_function :: (Ord num) => [num] -> [num]  
quicksort_function [] = []  
quicksort_function (x:y) =   
    let sorted_head = quicksort_function [num | num <- y, num <= x]  
        sorted_tail = quicksort_function [num | num <- y, num > x]  
    in  sorted_head ++ [x] ++ sorted_tail

--main function that runs 10 test cases 
main = do
    putStrLn("1.The unsorted sequence is:")
    print([9,8,7,6,1,2,3,5,4])
    putStrLn("The sorted sequence is:")
    print( quicksort_function [9,8,7,6,1,2,3,5,4])
    putStrLn("2.The unsorted sequence is:")
    print([5000,4000,1000,2000,3000])
    putStrLn("The sorted sequence is:")
    print(quicksort_function[5000,4000,1000,2000,3000])
    putStrLn("3.The unsorted sequence is:")
    print([5,8,99,0,0,1])
    putStrLn("The sorted sequence is:")
    print( quicksort_function [5,8,99,0,0,1])
    putStrLn("4.The unsorted sequence is:")
    print([11,56,78,990,2345,1236,12,1,98,84,33])
    putStrLn("The sorted sequence is:")
    print(quicksort_function[11,56,78,990,2345,1236,12,1,98,84,33])
    putStrLn("5.The unsorted sequence is:")
    print([17,35,98,44,26])
    putStrLn("The sorted sequence is:")
    print( quicksort_function [17,35,98,44,26])
    putStrLn("6.The unsorted sequence is:")
    print([3,2,1,0])
    putStrLn("The sorted sequence is:")
    print(quicksort_function[3,2,1,0])
    putStrLn("7.The unsorted sequence is:")
    print([80,19,89,63,46,89,96])
    putStrLn("The sorted sequence is:")
    print(quicksort_function[80,19,89,63,46,89,96])
    putStrLn("8.The unsorted sequence is:")
    print([9.79,0.4,5.33])
    putStrLn("The sorted sequence is:")
    print(quicksort_function[9.79,0.4,5.33])
    putStrLn("9.The unsorted sequence is:")
    print([1.2,3.4,4.09])
    putStrLn("The sorted sequence is:")
    print( quicksort_function [1.2,3.4,4.09])
