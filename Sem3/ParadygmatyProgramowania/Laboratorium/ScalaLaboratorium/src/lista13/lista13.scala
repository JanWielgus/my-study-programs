// Jan Wielgus



class DuplicatedKey(msg: String) extends Exception(msg)

class Dictionary[K, V] private (private val rep: List[(K, V)])(implicit ev: K => Ordered[K]){

    def lookup(key: K): Option[V] = ???

    def insert(keyValue: (K,V)): Dictionary[K, V] = ???

    def delete(key: K): Dictionary[K, V] = ???

    override def toString: String = ???
}

object Dictionary {
    def apply[K, V](elems: (K, V)*)(implicit ev: K => Ordered[K]): Dictionary[K, V] =
        new Dictionary[K, V](elems.toList)(ev)
}

/*
object TestDict {
  //class Point( ... }

  def main(args: Array[String]): Unit = {
    val dict = Dictionary("pies"->"dog", "kot"->"cat", "slon"->"elephant", "ptak"->"bird")
    println(dict)
    println(dict.lookup("pies"))
    println(dict.lookup("papuga"))
    val dict1 = dict.insert("papuga"->"parrot")
    println(dict1)
    println(dict1.lookup("papuga"))
    println(dict1.delete("papuga"))

    //val points = Dictionary(new Point(1,1) -> "p11", new Point -> "p00")
    //println(points)
    //println(points.insert(new Point(0.5,0)-> "p0.5"))
  }
}
*/
