#pragma once

#include "Counter.h"
#include "TestClasses.h"



template <class T>
class MySmartPointer
{
private:
	Counter* refCounter;
	T* pointer;


public:
	MySmartPointer(T* pointer)
		:MySmartPointer(pointer, new Counter())
	{
	}

	MySmartPointer(const MySmartPointer& other)
	{
		pointer = other.pointer;
		refCounter = other.refCounter;
		refCounter->increment();
	}

private:
	MySmartPointer(T* pointer, Counter* refCounter)
	{
		this->pointer = pointer;
		this->refCounter = refCounter;
		refCounter->increment();
	}

public:

	~MySmartPointer()
	{
		if (refCounter != NULL && refCounter->decrement() == 0)
		{
			delete pointer;
			delete refCounter;
		}
	}

	T& operator*()
	{
		return *pointer;
	}

	T* operator->()
	{
		return pointer;
	}

	MySmartPointer& operator=(const MySmartPointer& other)
	{
		if (&other != this)
		{
			if (refCounter != NULL && refCounter->decrement() == 0)
			{
				delete pointer;
				delete refCounter;
			}

			pointer = other.pointer;
			refCounter = other.refCounter;
			refCounter->increment();
		}

		return *this;
	}
};


