package com.bridgelabz.mathoperations;

//added functional interface which has 1 abstract method calculate
@FunctionalInterface
interface MyMathFuntion
{
	int calculate(int a , int b);
	static void printResult(String operation,int a, int b, MyMathFuntion function)
	{
		System.out.println("Result of "+a+" "+operation+" "+b+" is "+function.calculate(a,b));
	}
}

public class MathOperations 
{
	public static void main(String[] args) 
	{
		//defining some lambda function to perform math operations
		MyMathFuntion add = Integer::sum;
		MyMathFuntion Subtract = (x,y) -> (x-y);
		MyMathFuntion Multiply = (x,y) -> (x*y);
		MyMathFuntion Division = (x,y) -> (x/y);
		
		//calling and printing values returned by functions
		System.out.println("Result using lambda functions");
		System.out.println("Addition is "+ add.calculate(10, 5));
		System.out.println("Subtract is "+ Subtract.calculate(10, 5));
		System.out.println("Multiply is "+ Multiply.calculate(10, 5));
		System.out.println("Divide is "+ Division.calculate(10, 5));
		
		//passing lambda function as function parameter
		System.out.println("\nResult by lambda functions as function parameter");
		MyMathFuntion.printResult("Addition", 10, 5, add);
		MyMathFuntion.printResult("Subtract", 10, 5, Subtract);
		MyMathFuntion.printResult("Multiply", 10, 5, Multiply);
		MyMathFuntion.printResult("Divide", 10, 5, Division);
	}
}
