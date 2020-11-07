(*Jan Wielgus*)
(**)


(*Zadanie 2*)
let curry3 f x y z = f (x, y, z);;

let add3 (x, y, z) = x + y + z;;

add3 (1, 2, 3) = 6;;
curry3 add3 1 2 3 = 6;;


let uncurry3 f (x, y, z) = f x y z;;

let mult3 x y z = x * y * z;;

mult3 1 2 3 = 6;;
uncurry3 mult3 (1, 2, 3) = 6;;




(*Zadanie 3*)
let sumProd xs =
    List.fold_left (fun x y -> (fst x + y, snd x * y)) (0, 1) xs
    ;;
(* x - para/ acc    y - nowa wartosc z listy*)

sumProd [3; 4; 5] = (12, 60);;
sumProd [] = (0, 1);;
sumProd [5] = (5, 5);;
sumProd [1; 1; 1] = (3, 1);;





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

let listsLenOrder = (fun l1 l2 -> List.length l1 < List.length l2);;
insertionSort listsLenOrder [[1; 2; 3]; [0]; [3; 2; 1]; [4; 5]; [7; 8; 9]] = [[0]; [4; 5]; [1; 2; 3]; [3; 2; 1]; [7; 8; 9]];;



(*Zadanie 5b*)
let rec mergesort pred xs =
    let xsLen = List.length xs in

    let rec splitList fstHalfSize ys =
        if fstHalfSize <= 0 then ([], ys)
        else
            let temp = splitList (fstHalfSize - 1) (List.tl ys) in
            ((List.hd ys) :: fst temp, snd temp)
        in


    let rec mergeLists l1 l2 =
        match (l1, l2) with
        | ([], []) -> []
        | ([], _) -> l2
        | (_, []) -> l1
        | (hd1::tl1, hd2::tl2) -> if pred hd1 hd2 then hd1 :: mergeLists tl1 l2
                                    else hd2 :: mergeLists l1 tl2
        in
    
    match xs with
    | [] -> []
    | [elem] -> [elem]
    | _ ->
        let split = splitList (xsLen / 2) xs in
        mergeLists (mergesort pred (fst split)) (mergesort pred (snd split))
    ;;


mergesort (fun a b -> a < b) [3; 1; 5; 2; 4] = [1; 2; 3; 4; 5];;
mergesort (fun a b -> a < b) [4; 4; 8; 1; -1; 4; -1] = [-1; -1; 1; 4; 4; 4; 8];;
mergesort (fun a b -> a > b) [5; 2; -5; 0; 5; 2; -10] = [5; 5; 2; 2; 0; -5; -10];;
mergesort (fun a b -> a < b) [] = [];;
mergesort (fun a b -> a < b) [3] = [3];;


let testPred = fun a b -> a < b;;
mergesort testPred [2; 1] = [1; 2];;
mergesort testPred [3; 2; 1] = [1; 2; 3];;
mergesort testPred [3; 1; 2] = [1; 2; 3];;
mergesort testPred [1; 3; 2] = [1; 2; 3];;
mergesort testPred [4; 3; 2; 1] = [1; 2; 3; 4];;

let listsLenOrder = (fun l1 l2 -> List.length l1 <= List.length l2);;
mergesort listsLenOrder [[1; 2; 3]; [0]; [3; 2; 1]; [4; 5]; [7; 8; 9]] = [[0]; [4; 5]; [1; 2; 3]; [3; 2; 1]; [7; 8; 9]];;


