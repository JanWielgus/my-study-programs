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
	int getValue();
	int getChildrenNumber();
	void addNewChild();
	bool removeChild(const NodeStatic* childToRemove);
	NodeStatic* getParent();
	NodeStatic* getChild(int childOffset);
	vector<NodeStatic>* getChildren();

	void print();
	void printAllBelow();
	void printUp();
};




class TreeStatic
{
public:
	TreeStatic();
	TreeStatic(NodeStatic root);

private:
	NodeStatic root;

public:
	NodeStatic* getRoot();
	void printTree();

	static bool moveSubtree(NodeStatic* parentNode, NodeStatic* newChildNode);
};
