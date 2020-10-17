1 + 4;;;;

let zmienna = 5;;
  let lista = [1;2;3;4]
  lista.length;;

let rec last a = 
  if a = [] then -1 else
  if List.tl a = [] then List.hd a else
  last (List.tl a);;

last [1;9;5; 7];;

last [];;


let rec last2 a =
  match a with
  | [] -> -1
  | [elem] -> List.hd a
  | hd :: tl -> last2 (List.tl a)
  ;;


  last2 [1;9;5; 7];;

  last2 [];;
  last2 [5];;





(*Cw, lista 1, zad 1
jeszcze do sprawdzenia przypadki brzegowe*)

let rec flatten a =
    match a with
    | [] -> []
    | [elem] -> elem
    | head :: tail -> head @ (flatten tail)
    ;;


flatten [[5;6];[1;2;3]];;
flatten [[]; []];;
flatten [[5; 6]; []];;
flatten [[5]];;


(*Cw, lista 1, zad 2
zobacz czy wszystkie przypadki brzegowe*)

let rec count (a, lst) =
    match lst with
    | [] -> 0
    | [elem] -> if elem = a then 1 else 0
    | head :: tail ->
        (if head = a then 1 else 0) + count (a, tail)
    ;;

count ('a', ['a'; 'l'; 'a']) = 2;;
count ('b', ['q'; 'w'; 'w'; 'a']) = 0;;
count ('w', ['q'; 'w'; 'w'; 'a']) = 2;;
count ('c', []) = 0;;



(*Cw, lista 1, zad 3
zobacz czy wszystkie przypadki brzegowe*)

let rec replicate (thing, amt) = 
    match amt with
    | amt when amt < 0 -> raise (Invalid_argument "argument less than zero")
    | 0 -> []
    | 1 -> [thing]
    | x -> thing :: replicate (thing, (amt-1))
    ;;

(*replicate ('a', -5)*)
replicate ("ab", 0) = [];;
replicate (5, 1) = [5];;
replicate ('g', 2) = ['g'; 'g'];;
replicate ("la",3) = ["la"; "la"; "la"];;



(*Cw, lista 1, zad 4
zobacz czy wszystkie przypadki brzegowe*)

let rec sqrList a = 
    match a with
    | [] -> []
    | [elem] -> [elem * elem]
    | head :: tail -> (head * head) :: sqrList tail
    ;;

sqrList [1;2;3;-4] = [1; 4; 9; 16];;
sqrList [] = [];;
sqrList [-10] = [100];;



(*Cw, lista 1, zad 5
zobacz czy wszystkie przypadki brzegowe*)

let palindrome a =
    if a = [] then true
    else a = List.rev a
    ;;

palindrome ['a'; 'l'; 'a'] = true;;
palindrome [] = true;;
palindrome [5; 7; 2; 0; 1; 0; 2; 7; 5] = true;;
palindrome ["as"; "as"] = true;;
palindrome ["ik"; "s"] = false;;
palindrome [7] = true;;
palindrome ['a'; 'l'; 'b'] = false;;




(*Cw, lista 1, zad 6
zobacz czy wszystkie przypadki brzegowe*)

let rec listLength a =
    match a with
    | [] -> 0
    | [_] -> 1
    | head :: tail -> 1 + listLength tail
    ;;

listLength [5] = 1;;
listLength ["asdf"] == 1;;
listLength [] = 0;;
listLength ['a'; 'b'; 'b'; 'd'] = 4;;
