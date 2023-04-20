package com.exam;

public class ExMethod {
	
	public static void main(String[] args) {
		//다음과 같이 출력되도록 프로그램을 작성
		//*******************
		//* 포로리님 환영합니다. *
		//*******************
		//*******************
		//* 너부리님 환영합니다. *
		//*******************
		//*******************
		//* 호랭이님 환영합니다. *
		//*******************
		
//		  System.out.println("*******************");
//		  System.out.println("* 포로리님 환영합니다. *");
//		  System.out.println("*******************");
//		  
//		  System.out.println("*******************");
//		  System.out.println("* 너부리님 환영합니다. *");
//		  System.out.println("*******************");
//		  
//		  System.out.println("*******************");
//		  System.out.println("* 호랭이님 환영합니다. *");
//		  System.out.println("*******************");
	
//		welcome("포로리");
//		welcome("너부리");
//		welcome("호랭이");
//	}
//	
//	public static void welcome(String name) {
//		System.out.println("*******************");
//		System.out.println("* "+ name +"님 환영합니다. *");
//		System.out.println("*******************");
//		
//	}
	
		//2개의 숫자를 받아서, 두 수를 곱한 결과를 돌려주는
		//multi 함수를 정의하세요. 
		
	System.out.println(multi(10,20));	
	System.out.println(multi(20,10));
	multi(10,20);	
	System.out.println("*****************************");		
	System.out.println(multi(10,20));
	ex(10,20);
	System.out.println(ex1(10,20));
	System.out.println(ex2(10,20));
	System.out.println(ex3(10,20));
	}

	public static double multi(double n, double m) {
		return n*m;
//		System.out.println(n*m);
	}
	
	public static void ex(double n, double m) {
//		return n*m;
		System.out.println(n*m);
	}
	
	public static double ex1(double n, double m) {
		return n+m+1;
	}
	
	public static double ex2(double n, double m) {
		return 2*n+m+3;
	}
	
	public static double ex3(double n, double m) {
		double z = 2*n+m+3;
		return z;
	}
	
}
