// Jan Wielgus




// zadanie 2
def lrepeat [A](f: Int => Int)(lxs: LazyList[A]): LazyList[A] = {
    def lrepeatRec (n: Int, lys: LazyList[A], idx: Int): LazyList[A] = {
        lys match {
            case LazyList() => LazyList()
            case head#::tail => if (n > 0) head #:: lrepeatRec(n-1, lys, idx)
                                else lrepeatRec(f(idx+1), tail, idx+1)
        }
    }

    lrepeatRec(f(0), lxs, 0)
}

lrepeat((i: Int) => i+1)(LazyList.from(3)).take(11).toList == List(3, 4 ,4 ,5, 5, 5, 6, 6, 6, 6, 7)
lrepeat((i: Int) => i+2)(LazyList(5, 2, 8)).toList == List(5, 5, 2, 2, 2, 8, 8, 8, 8)
lrepeat((i: Int) => i+2)(LazyList()).toList == Nil