package com.bridgelabz.mathoperations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

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

		//creating Number list
		List<Integer> numberList = new ArrayList<Integer>();
		for (int i = 0; i < 6; i++)
		{
			numberList.add(i);
		}

		//method 1 using iterator
		System.out.println("\nNumbers using Iterator");
		Iterator<Integer> it = numberList.iterator();
		//number list using iterator
		while (it.hasNext()) 
		{
			Integer integer = it.next();
			System.out.println(integer);
		}

		//method 2 using consumer interface
		class Myconsumer implements Consumer<Integer>
		{
			public void accept(Integer t) 
			{			
				System.out.println(t);
			}		

		}
		
		Myconsumer action = new Myconsumer();
		System.out.println("\nNumbers using consumer interface");
		numberList.forEach(action);

		//method 3 Traversing using anonymous consumer
		System.out.println("\nNumbers using anonymous consumer");
		numberList.forEach(new Consumer<Integer>() 
		{
			public void accept(Integer t) 
			{
				System.out.println(t);	
			}
		}
		);
		
		//method 4 explicit lambda function
		System.out.println("\nNumbers using explicit lambda");
		Consumer<Integer> numberListAction = n -> {
			System.out.println(n);
		};
		numberList.forEach(numberListAction);

		//method 5 implicit lambda function
		System.out.println("\nNumbers using implicit lambda");
		numberList.forEach(n -> {
			System.out.println(n);
		});
		
		//method 6 using functional interface to convert integer to string
		System.out.println("\nConverting number list into double value");
		Function<Integer, Double> doubleFunction = Integer::doubleValue;
		numberList.forEach(n->{
			System.out.println(doubleFunction.apply(n));
		});
		
		//method 7 check even number in list
		System.out.println("\nCheck Even number in list and printing them");
		Predicate<Integer> checkEven = n -> n%2 == 0 ;
		numberList.forEach(n->{
			if(checkEven.test(n))
			{
				System.out.println("Value " + n );
			}
		});
		
		/*
		 * Performing some actions using streams
		 */
		
		//uc 2.1 iterate number using streams
		System.out.println("\nNumbers printed using streams are");
		numberList.stream()
					.forEach(n->{
							System.out.println(n);
					});
		
		//uc 2.2 transform integer into double
		System.out.println("\nNumbers converted into double using stream are ");
		numberList.stream()
					.map(Integer::doubleValue) //converting integer to double
					.forEach(n->System.out.println(n)); // printing converted values
	}
}
