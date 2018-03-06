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
	float s1Len, s2Len;

	public HashStringSimilarity(String s1, String s2, int sLength)
	{
		this.s1 = s1;
		this.s2 = s2;
		sLen = sLength;
		s1Len = vectorLength(s1);
		s2Len = vectorLength(s2);
	}

	public float vectorLength(String input){
		float length = 0;
		int key;
		HashTable table = new HashTable(50);
		for(int i = 0; i <= input.length() - sLen; i++){
			key = 0;
			for(int j = 0; j < sLen; j++){
				key += (int) input.charAt(i + j);
				//System.out.println("Ascii: " + input.charAt(j + i) + " Value: " + (int) input.charAt(j + i));
			}
			Tuple tuple = new Tuple(key, input.substring(i, i + sLen));
			table.add(tuple);
		}

		for(int i = 0; i < table.buckets.length; i++){
			if(table.buckets[i] != null){
				length += (float) Math.pow(table.buckets[i].size(), 2);
				System.out.println("bucket " + table.buckets[i] + " size: " + table.buckets[i].size());
			}
		}
		return (float) Math.sqrt(length);
	}

	public float lengthOfS1()
	{
		return s1Len;
	}

	public float lengthOfS2()
	{
		return s2Len;
	}

	public float similarity()
	{
		return 0;
	}
    public static void main(String[] args){
       HashStringSimilarity test = new HashStringSimilarity("hello", "world", 2);
       System.out.println(test.vectorLength("helloolleh"));
       //System.out.println(test.vectorLength("world"));
    }
}