#include <iostream>
#include "StaticTree.h"
#include "DynamicTree.h"
#include <vector>

using std::cout;
using std::endl;



// function prototypes
void nodeStaticTest();
void nodeStaticMoveSubtreeTest();
void nodeDynamicMoveSubtreeTest();


vector<TreeStatic> split_trees(NodeStatic* node);
vector<TreeDynamic> split_trees(NodeDynamic* node);
vector<TreeStatic> split_treesGood(NodeStatic* node);
vector<TreeDynamic> split_treesGood(NodeDynamic* node);
void badSplitTreesTest();
void goodSplitTreesTest();




int main()
{
    nodeStaticTest();
    cout << endl;


    nodeStaticMoveSubtreeTest();
    cout << endl;

    nodeDynamicMoveSubtreeTest();
    cout << endl;

    badSplitTreesTest();
    goodSplitTreesTest();


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





// bad:
//https://pastebin.com/kun7m3hX

vector<TreeStatic> split_trees(NodeStatic* node)
{
    vector<TreeStatic> trees;
    trees.push_back(TreeStatic(*node));

    vector<NodeStatic>& children = *node->getChildren();
    for (int i = 0; i < children.size(); i++)
    {
        vector<TreeStatic> temp = split_trees(&children[i]);
        trees.insert(trees.end(), temp.begin(), temp.end());
    }

    children.clear();

    return trees;
}


vector<TreeDynamic> split_trees(NodeDynamic* node)
{
    vector<TreeDynamic> trees;
    trees.push_back(TreeDynamic(node));

    vector<NodeDynamic*>& children = *node->getChildren();
    for (int i = 0; i < children.size(); i++)
    {
        vector<TreeDynamic> temp = split_trees(children[i]);
        trees.insert(trees.end(), temp.begin(), temp.end());
    }

    children.clear();

    return trees;
}


// good:


vector<TreeStatic> split_treesGood(NodeStatic* node)
{
    vector<TreeStatic> trees;

    while (node->getChildrenNumber() > 0)
    {
        NodeStatic* curChild = node->getChild(0);
        trees.push_back(TreeStatic(*curChild));
        node->removeChild(curChild);
    }

    return trees;
}


vector<TreeDynamic> split_treesGood(NodeDynamic* node)
{
    vector<TreeDynamic> trees;

    while (node->getChildrenNumber() > 0)
    {
        NodeDynamic* curChild = node->getChild(0);
        trees.push_back(TreeDynamic(curChild));
        node->removeChild(curChild);
    }

    return trees;
}


void badSplitTreesTest()
{
    cout << "tree split" << endl;
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

    vector<TreeStatic> testArray = split_trees(&root);
    cout << "Bad test: " << endl;
    for (int i = 0; i < testArray.size(); i++)
        cout << testArray[i].getRoot()->getValue() << endl;
    cout << "test end" << endl;
}


void goodSplitTreesTest()
{
    cout << "tree split" << endl;
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

    vector<TreeStatic> testArray = split_treesGood(&root);
    cout << "Good test: " << endl;
    for (int i = 0; i < testArray.size(); i++)
        cout << testArray[i].getRoot()->getValue() << endl;
    cout << "test end" << endl;
}


