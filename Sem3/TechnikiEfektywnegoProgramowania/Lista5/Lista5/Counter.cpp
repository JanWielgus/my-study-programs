#include "Counter.h"
#include <iostream>

using namespace std;


Counter::Counter()
{
	counter = 0;
}


Counter::Counter(int initialValue)
{
	counter = initialValue;
}


int Counter::increment()
{
	cout << "\tCounter: " << counter+1 << endl;
	return ++counter;
}


int Counter::decrement()
{
	cout << "\tCounter: " << counter-1 << endl;
	return --counter;
}


int Counter::get()
{
	return counter;
}
