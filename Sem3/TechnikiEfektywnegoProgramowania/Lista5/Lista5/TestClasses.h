#pragma once
#include <iostream>

using std::cout;
using std::endl;


class Base
{
protected:
    int val = 5;

public:
    virtual int getVal()
    {
        return val;
    }

    void showVal()
    {
        cout << getVal() << endl;
    }
};


class Foo : public Base
{
public:
    Foo()
    {
        val = 10;
    }

    void setVal(int newVal)
    {
        val = newVal;
    }
};
