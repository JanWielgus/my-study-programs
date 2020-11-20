#include "StaticTree.h"
#include <iostream>
#include "Consts.h"

using std::cout;
using std::endl;


NodeStatic::NodeStatic()
{
	value = 0;
	parentNode = NULL;
}


NodeStatic::~NodeStatic()
{
}


void NodeStatic::setValue(int newValue)
{
	value = newValue;
}


int NodeStatic::getChildrenNumber()
{
	return children.size();
}


void NodeStatic::addNewChild()
{
	NodeStatic newNode;
	newNode.parentNode = this;
	children.push_back(newNode);
}


bool NodeStatic::removeChild(const NodeStatic* childToRemove)
{
	for (int i = 0; i < children.size(); i++)
		if (&children[i] == childToRemove)
		{
			children.erase(children.begin() + i);
			return true;
		}

	return false;
}

NodeStatic* NodeStatic::getParent()
{
	return parentNode;
}


NodeStatic* NodeStatic::getChild(int childOffset)
{
	if (childOffset < 0 || childOffset >= children.size())
		return NULL;

	return &children[childOffset];
}


void NodeStatic::print()
{
	cout << value;
}


void NodeStatic::printAllBelow()
{
	print();

	for (int i = 0; i < children.size(); i++)
	{
		cout << Consts::Space;
		children[i].printAllBelow();
	}
}


void NodeStatic::printUp()
{
	print();
	cout << Consts::Space;
	if (parentNode != NULL)
		parentNode->printUp();
}







NodeStatic* TreeStatic::getRoot()
{
	return &root;
}


void TreeStatic::printTree()
{
	root.printAllBelow();
	cout << endl;
}


bool TreeStatic::moveSubtree(NodeStatic* parentNode, NodeStatic* newChildNode)
{
	if (parentNode == newChildNode)
		return false;

	parentNode->addNewChild();
	int newChildIndex = parentNode->getChildrenNumber() - 1;

	*parentNode->getChild(newChildIndex) = *newChildNode;

	NodeStatic* oldParent = newChildNode->getParent();
	if (oldParent != NULL)
		oldParent->removeChild(newChildNode);

	return true;
}
