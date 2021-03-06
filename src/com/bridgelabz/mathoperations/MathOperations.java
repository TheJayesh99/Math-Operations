package com.bridgelabz.mathoperations;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
		for (int i = 1; i < 6; i++)
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
		class MyConsumer implements Consumer<Integer>
		{
			public void accept(Integer t) 
			{			
				System.out.println(t);
			}		

		}
		
		MyConsumer action = new MyConsumer();
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

		
		//uc 2.3 transform integer into double and store them in list
		System.out.println("\nNumbers converted into double using stream and store them to list ");
		List<Double> doublelist = numberList.stream()
								.map(Integer::doubleValue) //converting integer to double
								.collect(Collectors.toList()); // storing converted values into new list
		System.out.println("value in new list are "+ doublelist);
	
		//uc 2.4 filter even numbers and store them
		System.out.println("\nEven Numbers in number list are");
		List<Integer>evenNumberList = numberList.stream()
												.filter(checkEven) //filtering even values
												.collect(Collectors.toList());
		System.out.println(evenNumberList);

		//uc 2.5 filter even numbers and display 1st even number
		System.out.println("\nFirst Even Numbers in number list is");
		numberList.stream()
				.filter(checkEven) //filtering even values
				.peek(n->System.out.println(n)) //peaking value
				.findFirst() //find 1st element in stream
				.orElse(null); //if there is not any even number then it should return null
		
		//uc2.6 find minimum and maximum value
		Integer minimumValue = numberList.stream()
									.filter(checkEven)
									.min(Comparator.comparing(Integer::intValue)) //checks minimum values 
									.orElse(null);
		
		Integer maximumValue = numberList.stream()
									.filter(checkEven)
									.max(Comparator.comparing(Integer::intValue)) //checks maximum values
									.orElse(null);
		System.out.println("\nIn the list minimum Even number is "+ minimumValue +" and maximum is "+ maximumValue);
		
		//uc2.7 find sum and average
		Integer sumInteger = numberList.stream()
										.reduce(0,(a,b)->a+b); //returns the sum of the number
		System.out.println("Sum of numbers in the list is "+ sumInteger);
		int count = (int) numberList.stream()
									.count(); //returns the number of element present in it
		System.out.println("Average of the numbers present in the list is "+ sumInteger/count);
		
		//uc2.8 find at least 1 number is even or all numbers are even
		boolean allEvenNumber = numberList.stream()
				.allMatch(checkEven); //returns true if all value matches 
		boolean atleast1Even = numberList.stream()
				.anyMatch(checkEven);//returns true if any 1 value match 
		System.out.println("\nAll numbers are even in the list = " +allEvenNumber
				+"\nAt least 1 number present in the list = " +atleast1Even);
	
		//uc2.9 sort the list elements
		List<Integer> newSortedList = numberList.stream()
										.sorted() //sorting the values
										.collect(Collectors.toList()); //returns new sorted list
		System.out.println("\nList after sorting " + newSortedList);
	}
}
