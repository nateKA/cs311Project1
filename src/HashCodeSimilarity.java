// LEAVE THIS FILE IN THE DEFAULT PACKAGE
//  (i.e., DO NOT add 'package cs311.pa1;' or similar)

// DO NOT MODIFY THE EXISTING METHOD SIGNATURES
//  (you may, however, add member fields and additional methods)

// DO NOT INCLUDE LIBRARIES OUTSIDE OF THE JAVA STANDARD LIBRARY
//  (i.e., you may include java.util.ArrayList etc. here, but not junit, apache commons, google guava, etc.)

import java.util.ArrayList;

/**
* @author Hugh Potter
*/

public class HashCodeSimilarity
{
	// member fields and other member methods
	String s1,s2;
	int sLength;
	HashTable union = new HashTable(300);

	public HashCodeSimilarity(String s1, String s2, int sLength)
	{
		// implementation
		this.s1 = s1;
		this.s2 = s2;
		this.sLength = sLength;
	}

	private int probe(int i, Tuple t){
		ArrayList<Tuple> a = union.search(i);
		while(a!=null ){
			if(a.get(0).getKey()==i)break;
			i = i+1;
			if(i >= union.size())i=0;
			a = union.search(i);
		}
		return i;
	}
	public float lengthOfS1()
	{
		HashTable table = new HashTable(100);
		double a = 6;
		int sum = 0;
		float vector = 0;

		for(int i = 0; i < sLength; i++){
			sum += s1.charAt(i) * Math.pow(a,sLength-i-1);
		}

		Tuple tup = new Tuple(sum,s1.substring(0,sLength));
		table.add(tup);
		int index = probe(sum,tup);
		if(union.search(index)==null)
		union.add(new Tuple(index,tup.getValue()));
		vector = 1;

		for(int i = 1; i <= s1.length()-sLength; i++){
			//we found another occurrence of i for f(S,i)
			char passed = (char)(s1.charAt(i-1)*Math.pow(a,sLength-1));
			char arrived = (char)(s1.charAt(i+sLength-1));
			sum = (int)((sum - passed)*a) + arrived;

			//subtract the old count for i
			int value = 0;
			if(table.search(sum)!=null)
				for(Tuple k : table.search(sum)){
					if(sum == k.getKey())
						value++;
				}
			vector -= (value*value);

			//tell table we found another i
			Tuple t = new Tuple(sum,s1.substring(i,i+sLength));
			table.add(t);

			//fill union
			index = probe(sum,t);
			if(union.search(index)==null)
			union.add(new Tuple(index,t.getValue()));

			//add the new calculation for f(S,i)
			value++;
			vector += value*value;
		}

		table.printTable(false);

		return (float)Math.sqrt(vector);
	}

	public float lengthOfS2()
	{
		HashTable table = new HashTable(100);
		double a = 6;
		int sum = 0;
		float vector = 0;

		for(int i = 0; i < sLength; i++){
			sum += s2.charAt(i) * Math.pow(a,sLength-i-1);
		}

		Tuple tup = new Tuple(sum,s2.substring(0,sLength));
		table.add(tup);
		int index = probe(sum,tup);
		if(union.search(index)==null)
			union.add(new Tuple(index,tup.getValue()));
		vector = 1;

		for(int i = 1; i <= s2.length()-sLength; i++){
			//we found another occurrence of i for f(S,i)
			char passed = (char)(s2.charAt(i-1)*Math.pow(a,sLength-1));
			char arrived = (char)(s2.charAt(i+sLength-1));
			sum = (int)((sum - passed)*a) + arrived;

			//subtract the old count for i
			int value = 0;
			if(table.search(sum)!=null)
				for(Tuple k : table.search(sum)){
					if(sum == k.getKey())
						value++;
				}
			vector -= (value*value);

			//tell table we found another i
			Tuple t = new Tuple(sum,s2.substring(i,i+sLength));
			table.add(t);

			//fill union
			index = probe(sum,t);
			if(union.search(index)==null)
				union.add(new Tuple(index,t.getValue()));

			//add the new calculation for f(S,i)
			value++;
			vector += value*value;
		}
		table.printTable(false);

		return (float)Math.sqrt(vector);
	}

	/**
	 * Time = O( m + n * k(i) ) where n = str.length()
	 * @param str
	 * @return
	 */
	private float vectorLengthHelper(String str){
		HashTable table = new HashTable(100);
		double a = 6;
		int sum = 0;
		float vector = 0;

		for(int i = 0; i < sLength; i++){
			sum += str.charAt(i) * Math.pow(a,sLength-i-1);
		}

		table.add(new Tuple(sum,str.substring(0,sLength)));
		vector = 1;

		for(int i = 1; i <= str.length()-sLength; i++){
			//we found another occurrence of i for f(S,i)
			char passed = (char)(str.charAt(i-1)*Math.pow(a,sLength-1));
			char arrived = (char)(str.charAt(i+sLength-1));
			sum = (int)((sum - passed)*a) + arrived;

			//subtract the old count for i
			int value = 0;
			if(table.search(sum)!=null)
			for(Tuple k : table.search(sum)){
				if(sum == k.getKey())
					value++;
			}
			vector -= (value*value);

			//tell table we found another i
			Tuple t = new Tuple(sum,str.substring(i,i+sLength));
			table.add(t);
			value++;

			//add the new calculation for f(S,i)
			vector += value*value;
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
		String s1 = FileReader.getStringFromFile("src/shak1");
		String s2 = FileReader.getStringFromFile("src/shak2");
		HashCodeSimilarity hcs = new HashCodeSimilarity(s1,s2,4);
		System.out.println(Math.pow(hcs.lengthOfS1(),2));
	}
}