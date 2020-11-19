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





TreeStatic::TreeStatic()
{
}


TreeStatic::~TreeStatic()
{
}


NodeStatic* TreeStatic::getRoot()
{
	return &root;
}


void TreeStatic::printTree()
{
	root.printAllBelow();
}
