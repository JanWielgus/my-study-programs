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
			destruct();
			
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


	template <class A>
	friend class MySmartPointer;


	template <class Dst>
	static MySmartPointer<Dst> dynamicCast(const MySmartPointer<T>& toCast)
	{
		MySmartPointer<Dst> newSmart(dynamic_cast<Dst*>(toCast.pointer), toCast.refCounter);
		return newSmart;
	}

	
	template <class Dst>
	static MySmartPointer<Dst> dynamicCast(MySmartPointer<T>&& toCast)
	{
		MySmartPointer<Dst> newSmart(dynamic_cast<Dst*>(toCast.pointer), toCast.refCounter);
		toCast.pointer = NULL;
		toCast.refCounter = NULL;
		return newSmart;
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


