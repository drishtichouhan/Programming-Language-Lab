----Following are the embedded test cases------
{-

Test #1 : String of comma-seperated integers:
"1,2,3,4,5,6,7,8,9,10"


List of integers: 
[1,2,3,4,5,6,7,8,9,10]


The lcm of the numbers is: 
2520


Inorder Traversal of the tree: 
[1,2,3,4,5,6,7,8,9,10]


Preorder Traversal of the tree: 
[1,2,3,4,5,6,7,8,9,10]


Postorder Traversal of the tree: 
[10,9,8,7,6,5,4,3,2,1]


Test #2 : String of comma-seperated integers:
"121"


List of integers: 
[121]


The lcm of the numbers is: 
121


Inorder Traversal of the tree: 
[121]


Preorder Traversal of the tree: 
[121]


Postorder Traversal of the tree: 
[121]


Test #3 : String of comma-seperated integers:
"3,6,1,23,56,87,2,12"


List of integers: 
[3,6,1,23,56,87,2,12]


The lcm of the numbers is: 
112056


Inorder Traversal of the tree: 
[1,2,3,6,12,23,56,87]


Preorder Traversal of the tree: 
[3,1,2,6,23,12,56,87]


Postorder Traversal of the tree: 
[2,1,12,87,56,23,6,3]


Test #4 : String of comma-seperated integers:
"9,23,14,65,76,12,23"


List of integers: 
[9,23,14,65,76,12,23]


The lcm of the numbers is: 
7158060


Inorder Traversal of the tree: 
[9,12,14,23,65,76]


Preorder Traversal of the tree: 
[9,23,14,12,65,76]


Postorder Traversal of the tree: 
[12,14,76,65,23,9]


Test #5 : String of comma-seperated integers:
"10,9,8,7"


List of integers: 
[10,9,8,7]


The lcm of the numbers is: 
2520


Inorder Traversal of the tree: 
[7,8,9,10]


Preorder Traversal of the tree: 
[10,9,8,7]


Postorder Traversal of the tree: 
[7,8,9,10]
--}

import qualified Data.List

stringListToIntList :: [String] -> [Int]
stringListToIntList = map read 

stringToList     :: (Char -> Bool) -> String -> [String]
stringToList p s =  case dropWhile p s of
                      "" -> []
                      s' -> w : stringToList p s''
                            where (w, s'') = break p s'

lcm_calculator :: Integral a => [a] -> a
lcm_calculator [] = 1
lcm_calculator (x:xs) = lcm x (lcm_calculator xs)

--Tree data structure--
data Tree x = Nil | Node (Tree x) x (Tree x) deriving Show

--A function to test if the tree is empty--
isEmpty :: (Ord a) => Tree a -> Bool
isEmpty Nil = True
isEmpty  _  = False

--inserting nodes into the tree--
insertFunc :: (Ord a) => Tree a -> a -> Tree a
insertFunc Nil x = Node Nil x Nil
insertFunc (Node tree1 v tree2) x 
 | v == x = Node tree1 v tree2
 | v  < x = Node tree1 v (insertFunc tree2 x)
 | v  > x = Node (insertFunc tree1 x) v tree2

--A function to create a tree from a given list of numbers--
createTree :: (Ord a) => [a] -> Tree a
createTree [] = Nil
createTree (h:t) = createTree2 (Node Nil h Nil) t
 where
  createTree2 tr [] = tr
  createTree2 tr (h:t) = createTree2 (insertFunc tr h) t

--printing postorder traversal--
postOrderTraversal :: (Ord a) => Tree a -> [a]
postOrderTraversal Nil = []
postOrderTraversal (Node tree1 v tree2) = postOrderTraversal tree1 ++ postOrderTraversal tree2 ++ [v]

--printing inorder traversal--
inOrderTraversal :: (Ord a) => Tree a -> [a]
inOrderTraversal Nil = []
inOrderTraversal (Node tree1 v tree2) = inOrderTraversal tree1 ++ [v] ++ inOrderTraversal tree2

--printing preorder traversal--
preOrderTraversal :: (Ord a) => Tree a -> [a]
preOrderTraversal Nil = []
preOrderTraversal (Node tree1 v tree2) = [v] ++ preOrderTraversal tree1 ++ preOrderTraversal tree2

main = do
    
    putStrLn "Please enter a list of comma-seperated numbers:"
    line <- getLine
    let l = stringToList (==',') line
    putStrLn("\n")
    putStrLn "List of integers: "
    let intList = stringListToIntList l
    print $ intList
    putStrLn("\n")
    putStrLn "The lcm of the numbers is: "
    print $ lcm_calculator intList
    putStrLn("\n")
    let t = createTree intList
    putStrLn "Inorder Traversal of the tree: "
    print $ inOrderTraversal t
    putStrLn("\n")
    putStrLn "Preorder Traversal of the tree: "
    print $ preOrderTraversal t
    putStrLn("\n")
    putStrLn "Postorder Traversal of the tree: "
    print $postOrderTraversal t
    putStrLn("\n")

    putStrLn "-----------The program ends here-----------"
    putStrLn("\n")
    