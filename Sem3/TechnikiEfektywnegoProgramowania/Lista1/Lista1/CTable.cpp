#include "CTable.h"
#include <iostream>

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
	printTextAndName("bezparametrowy");
}


CTable::CTable(string name, int arrayLength)
{
	array = NULL; // this is bad
	setName(name);
	setNewSize(arrayLength);
	printTextAndName("z parametrami");
}


CTable::CTable(const CTable& other)
{
	array = NULL; // this is bad

	setName(other.name + CopyNameSuffix);

	setNewSize(other.arraySize);
	for (int i = 0; i < arraySize; i++)
		array[i] = other.array[i];

	printTextAndName("kopiujacy");
}


CTable::~CTable()
{
	if (!array) // !!! should be: if (array != nullptr)
		delete[] array;

	printTextAndName("usuwam");
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
	cout << text << ": '" << name << '\'' << endl;
}
