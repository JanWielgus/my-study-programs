(*Jan Wielgus*)


(*zadanie 1*)
let rec last a =
    if a = [] then raise (Failure "last of empty list")
    else if List.tl a = [] then List.hd a
    else last (List.tl a)
    ;;

