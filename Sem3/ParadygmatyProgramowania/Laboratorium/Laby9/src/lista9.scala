// Jan Wielgus


// Zadanie 1

class MyQueue[T] private(private val front: List[T], private val back: List[T])
{
    def makeNormal(f: List[T], b: List[T]): MyQueue[T] = {
        if (f == Nil) new MyQueue(b.reverse, Nil)
        else new MyQueue(f, b)
    }

    def enqueue(elem: T): MyQueue[T] = {
        makeNormal(front, elem :: back)
    }

    def first: T = {
        front match {
            case Nil => throw new IllegalStateException("empty queue has no elements")
            case h::_ => h
        }
    }

    def firstOption: Option[T] = {
        front match {
            case Nil => None
            case h::_ => Some(h)
        }
    }

    def dequeue: MyQueue[T] = {
        front match {
            case Nil => new MyQueue[T](Nil, Nil)
            case _::Nil => new MyQueue[T](back.reverse, Nil)
            case _::t => new MyQueue[T](t, back)
        }
    }

    def isEmpty: Boolean = front == Nil

    def this() = {
        this(Nil, Nil)
    }
}

object MyQueue
{
    def empty[T] = new MyQueue[T](Nil, Nil)
    def apply[T](initList: List[T]) = new MyQueue[T](initList, Nil)
    def apply[T]() = new MyQueue[T](Nil, Nil)
}





// Zadanie 2

sealed trait BT[+A]
case object Empty extends BT[Nothing]
case class Node[+A](elem: A, left: BT[A], right: BT[A]) extends BT[A]


object Lista9
{
    def main(args: Array[String]): Unit = {
        val testQueue = new MyQueue[Int]
        val testQueue2 = MyQueue[Int]()
        val testQueue3 = MyQueue.empty;
        //val testQueue4 = MyQueue('a', 'b', 'c') // z wykladu 10

        println(breadthBT(tt) == List(1, 2, 3, 4, 5, 6))
        println(breadthBT(Node(0, Empty, Empty)) == List(0))
        println(breadthBT(Empty) == Nil)
        println(breadthBT (Node(0, Empty, Node(1, Empty, Node(2, Empty, Empty))) ) == List(0, 1, 2))
        println(breadthBT (Node(0, Node(1, Node(2, Empty, Empty), Empty), Empty) ) == List(0, 1, 2))
    }


    def breadthBT[A](tree: BT[A]): List[A] = {
        def bfs (queue: MyQueue[BT[A]]): List[A] = {
            if (queue.isEmpty) Nil
            else queue.first match {
                case Node (value, Empty, Empty) => value :: bfs(queue.dequeue)
                case Node (value, n1, Empty) => value :: bfs(queue.dequeue.enqueue(n1))
                case Node (value, Empty, n2) => value :: bfs(queue.dequeue.enqueue(n2))
                case Node (value, n1, n2) => value :: bfs(queue.dequeue.enqueue(n1).enqueue(n2))
                case _ => Nil
            }
        }
        bfs(MyQueue(List(tree)))
    }


    val t = Node(1,Node(2,Empty,Node(3,Empty,Empty)),Empty)
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
}




































