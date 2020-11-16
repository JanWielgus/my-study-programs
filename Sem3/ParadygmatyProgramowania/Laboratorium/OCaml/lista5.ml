(*Jan Wielgus*)
(**)

type 'a llist = LNil | LCons of 'a * (unit -> 'a llist);;

let rec lfrom k = LCons (k, function () -> lfrom (k+1));;

let rec ltake (n, lxs) =
    match (n, lxs) with
    (0, _) -> []
    | (_, LNil) -> []
    | (n, LCons(x,xf)) -> x::ltake(n-1, xf())
    ;;


type 'a lBT = LEmpty | LNode of 'a * ('a lBT Lazy.t) * ('a lBT Lazy.t);;



(*zadanie 1*)
let lrepeat f lxs =
    let rec lrepeatRec n lys idx =
        match lys with
        | LNil -> LNil
        | LCons (head, tail) -> if n > 0 then LCons (head, (fun()->lrepeatRec (n-1) lys idx))
                                    else lrepeatRec (f (idx+1)) (tail()) (idx+1)
    in

    lrepeatRec (f 0) lxs 0
    ;;

ltake (11, lrepeat (fun i -> i+1) (lfrom 3)) = [3; 4; 4; 5; 5; 5; 6; 6; 6; 6; 7];;
let testLList = LCons(5, (fun()->LCons(2, (fun()->LCons(8, (fun()->LNil))))));;
ltake (20, lrepeat (fun i -> i+2) testLList) = [5; 5; 2; 2; 2; 8; 8; 8; 8];;
ltake (20, lrepeat (fun i -> i*3) LNil) = [];;






(*zadanie 3*)
let toLBST xs =
    let rec insertToBST elem bst =
        match bst with
        | LEmpty -> LNode(elem, lazy(LEmpty), lazy(LEmpty))
        | LNode(curElem, lazy n1, lazy n2) -> if elem < curElem then LNode(curElem, lazy(insertToBST elem n1), lazy(n2))
                                    else LNode(curElem, lazy(n1), lazy(insertToBST elem n2))
    in

    let rec toLBSTRec ys =
        match ys with
        | [] -> LEmpty
        | head::tail -> insertToBST head (toLBSTRec tail)
    in

    toLBSTRec (List.rev xs)
    ;;


let rec inorderLBST lbst =
    match lbst with
    | LEmpty -> []
    | LNode (elem, lazy n1, lazy n2) -> (inorderLBST n1) @ [elem] @ (inorderLBST n2)
    ;;


inorderLBST (toLBST [5; 2; 7; 1]) = [1; 2; 5; 7];;
inorderLBST (toLBST [10; 13; 14; 12; 8; 9; 1]) = [1; 8; 9; 10; 12; 13; 14];;
inorderLBST (toLBST []) = [];;
inorderLBST (toLBST [2; 10]) = [2; 10];;
inorderLBST (toLBST [5; 4; 3; 2; 1]) = [1; 2; 3; 4; 5];;

