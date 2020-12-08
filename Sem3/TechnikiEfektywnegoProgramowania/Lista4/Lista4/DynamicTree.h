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
	std::vector<NodeDynamic<T>*> children;
	NodeDynamic<T>* parentNode;
	T value;

public:
	NodeDynamic()
	{
		value = T();
		parentNode = NULL;
	}

	NodeDynamic(const T& initValue)
	{
		value = initValue;
		parentNode = NULL;
	}

	NodeDynamic(const NodeDynamic<T>& other)
	{
		value = other.value;
		parentNode = NULL;

		for (int i = 0; i < other.children.size(); i++)
			children.push_back(new NodeDynamic(*other.children[i]));
	}

	NodeDynamic<T>& operator=(const NodeDynamic<T>& other)
	{
		if (this != &other)
		{
			value = other.value;
			parentNode = NULL;

			for (int i = 0; i < other.children.size(); i++)
				children.push_back(new NodeDynamic<T>(*other.children[i]));
		}
		return *this;
	}

	~NodeDynamic()
	{
		for (int i = 0; i < children.size(); i++)
			delete children[i];
	}

	void setValue(const T& newValue)
	{
		value = newValue;
	}

	T getValue()
	{
		return value;
	}

	int getChildrenNumber()
	{
		return children.size();
	}

	void addNewChild()
	{
		addNewChild(new NodeDynamic<T>());
	}

	void addNewChild(NodeDynamic<T>* nodeToAdd)
	{
		nodeToAdd->parentNode = this;
		children.push_back(nodeToAdd);
	}

	bool removeChild(const NodeDynamic<T>* childToRemove)
	{
		for (int i = 0; i < children.size(); i++)
			if (children[i] == childToRemove)
			{
				children.erase(children.begin() + i);
				return true;
			}

		return false;
	}

	NodeDynamic<T>* getParent()
	{
		return parentNode;
	}

	NodeDynamic<T>* getChild(int childIndex)
	{
		if (childIndex < 0 || childIndex >= children.size())
			return NULL;

		return children[childIndex];
	}

	std::vector<NodeDynamic<T>*>* getChildren()
	{
		return &children;
	}

	void print()
	{
		std::cout << value;
	}

	void printAllBelow()
	{
		std::queue<NodeDynamic<T>*> q;
		q.push(this);

		while (!q.empty())
		{
			NodeDynamic<T>* curNode = q.front();
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

	TreeDynamic(const TreeDynamic<T>& other)
	{
		root = new NodeDynamic<T>(*other.root);
	}

	TreeDynamic<T>& operator=(const TreeDynamic<T>& other)
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



