// Jan Wielgus


sealed trait lBT[+A]
case object LEmpty extends lBT[Nothing]
case class LNode[+A](elem: A, left: () => lBT[A], right: () => lBT[A]) extends lBT[A]




// Zadanie 1
def lrepeat [A](k: Int)(lxs: LazyList[A]): LazyList[A] = {
    def lrepeatRec (n: Int, ys: LazyList[A]): LazyList[A] = {
        ys match {
            case LazyList() => LazyList()
            case head#::tail => if (n > 0) head #:: lrepeatRec(n-1, ys)
                                else lrepeatRec(k, tail)
        }
    }

    lrepeatRec(k, lxs)
}

lrepeat(3)(LazyList(5, 1)).toList == List(5, 5, 5, 1, 1, 1)
lrepeat(2)(LazyList(4, 5, 6, 7, 8)).toList == List(4, 4, 5, 5, 6, 6, 7, 7, 8, 8)
lrepeat(4)(LazyList()).toList == Nil





// Zadanie 2
// Powinno byc od 0
val lfib: LazyList[Int] = {
    def lfibRec (x2: Int, x1: Int): LazyList[Int] = {
        (x1+x2) #:: lfibRec(x1, x1+x2)
    }

    1 #:: 1 #:: lfibRec(1, 1)
}

lfib.take(10).toList == List(1, 1, 2, 3, 5, 8, 13, 21, 34, 55)






// Zadanie 3a
def lBreadth [A](ltree: lBT[A]): LazyList[A] = {
    def bfs (queue: List[lBT[A]]): LazyList[A] = {
        queue match {
            case LNode(value, n1, n2) :: tl => value #:: bfs(tl ::: List(n1(), n2()))
            case LEmpty :: tail => bfs(tail)
            case Nil => LazyList()
        }
    }

    bfs(List(ltree))
}

val testlBT = LNode(5, ()=>LNode(3, ()=>LEmpty, ()=>LNode(4, ()=>LEmpty, ()=>LEmpty)), ()=>LNode(7, ()=>LEmpty, ()=>LEmpty))

lBreadth(testlBT).toList == List(5, 3, 7, 4)
lBreadth(LEmpty).toList == Nil




// Zadanie 3b
def lTree (n: Int): lBT[Int] = {
    LNode(n, () => lTree(2*n), () => lTree(2*n+1))
}

lBreadth(lTree(1)).take(6).toList == List(1, 2, 3, 4, 5, 6)
lBreadth(lTree(5)).take(8).toList == List(5, 10, 11, 20, 21, 22, 23, 40)








































