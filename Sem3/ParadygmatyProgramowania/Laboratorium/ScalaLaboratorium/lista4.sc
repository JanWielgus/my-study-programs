// Jan Wielgus


sealed trait BT[+A]
case object Empty extends BT[Nothing]
case class Node [+A](elem: A, left: BT[A], right: BT[A]) extends BT[A]


val t = Node(1, Node(2, Empty, Node(3, Empty, Empty)), Empty)


val tt = Node(1,
    Node(2,
        Node(4,
            Empty,
            Empty),
        Empty),
    Node(3,
        Node(5,
            Empty,
            Node(6, Empty, Empty)),
        Empty)
)


// zadanie 1
def sumBT (btree: BT[Int]): Int =
    btree match {
        case Empty => 0
        case Node (value, n1, n2) =>
            value + sumBT(n1) + sumBT(n2)
    }

sumBT(t) == 6
sumBT(tt) == 21
sumBT(Empty) == 0




// zadanie 2
def foldBT [A, B](f: A => (B, B) => B)(tree: BT[A])(acc: B): B =
    tree match {
        case Empty => acc
        case Node (value, n1, n2) => f(value)( foldBT(f)(n1)(acc), foldBT(f)(n2)(acc) )
    }

foldBT((x: Int) => (lAcc: Int, rAcc: Int) => x + lAcc + rAcc)(t)(0) == 6
foldBT((x: Int) => (lAcc: Int, _: Int) => x + lAcc) (t) (0) == 3 // sum only left edge






// zadanie 3a
def sumBTfold (tree: BT[Int]): Int =
    foldBT((x:Int) => (lAcc: Int, rAcc: Int) => x + lAcc + rAcc)(tree)(0)

sumBTfold(t) == 6
sumBTfold(Empty) == 0




// zadanie 3b preorder
def preorderBTfold [A](tree: BT[A]): List[A] =
    foldBT((x: A) => (lAcc: List[A], rAcc: List[A]) => x :: lAcc ::: rAcc)(tree)(Nil)

preorderBTfold(tt) == List(1, 2, 4, 3 ,5, 6)
preorderBTfold(Empty) == Nil




// zadanie 3b inorder
def inorderBTfold [A](tree: BT[A]): List[A] =
    foldBT((x: A) => (lAcc: List[A], rAcc: List[A]) => lAcc ::: (x :: rAcc) )(tree)(Nil)

inorderBTfold(tt) == List(4, 2, 1, 5, 6, 3)
inorderBTfold(t) == List(2, 3, 1)
inorderBTfold(Empty) == Nil





// zadanie 3b postorder
def postorderBTfold [A](tree: BT[A]): List[A] =
    foldBT((x: A) => (lAcc: List[A], rAcc: List[A]) => lAcc ::: rAcc ::: List(x) )(tree)(Nil)

postorderBTfold(tt) == List(4, 2, 6, 5, 3, 1)
postorderBTfold(Empty) == Nil





// Zadanie 4
def mapBT [A, B](f: A => B, tree: BT[A]): BT[B] =
    foldBT((x: A, acc: (BT[B], BT[B])) => Node(f(x), acc._1, acc._2), tree, Empty)

mapBT((v: Int) => 2 * v, t) == Node (2, Node (4, Empty, Node (6, Empty, Empty)), Empty)
mapBT((v: Int) => 3 * v, Empty) == Empty



































