package com.bridgelabz.mathoperations;

//added functional interface which has 1 abstract method calculate
@FunctionalInterface
interface MyMathFuntion
{
	int calculate(int a , int b);
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
		System.out.println("Addition is "+ add.calculate(10, 5));
		System.out.println("Subtract is "+ Subtract.calculate(10, 5));
		System.out.println("Multiply is "+ Multiply.calculate(10, 5));
		System.out.println("Divide is "+ Division.calculate(10, 5));
	}
}
