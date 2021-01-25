// Jan Wielgus

package lista10

/**
 * A class for immutable linked lists representing ordered collections of elements of type A.
 *
 * This class comes with two implementing case classes lista10.Nil and lista10.:: that implement the abstract members isEmpty, head and tail.
 *
 */
sealed abstract class MyList[+A] {
  /**
   * Adds an element at the beginning of this list.
   *
   * @param elem      the element to prepend.
   * @return a list which contains x as first element and which continues with this list. 
   * @example 1 :: MyList(2, 3) = MyList(2, 3).::(1) = MyList(1, 2, 3)
   */
    def ::[S >: A](elem: S): MyList[S] = elem :: this

  /**
   * Adds the elements of a given list in front of this list.
   * @param prefix The list elements to prepend.
   * @return a list resulting from the concatenation of the given list prefix and this list.
   */
    def :::[S >: A](prefix: MyList[S]): MyList[S] =
      if (prefix.isEmpty) this
      else prefix.head :: prefix.tail ::: this

  /**
   * Selects the first element of this iterable collection.
   * @return    the first element of this iterable collection.
   */
  def head: A

  /**
   * Tests whether the list is empty.
   * @return true if the list contains no elements, false otherwise.
   */
  def isEmpty: Boolean

  /**
   * Returns the rest of the collection without its first element.
   * @return The rest of the collection without its first element.
   */
  def tail: MyList[A]

  override def toString: String = {
    def aux(ys: MyList[A]): String =
      ys match {
        case x :: Nil => s"$x)"
        case x :: xs => s"$x, ${aux(xs)}"
        case Nil => ")"
      }
    "MyList(" + aux(this)
  }

}


case object Nil extends MyList[Nothing] {
  override def isEmpty = true
  
  /* *
   * @throws [[java.util.NoSuchElementException]]
   */ 
  @throws(classOf[NoSuchElementException]) 
  def head: Nothing =
    throw new NoSuchElementException("head of empty list")
	
  @throws(classOf[UnsupportedOperationException])	
  def tail: MyList[Nothing] =
    throw new UnsupportedOperationException("tail of empty list")
}


final case class ::[+A](hd: A, tl: MyList[A]) extends MyList[A] {
  def head = hd
  def tail = tl
  override def isEmpty: Boolean = false
}

/* or shorter
final case class ::[A](head: A, tail: MyList[A]) extends MyList[A] {
  override def isEmpty: Boolean = false
}
*/

object MyList {
  def apply[A](elems: A*): MyList[A] =
    elems.foldRight[MyList[A]] (Nil) ((elem, acc) => elem :: acc)
}


abstract class Zwierz(val imie: String = "bez imienia"){
  override def toString = s"${getClass.getSimpleName}($imie)"
}
class Pies(imie: String = "bez imienia")  extends Zwierz(imie)
class Kot(imie: String = "bez imienia")  extends Zwierz(imie)

/*
object TestMyListInvariant {
  def main(args: Array[String]): Unit = {
    val psy = new Pies() :: new Pies("Azor") :: Nil()
    val koty = new Kot("Mruczek") :: new Kot() :: Nil()
    println(s"psy = $psy")
    println(s"koty = $koty")
	println(s"koty i kot = ${koty ::: MyList(new Kot("Kicia"))}")
	println(s"lista liczb = ${MyList(1, 2, 3)}")
    println(s"pusta lista = ${Nil()}")	
	
    val zwierzaki: MyList[Zwierz] = (new Kot("Mruczek")).asInstanceOf[Zwierz] :: psy.asInstanceOf[MyList[Zwierz]]
    println(s"zwierzaki = $zwierzaki")
	println(s"koty i psy = ${koty.asInstanceOf[MyList[Zwierz]] ::: psy.asInstanceOf[MyList[Zwierz]]}")
	println(s"koty i psy = ${MyList((new Kot("Kicia")).asInstanceOf[Zwierz], (new Pies("As")).asInstanceOf[Zwierz])}")

  }
}
*/


object TestMyListCovariant {
  def main(args: Array[String]): Unit = {
    val psy = new Pies() :: new Pies("Azor") :: Nil
    val koty = new Kot("Mruczek") :: new Kot() :: Nil
    println(s"psy = $psy")
    println(s"koty = $koty")
	println(s"koty i kot = ${koty ::: MyList(new Kot("Kicia"))}")
	println(s"lista liczb = ${MyList(1, 2, 3)}")
    println(s"pusta lista = $Nil")
	
    val zwierzaki = new Kot("Mruczek") :: psy
    println(s"zwierzaki = $zwierzaki")
    println(s"koty i psy = ${koty ::: psy}")
    println(s"koty i psy = ${MyList(new Kot("Kicia"), new Pies("As"))}")
  }
}


