#include <iostream>
#include "MySmartPointer.h"
#include "TestClasses.h"

using std::cout;
using std::endl;


// https://pastebin.com/gEKKcJzb



int main()
{
    std::cout << "Hello World!\n";


    MySmartPointer<int> testSmartPointer(new int);
    *testSmartPointer = 5;
    cout << *testSmartPointer << endl;



    return 0;
}


