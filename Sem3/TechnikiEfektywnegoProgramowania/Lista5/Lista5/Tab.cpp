#include "Tab.h"
#include <iostream>


Tab::Tab()
{
	array = new int[DefaultTabSize];
	size = DefaultTabSize;
}


Tab::Tab(const Tab& other)
{
	copy(other);
	std::cout << "Copy ";
}


Tab::Tab(Tab&& other)
{
	array = other.array;
	size = other.size;
	other.array = NULL;	
	std::cout << "MOVE ";
}


Tab::~Tab()
{
	if (array != NULL)
		delete array;
	std::cout << "Destr ";
}


Tab& Tab::operator=(const Tab& other)
{
	if (&other != this)
	{
		if (array != NULL) delete array;
		copy(other);
		std::cout << "op= ";
	}

	return *this;
}


Tab& Tab::operator=(Tab&& other)
{
	if (&other != this)
	{
		array = other.array;
		size = other.size;
		other.array = NULL;
		std::cout << "MOVE op=";
	}

	return *this;
}


bool Tab::setSize(int newSize)
{
	if (newSize <= 0)
		return false;

	if (newSize == size)
		return true;

	int* newArray = new int[newSize];

	if (array != NULL) // if (array  != nullptr)
	{
		int copyUpperBound = newSize <= size ? newSize : size; // min
		for (int i = 0; i < copyUpperBound; i++)
			newArray[i] = array[i];

		delete[] array;
	}

	array = newArray;
	size = newSize;
	return true;
}


int Tab::getSize()
{
	return size;
}


void Tab::copy(const Tab& cOther)
{
	array = new int[cOther.size];
	size = cOther.size;
	for (int ii = 0; ii < cOther.size; ii++)
		array[ii] = cOther.array[ii];
}
