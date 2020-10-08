// Lista1.cpp : This file contains the 'main' function. Program execution begins and ends there.
// Author: Jan Wielgus
// Date: 08.10.2020

#include <iostream>

using std::cout;
using std::endl;


const int ArraySizeX = 5;
const int ArraySizeY = 3;


void allocTableAdd5(int size);
bool allocTable2Dim(int*** table, int sizeX, int sizeY);
bool deallocTable2Dim(int** table, int sizeX);

void fillIntArrayAscending(int* array, int size, int initialValue);
void showIntArray(int* array, int size);
void fill2DimArray(int** array, int sizeX, int sizeY);
void show2DimArray(int** array, int sizeX, int sizeY);
void showDebugMessage(const char* prefix, bool flag);


int main()
{
    allocTableAdd5(10);


    cout << endl << endl;


    int** twoDimArray;
    bool allocationFlag = allocTable2Dim(&twoDimArray, ArraySizeX, ArraySizeY);
    showDebugMessage("Two dimensional array allocation", allocationFlag);
    fill2DimArray(twoDimArray, ArraySizeX, ArraySizeY);
    show2DimArray(twoDimArray, ArraySizeX, ArraySizeY);
    bool deallocationFlag = deallocTable2Dim(twoDimArray, ArraySizeX);
    showDebugMessage("Two dimensional array deallocation", deallocationFlag);
}





void allocTableAdd5(int size)
{
    if (size <= 0)
        return;

    int* array = new int[size];

    fillIntArrayAscending(array, size, 5);
    showIntArray(array, size);

    delete[] array;
}


bool allocTable2Dim(int*** table, int sizeX, int sizeY)
{
    if (sizeX <= 0 || sizeY <= 0)
        return false;

    *table = new(std::nothrow) int*[sizeX];
    if (!(*table))
        return false;

    for (int i = 0; i < sizeX; i++)
    {
        (*table)[i] = new int[sizeY];

        if (!(*table)[i])
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
    cout << "Array with address of first element " << array << endl;
    for (int i = 0; i < size; i++)
        cout << i + 1 << ". " << array[i] << endl;
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
        cout << "successful";
    else
        cout << "unsuccessful";
    cout << endl;
}

