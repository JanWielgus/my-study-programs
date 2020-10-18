(*Jan Wielgus*)


(*zadanie 2 a*)
let rec fib a =
    if a < 0 then raise (Failure "Less than zero")
    else if a = 0 then 0
    else if a = 1 then 1
    else (fib (a - 2)) + (fib (a - 1))
    ;;

fib 10 = 55;;
fib 0 = 0;;
fib 1 = 1;;
fib 2 = 1;;
(*fib -5;; throw exception *)


(*zadanie 2 b*)
let rec fibTail n =
    if n < 0 then raise (Failure "Less than zero")
    else 
        let rec fibIter (n, fib1, fib2) = 
            if n <= 0 then fib1
            else fibIter(n - 1, fib2, fib1 + fib2) in
        fibIter(n, 0, 1)
    ;;
      
fibTail 42 = 267914296;;
fibTail 10 = 55;;
fibTail 0 = 0;;
fibTail 1 = 1;;
fibTail 2 = 1;;
(*fibTail -5 throw exception *)



(*zadanie 3*)
let root3 a =
    let x = if a > 1. then a /. 3. else a in
    let rec root3R(a, x, eps) =
        let l = if (x ** 3. -. a) < 0. then (a -. x ** 3.) else (x ** 3. -. a)
        and p = if eps *. a < 0. then -. eps *. a else eps *. a in
            if l <= p then x
            else root3R(a, x +. (a /. x /. x -. x) /. 3., eps) in
    root3R(a, x, 10. ** (-15.))
    ;;

root3 8. = 2.;;
root3 (-8.) = -2.;;
root3 1. = 1.;;
root3 0. = 0.;;





(*zadanie 4*)
let [_; _; x; _; _] = [-2; -1; 0; 1; 2];;
x = 0;;

let [(_, _); (x, _)] = [(1,2); (0,1)];;
x = 0;;



(*zadanie 5*)
let rec initSegment (a, b) =
    if a = b then true
    else if a = [] then true
    else if b = [] then false
    else if List.hd a = (List.hd b)
        then initSegment(List.tl a, List.tl b)
    else false
    ;;

initSegment([1; 2; 3], [1; 2; 3; 4; 5]) = true;;
initSegment([1; 2; 4], [1; 2; 3; 4; 5]) = false;;
initSegment([1], [1; 2; 3; 4; 5]) = true;;
initSegment([5], [1; 2; 3; 4; 5]) = false;;
initSegment([], [3;4;2;1]) = true;;
initSegment([], []) = true;;



(*zadanie 6 a*)
let rec replaceNth (a, pos, x) =
    if pos < 0 then raise (Failure "Position cannot be less than zero")
    else if pos >= List.length a then raise (Failure "Position is out of bounds")
    else match (a, pos, x) with
        | ([], _, _) -> raise (Failure "List cannot be empyt")
        | ([elem], 0, x) -> [x]
        | (head::tail, 0, x) -> x :: tail
        | (head::tail, pos, x) -> head :: replaceNth (tail, pos - 1, x)
    ;;

replaceNth([1; 2; 3; 4], 1, 22) = [1; 22; 3; 4];;
replaceNth([1; 2; 3; 4], 3, 44) = [1; 2; 3; 44];;
replaceNth(['a'], 0, 'b') = ['b'];;
(* replaceNth([], 0, 'b')  throw exception "Position is out of bounds" *)
(* replaceNth([5], -5, 8)  throw exception "Position cannot be less than zero" *)




