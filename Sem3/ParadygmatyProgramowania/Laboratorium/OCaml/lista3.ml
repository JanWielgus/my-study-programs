(*Jan Wielgus*)
(**)




(*zadanie 2a*)
let rec czyIstnieje pred xs =
    match xs with
    | [] -> false
    | head::tail -> pred head || czyIstnieje pred tail
    ;;

czyIstnieje (fun x -> x=2) [1;2;3;5] = true;;
czyIstnieje (fun x -> x > 5) [1; 2; 3; 4] = false;;
czyIstnieje (fun x -> x < 5) [1; 2; 3; 4] = true;;
czyIstnieje (fun x -> x = 3) [3] = true;;
czyIstnieje (fun x -> x <> 3) [3] = false;;
czyIstnieje (fun x -> x > 0) [] = false;;
czyIstnieje (fun s -> String.length s > 3) ["ok"; "bez"; "rutna"] = true;;




(*zadanie 2b*)
let czyIstnieje2 pred xs =
    List.fold_left (fun x y -> x || pred y) false xs
    ;;

czyIstnieje2 (fun x -> x=2) [1;2;3;5] = true;;
czyIstnieje2 (fun x -> x > 5) [1; 2; 3; 4] = false;;
czyIstnieje2 (fun x -> x < 5) [1; 2; 3; 4] = true;;
czyIstnieje2 (fun x -> x = 3) [3] = true;;
czyIstnieje2 (fun x -> x <> 3) [3] = false;;
czyIstnieje2 (fun x -> x > 0) [] = false;;
czyIstnieje2 (fun s -> String.length s > 3) ["ok"; "bez"; "rutna"] = true;;




(*zadanie 2c*)
let rec czyIstnieje3 pred xs =
    List.fold_right (fun x y -> y || pred x) xs false
    ;;

czyIstnieje3 (fun x -> x=2) [1;2;3;5] = true;;
czyIstnieje3 (fun x -> x > 5) [1; 2; 3; 4] = false;;
czyIstnieje3 (fun x -> x < 5) [1; 2; 3; 4] = true;;
czyIstnieje3 (fun x -> x = 3) [3] = true;;
czyIstnieje3 (fun x -> x <> 3) [3] = false;;
czyIstnieje3 (fun x -> x > 0) [] = false;;
czyIstnieje3 (fun s -> String.length s > 3) ["ok"; "bez"; "rutna"] = true;;




(*zadanie 3*)
let filter pred xs =
    List.fold_right (fun x ys -> if pred x then x::ys else ys) xs []
    ;;

filter (fun x -> x = 2) [1; 2; 3; 4; 5; 4; 3; 2; 1] = [2; 2];;
filter (fun x -> x > 2) [1; 2; 3; 4; 5; 4; 3; 2; 1] = [3; 4; 5; 4; 3];;
filter (fun x -> x > 5) [8; 1; 3] = [8];;
filter (fun x -> x < 0) [] = [];;
filter (fun x -> x > 0  &&  x < 3) [-1; 0; 1; 2; 3; 4] = [1; 2];;





(*zadanie 4a*)
let rec usun1 pred xs =
    match xs with
    | [] -> []
    | head::tail -> if pred head then tail
                    else head :: usun1 pred tail
    ;;

usun1 (fun x -> x=2) [1;2;3;2;5] = [1;3;2;5];;
usun1 (fun x -> x = 2) [] = [];;
usun1 (fun x -> x > 4) [8; 8; 9; 10] = [8; 9; 10];;
usun1 (fun x -> x < 3) [5; 6; 7; 8] = [5; 6; 7; 8];;





(*zadanie 4b*)
let usun1_2 pred xs =
    let rec usun1_2tail checked ys =
        match ys with
        | [] -> List.rev checked
        | head::tail -> if pred head then List.rev_append checked tail
                        else usun1_2tail (head::checked) tail
        in
    
    usun1_2tail [] xs
    ;;

usun1_2 (fun x -> x = 2) [1; 2; 3; 2; 5] = [1; 3; 2; 5];;
usun1_2 (fun x -> x = 2) [] = [];;
usun1_2 (fun x -> x > 4) [8; 8; 9; 10] = [8; 9; 10];;
usun1_2 (fun x -> x < 3) [5; 6; 7; 8] = [5; 6; 7; 8];;
