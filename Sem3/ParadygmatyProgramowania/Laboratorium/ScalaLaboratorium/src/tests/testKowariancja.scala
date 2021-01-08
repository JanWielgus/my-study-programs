

object Lista10 {
    def main(args: Array[String]): Unit = {
        var q = Queue.empty[Point]
        q = q.enqueue(new Point3D(2,2,2))
        q = q.enqueue(new Point(10,10))
        q = q.dequeue
        println(q.first.y == 10)
    }

    // Zadanie 4
    def copy[T](dest: collection.mutable.Seq[T], src: collection.mutable.Seq[T]): Unit = {
        var i = 0
        src.foreach(x => (dest.update(i, x), i += 1))
    }
}

class Point(var x: Int = 0, var y: Int = 0) {}

class Point3D(x: Int = 1, y: Int = 1, var z: Int = 1)
    extends Point(x,y) {}


class UnderflowException(msg: String) extends Exception(msg)

// Zadanie 3
class Queue[+T] private(private[this] val head: List[T], private[this] val tail: List[T]){
    def enqueue[S >: T](x: S): Queue[S] = {
        head match{
            case Nil => new Queue[S](x::head, tail)
            case _ => new Queue[S](head, x::tail)
        }
    }

    def dequeue: Queue[T] = {
        head match{
            case Nil => new Queue[T](Nil, Nil)
            case h::Nil => new Queue[T](tail.reverse, Nil)
            case h::t => new Queue[T](t, tail)
        }
    }

    def first:T = {
        head match {
            case Nil => throw new UnderflowException("No elements in queue")
            case h :: t => h
        }
    }

    def isEmpty:Boolean = {
        head == Nil
    }
}
object Queue {
    def empty[T] = new Queue[T](Nil, Nil)
    def apply[T](xs: T*) = new Queue[T](xs.toList, Nil)
}
