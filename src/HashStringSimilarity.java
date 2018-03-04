// LEAVE THIS FILE IN THE DEFAULT PACKAGE
//  (i.e., DO NOT add 'package cs311.pa1;' or similar)

// DO NOT MODIFY THE EXISTING METHOD SIGNATURES
//  (you may, however, add member fields and additional methods)

// DO NOT INCLUDE LIBRARIES OUTSIDE OF THE JAVA STANDARD LIBRARY
//  (i.e., you may include java.util.ArrayList etc. here, but not junit, apache commons, google guava, etc.)

/**
* @author Hugh Potter
*/

public class HashStringSimilarity
{
	// member fields and other member methods
	String s1, s2;
	int sLen;

	public HashStringSimilarity(String s1, String s2, int sLength)
	{
		this.s1 = s1;
		this.s2 = s2;
		sLen = sLength;
	}

	public float vectorLength(String input){
		int length = 0;
		HashTable table = new HashTable(40); //number of alphanumeric char
		for(int i = 0; i < input.length(); i++){
			Tuple tuple = new Tuple(input.charAt(i) - '0', input.substring(i, i));
			table.add(tuple);
		}
		for(int i = 0; i < table.numBuckets; i++){
			length += table.buckets[i].size();
		}
		return 0;
	}

	public float lengthOfS1()
	{
		String temp = s1;
		int len = 0;
		int charCount = 0;
		for(int i = 0; i < temp.length(); i++){
			for(int j = 0; j < temp.length(); j++){
				if(temp.charAt(i) == temp.charAt(j)){
					charCount++;
				}
			}
			charCount *= charCount;
		}
		return s1.length();
	}

	public float lengthOfS2()
	{
		return s2.length();
	}

	public float similarity()
	{
		return 0;
	}
}