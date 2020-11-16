(*Jan Wielgus*)



(*zadanie 2*)
let test a = raise(Failure "failure");;




(*zadanie 3*)
type 'a bt = Empty | Node of 'a * 'a bt * 'a bt;;


let breadthBT tree =
    let rec bfs queue =
        if queue = [] then []
        else
            match queue with
            | Node (value, Empty, Empty) :: tl -> value :: bfs tl
            | Node (value, n1, Empty) :: tl -> value :: bfs (tl @ [n1])
            | Node (value, Empty, n2) :: tl -> value :: bfs (tl @ [n2])
            | Node (value, n1, n2) :: tl -> value :: bfs (tl @ [n1; n2])
            | _ -> []
        in
    bfs [tree]
    ;;

let tt = Node(1,
            Node(2,
                Node(4,
                    Empty,
                    Empty
                    ),
                Empty
                ),
            Node(3,
                Node(5,
                    Empty,
                    Node(6,
                        Empty,
                        Empty
                        )
                    ),
                Empty
            )
        );;
    
breadthBT tt = [1; 2; 3; 4; 5; 6];;
breadthBT (Node(0, Empty, Empty)) = [0];;
breadthBT Empty = [];;
breadthBT (Node(0, Empty, Node(1, Empty, Node(2, Empty, Empty))) ) = [0; 1; 2];;
breadthBT (Node(0, Node(1, Node(2, Empty, Empty), Empty), Empty) ) = [0; 1; 2];;






(*zadanie 4a*)
let innerPathLen bt =
    let rec innPathRec level tree =
        match tree with
        | Empty -> 0
        | Node (value, n1, n2) ->
            level + (innPathRec (level + 1) n1) + (innPathRec (level + 1) n2)
        in
        innPathRec 0 bt
    ;;
    
innerPathLen tt = 9;;
innerPathLen Empty = 0;;
innerPathLen (Node(0, Empty, Empty)) = 0;;
innerPathLen (Node(0, Node(1, Empty, Empty), Empty)) = 1;;
innerPathLen (Node(0, Node(1, Empty, Empty), Node(2, Empty, Empty))) = 2;;
innerPathLen (Node(0, Node(1, Node(2, Empty, Empty), Empty), Empty)) = 3;;





(*zadanie 4b*)
let outerPathLen bt =
    let rec nodeAmt tree =
        match tree with
        | Empty -> 0
        | Node (_, n1, n2) -> 1 + nodeAmt n1 + nodeAmt n2
        in
    innerPathLen bt + 2 * nodeAmt bt
    ;;

outerPathLen tt = 21;;
outerPathLen Empty = 0;;
outerPathLen (Node(0, Empty, Empty)) = 2;;
outerPathLen (Node(0, Node(1, Empty, Empty), Empty)) = 5;;






(*zadanie 5*)
type 'a graph = Graph of ('a -> 'a list);;

let depthSearch (Graph succ) startNode =
    let rec search visited stack =
        match stack with
        | [] -> []
        | h::t -> if List.mem h visited then search visited t
                else h :: search (h :: visited) (succ h @ t)
        in
    search [] [startNode]
    ;;


let g = Graph (function
    | 0 -> [3]
    | 1 -> [0;2;4]
    | 2 -> [1]
    | 3 -> []
    | 4 -> [0;2]
    | n -> failwith ("Graph g: node "^string_of_int n^" doesn't exist")
    )
    ;;


depthSearch g 4 = [4; 0; 3; 2; 1];;
    
