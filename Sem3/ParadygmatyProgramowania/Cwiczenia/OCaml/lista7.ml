(*Jan Wielgus*)
(**)


module type QUEUE_FUN =
sig
    type 'a t
    exception Empty of string
    val empty: unit -> 'a t
    val enqueue: 'a * 'a t -> 'a t
    val dequeue: 'a t -> 'a t
    val first: 'a t -> 'a
    val isEmpty: 'a t -> bool
end;;




(* zadanie 1 a *)
module OneListQueue : QUEUE_FUN =
struct
    type 'a t = 'a list
    exception Empty of string

    let empty() = []
    
    let enqueue(e, q) = q @ [e]

    let dequeue(q) =
        match q with
        | [] -> []
        | _::qt -> qt

    let first(q) =
        match q with
        | [] -> raise (Empty "module OneListQueue: first")
        | head::_ -> head

    let isEmpty(q) = q=[]
end;;

let testQueue1 = OneListQueue.(enqueue(0, enqueue(1, enqueue(2, empty()))));;
OneListQueue.first(testQueue1) = 2;;
(*OneListQueue.(first(empty()));;*) (* exception*);;
OneListQueue.(first(dequeue(enqueue(5, testQueue1)))) = 1;;
OneListQueue.(isEmpty(empty())) = true;;
OneListQueue.(isEmpty(testQueue1)) = false;;
OneListQueue.(isEmpty(enqueue(12, empty()))) = false;;




(* zadanie 1 b *)
module TwoListsQueue : QUEUE_FUN =
struct
    type 'a t = 'a list * 'a list
    exception Empty of string

    let makeNormal(x, y) =
        if x=[] then (List.rev y, [])
        else (x, y)

    let empty() = ([], [])

    let enqueue(e, (x, y)) =
        makeNormal(x, e::y)

    let dequeue(x, y) =
        match x with
        | [] -> (List.rev y, [])
        | _::xt -> makeNormal(xt, y)

    let first(x, y) =
        match x with
        | [] -> raise (Empty "module TwoListsQueue: first")
        | xh::_ -> xh

    let isEmpty(x, y) = x=[]
end;;


let testQueue1 = TwoListsQueue.(enqueue(0, enqueue(1, enqueue(2, empty()))));;
TwoListsQueue.first(testQueue1) = 2;;
(*TwoListsQueue.(first(empty()));;*) (* exception*);;
TwoListsQueue.(first(dequeue(enqueue(5, testQueue1)))) = 1;;
TwoListsQueue.(isEmpty(empty())) = true;;
TwoListsQueue.(isEmpty(testQueue1)) = false;;
TwoListsQueue.(isEmpty(enqueue(12, empty()))) = false;;


