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
    if lst = [] then [] 
    else 
        if n = 0 then lst
        else List.hd lst :: testFun3 (lst, (n-1))
    ;;

testFun3 ([1; 2; 3; 4], 4);;
testFun3 ([5], 3);;
testFun3 ([], 3);;



(*zadanie 2*)
let rec suma a =
    if a = [] then 0.
    else if List.tl a = [] then List.hd a
    else List.hd a +. suma (List.tl a)
    ;;

suma [1.; 2.; 3.; 4.; 5.] = 15.;;
suma [] = 0.;;
suma [-1.; 2.; 3.] = 4.;;
suma [5.6] = 5.6;;



(*zadanie 3*)

let rec ends a =
    if a = [] then raise (Failure "empty list")
    else if List.tl a = [] then (List.hd a, List.hd a)
    else (List.hd a, snd (ends (List.tl a)))
    ;;

ends [1; 3; 5; 6; 9] = (1, 9);;
ends [1] = (1, 1);;
(*ends [];;  ---- throws exception*)
