#include <iostream>
#include "DynamicTree.h"
#include <vector>

using std::cout;
using std::endl;
using std::vector;



// function prototypes
void nodeDynamicMoveSubtreeTest();

template <class T>
vector<TreeDynamic<T>> splitTrees(NodeDynamic<T>* node);

void splitTreesTest();




int main()
{
    nodeDynamicMoveSubtreeTest();
    cout << endl;

    splitTreesTest();
    cout << endl;

    return 0;
}






void nodeDynamicMoveSubtreeTest()
{
    cout << "NodeDynamic Move Subtree Test" << endl;

    TreeDynamic<int> tree1;

    tree1.getRoot()->addNewChild();
    tree1.getRoot()->addNewChild();
    tree1.getRoot()->addNewChild();
    tree1.getRoot()->getChild(0)->setValue(1);
    tree1.getRoot()->getChild(1)->setValue(2);
    tree1.getRoot()->getChild(2)->setValue(3);

    tree1.getRoot()->getChild(2)->addNewChild();
    tree1.getRoot()->getChild(2)->getChild(0)->setValue(4);


    TreeDynamic<int> tree2;
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


    NodeDynamic<int>* parentNode = tree1.getRoot()->getChild(2);
    NodeDynamic<int>* newChildNode = tree2.getRoot()->getChild(0);
    TreeDynamic<int>::moveSubtree(parentNode, newChildNode);

    cout << "Tree 1 after move: ";
    tree1.printTree();
    cout << "Tree 2 after move: ";
    tree2.printTree();
}




template <class T>
vector<TreeDynamic<T>> splitTrees(NodeDynamic<T>* node)
{
    vector<TreeDynamic<T>> trees;

    while (node->getChildrenNumber() > 0)
    {
        NodeDynamic<T>* curChild = node->getChild(0);
        trees.push_back(TreeDynamic<T>(curChild));
        node->removeChild(curChild);
    }

    return trees;
}




void splitTreesTest()
{
    cout << "Tree split test" << endl;
    NodeDynamic<int> root;

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

    cout << "Root before split has " << root.getChildrenNumber() << " children" << endl;

    vector<TreeDynamic<int>> testArray = splitTrees(&root);
    cout << "Showing values of each root after split: " << endl;
    for (int i = 0; i < testArray.size(); i++)
        cout << testArray[i].getRoot()->getValue() << endl;
    cout << "Now rool has " << root.getChildrenNumber() << " children" << endl;
    cout << "test end" << endl;
}


