#include <iostream>
#include "StaticTree.h"
#include "DynamicTree.h"

using std::cout;
using std::endl;



// function prototypes
void nodeStaticTest();
void nodeStaticMoveSubtreeTest();
void nodeDynamicMoveSubtreeTest();


int main()
{
    nodeStaticTest();
    cout << endl;


    nodeStaticMoveSubtreeTest();
    cout << endl;

    nodeDynamicMoveSubtreeTest();
    cout << endl;

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
    cout << endl;
}




void nodeStaticMoveSubtreeTest()
{
    cout << "NodeStatic Move Tree Test" << endl;

    TreeStatic tree1;

    tree1.getRoot()->addNewChild();
    tree1.getRoot()->addNewChild();
    tree1.getRoot()->addNewChild();
    tree1.getRoot()->getChild(0)->setValue(1);
    tree1.getRoot()->getChild(1)->setValue(2);
    tree1.getRoot()->getChild(2)->setValue(3);

    tree1.getRoot()->getChild(2)->addNewChild();
    tree1.getRoot()->getChild(2)->getChild(0)->setValue(4);


    TreeStatic tree2;
    tree2.getRoot()->setValue(50);

    tree2.getRoot()->addNewChild();
    tree2.getRoot()->addNewChild();
    tree2.getRoot()->getChild(0)->setValue(54);
    tree2.getRoot()->getChild(1)->setValue(55);

    tree2.getRoot()->getChild(0)->addNewChild();
    tree2.getRoot()->getChild(0)->addNewChild();
    tree2.getRoot()->getChild(0)->getChild(0)->setValue(56);
    tree2.getRoot()->getChild(0)->getChild(1)->setValue(57);

    tree2.getRoot()->getChild(0)->getChild(0)->addNewChild();
    tree2.getRoot()->getChild(0)->getChild(0)->getChild(0)->setValue(58);



    cout << "Tree 1 before move: ";
    tree1.printTree();
    cout << "Tree 2 before move: ";
    tree2.printTree();

    
    NodeStatic* parentNode = tree1.getRoot()->getChild(2);
    NodeStatic* newChildNode = tree2.getRoot()->getChild(0);
    TreeStatic::moveSubtree(parentNode, newChildNode);

    cout << "Tree 1 after move: ";
    tree1.printTree();
    cout << "Tree 2 after move: ";
    tree2.printTree();
}




void nodeDynamicMoveSubtreeTest()
{
    cout << "NodeDynamic Move Subtree Test" << endl;

    TreeDynamic tree1;

    tree1.getRoot()->addNewChild();
    tree1.getRoot()->addNewChild();
    tree1.getRoot()->addNewChild();
    tree1.getRoot()->getChild(0)->setValue(1);
    tree1.getRoot()->getChild(1)->setValue(2);
    tree1.getRoot()->getChild(2)->setValue(3);

    tree1.getRoot()->getChild(2)->addNewChild();
    tree1.getRoot()->getChild(2)->getChild(0)->setValue(4);


    TreeDynamic tree2;
    tree2.getRoot()->setValue(50);

    tree2.getRoot()->addNewChild();
    tree2.getRoot()->addNewChild();
    tree2.getRoot()->getChild(0)->setValue(54);
    tree2.getRoot()->getChild(1)->setValue(55);

    tree2.getRoot()->getChild(0)->addNewChild();
    tree2.getRoot()->getChild(0)->addNewChild();
    tree2.getRoot()->getChild(0)->getChild(0)->setValue(56);
    tree2.getRoot()->getChild(0)->getChild(1)->setValue(57);

    tree2.getRoot()->getChild(0)->getChild(0)->addNewChild();
    tree2.getRoot()->getChild(0)->getChild(0)->getChild(0)->setValue(58);



    cout << "Tree 1 before move: ";
    tree1.printTree();
    cout << "Tree 2 before move: ";
    tree2.printTree();


    NodeDynamic* parentNode = tree1.getRoot()->getChild(2);
    NodeDynamic* newChildNode = tree2.getRoot()->getChild(0);
    TreeDynamic::moveSubtree(parentNode, newChildNode);

    cout << "Tree 1 after move: ";
    tree1.printTree();
    cout << "Tree 2 after move: ";
    tree2.printTree();
}


