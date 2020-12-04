(*Jan Wielgus*)
(**)



(* zadanie 1 *)


type ordering = LT | EQ | GT;;

module type ORDER =
sig
    type t
    val compare: t -> t -> ordering
end;;

module StringOrder: ORDER with type t = string =
struct
    type t = string
    let compare s1 s2 = if s1<s2 then LT else
    if s1>s2 then GT else EQ
end;;


module type DICTIONARY =
sig
    type key (* type of keys *)
    type 'a t (* type of dictionary *)
    exception DuplicatedKey of key (* error in insert *)
    val empty: unit -> 'a t (* empty dictionary *)
    val lookup: 'a t -> key -> 'a option
    val insert: 'a t -> key * 'a -> 'a t
    val delete: 'a t -> key -> 'a t
    val update: 'a t -> key * 'a -> 'a t (*not necessary*)
end;;




module Dictionary (Key: ORDER) : DICTIONARY with type key = Key.t =
struct
    type key = Key.t
    type 'a t = Tip | Node of key * 'a * 'a t * 'a t

    exception DuplicatedKey of key

    let empty = Tip

    let rec lookup tree key =
        match tree with
        | Node(k,info,t1,t2) ->
            (match Key.compare key k with
                | LT -> lookup t1 key
                | EQ -> Some info
                | GT -> lookup t2 key
            )
        | Tip -> None
        ;;


    let rec insert tree (key, value) =
        match tree with
            | Tip -> Node(key, value, Tip, Tip)
            | Node(k,info,t1,t2) ->
                (match Key.compare key k with
                    | LT -> Node(k, info, insert t1 (key,value), t2)
                    | EQ -> raise (DuplicatedKey key)
                    | GT -> Node(k, info, t1, insert t2 (key,value))
                )
        ;;
        
        


    (* deletemin T returns a triple consisting of the least
    element y in tree T, its associated value and the tree
    that results from deleting y from T.
    *)
    let rec deletemin tree =
        match tree with
            | Node(k,info,Tip,t2) -> (k,info,t2)
            (* This is the critical case. If the left subtree
            is empty, then the element at the current node
            is the min. *)
            | Node(k,info,t1,t2) ->
                let (key,value,l) = deletemin t1 in
                (key, value, Node(k, info, l, t2))
            | Tip -> failwith "Dictionary: implementation error"
        ;;


    let rec delete tree key =
        match tree with
            | Tip -> Tip
            | Node(k,info,t1,t2) ->
                match Key.compare key k with
                    | LT -> Node(k,info, delete t1 key, t2)
                    | EQ ->
                        ( match (t1, t2) with
                        | (Tip, t2) -> t2
                        | (t1, Tip) -> t1
                        | _ ->
                            let (ki,inf,t_right) = deletemin t2 in
                            Node(ki,inf,t1,t_right)
                        )
                    | GT -> Node(k,info, t1, delete t2 key)
        ;;

    let rec update tree (key, value) =
        match tree with
            | Tip -> Node(key, value, Tip, Tip)
            | Node(k,info,t1,t2) ->
                ( match Key.compare key k with
                    | LT -> Node(k, info, update t1(key,value), t2)
                    | EQ -> Node(k, value, t1, t2)
                    | GT -> Node(k, info, t1, update t2(key,value))
                )
        ;;
end;; (* Dictionary *)