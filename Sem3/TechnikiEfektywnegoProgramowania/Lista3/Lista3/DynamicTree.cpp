#include "DynamicTree.h"
#include <iostream>
#include "Consts.h"

using std::cout;
using std::endl;



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


void NodeDynamic::setValue(int newValue)
{
	value = newValue;
}


int NodeDynamic::getChildrenNumber()
{
	return children.size();
}


void NodeDynamic::addNewChild()
{
	NodeDynamic* newNode = new NodeDynamic();
	newNode->parentNode = this;
	children.push_back(newNode);
}


//TODO: getChild


void NodeDynamic::print()
{
	cout << value;
}


void NodeDynamic::printAllBelow()
{
	print();

	for (int i = 0; i < children.size(); i++)
	{
		cout << Consts::Space;
		children[i]->printAllBelow();
	}
}




TreeDynamic::TreeDynamic()
{
	root = new NodeDynamic();
}


TreeDynamic::~TreeDynamic()
{
	delete root;
}


NodeDynamic* TreeDynamic::getRoot()
{
	return root;
}


void TreeDynamic::printTree()
{
	root->printAllBelow();
}



