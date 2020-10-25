#include <iostream>

using namespace std;


static const string TestMsg = "Test";
static const string TestEndMsg = "Test end";



class Node
{
private:
    Node* child1;
    Node* child2;
    Node* parent;

public:
    Node()
    {
        child1 = NULL;
        child2 = NULL;
        parent = NULL;
    }

    Node(Node* parent)
        : Node()
    {
        this->parent = parent;
    }

    ~Node()
    {
        if (child1 != NULL)
        {
            delete child1;
            child1 = NULL;
        }
        
        if (child2 != NULL)
        {
            delete child2;
            child2 = NULL;
        }
    }

    bool setChild(Node* node)
    {
        bool returnFlag = true;
        if (child1 == NULL)
        {
            child1 = node;
            child1->parent = this;
        }
        else if (child2 == NULL)
        {
            child2 = node;
            child2->parent = this;
        }
        else
            returnFlag = false;

        return returnFlag;
    }

    Node* getChild1()
    {
        return child1;
    }

    Node* getChild2()
    {
        return child2;
    }
};



int main()
{
    cout << TestMsg << endl;

    Node* root = new Node();
    root->setChild(new Node());
    root->setChild(new Node());
    root->getChild1()->setChild(new Node());
    root->getChild2()->setChild(new Node());
    root->getChild1()->getChild1()->setChild(new Node());

    delete root;

    cout << TestEndMsg << endl;
}


