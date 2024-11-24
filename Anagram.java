
import java.util.Random;


/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		//Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		//Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");

		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		str1 = str1.toLowerCase();
		str2 = str2.toLowerCase();
		str1 = str1.replace(" ", "");
		str2 = str2.replace(" ", "");
		StringBuilder sb1 = new StringBuilder(str1);
		StringBuilder sb2 = new StringBuilder(str2);
		int lengthStr1 = str1.length();
		int lengthStr2 = str2.length();		
		if(lengthStr1 != lengthStr2) return false;
		for(int i = 0; i < lengthStr1; i++){
			for(int j = 0; j < lengthStr2; j++){
				if(sb1.charAt(0) == sb2.charAt(j)){
					sb1.deleteCharAt(0);
					sb2.deleteCharAt(j);
					if(sb1.isEmpty() && sb2.isEmpty()) return true;
					break;
				}
			}
		}
		return false;
	}
	public static String preProcess(String str) {
		str = str.toLowerCase();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < str.length(); i++) {
			if (Character.isLetter(str.charAt(i))) {
				//adding data into the StringBuilder
				sb.append(str.charAt(i));
			}
		}
		//return the String contained in the StringBuilder
		return sb.toString();
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		StringBuilder sb = new StringBuilder();
		int num = str.length();
		Random rnd = new Random();
		int indexToChange;
		for (int i = 0; i < num; i++)
		{
			if(str.length() > 1){
				indexToChange = rnd.nextInt(str.length() - 1);
			}
			else  indexToChange = 0;
			sb.append(str.charAt(indexToChange));
			str = str.substring(0, indexToChange) + str.substring(indexToChange + 1);
		}
		return sb.toString();
	}
}