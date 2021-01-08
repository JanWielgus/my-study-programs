#pragma once

#include "Counter.h"
#include "TestClasses.h"



template <class T>
class MySmartPointer
{
	T* pointer;
	Counter* refCounter;


public:
	MySmartPointer(T* pointer)
	{
		setUpForNewPointer(pointer, new Counter());
	}

	MySmartPointer(const MySmartPointer& other)
	{
		setUpForNewPointer(other.pointer, other.refCounter);
	}

	MySmartPointer(MySmartPointer&& toMove)
	{
		pointer = toMove.pointer;
		refCounter = toMove.refCounter;
		toMove.pointer = NULL;
		toMove.refCounter = NULL;
	}

private:
	MySmartPointer(T* pointer, Counter* refCounter)
	{
		setUpForNewPointer(pointer, refCounter);
	}

public:

	~MySmartPointer()
	{
		destruct();
	}

	MySmartPointer& operator=(const MySmartPointer& other)
	{
		if (this != other)
		{
			destruct();
			setUpForNewPointer(other.pointer, other.refCounter);
		}

		return *this;
	}

	MySmartPointer& operator=(MySmartPointer&& toMove) noexcept
	{
		if (this != &toMove)
		{
			pointer = toMove.pointer;
			refCounter = toMove.refCounter;
			toMove.pointer = NULL;
			toMove.refCounter = NULL;
		}

		return *this;
	}

	T& operator*()
	{
		return *pointer;
	}

	T* operator->()
	{
		return pointer;
	}


private:
	void setUpForNewPointer(T* pointer, Counter* refCounter)
	{
		this->pointer = pointer;
		this->refCounter = refCounter;
		refCounter->increment();
	}
	
	
	void destruct() const
	{
		if (refCounter != NULL && refCounter->decrement() == 0)
		{
			delete pointer;
			delete refCounter;
		}
	}
};


