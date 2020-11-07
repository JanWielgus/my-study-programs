// Jan Wielgus


// Zadanie 3
def sumProd (xs: List[Int]): (Int, Int) =
    xs.foldLeft (0, 1) ((x: (Int, Int), y: Int) => (x._1 + y, x._2 * y))

sumProd(List(3, 4, 5)) == (12, 60)
sumProd(Nil) == (0, 1)
sumProd(List(5)) == (5, 5)
sumProd(List(1, 1, 1)) == (3, 1)





// Zadanie 5a
def insertionSort [A](pred: A=>A=>Boolean, xs: List[A]): List[A] = {
    def sortedInsert (ys: List[A], a: A): List[A] = {
        ys match {
            case Nil => List(a)
            case head :: tail => if (pred(a)(head)) a :: ys
                else head :: sortedInsert(tail, a)
        }
    }

    def insertionSortRec (toSort: List[A], sorted: List[A]): List[A] ={
        toSort match {
            case Nil => sorted
            case head :: tail => insertionSortRec(tail, sortedInsert(sorted, head))
        }
    }

    xs match {
        case Nil => Nil
        case List(elem) => List(elem)
        case head::tail => insertionSortRec(xs, Nil)
    }
}

insertionSort((a:Int) => (b:Int) => a < b, List(3, 1, 5, 2, 4)) == List(1, 2, 3, 4, 5)
insertionSort((a:Int) => (b:Int) => a < b, List(4, 4, 8, 1, -1, 4, -1)) == List(-1, -1, 1, 4, 4, 4, 8)
insertionSort((a:Int) => (b:Int) => a > b, List(5, 2, -5, 0, 5, 2, -10)) == List(5, 5, 2, 2, 0, -5, -10)
insertionSort((a:Int) => (b:Int) => a < b, Nil) == Nil
insertionSort((a:Int) => (b:Int) => a < b, List(3)) == List(3)

insertionSort((l1: List[Int]) => (l2: List[Int]) => l1.length < l2.length,
    List(List(1, 2, 3), List(0), List(3, 2, 1), List(4, 5), List(7, 8, 9))) == List(List(0), List(4, 5), List(1, 2, 3), List(3, 2, 1), List(7, 8, 9))






// Zadanie 5b
def mergesort [A](pred: A=>A=>Boolean, xs: List[A]): List[A] = {
    val xsLen = xs.length

    def splitList (fstHalfSize: Int, ys: List[A]): (List[A], List[A]) = {
        if (fstHalfSize <= 0) (Nil, ys)
        else {
            val temp = splitList(fstHalfSize - 1, ys.tail)
            (ys.head :: temp._1, temp._2)
        }
    }

    def mergeLists (l1: List[A], l2: List[A]): List[A] = {
        (l1, l2) match {
            case (Nil, Nil) => Nil
            case (Nil, _) => l2
            case (_, Nil) => l1
            case (hd1::tl1, hd2::tl2) => if (pred(hd1)(hd2)) hd1 :: mergeLists(tl1, l2)
                else hd2 :: mergeLists(l1, tl2)
        }
    }

    xs match {
        case Nil => Nil
        case List(elem) => List(elem)
        case _ => {
            val split = splitList(xsLen / 2, xs)
            mergeLists(mergesort(pred, split._1), mergesort(pred, split._2))
        }
    }
}


mergesort((a:Int) => (b:Int) => a < b, List(3, 1, 5, 2, 4)) == List(1, 2, 3, 4, 5)
mergesort((a:Int) => (b:Int) => a < b, List(4, 4, 8, 1, -1, 4, -1)) == List(-1, -1, 1, 4, 4, 4, 8)
mergesort((a:Int) => (b:Int) => a > b, List(5, 2, -5, 0, 5, 2, -10)) == List(5, 5, 2, 2, 0, -5, -10)
mergesort((a:Int) => (b:Int) => a < b, Nil) == Nil
mergesort((a:Int) => (b:Int) => a < b, List(3)) == List(3)

mergesort((l1: List[Int]) => (l2: List[Int]) => l1.length <= l2.length,
    List(List(1, 2, 3), List(0), List(3, 2, 1), List(4, 5), List(7, 8, 9))) == List(List(0), List(4, 5), List(1, 2, 3), List(3, 2, 1), List(7, 8, 9))


