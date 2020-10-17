(*Jan Wielgus*)


(*zadanie 1*)
let rec flatten a =
    if a = [] then []
    else List.hd a @ (flatten (List.tl a))
    ;;

flatten [[5; 6];[1; 2; 3]] = [5; 6; 1; 2; 3];;
flatten [[]; []] = [];;
flatten [[5; 6]; []] = [5; 6];;
flatten [[5]] = [5];;


(*zadanie 2*)
let rec count (a, lst) =
    if lst = [] then 0
    else
        (if (List.hd lst) = a then 1 else 0) + count (a, List.tl lst)
    ;;

count ('a', ['a'; 'l'; 'a']) = 2;;
count ('b', ['q'; 'w'; 'w'; 'a']) = 0;;
count ('w', ['q'; 'w'; 'w'; 'a']) = 2;;
count ('c', []) = 0;;



(*zadanie 3*)
let rec replicate (thing, amt) =
    if amt < 0 then raise (Failure "Argument less then zero")
    else if amt = 0 then []
    else if amt = 1 then [thing]
    else thing :: replicate (thing, (amt-1))
    ;;

(*replicate ('a', -5)*)
replicate ("ab", 0) = [];;
replicate (5, 1) = [5];;
replicate ('g', 2) = ['g'; 'g'];;
replicate ("la",3) = ["la"; "la"; "la"];;



(*zadanie 4*)
let rec sqrList a =
    if a = [] then []
    else ((List.hd a) * (List.hd a)) :: sqrList (List.tl a)
    ;;

sqrList [1;2;3;-4] = [1; 4; 9; 16];;
sqrList [] = [];;
sqrList [-10] = [100];;



(*zadanie 5*)
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



(*zadanie 6*)
let rec listLength a =
    if a = [] then 0
    else 1 + listLength (List.tl a)
    ;;

listLength [5] = 1;;
listLength ["asdf"] == 1;;
listLength [] = 0;;
listLength ['a'; 'b'; 'b'; 'd'] = 4;;