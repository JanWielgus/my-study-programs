#include "CTable.h"
#include <iostream>
#include "DmbConstants.h"
#include <algorithm>

using std::string;
using std::cout;
using std::endl;
using std::min;


const string CTable::DefaultName = "no_name";
const int CTable::DefaultArraySize = 10;
const string CTable::CopyNameSuffix = "_copy";


CTable::CTable()
{
	array = NULL; // this is bad
	setName(DefaultName);
	setNewSize(DefaultArraySize);
	printTextAndName(DmbConsts::DefaultCtorMsg);
}


CTable::CTable(string name, int arrayLength)
{
	array = NULL; // this is bad
	setName(name);
	setNewSize(arrayLength);
	printTextAndName(DmbConsts::ParametrizedCtorMsg);
}


CTable::CTable(const CTable& other)
{
	array = NULL; // this is bad

	setName(other.name + CopyNameSuffix);

	setNewSize(other.arraySize);
	for (int i = 0; i < arraySize; i++)
		array[i] = other.array[i];

	printTextAndName(DmbConsts::CopyCtorMsg);
}


CTable::~CTable()
{
	if (!array) // !!! should be: if (array != nullptr)
		delete[] array;

	printTextAndName(DmbConsts::DeletingMsg);
}


CTable& CTable::operator=(const CTable& other)
{
	if (this != &other)
	{
		setName(other.name + CopyNameSuffix);

		setNewSize(other.arraySize);
		for (int i = 0; i < arraySize; i++)
			array[i] = other.array[i];
	}

	return *this;
}


void CTable::setName(string name)
{
	this->name = name;
}


bool CTable::setNewSize(int newArraySize)
{
	if (newArraySize == arraySize)
		return true;

	int* newArray = new(std::nothrow) int[newArraySize];
	if (!newArray) // if (array == nullptr)
		return false;

	if (array) // if (array  != nullptr)
	{
		for (int i = 0; i < min(newArraySize, arraySize); i++)
			newArray[i] = array[i];

		delete[] array;
	}

	array = newArray;
	arraySize = newArraySize;
	return true;
}


CTable* CTable::clone()
{
	return new CTable(*this); // !!! this should be used in every place where this method is used !!!
}



void CTable::printTextAndName(string text)
{
	using namespace DmbConsts;
	cout << text << ColonWithSpace << SingleQuoteSign << name << SingleQuoteSign << endl;
}
