#pragma once

#include <vector>
#include <string>
#include <iostream>
#include "Consts.h"
#include <queue>


template <class T>
class TreeDynamic;


template <class T>
class NodeDynamic
{
private:
	std::vector<NodeDynamic*> children;
	NodeDynamic* parentNode;
	T value;

public:
	NodeDynamic()
	{
		value = 0;
		parentNode = NULL;
	}

	~NodeDynamic()
	{
		for (int i = 0; i < children.size(); i++)
			delete children[i];
	}

	NodeDynamic(const NodeDynamic& other)
	{
		value = other.value;
		parentNode = NULL;

		for (int i = 0; i < other.children.size(); i++)
			children.push_back(new NodeDynamic(*other.children[i]));
	}

	NodeDynamic& operator=(const NodeDynamic& other)
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

	void setValue(int newValue)
	{
		value = newValue;
	}

	int getValue()
	{
		return value;
	}

	int getChildrenNumber()
	{
		return children.size();
	}

	void addNewChild()
	{
		addNewChild(new NodeDynamic());
	}

	void addNewChild(NodeDynamic* nodeToAdd)
	{
		nodeToAdd->parentNode = this;
		children.push_back(nodeToAdd);
	}

	bool removeChild(const NodeDynamic* childToRemove)
	{
		for (int i = 0; i < children.size(); i++)
			if (children[i] == childToRemove)
			{
				children.erase(children.begin() + i);
				return true;
			}

		return false;
	}

	NodeDynamic* getParent()
	{
		return parentNode;
	}

	NodeDynamic* getChild(int childIndex)
	{
		if (childIndex < 0 || childIndex >= children.size())
			return NULL;

		return children[childIndex];
	}

	std::vector<NodeDynamic*>* getChildren()
	{
		return &children;
	}

	void print()
	{
		std::cout << value;
	}

	void printAllBelow()
	{
		std::queue<NodeDynamic*> q;
		q.push(this);

		while (!q.empty())
		{
			NodeDynamic* curNode = q.front();
			curNode->print();
			std::cout << Consts::Space;
			q.pop();

			for (int i = 0; i < curNode->children.size(); i++)
				q.push(curNode->children[i]);
		}
	}
};





template <class T>
class TreeDynamic
{
private:
	NodeDynamic<T>* root;

public:
	TreeDynamic()
	{
		root = new NodeDynamic<T>();
	}

	TreeDynamic(NodeDynamic<T>* root)
	{
		this->root = root;
	}

	~TreeDynamic()
	{
		if (root != NULL)
			delete root;
	}

	TreeDynamic(const TreeDynamic& other)
	{
		root = new NodeDynamic<T>(*other.root);
	}

	TreeDynamic& operator=(const TreeDynamic& other)
	{
		if (this != &other)
			root = new NodeDynamic<T>(*other.root);

		return *this;
	}

	NodeDynamic<T>* getRoot()
	{
		return root;
	}

	void printTree()
	{
		root->printAllBelow();
		std::cout << std::endl;
	}

	static bool moveSubtree(NodeDynamic<T>* parentNode, NodeDynamic<T>*& newChildNode)
	{
		if (parentNode == newChildNode)
			return false;

		NodeDynamic<T>* oldParent = newChildNode->getParent();

		parentNode->addNewChild(newChildNode);

		if (oldParent != NULL)
			oldParent->removeChild(newChildNode);
		else
			newChildNode = NULL; // if this is root

		return true;
	}
};



