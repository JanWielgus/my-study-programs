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

	NodeDynamic(const NodeDynamic& other) = delete;
	NodeDynamic& operator=(const NodeDynamic& other) = delete;

	void setValue(int newValue);
	int getValue();
	int getChildrenNumber();
	void addNewChild();
	void addNewChild(NodeDynamic* nodeToAdd);
	bool removeChild(const NodeDynamic* childToRemove);
	NodeDynamic* getParent();
	NodeDynamic* getChild(int childOffset);

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

	TreeDynamic(const TreeDynamic& other) = delete;
	TreeDynamic& operator=(const TreeDynamic& other) = delete;

	NodeDynamic* getRoot();
	void printTree();

	static bool moveSubtree(NodeDynamic* parentNode, NodeDynamic*& newChildNode);
};

