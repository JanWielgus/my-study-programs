#include "CTable.h"
#include <iostream>
#include "DmbConstants.h"

using std::string;
using std::cout;
using std::endl;


const string Table::DefaultName = "no_name";
const string Table::CopyNameSuffix = "_copy";
const int Table::DefaultArraySize = 10;


Table::Table()
{
	array = NULL; // array = nullptr
	arraySize = 0; 
	setName(DefaultName);
	setNewSize(DefaultArraySize);
	printTextAndName(DmbConsts::DefaultCtorMsg);
}


Table::Table(string name, int arraySize)
{
	this->array = NULL; // this->array = nullptr
	this->arraySize = 0;
	setName(name);
	setNewSize(arraySize);
	printTextAndName(DmbConsts::ParametrizedCtorMsg);
}


Table::Table(const Table& other)
{
	array = NULL; // array = nullptr
	arraySize = 0;

	setName(other.name + CopyNameSuffix);

	setNewSize(other.arraySize);
	for (int i = 0; i < arraySize; i++)
		array[i] = other.array[i];

	printTextAndName(DmbConsts::CopyCtorMsg);
}


Table::~Table()
{
	if (array) // !!! if (array != nullptr)
		delete[] array;

	printTextAndName(DmbConsts::DeletingMsg);
}


Table& Table::operator=(const Table& other)
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


Table Table::operator+(const Table& other)
{
	Table newTable;
	newTable.setNewSize(arraySize + other.arraySize);
	
	for (int i = 0; i < arraySize; i++)
		newTable.array[i] = array[i];

	for (int i = 0; i < other.arraySize; i++)
		newTable.array[arraySize + i] = other.array[i];
	
	return newTable;
}


void Table::setName(string name)
{
	this->name = name;
}


bool Table::setNewSize(int newArraySize)
{
	if (newArraySize <= 0)
		return false;

	if (newArraySize == arraySize)
		return true;

	int* newArray = new(std::nothrow) int[newArraySize];
	if (!newArray) // if (array == nullptr)
		return false;

	if (array) // if (array  != nullptr)
	{
		int copyUpperBound = newArraySize < arraySize ? newArraySize : arraySize; // min
		for (int i = 0; i < copyUpperBound; i++)
			newArray[i] = array[i];

		delete[] array;
	}

	array = newArray;
	arraySize = newArraySize;
	return true;
}


Table* Table::clone()
{
	return new Table(*this); // !!! this should be used in every place where this method is used !!!
}


bool Table::setValueAt(int index, int newValue)
{
	if (index >= arraySize)
		return false;

	array[index] = newValue;
	return true;
}


void Table::print()
{
	using namespace DmbConsts;
	cout << SingleQuote << name << SingleQuote << endl;

	for (int i = 0; i < arraySize; i++)
		cout << array[i] << Space << Space;
	cout << endl;
}


int Table::getSize()
{
	return arraySize;
}



void Table::printTextAndName(string text)
{
	using namespace DmbConsts;
	cout << text << Colon << Space << SingleQuote << name << SingleQuote << endl;
}
