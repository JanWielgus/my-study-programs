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
    let compare s1 s2 = if s1 < s2 then LT else
    if s1 > s2 then GT else EQ
end;;


module IntOrder: ORDER with type t = int =
struct
    type t = int
    let compare i1 i2 = if i1 < i2 then LT else
    if i1 > i2 then GT else EQ
end;;


module type DICTIONARY =
sig
    type key (* type of keys *)
    type 'a t (* type of dictionary *)

    val empty: unit -> 'a t (* empty dictionary *)
    val lookup: 'a t -> key -> 'a option
    val insert: 'a t -> key * 'a -> 'a t
    val delete: 'a t -> key -> 'a t
    val update: 'a t -> key * 'a -> 'a t (*not necessary*)
end;;




module Dictionary (Key: ORDER) : DICTIONARY with type key = Key.t =
struct
    type key = Key.t
    type 'a t = (key * 'a) list

    exception DuplicatedKey of key

    let empty() = []

    let rec lookup xs _key =
        match xs with
        | [] -> None
        | (k, elem)::tail ->
            (match Key.compare _key k with
            | LT -> None
            | EQ -> Some elem
            | GT -> lookup tail _key
            )
        

    let rec insert xs (_key, elem) =
        let _ = raise (DuplicatedKey _key) in
        match xs with
        | [] -> [(_key, elem)]
        | (k, x)::tail ->
            (match Key.compare _key k with
            | LT -> (_key, elem) :: xs
            | EQ -> raise (DuplicatedKey _key)
            | GT -> (k, x) :: insert tail (_key, elem)
            )


    let rec delete xs _key =
        match xs with
        | [] -> []
        | (k, x)::tail ->
            (match Key.compare _key k with
            | LT -> xs
            | EQ -> tail
            | GT -> (k, x) :: delete tail _key
            )

    
    let rec update xs (_key, elem) =
        match xs with
        | [] -> [(_key, elem)]
        | (k, x)::tail ->
            (match Key.compare _key k with
            | LT -> xs
            | EQ -> (_key, elem) :: tail
            | GT -> (k, x) :: update tail (_key, elem)
            )

    
end;; (* Dictionary *)



module StringDict = Dictionary(StringOrder);;

let ( <| ) d (k,x) = StringDict.update d (k,x);;

let dict = StringDict.empty();;


let dict = dict <| ("kot","cat")
                <| ("slon","elephant")
                <| ("pies","dog")
                <| ("ptak","bird")
                ;;

let dict = dict <| ("kot","cat");;

StringDict.lookup dict "pies";;
StringDict.lookup dict "papuga";;
let dict = dict <| ("papuga","parrot");;
StringDict.lookup dict "papuga";;


module IntDict = Dictionary(IntOrder);;

