(*Jan Wielgus*)
(**)

type 'a bt = Empty | Node of 'a * 'a bt * 'a bt;;
let t = Node(1, Node(2, Empty, Node(3, Empty, Empty)), Empty);;

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


(*zadanie 1*)
let rec sumBT tree =
    match tree with
    | Empty -> 0
    | Node (value, n1, n2) ->
        value + sumBT n1 + sumBT n2
    ;;

sumBT t = 6;;
sumBT tt = 21;;
sumBT Empty = 0;;





(*zadanie 2*)
let rec foldBT f tree acc =
    match tree with
    | Empty -> acc
    | Node (value, n1, n2) -> f value (foldBT f n1 acc, foldBT f n2 acc)
    ;;

foldBT (fun x (lAcc, rAcc) -> x + lAcc + rAcc) t 0 = 6;;
foldBT (fun x (lAcc, rAcc) -> x + lAcc) t 0 = 3;;





(*zadanie 3a*)
let sumBTfold tree =
    foldBT (fun x (lAcc, rAcc) -> x + lAcc + rAcc) tree 0
    ;;

sumBTfold t = 6;;
sumBTfold Empty = 0;;




(*zadanie 3b preorder*)
let preorderBTfold tree =
    foldBT (fun x (lAcc, rAcc) -> (x :: lAcc) @ rAcc) tree []
    ;;

preorderBTfold tt = [1; 2; 4; 3; 5; 6];;
preorderBTfold Empty = [];;



(*zadanie 3b inorder*)
let inorderBTfold tree =
    foldBT (fun x (lAcc, rAcc) -> lAcc @ (x :: rAcc)) tree []
    ;;

inorderBTfold tt = [4; 2; 1; 5; 6; 3];;
inorderBTfold t = [2; 3; 1];;
inorderBTfold Empty = [];;



(*zadanie 3b postorder*)
let postorderBTfold tree =
    foldBT (fun x (lAcc, rAcc) -> lAcc @ rAcc @ [x]) tree []
    ;;

postorderBTfold tt = [4; 2; 6; 5; 3; 1];;
postorderBTfold Empty = [];;






(*zadanie 4*)
let mapBT f tree =
    foldBT (fun x (lAcc, rAcc) -> Node (f x, lAcc, rAcc)) tree Empty
    ;;

mapBT (fun v -> 2 * v) t = Node (2, Node (4, Empty, Node (6, Empty, Empty)), Empty);;
mapBT (fun v -> 3 * v) Empty = Empty;;
    

