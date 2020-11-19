#include <iostream>
#include "StaticTree.h"

using std::cout;
using std::endl;



// function prototypes
void nodeStaticTest();


int main()
{
    std::cout << "Hello World!\n";

    nodeStaticTest();


    return 0;
}




void nodeStaticTest()
{
    cout << "NodeStatic test" << endl;
    NodeStatic root;

    root.addNewChild();
    root.addNewChild();
    root.getChild(0)->setValue(1);
    root.getChild(1)->setValue(2);

    root.getChild(0)->addNewChild();
    root.getChild(0)->addNewChild();
    root.getChild(0)->getChild(0)->setValue(11);
    root.getChild(0)->getChild(1)->setValue(12);

    root.getChild(1)->addNewChild();
    root.getChild(1)->addNewChild();
    root.getChild(1)->getChild(0)->setValue(21);
    root.getChild(1)->getChild(1)->setValue(22);

    root.printAllBelow();
    cout << endl;


    cout << "Printup test" << endl;
    root.getChild(0)->getChild(1)->printUp();
}


