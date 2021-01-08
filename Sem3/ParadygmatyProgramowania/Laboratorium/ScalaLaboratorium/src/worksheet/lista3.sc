// Jan Wielgus


// zadanie 1
@scala.annotation.tailrec
def czyIstnieje[A](p: A => Boolean) (xs: List[A]): Boolean =
    xs match {
        case Nil => false
        case head::tail => if (p(head)) true
                            else czyIstnieje(p)(tail)
    }

czyIstnieje ((x: Int) => x==2) (List(5,1,2,3)) == true
czyIstnieje ((x: Int) => x > 5) (List(1, 2, 3, 4)) == false
czyIstnieje ((x: Int) => x < 5) (List(1, 2, 3, 4)) == true
czyIstnieje ((x: Int) => x == 3) (List(3)) == true
czyIstnieje ((x: Int) => x != 3) (List(3)) == false
czyIstnieje ((x: Int) => x > 0) (Nil) == false
czyIstnieje ((s: String) => s.length() > 3) (List("ok", "bez", "rutyna")) == true

//czyIstnieje ((x: Float) => x == 1.23) (List(1.0, 1.4f, 1.23f)) == true
