(*Jan Wielgus*)


(*zadanie 1*)
let pairSquare1 (a, b) =
    (a*a, b*b)
    ;;

pairSquare1 (3, 4) = (9, 16);;

let testFun2 (a, b) =
    let sum = a +. b in
    if sum = a then true else false
    ;;

testFun2 (5., 0.) = true;;
testFun2 (9., 10.) = false;;

let rec testFun3 (lst, n) = 
    match lst with
    | [] -> []
    | head :: tail -> 
        if n = 0 then head :: tail
        else head :: testFun3 (lst, (n-1))
    ;;



testFun3 ([1; 2; 3; 4], 4);;
testFun3 ([5], 3);;



(*zadanie 2*)
let rec suma a = 
    match a with
    | [] -> 0.
    | [elem] -> elem
    | head :: tail -> head +. suma tail
    ;;

suma [1.; 2.; 3.; 4.; 5.] = 15.;;
suma [] = 0.;;
suma [-1.; 2.; 3.] = 4.;;
suma [5.6] = 5.6;;



(*zadanie 3*)
let rec ends a =
    match a with
    | [] -> raise (Invalid_argument "List cannot be empty");
    | [elem] -> (elem, elem)
    | head :: tail -> (head, snd (ends tail))
    ;;

ends [1; 3; 5; 6; 9] = (1, 9);;
ends [1] = (1, 1);;
ends [];;
