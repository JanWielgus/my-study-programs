#include "Tab.h"
#include <iostream>


Tab::Tab()
{
	array = NULL;
	setSize(DefaultTabSize);
}


Tab::Tab(const Tab& other)
{
	array = NULL;
	setSize(other.size);
	copyArray(other.array, array, other.size);
}


Tab::Tab(Tab&& other) noexcept
{
	array = other.array;
	size = other.size;
	other.array = NULL;
	other.size = 0;
}


Tab::~Tab()
{
	destruct();
}


Tab& Tab::operator=(const Tab& other)
{
	if (&other != this)
	{
		destruct();
		setSize(other.size);
		copyArray(other.array, array, other.size);
	}

	return *this;
}


Tab& Tab::operator=(Tab&& other) noexcept
{
	if (&other != this)
	{
		destruct();
		array = other.array;
		size = other.size;
		other.array = NULL;
		other.size = 0;
	}

	return *this;
}


bool Tab::setSize(size_t newSize)
{
	if (newSize == size && array != NULL)
		return true;

	int* newArray = new int[newSize];

	if (array != NULL)
	{
		copyArray(array, newArray, std::min<size_t>(size, newSize));
		delete[] array;
	}

	array = newArray;
	size = newSize;
	return true;
}


int Tab::getSize() const
{
	return size;
}


void Tab::copyArray(const int* source, int* dest, size_t size)
{
	for (size_t i = 0; i < size; i++)
		dest[i] = source[i];
}


void Tab::destruct()
{
	if (array != NULL)
		delete array;
}
