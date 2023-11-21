package dynamic.prog;

import java.util.concurrent.atomic.AtomicInteger;

public class Palindrome {
	
	private boolean containsPalindrome(String word, int startIndex, int endIndex) {
		//Base case
		if (startIndex >= endIndex) {
			return true;
		}
		
		if (word.charAt(startIndex) != word.charAt(endIndex)) {
			return false;
		}
		
		return containsPalindrome(word, startIndex+1, endIndex-1);
	}
	
	public boolean containsPalindrome(String word) {
		return containsPalindrome(word, 0, word.length() - 1);
	}
	
	private void numberOfPalindrome(String word, int startIndex, int endIndex, AtomicInteger numPalindromes) {
		//Base case
		if (startIndex >= endIndex) {
			numPalindromes.incrementAndGet();
			return;
		}
		
		if (word.charAt(startIndex) != word.charAt(endIndex)) {
			return;
		}
		
		int start = startIndex+1;
		int end = endIndex-1;
		
		while (start <= end) {
			numberOfPalindrome(word, start, end, numPalindromes);
			start++;
			end--;
		}
	}
	
	public int numberOfPalindrome(String word) {
		AtomicInteger numPalindromes = new AtomicInteger(0);
		numberOfPalindrome(word, 0, word.length() - 1, numPalindromes);
		return numPalindromes.get();
	}
	
	public static void main(String[] args) {
		String word = "cabbac";
		Palindrome pal = new Palindrome();
		System.out.println(pal.containsPalindrome(word));
		System.out.println(pal.numberOfPalindrome(word));
	}
}