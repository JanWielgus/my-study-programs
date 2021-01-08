#pragma once
#include <string>


class Table
{
	std::string name;
	int* array;
	int arraySize;

	static const std::string DefaultName;
	static const std::string CopyNameSuffix;
	static const int DefaultArraySize;

public:
	Table();
	Table(std::string name, int arraySize);
	Table(const Table& other);
	Table(Table&& toMove) noexcept;
	~Table();
	
	Table& operator=(const Table& other);
	Table& operator=(Table&& toMove) noexcept;
	
	Table operator+(const Table& other) const;

	Table operator<<(int val) const;
	Table operator>>(int val) const;

	void shiftLeft();
	void shiftRight();

	void setName(std::string name);
	bool setNewSize(int newArraySize);
	Table* clone(); // !!! this method should not exist and is really dangerous for the code !!!

	bool setValueAt(int index, int newValue);
	void print();

	int getSize();


private:
	void printTextAndName(std::string text);
};

