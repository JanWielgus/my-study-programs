// Jan Wielgus



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