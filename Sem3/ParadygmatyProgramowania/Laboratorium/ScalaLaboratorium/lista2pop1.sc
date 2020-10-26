// Jan Wielgus



// zadanie 3
def reverse [A](xs: List[A]): List[A] = {
    @scala.annotation.tailrec
    def reverseRec (toRev: List[A], reversed: List[A]): List[A] ={
        toRev match {
            case Nil => reversed
            case head::tail => reverseRec(tail, head::reversed)
        }
    }
    reverseRec(xs, Nil)
}

reverse(List(1, 2)) == List(2, 1)
reverse(List(5, 4, 3, 2, 1)) == List(1, 2, 3, 4, 5)
reverse(List(1, 2, 3, 4)) == List(4, 3, 2, 1)
reverse(List()) == Nil
reverse(Nil) == Nil
reverse(List('a')) == List('a')





// zadanie 4
def replicate (xs: List[Int]): List[Int] = {
    def replicateElem(x: Int, n: Int): List[Int] = {
        n match {
            case _ if n <= 0 => Nil
            case 1 => List(x)
            case _ => x :: replicateElem(x, n - 1)
        }
    }
    xs match {
        case Nil => Nil
        case head::tail => replicateElem(head, head) ::: replicate(tail)
    }
}


replicate(List(1, 0, 4, -2, 3)) == List(1, 4, 4, 4, 4, 3, 3, 3)
replicate(List(0, 0, -1, -2)) == Nil
replicate(List(2, 2, 2)) == List(2, 2, 2, 2, 2, 2)
replicate(List(-5, 5)) == List(5, 5, 5, 5, 5)