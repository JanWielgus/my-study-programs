// Jan Wielgus



// Zadanie 3
sealed trait BT[+A]
case object Empty extends BT[Nothing]
case class Node [+A](elem: A, left: BT[A], right: BT[A]) extends BT[A]


def breadthBT [A](tree: BT[A]): List[A] = {
    def bfs (queue: List[BT[A]]): List[A] = {
        if (queue == Nil) Nil
        else queue match {
            case Node (value, Empty, Empty) :: tl => value :: bfs(tl)
            case Node (value, n1, Empty) :: tl => value :: bfs(tl ::: List(n1))
            case Node (value, Empty, n2) :: tl => value :: bfs(tl ::: List(n2))
            case Node (value, n1, n2) :: tl => value :: bfs(tl ::: List(n1, n2))
            case _ => Nil
        }
    }
    bfs(List(tree))
}

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

breadthBT(tt) == List(1, 2, 3, 4, 5, 6)
breadthBT(Node(0, Empty, Empty)) == List(0)
breadthBT(Empty) == Nil
breadthBT (Node(0, Empty, Node(1, Empty, Node(2, Empty, Empty))) ) == List(0, 1, 2)
breadthBT (Node(0, Node(1, Node(2, Empty, Empty), Empty), Empty) ) == List(0, 1, 2)





// Zadanie 4a
def innerPathLen [A](bt: BT[A]): Int = {
    def innPathRec (level: Int, tree: BT[A]): Int = {
        tree match {
            case Empty => 0
            case Node(value, n1, n2) =>
                level + innPathRec(level + 1, n1) + innPathRec(level + 1, n2)
        }
    }
    innPathRec(0, bt)
}

innerPathLen(tt) == 9
innerPathLen(Empty) == 0
innerPathLen(Node(0, Empty, Empty)) == 0
innerPathLen(Node(0, Node(1, Empty, Empty), Empty)) == 1
innerPathLen(Node(0, Node(1, Empty, Empty), Node(2, Empty, Empty))) == 2
innerPathLen(Node(0, Node(1, Node(2, Empty, Empty), Empty), Empty)) == 3




// Zadanie 4b
def outerPathLen [A](bt: BT[A]): Int = {
    def nodeAmt (tree: BT[A]): Int = {
        tree match {
            case Empty => 0
            case Node(_, n1, n2) => 1 + nodeAmt(n1) + nodeAmt(n2)
        }
    }
    innerPathLen(bt) + 2 * nodeAmt(bt)
}

outerPathLen(tt) == 21
outerPathLen(Empty) == 0
outerPathLen(Node(0, Empty, Empty)) == 2
outerPathLen(Node(0, Node(1, Empty, Empty), Empty)) == 5




// Zadanie 5
sealed trait Graphs[A]
case class Graph [A](succ: A => List[A]) extends Graphs[A]

def depthSearch [A](g: Graph[A])(startNode: A): List[A] = {
    def search (visited: List[A], queue: List[A]): List[A] = {
        queue match {
            case Nil => Nil
            case h::t => if (visited.contains())
        }
    }
}
