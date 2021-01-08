// Lista1.cpp : This file contains the 'main' function. Program execution begins and ends there.
// Author: Jan Wielgus
// Date: 08.10.2020

#include <iostream>
#include "CTable.h"
#include "DmbConstants.h"

using std::cout;
using std::endl;


static const int ArraySizeX = 5;
static const int ArraySizeY = 3;


// exercise 1 - 3
// Better option for table is array, but left as in exercise
void allocTableAdd5(int size);
bool allocTable2Dim(int*** table, int sizeX, int sizeY);
bool deallocTable2Dim(int** table, int sizeX);

void fillIntArrayAscending(int* array, int size, int initialValue);
void showIntArray(int* array, int size);
void fill2DimArray(int** array, int sizeX, int sizeY);
void show2DimArray(int** array, int sizeX, int sizeY);
void showDebugMessage(const char* prefix, bool flag);

// exercise 4
void modify_tab(Table* tab, int newSize); // will modify
void modify_tab(Table tab, int newSize); // won't modify

void testCTableStaticAndDynamicAllocation();


int main()
{
    allocTableAdd5(DmbConsts::TestArraySize);


    cout << endl << endl;


    int** twoDimArray;
    bool allocationFlag = allocTable2Dim(&twoDimArray, ArraySizeX, ArraySizeY);
    showDebugMessage("Two dimensional array allocation", allocationFlag);
    fill2DimArray(twoDimArray, ArraySizeX, ArraySizeY);
    show2DimArray(twoDimArray, ArraySizeX, ArraySizeY);
    bool deallocationFlag = deallocTable2Dim(twoDimArray, ArraySizeX);
    showDebugMessage("Two dimensional array deallocation", deallocationFlag);


    cout << endl;
    testCTableStaticAndDynamicAllocation();


    cout << endl << DmbConsts::ProgramEndMessage << endl;





    cout << endl;
    cout << "List 2 tests" << endl;
    
    
    Table tab0, tab1;
    tab0.setNewSize(6);
    tab1.setNewSize(4);
    
    for (int i = 0; i < tab0.getSize(); i++)
        tab0.setValueAt(i, i + 1);
    for (int i = 0; i < tab1.getSize(); i++)
        tab1.setValueAt(i, i + 51);

    cout << "Concatenation: " << endl;
    (tab0 + tab1).print();

    tab0 = tab1;
    tab1.setValueAt(2, 123);
    tab0.print();
    tab1.print();


    tab1 >> 2;
    tab1.print();
}





void allocTableAdd5(int size)
{
    if (size <= 0)
        return;

    int* array = new(std::nothrow) int[size];
    if (!array) // if (array == nullptr)
        return;

    fillIntArrayAscending(array, size, DmbConsts::FiveValue);
    showIntArray(array, size);

    delete[] array;
}


bool allocTable2Dim(int*** table, int sizeX, int sizeY)
{
    if (sizeX <= 0 || sizeY <= 0)
        return false;

    *table = new(std::nothrow) int*[sizeX];
    if (!(*table)) // if (*table == nullptr)
        return false;

    for (int i = 0; i < sizeX; i++)
    {
        (*table)[i] = new(std::nothrow) int[sizeY];

        if (!(*table)[i]) // if ((*table)[i] == nullptr
        {
            deallocTable2Dim(*table, i);
            return false;
        }
    }

    return true;
}


// SizeY is not necessary
bool deallocTable2Dim(int** table, int sizeX)
{
    if (sizeX <= 0)
        return false;

    for (int i = 0; i < sizeX; i++)
        delete[] table[i];
    delete[] table;

    return true;
}


void fillIntArrayAscending(int* array, int size, int initialValue = 0)
{
    for (int i = 0; i < size; i++)
        array[i] = initialValue++;
}


void showIntArray(int* array, int size)
{
    cout << DmbConsts::IntArrayShowMsg << array << endl;
    for (int i = 0; i < size; i++)
        cout << i + 1 << DmbConsts::DotWithSpace << array[i] << endl;
}


void fill2DimArray(int** array, int sizeX, int sizeY)
{
    if (sizeY < 0) // Don't have to check X because of first for loop
        return;

    for (int x = 0; x < sizeX; x++)
    {
        for (int y = 0; y < sizeY; y++)
            array[x][y] = x * sizeY + y;
    }
}


void show2DimArray(int** array, int sizeX, int sizeY)
{
    if (sizeY <= 0) // Don't have to check X because of first for loop
        return;

    for (int x = 0; x < sizeX; x++)
    {
        for (int y = 0; y < sizeY; y++)
            cout << array[x][y] << '\t';
        cout << endl;
    }
    cout << endl;
}


void showDebugMessage(const char* prefix, bool flag)
{
    cout << prefix << " ";
    if (flag)
        cout << DmbConsts::SuccessfulText;
    else
        cout << DmbConsts::UnsuccessfulText;
    cout << endl;
}





void modify_tab(Table* tab, int newSize)
{
    tab->setNewSize(newSize);
}



void modify_tab(Table tab, int newSize)
{
    tab.setNewSize(newSize);
}


void testCTableStaticAndDynamicAllocation()
{
    cout << DmbConsts::StaticAllocationText << endl;
    Table test1;
    Table test2(DmbConsts::StaticTestObject2Name, 7);
    Table test3(test1);

    cout << DmbConsts::DynamicAllocationText << endl;
    Table* dynamicTest1 = new Table(DmbConsts::DynamicTestObject1Name, 123);
    dynamicTest1->setNewSize(5);
    Table* dynamicArray1 = new Table[3];
    dynamicArray1[1].setNewSize(100);
    cout << DmbConsts::DeallocationMsg << endl;
    delete dynamicTest1;
    delete[] dynamicArray1;

    cout << DmbConsts::TestFunctionEndMsg << endl;
}

