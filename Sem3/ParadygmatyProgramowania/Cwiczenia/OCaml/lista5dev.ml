(*Jan Wielgus*)
(**)

type 'a llist = LNil | LCons of 'a * 'a llist Lazy.t;;

let rec lfrom k = LCons (k, lazy(lfrom (k+1)));;

let rec ltake = function
    | (0, _) -> []
    | (_, LNil) -> []
    | (n, LCons(x, lazy xs)) -> x::ltake(n-1, xs)
    ;;


(*zadanie 1*)
let lrepeat k xs =
    let rec lrepeatRec n ys =
        match ys with
        | LNil -> LNil
        | LCons (head, lazy tail) -> if n > 0 then LCons (head, lazy(lrepeatRec (n-1) ys))
                                    else lrepeatRec k tail
    in

    lrepeatRec k xs
    ;;

let test = LCons(5, lazy(LCons(1, lazy(LNil))));;
ltake (10, lrepeat 3 test) = [5; 5; 5; 1; 1; 1];;
ltake (10, lrepeat 2 (lfrom 4)) = [4; 4; 5; 5; 6; 6; 7; 7; 8; 8];;
ltake (10, lrepeat 4 LNil) = [];;




(*zadanie 2*)
let lfib =
    let rec lfibRec (x2, x1) =
        LCons(x1+x2, lazy(lfibRec (x1, x1+x2)))
    in
    LCons(1, lazy(LCons(1, lazy(lfibRec (1, 1)))))
    ;;

ltake (10, lfib) = [1; 1; 2; 3; 5; 8; 13; 21; 34; 55];;


