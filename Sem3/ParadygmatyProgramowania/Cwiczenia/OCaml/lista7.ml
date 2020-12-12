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








(* zadanie 2 *)

module type QUEUE_MUT =
sig
    type 'a t
        (* The type of queues containing elements of type ['a]. *)
    exception Empty of string
        (* Raised when [first q] is applied to an empty queue [q]. *)
    exception Full of string
        (* Raised when [enqueue(x,q)] is applied to a full queue [q]. *)
    val empty: int -> 'a t
        (* [empty n] returns a new queue of length [n], initially empty. *)
    val enqueue: 'a * 'a t -> unit
        (* [enqueue (x,q)] adds the element [x] at the end of a queue [q]. *)
    val dequeue: 'a t -> unit
        (* [dequeue q] removes the first element in queue [q] *)
    val first: 'a t -> 'a
        (* [first q] returns the first element in queue [q] without removing
        it from the queue, or raises [Empty] if the queue is empty. *)
    val isEmpty: 'a t -> bool
        (* [isEmpty q] returns [true] if queue [q] is empty,
        otherwise returns [false]. *)
    val isFull: 'a t -> bool
        (* [isFull q] returns [true] if queue [q] is full,
        otherwise returns [false]. *)
end;;



module MutableQueue : QUEUE_MUT =
struct
    type 'a t = ('a option array * int ref * int ref) (*array, index of first, index after last*)

    exception Empty of string
    exception Full of string

    let empty initialSize =
        (Array.make initialSize None, ref 0, ref 0)

    let enqueue (elem, (arr, _, back)) =
        match arr.(!back) with
            | None -> arr.(!back) <- Some(elem);
                        back := (!back + 1) mod (Array.length arr)
            | _ -> raise (Full "enqueue: queue is full")

    let dequeue (arr, front, _) =
        match arr.(!front) with
            | None -> ()
            | _ -> arr.(!front) <- None;
            front := (!front + 1) mod (Array.length arr)

    let first (arr, front, _) =
        match arr.(!front) with
            | Some(elem) -> elem
            | _ -> raise (Empty "first: queue is empty")

    let isEmpty (arr, front, _) =
        arr.(!front) = None

    let isFull (arr, _, back) =
        arr.(!back) <> None
end;;


let cq = MutableQueue.empty 3;;
MutableQueue.isEmpty cq = true;;
MutableQueue.isFull cq = false;;

MutableQueue.enqueue (1, cq);;
MutableQueue.enqueue (2, cq);;
MutableQueue.enqueue (3, cq);;

MutableQueue.isEmpty cq = false;;
MutableQueue.isFull cq = true;;
MutableQueue.first cq = 1;;

MutableQueue.dequeue cq;;
MutableQueue.dequeue cq;;
MutableQueue.first cq = 3;;
MutableQueue.isEmpty cq = false;;
MutableQueue.isFull cq = false;;

MutableQueue.enqueue (4, cq);;
MutableQueue.enqueue (5, cq);;
MutableQueue.isEmpty cq = false;;
MutableQueue.isFull cq = true;;

(*MutableQueue.enqueue (6, cq) -> raise exception*)

MutableQueue.dequeue cq;;
MutableQueue.dequeue cq;;
MutableQueue.dequeue cq;;

MutableQueue.isEmpty cq = true;;
MutableQueue.isFull cq = false;;

(*MutableQueue.first cq -> raise exception*)



