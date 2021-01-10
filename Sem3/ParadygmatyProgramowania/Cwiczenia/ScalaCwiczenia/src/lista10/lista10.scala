package lista10

// Jan Wielgus



// zadanie 3

class MyQueue[+T] private(private val front: List[T], private val back: List[T])
{
    def enqueue[S >: T](elem: S): MyQueue[S] = {
        if (front == Nil) new MyQueue[S](elem::Nil, back)
        else new MyQueue[S](front, elem::back)
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
    def apply[T](args: T*) = new MyQueue[T](args.toList, Nil)
}





object Main {
    def main(args: Array[String]): Unit = {
        var testQueue = MyQueue[Int](1, 2, 3)

        testQueue = testQueue.dequeue.dequeue
        println(testQueue.first) // 3
        var valQueue: MyQueue[AnyVal] = testQueue.enqueue(5)
        valQueue = valQueue.dequeue
        println(valQueue.first) // 5
        println(valQueue.dequeue.isEmpty) // true
    }



    // zadanie 4

    def MyCopy[T](src: scala.collection.mutable.Seq[T],
                  dst: scala.collection.mutable.Seq[T]): Unit = {
        var i:Int = 0
        for (elem <- src) {
            dst.update(i, elem)
            i += 1
        }
    }
}

