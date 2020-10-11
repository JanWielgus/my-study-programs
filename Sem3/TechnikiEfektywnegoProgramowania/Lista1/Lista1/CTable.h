#pragma once
#include <string>


class CTable
{
private:
	std::string name;
	int* array;
	int arraySize;

	static const string DefaultName;
	static const int DefaultArraySize;
	static const string CopyNameSuffix;

public:
	CTable();
	CTable(std::string name, int arrayLength);
	CTable(const CTable& other);
	~CTable();

	void setName(std::string name);
	bool setNewSize(int arrayLength);
	CTable* clone(); // !!! this method should not exist and is really dangerous for the code !!!


private:
	void printTextAndName(std::string text);
};

