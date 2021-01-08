// Jan Wielgus



// zadanie 1
def whileLoop(pred: () => Boolean)(f: () => Unit): Unit = {
    if (pred()) {
        f()
        whileLoop(pred)(f)
    }
}

var x=7
var l = List[Int]()
whileLoop(x>0)({l = x::l;x=x-1})
l == List(1, 2, 3, 4, 5, 6, 7)






// zadanie 2 a) swap
def swap [A](tab: Array[A], i: Int, j: Int): Unit = {
    val temp = tab(i)
    tab(i) = tab(j)
    tab(j) = temp
}


val testArr = Array(1, 2, 3, 4, 5)
swap(testArr, 0, 4)
testArr.toList == List(5, 2, 3, 4, 1)



// zadanie 2 b) partition
def choose_pivot [A](tab: Array[A], m: Int, n: Int): A = {
    tab((m+n)/2)
}

def partition (tab: Array[Int], l: Int, r: Int): (Int, Int) = {
    var i = l
    var j = r
    val pivot = choose_pivot(tab, l, r)
    while (i <= j) {
        while (tab(i) < pivot) i = i+1
        while (pivot < tab(j)) j = j-1
        if (i <= j) {
            swap(tab, i, j)
            i = i+1
            j = j-1
        }
    }
    (i, j)
}


// zadanie 2 c) quick
def quick (tab: Array[Int], l: Int, r: Int): Unit = {
    if (l < r) {
        val (i, j) = partition(tab, l, r)
        if (j-1 < r-i) {
            quick(tab, l, j)
            quick(tab, i, r)
        }
        else {
            quick(tab, i, r)
            quick(tab, l, j)
        }
    }
}



// zadanie 2 d) quicksort
def quicksort (tab: Array[Int]): Unit = {
    quick(tab, 0, tab.length - 1)
}

val t1 = Array(4, 8, 1, 12, 7, 3, 1, 9)
quicksort(t1)
t1.toList == List(1, 1, 3, 4, 7, 8, 9, 12)































