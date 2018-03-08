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
		return (float)Math.sqrt(vectorLengthHelper(s1));
	}

	public float lengthOfS2()
	{
		return (float)Math.sqrt(vectorLengthHelper(s2));
	}

	/**
	 * Time = O(n) where n = str.length()
	 * @param str
	 * @return
	 */
	private float vectorLengthHelper(String str){
		HashTable table = new HashTable(100);
		double a = 6, m = a;
		int sum = 0;
		float vector = 0;

		for(int i = 0; i < sLength; i++){
			sum += str.charAt(i) * Math.pow(a,sLength-i-1);
		}

		table.add(new Tuple(sum,str.substring(0,sLength)));

		for(int i = 1; i <= str.length()-sLength; i++){
			//we found another occurrence of i for f(S,i)
			char passed = (char)(str.charAt(i-1)*Math.pow(a,sLength-1));
			char arrived = (char)(str.charAt(i+sLength-1));
			sum = (int)((sum - passed)*a) + arrived;

			Tuple t = new Tuple(sum,str.substring(i,i+sLength));
			table.add(t);
		}



		table.printTable(false);
		return vector;
	}
	private float vectorLengthHelper1(String str){
		HashTable table = new HashTable(100);
		double a = 6, m = a;
		int sum = 0;
		float vector = 0;

		for(int i = 0; i < sLength; i++){
			sum += str.charAt(i) * Math.pow(a,sLength-i-1);
		}

		table.add(new Tuple(sum,str.substring(0,sLength)));
		vector += table.search(sum).size() * table.search(sum).size();

		for(int i = 1; i <= str.length()-sLength; i++){
			//we found another occurrence of i for f(S,i)
			char passed = (char)(str.charAt(i-1)*Math.pow(a,sLength-1));
			char arrived = (char)(str.charAt(i+sLength-1));
			sum = (int)((sum - passed)*a) + arrived;

			//subtract the old count for i
			int value = table.search(sum)==null?0:table.search(sum).size();
			vector -= (value*value);

			//tell table we found another i
			Tuple t = new Tuple(sum,str.substring(i,i+sLength));
			table.add(t);

			//add the new calculation for f(S,i)
			vector += table.search(sum).size() * table.search(sum).size();
		}


		table.printTable(false);
		return vector;
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
		HashCodeSimilarity hcs = new HashCodeSimilarity("aroseisaroseisarose","aroseisaflowerwhichisarose",4);
		System.out.println(Math.pow(hcs.lengthOfS2(),2));
	}
}