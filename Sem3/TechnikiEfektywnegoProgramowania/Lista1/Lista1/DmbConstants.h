#pragma once

namespace DmbConsts
{
	typedef const char* const ConstTextType;

	const int FiveValue = 5; // this is out of sense
	const int TestArraySize = 10;

	// Main constants
	ConstTextType IntArrayShowMsg = "Array with address of first element ";
	ConstTextType SuccessfulText = "successful";
	ConstTextType UnsuccessfulText = "unsuccessful";
	ConstTextType StaticAllocationText = "Static allocation";
	ConstTextType DynamicAllocationText = "Dynamic allocation";
	ConstTextType StaticTestObject2Name = "Object 2 name";
	ConstTextType DynamicTestObject1Name = "Dynamic object 1";
	ConstTextType DeallocationMsg = "Deallocation of dynamically allocated objects";
	ConstTextType TestFunctionEndMsg = "End of test function";
	ConstTextType DotWithSpace = ". ";
	ConstTextType ProgramEndMessage = "Code execution successful";


	// CTable constants
	ConstTextType DefaultCtorMsg = "bezparametrowy";
	ConstTextType ParametrizedCtorMsg = "z parametrami";
	ConstTextType CopyCtorMsg = "kopiujacy";
	ConstTextType DeletingMsg = "deleting";
	const char Colon = ':';
	const char Space = ' ';
	const char SingleQuote = '\'';
}
