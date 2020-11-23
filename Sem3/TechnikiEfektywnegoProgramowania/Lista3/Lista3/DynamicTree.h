#pragma once

#include <vector>
#include <string>

using std::vector;
using std::string;


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

	NodeDynamic(const NodeDynamic& other);
	NodeDynamic& operator=(const NodeDynamic& other);

	void setValue(int newValue);
	int getValue();
	int getChildrenNumber();
	void addNewChild();
	void addNewChild(NodeDynamic* nodeToAdd);
	bool removeChild(const NodeDynamic* childToRemove);
	NodeDynamic* getParent();
	NodeDynamic* getChild(int childOffset);
	vector<NodeDynamic*>* getChildren();

	void print();
	void printAllBelow();
};



class TreeDynamic
{
private:
	NodeDynamic* root;

public:
	TreeDynamic();
	TreeDynamic(NodeDynamic* root);
	~TreeDynamic();

	TreeDynamic(const TreeDynamic& other);
	TreeDynamic& operator=(const TreeDynamic& other);

	NodeDynamic* getRoot();
	void printTree();

	static bool moveSubtree(NodeDynamic* parentNode, NodeDynamic*& newChildNode);
};

