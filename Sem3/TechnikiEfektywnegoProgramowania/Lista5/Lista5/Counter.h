#pragma once

class Counter
{
private:
	int counter;

public:
	Counter();
	Counter(int initialValue);

	int increment();
	int decrement();
	int get();
};

