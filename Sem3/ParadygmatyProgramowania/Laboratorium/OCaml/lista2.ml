(*Jan Wielgus*)


(*zadanie 1*)
let rec take x a =
    match a with
    | [] -> []
    | head::tail -> if x >= 1 then head :: take (x-1) tail
        else []
    ;;

take 2 [1;2;3;5;6] = [1;2];;
take (-2) [1;2;3;5;6] = [];;
take 8 [1;2;3;5;6] = [1;2;3;5;6];;
take 5 [] = [];;
take (-5) [] = [];;
take 3 [1; 2; 3] = [1; 2; 3];;
take 0 [4; 5] = [];;




(*zadanie 2*)
let rec drop x a =
    match a with
    | [] -> []
    | head::tail -> if x <= 0 then a else drop (x-1) tail
    ;;

drop 2 [1;2;3;5;6] = [3;5;6];;
drop (-2) [1;2;3;5;6] = [1;2;3;5;6];;
drop 8 [1;2;3;5;6] = [];;
drop 3 [1; 2; 3] = [];;
drop 0 [5; 6; 7; 8] = [5; 6; 7; 8];;
