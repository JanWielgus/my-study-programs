#include "DynamicTree.h"
#include <iostream>
#include "Consts.h"
#include <queue>

using std::cout;
using std::endl;
using std::queue;



NodeDynamic::NodeDynamic()
{
	value = 0;
	parentNode = NULL;
}


NodeDynamic::~NodeDynamic()
{
	for (int i = 0; i < children.size(); i++)
		delete children[i];
}


NodeDynamic::NodeDynamic(const NodeDynamic& other)
{
	value = other.value;
	parentNode = NULL;
	
	for (int i = 0; i < other.children.size(); i++)
		children.push_back(new NodeDynamic(*other.children[i]));
}


NodeDynamic& NodeDynamic::operator=(const NodeDynamic& other)
{
	if (this != &other)
	{
		value = other.value;
		parentNode = NULL;

		for (int i = 0; i < other.children.size(); i++)
			children.push_back(new NodeDynamic(*other.children[i]));
	}
	return *this;
}


void NodeDynamic::setValue(int newValue)
{
	value = newValue;
}


int NodeDynamic::getValue()
{
	return value;
}


int NodeDynamic::getChildrenNumber()
{
	return children.size();
}


void NodeDynamic::addNewChild()
{
	addNewChild(new NodeDynamic());
}


void NodeDynamic::addNewChild(NodeDynamic* nodeToAdd)
{
	nodeToAdd->parentNode = this;
	children.push_back(nodeToAdd);
}


bool NodeDynamic::removeChild(const NodeDynamic* childToRemove)
{
	for (int i = 0; i < children.size(); i++)
		if (children[i] == childToRemove)
		{
			children.erase(children.begin() + i);
			return true;
		}

	return false;
}


NodeDynamic* NodeDynamic::getParent()
{
	return parentNode;
}


NodeDynamic* NodeDynamic::getChild(int childOffset)
{
	if (childOffset < 0 || childOffset >= children.size())
		return NULL;

	return children[childOffset];
}

vector<NodeDynamic*>* NodeDynamic::getChildren()
{
	return &children;
}


void NodeDynamic::print()
{
	cout << value;
}


void NodeDynamic::printAllBelow()
{
	queue<NodeDynamic*> q;
	q.push(this);

	while (!q.empty())
	{
		NodeDynamic* curNode = q.front();
		curNode->print();
		cout << Consts::Space;
		q.pop();

		for (int i = 0; i < curNode->children.size(); i++)
			q.push(curNode->children[i]);
	}
}




TreeDynamic::TreeDynamic()
{
	root = new NodeDynamic();
}

TreeDynamic::TreeDynamic(NodeDynamic* root)
{
	this->root = root;
}


TreeDynamic::~TreeDynamic()
{
	if (root != NULL)
		delete root;
}


TreeDynamic::TreeDynamic(const TreeDynamic& other)
{
	root = new NodeDynamic(*other.root);
}


TreeDynamic& TreeDynamic::operator=(const TreeDynamic& other)
{
	if (this != &other)
	{
		root = new NodeDynamic(*other.root);
	}
	return *this;
}


NodeDynamic* TreeDynamic::getRoot()
{
	return root;
}


void TreeDynamic::printTree()
{
	root->printAllBelow();
	cout << endl;
}


bool TreeDynamic::moveSubtree(NodeDynamic* parentNode, NodeDynamic*& newChildNode)
{
	if (parentNode == newChildNode)
		return false;

	NodeDynamic* oldParent = newChildNode->getParent();

	parentNode->addNewChild(newChildNode);

	if (oldParent != NULL)
		oldParent->removeChild(newChildNode);
	else
		newChildNode = NULL; // if this is root

	return true;
}



