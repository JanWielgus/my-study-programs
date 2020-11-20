#pragma once

#include <vector>

using std::vector;


class NodeStatic
{
private:
	vector<NodeStatic> children;
	NodeStatic* parentNode;
	int value;

public:
	NodeStatic();
	~NodeStatic();

	void setValue(int newValue);
	int getChildrenNumber();
	void addNewChild();
	bool removeChild(const NodeStatic* childToRemove);
	NodeStatic* getParent();
	NodeStatic* getChild(int childOffset);

	void print();
	void printAllBelow();
	void printUp();
};




class TreeStatic
{
private:
	NodeStatic root;

public:
	NodeStatic* getRoot();
	void printTree();

	static bool moveSubtree(NodeStatic* parentNode, NodeStatic* newChildNode);
};
