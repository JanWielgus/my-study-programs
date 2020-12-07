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

    let rec lookup xs key =
        match xs with
        | [] -> None
        | (k, elem)::tail ->
            (match Key.compare key k with
            | LT -> None
            | EQ -> Some elem
            | GT -> lookup tail key
            )
        

    let rec insert xs (key, elem) =
        match xs with
        | [] -> [(key, elem)]
        | (k, x)::tail ->
            (match Key.compare key k with
            | LT -> (key, elem) :: xs
            | EQ -> raise (DuplicatedKey k)
            | GT -> (k, x) :: insert tail (key, elem)
            )


    let rec delete xs key =
        match xs with
        | [] -> []
        | (k, x)::tail ->
            (match Key.compare key k with
            | LT -> xs
            | EQ -> tail
            | GT -> (k, x) :: delete tail key
            )

    
    let rec update xs (key, elem) =
        match xs with
        | [] -> [(key, elem)]
        | (k, x)::tail ->
            (match Key.compare key k with
            | LT -> (key, elem) :: xs
            | EQ -> (key, elem) :: tail
            | GT -> (k, x) :: update tail (key, elem)
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

(* let dict = StringDict.insert dict ("kot","cat");; throws duplicated key exception *)

StringDict.lookup dict "pies";;
StringDict.lookup dict "papuga";;
let dict = dict <| ("papuga","parrot");;
StringDict.lookup dict "papuga";;





module IntDict = Dictionary(IntOrder);;

let ( <| ) d (k,x) = IntDict.update d (k,x);;

let dict = IntDict.empty();;

let dict = dict <| (1, "czarny")
                <| (2, "bialy")
                <| (5, "czerwony")
                ;;

IntDict.lookup dict 5;;
IntDict.lookup dict 4 = None;;

IntDict.lookup dict 1;;
let dict = IntDict.delete dict 1;;
IntDict.lookup dict 1;;
IntDict.lookup dict 2;;


