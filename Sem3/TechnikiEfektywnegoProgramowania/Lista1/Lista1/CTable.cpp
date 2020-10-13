#include "CTable.h"
#include <iostream>
#include "DmbConstants.h"

using std::string;
using std::cout;
using std::endl;


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


bool CTable::setNewSize(int arrayLength)
{
	if (!array) // should be: if (array != nullptr)
		delete[] array;

	arraySize = arrayLength;
	array = new(std::nothrow) int[arraySize];

	if (!array)
		return false;
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
