// Jan Wielgus


// zadanie 2 a
def fib(n: Int): Int = {
    if (n < 0) throw new Exception("Less than zero")
    else if (n == 0) 0
    else if (n == 1) 1
    else fib(n - 2) + fib(n - 1)
}

fib(10) == 55
fib(0) == 0
fib(1) == 1
fib(2) == 1
// fib(-5) throw exception


// zadanie 2 b
def fibTail(n: Int): Int = {
    def fibIter(n: Int, f1: Int, f2: Int): Int = {
        if (n == 0) f1
        else fibIter(n-1, f2, f1 + f2)
    }
    fibIter(n,0,1)
}

fibTail(42) == 267914296
fibTail(10) == 55
fibTail(0) == 0
fibTail(1) == 1
fibTail(2) == 1
// fibTail(-5) throw exception


// zadanie 3

def root3(n: Double): Double = {
    @scala.annotation.tailrec
    def root3Iter (a : Double): Double =
    {
        if (Math.abs(a * a * a - n) <= 1.0e-15 * Math.abs(n)) a
        else root3Iter(a + (n / (a*a) - a) / 3)
    }
    if(Math.abs(n) > 1) root3Iter(n/3)
    else root3Iter(n)
}

root3 (8) == 2
root3 (-8) == -2
root3 (1) == 1
root3 (0) == 0


val root3Fun = (n: Double) => {
    @scala.annotation.tailrec
    def root3Iter (a : Double): Double =
    {
        if (Math.abs(a * a * a - n) <= 1.0e-15 * Math.abs(n)) a
        else root3Iter(a + (n / (a*a) - a) / 3)
    }
    if(Math.abs(n) > 1) root3Iter(n/3)
    else root3Iter(n)
}

root3Fun (8) == 2
root3Fun (-8) == -2
root3Fun (1) == 1
root3Fun (0) == 0



// zadanie 4 a
val List(_, _, x, _, _) = List(-2,-1,0,1,2)
x == 0


// zadanie 4 b
val List(List(_, _), List(x, _)) = List(List(1, 2), List(0, 1))
x == 0


// zadanie 5
def initSegment[A](xs:List[A],ys:List[A]): Boolean = {
    if (xs == ys) true
    else if (xs == Nil) true
    else if (ys == Nil) false
    else if (xs.head == ys.head) initSegment(xs.tail, ys.tail)
    else false
}

initSegment(List(1, 2, 3), List(1, 2, 3, 4, 5)) == true
initSegment(List(1, 2, 4), List(1, 2, 3, 4, 5)) == false
initSegment(List(1), List(1, 2, 3, 4, 5)) == true
initSegment(List(5), List(1, 2, 3, 4, 5)) == false
initSegment(Nil, List(3, 4, 2, 1)) == true
initSegment(Nil, Nil) == true



// zadanie 6 a
def replaceNth[A](xs: List[A], n: Int, x: A): List[A] = {
    if (n < 0) throw new Exception("Position cannot be less than zero")
    else if (n >= xs.length) throw new Exception("Position is out of bounds")
    else (xs, n, x) match {
        case (Nil, _, _) => throw new Exception("List cannot be empty")
        case (List(_), 0, x) => List(x)
        case (xs, 0, x) => x :: xs.tail
        case (xs, n, x) => xs.head :: replaceNth(xs.tail, n - 1, x)
    }
}

replaceNth(List(1, 2, 3, 4), 1, 22) == List(1, 22, 3, 4)
replaceNth(List(1, 2, 3, 4), 3, 44) == List(1, 2, 3, 44)
replaceNth(List('a'), 0, 'b') == List('b')
// replaceNth(Nil, 0, 'b') throw exception
// replaceNth(List(5), -5, 8) throw exception
















