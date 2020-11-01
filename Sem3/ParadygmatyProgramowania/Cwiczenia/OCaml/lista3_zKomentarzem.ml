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





(*Zadanie 5a*)
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
insertionSort (fun a b -> a < b) [4; 4; 8; 1; -1; 4; -1] = [-1; -1; 1; 4; 4; 4; 8];;
insertionSort (fun a b -> a > b) [5; 2; -5; 0; 5; 2; -10] = [5; 5; 2; 2; 0; -5; -10];;
insertionSort (fun a b -> a < b) [] = [];;
insertionSort (fun a b -> a < b) [3] = [3];;



(*Zadanie 5b*)
let rec mergesort pred xs =
    let xsLen = List.length xs in

    let rec splitListRev remain ys half1 half2 =
        match remain with
        | 0 -> (half1, half2)
        | _ -> if remain > xsLen/2
                    then splitListRev (remain - 1) (List.tl ys) (List.hd ys :: half1) half2
                else splitListRev (remain - 1) (List.tl ys) half1 (List.hd ys :: half2)
        in
    
    let splitList len ys =
        let split = splitListRev len ys [] [] in
        (List.rev(fst split), List.rev(snd split))
        in
    
    (*Merge lists and return reversed merged list*)
    let rec mergeListsRev l1 l2 acc =
        match (l1, l2) with
        | ([], []) -> acc
        | ([], _) -> mergeListsRev [] (List.tl l2) (List.hd l2 :: acc) (* If l1 is empty, add from l2*)
        | (_, []) -> mergeListsRev (List.tl l1) [] (List.hd l1 :: acc) (* If l2 is empty, add from l1*)
        | (hd1::tl1, hd2::tl2) -> if pred hd1 hd2 then mergeListsRev tl1 tl2 (hd2 :: hd1 :: acc)
                                    else mergeListsRev tl1 tl2 (hd1 :: hd2 :: acc)
        in
    
    let rec mergeLists l1 l2 =
        List.rev(mergeListsRev l1 l2 [])
        in
    
    match xs with
    | [] -> []
    | [elem] -> [elem]
    | _ ->
        let split = splitList xsLen xs in
        mergeLists (mergesort pred (fst split)) (mergesort pred (snd split))
    ;;


mergesort (fun a b -> a < b) [3; 1; 5; 2; 4];;
mergesort (fun a b -> a < b) [4; 4; 8; 1; -1; 4; -1];;
mergesort (fun a b -> a > b) [5; 2; -5; 0; 5; 2; -10];;
mergesort (fun a b -> a < b) [] = [];;
mergesort (fun a b -> a < b) [3] = [3];;


let testPred = fun a b -> a < b;;
mergesort testPred [2; 1] = [1; 2];;
mergesort testPred [3; 2; 1];;
mergesort testPred [3; 1; 2];;
mergesort testPred [1; 3; 2];;
mergesort testPred [4; 3; 2; 1];;


