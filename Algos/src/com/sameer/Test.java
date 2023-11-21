package com.sameer;

public class Test {
	
	public void testTransactionObserver(TransactionObserver to) {
		System.out.println(to.getClass().getSimpleName());
	}
	
	public static boolean eval1() {
		return true;
	}
	
	public static boolean eval2() {
		return false;
	}
	
	public boolean evaluate() {
		return Test.eval1() ? true : Test.eval2() ? true : false;
	}

	public static void main(String[] args) {
		String a1 = "sameer";
		String a2 = "khan";
		String key = String.format("%s:%s", a1,a2);
		System.out.println(key);
		
		System.out.println(Boolean.toString(true));
		
		//int oca = null;
		//System.out.println(oca);
	}

}
