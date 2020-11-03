#pragma once
#include <string>


class Table
{
private:
	std::string name;
	int* array;
	int arraySize;

	static const std::string DefaultName;
	static const std::string CopyNameSuffix;
	static const int DefaultArraySize;

public:
	Table();
	Table(std::string name, int arrayLength);
	Table(const Table& other);
	~Table();
	
	Table& operator=(const Table& other); // should go in pair with copy constructor
	Table operator+(const Table& other);

	void setName(std::string name);
	bool setNewSize(int newArraySize);
	Table* clone(); // !!! this method should not exist and is really dangerous for the code !!!

	bool setValueAt(int index, int newValue);
	void print();

	int getSize();


private:
	void printTextAndName(std::string text);
};

