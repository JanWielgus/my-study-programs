(*Jan Wielgus*)
(**)


(*Zadanie 2 - poki co nie oddawac*)
let curry3 f x y z = f (x, y, z);;

let add3 (x, y, z) = x + y + z;;

add3 (1, 2, 3);;
curry3 add3 1 2 3;;




(*Zadanie 3*)
let sumProd xs =
    match xs with
    | [] -> (0, 1)
    | _ -> List.fold_left (fun x y -> (fst x + y, snd x * y)) (0, 1) xs
    ;;
(* x - para, acc    y - nowa wartosc z listy*)

sumProd [3; 4; 5] = (12, 60);;
(*wincyj testow*)


(*Zadanie 3 z podwojnym fold_left (czyli wersja NIE do oddania*)
let sumProd xs =
    match xs with
    | [] -> (0, 1)
    | _ -> (List.fold_left (fun x y -> x+y) 0 xs,
            List.fold_left (fun x y -> x*y) 1 xs)
    ;;





(*Zadanie 5*)
(*If pred == true then put fst before snd*)
let insertionSort pred xs =
    let rec sortedInsert ys a =
        match ys with
        | [] -> [a]
        | head::tail -> if pred a head then a :: ys
                        else head :: sortedInsert tail a
        in
    
    let rec insertionSortRec toSort sorted =
        match toSort with
        | [] -> sorted
        | head::tail -> insertionSortRec tail (sortedInsert sorted head)
        in

    match xs with
    | [] -> []
    | [elem] -> [elem]
    | head::tail -> insertionSortRec xs []
    ;;

insertionSort (fun a b -> a < b) [3; 1; 5; 2; 4] = [1; 2; 3; 4; 5];;
insertionSort (fun a b -> a > b) [5; 2; -5; 0; 5; 2; -10] = [5; 5; 2; 2; 0; -5; -10];;
insertionSort (fun a b -> a < b) [] = [];;
insertionSort (fun a b -> a < b) [3] = [3];;




