// Jan Wielgus


// zadanie 3
def reverse [A](xs: List[A]): List[A] =
xs match {
    case Nil => Nil
    case head::tail => reverse(tail) ::: List(head)
}

reverse(List(1, 2)) == List(2, 1)
reverse(List(5, 4, 3, 2, 1)) == List(1, 2, 3, 4, 5)
reverse(List(1, 2, 3, 4)) == List(4, 3, 2, 1)
reverse(List()) == List()
reverse(List('a')) == List('a')



// zadanie 4
def replicate (xs: List[Int]): List[Int] = {
    def replicateElem(x: Int, n: Int): List[Int] = {
        n match {
            case _ if n <= 0 => Nil
            case 1 => List(x)
            case _ => List.concat(List(x), replicateElem(x, n - 1))
        }
    }
    xs match {
        case Nil => Nil
        case head::tail =>
    }
}

