// LEAVE THIS FILE IN THE DEFAULT PACKAGE
//  (i.e., DO NOT add 'package cs311.pa1;' or similar)

// DO NOT MODIFY THE EXISTING METHOD SIGNATURES
//  (you may, however, add member fields and additional methods)

// DO NOT INCLUDE LIBRARIES OUTSIDE OF THE JAVA STANDARD LIBRARY
//  (i.e., you may include java.util.ArrayList etc. here, but not junit, apache commons, google guava, etc.)

/**
* @author Hugh Potter
*/

public class HashCodeSimilarity
{
	// member fields and other member methods
	String s1,s2;
	int sLength;

	public HashCodeSimilarity(String s1, String s2, int sLength)
	{
		// implementation
		this.s1 = s1;
		this.s2 = s2;
		this.sLength = sLength;
	}

	public float lengthOfS1()
	{
		return (float)(helper(s1));
	}

	public float lengthOfS2()
	{
		return (float)(helper(s2));
	}

	private float helper(String str){
		HashTable table = new HashTable(255);
		int sum = 0;
		for(int i = 0; i < str.length(); i++){
			int key = str.charAt(i);
			int contribution = table.search(key)==null?0:(table.search(key).size()*table.search(key).size());
			sum -= contribution;
			Tuple t = new Tuple(key,""+str.charAt(i));
			table.add(t);
			sum += table.search(key).size() * table.search(key).size();
		}

		return sum;
	}

	public float similarity()
	{
		// implementation
		return 0;
	}

	private HashTable getShingles(String str){
		int firstShingle = 0;
		HashTable table = new HashTable(100);

		for(int i = 0; i < sLength; i++){
			firstShingle += (int)str.charAt(i);
		}
		Tuple t = new Tuple(firstShingle,str.substring(0,sLength));
		table.add(t);

		for(int i = 1; i < str.length()-sLength+1; i++){
			int nextShingle = firstShingle - str.charAt(i-1) + str.charAt(i+sLength-1);
			Tuple shingle = new Tuple(nextShingle,str.substring(i,i+sLength));
			table.add(shingle);
		}

		return table;
	}

	public static void main(String[] args){
		//{1268264612
		HashCodeSimilarity hcs = new HashCodeSimilarity("1268264612","251188438",4);
		System.out.println(hcs.lengthOfS1());
	}
}