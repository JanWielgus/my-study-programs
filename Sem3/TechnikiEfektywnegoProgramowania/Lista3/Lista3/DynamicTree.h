#pragma once

#include <vector>

using std::vector;


class TreeDynamic;

class NodeDynamic
{
private:
	vector<NodeDynamic*> children;
	NodeDynamic* parentNode;
	int value;

public:
	NodeDynamic();
	~NodeDynamic();

	void setValue(int newValue);
	int getChildrenNumber();
	void addNewChild();
	TreeDynamic* getChild(int childOffset);

	void print();
	void printAllBelow();
};



class TreeDynamic
{
private:
	NodeDynamic* root;

public:
	TreeDynamic();
	~TreeDynamic();

	NodeDynamic* getRoot();
	void printTree();
};

