// Jan Wielgus



// zadanie 1
def flatten[A](xss: List[List[A]]): List[A] = {
    if (xss == Nil) Nil
    else xss.head ::: flatten(xss.tail)
}

flatten(List(List(5, 6), List(1, 2, 3))) == List(5, 6, 1, 2, 3)
flatten(List(Nil, Nil)) == Nil
flatten(List(List(5, 6), Nil)) == List(5, 6)
flatten(List(List(5))) == List(5)


// zadanie 2
def count[A] (x: A, xs: List[A]): Int = {
    if (xs == Nil) 0
    else (if (xs.head == x) 1 else 0) + count(x, xs.tail)
}

count ('a', List('a', 'l', 'a')) == 2
count ('b', List('q', 'w', 'w', 'a')) == 0
count ('w', List('q', 'w', 'w', 'a')) == 2
count ('c', Nil) == 0



// zadanie 3
def replicate [A] (x: A, n: Int): List[A] = {
    if (n < 0) throw new Exception("Negative number")
    else if (n == 0) Nil
    else if (n == 1) List(x)
    else x :: replicate(x, n - 1)
}

// replicate ('a', -5) // throw exception
replicate ("ab", 0) == Nil
replicate (5, 1) == List(5)
replicate ('g', 2) == List('g', 'g')
replicate ("la",3) == List("la", "la", "la")




// zadanie 4
def sqrList (xs: List[Int]): List[Int] =
    if (xs == Nil) Nil
    else (xs.head * xs.head) :: sqrList(xs.tail)

sqrList(List(1, 2, 3, -4)) == List(1, 4, 9, 16)
sqrList(Nil) == Nil
sqrList(List(-10)) == List(100)





// Zadanie 5
def palindrome [A] (xs: List[A]): Boolean =
    xs == xs.reverse

palindrome (List('a', 'l', 'a')) == true
palindrome (Nil) == true
palindrome(List(5, 7, 2, 0, 1, 0, 2, 7, 5)) == true
palindrome (List("as", "as")) == true
palindrome (List("ik", "s")) == false
palindrome (List(7)) == true
palindrome (List('a', 'l', 'b')) == false



// zadanie 6
def listLength [A](xs: List[A]): Int =
    if (xs == Nil) 0
    else 1 + listLength(xs.tail);

listLength (List(5)) == 1
listLength (List("asdf")) == 1
listLength (Nil) == 0
listLength (List('a', 'b', 'b', 'd')) == 4

