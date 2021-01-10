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



    // Wejsciowka test


    cout << "Test dynamic cast" << endl;

    
    MySmartPointer<Base> basePtr(new Derivative());
    cout << basePtr->getVal() << endl;

    MySmartPointer<Derivative> fooPtr = MySmartPointer<Base>::dynamicCast<Derivative>(basePtr);
    cout << basePtr->getVal() << endl;

    {
        MySmartPointer<Derivative> fooTempPtr = MySmartPointer<Base>::dynamicCast<Derivative>(basePtr);
        cout << basePtr->getVal() << endl;
        
        fooTempPtr->setVal(2);
    }

    cout << basePtr->getVal() << endl;



    cout << "Test move" << endl;
    

    MySmartPointer<Base> basePtrToMove(new Derivative());

    MySmartPointer<Derivative> fooMovedFromBase = MySmartPointer<Base>::dynamicCast<Derivative>(std::move(basePtrToMove));

    //basePtrToMove->showVal(); // <---- throw exception

    fooMovedFromBase->showVal();

    


    return 0;
}


