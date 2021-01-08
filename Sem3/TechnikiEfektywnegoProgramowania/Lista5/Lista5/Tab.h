#pragma once

class Tab
{
	static const int DefaultTabSize = 10;

	int* array;
	size_t size;


public:
	Tab();
	Tab(const Tab& other);
	Tab(Tab&& other) noexcept;
	~Tab();

	Tab& operator=(const Tab& other);
	Tab& operator=(Tab&& other) noexcept;

	bool setSize(size_t newSize);
	int getSize() const;


private:
	static void copyArray(const int* source, int* dest, size_t size);
	void destruct();
};

