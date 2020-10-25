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

