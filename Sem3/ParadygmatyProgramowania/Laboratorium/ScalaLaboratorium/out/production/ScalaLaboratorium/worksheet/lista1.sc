// Jan Wielgus


// zadanie 2
def suma(a: List[Double]): Double =
    if(a == Nil) 0.0
    else if (a.tail == Nil) a.head
    else a.head + suma(a.tail)

suma(List(1.0, 2.0, 3.0, 4.0, 5.0)) == 15.0
suma(Nil) == 0.0
suma(List(-1, 2, 3)) == 4
suma(List(5.6)) == 5.6



// zadanie 3
def ends [A](a: List[A]): (A, A) =
    if(a == Nil) throw new NoSuchElementException("empty list")
    else if(a.tail == Nil) (a.head, a.head)
    else (a.head, ends(a.tail)._2)

ends(List(1, 3, 5, 6, 9)) == (1, 9)
ends(List(1)) == (1, 1)
//ends(List()) //java.util.NoSuchElementException: empty list