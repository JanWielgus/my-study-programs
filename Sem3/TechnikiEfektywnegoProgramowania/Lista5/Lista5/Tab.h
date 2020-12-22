#pragma once

class Tab
{
private:
	static const int DefaultTabSize = 10;

	int* array;
	int size;


public:
	Tab();
	Tab(const Tab& other);
	Tab(Tab&& other);
	~Tab();

	Tab& operator=(const Tab& other);
	Tab& operator=(Tab&& other);

	bool setSize(int newSize);
	int getSize();


private:
	void copy(const Tab& other);
};

