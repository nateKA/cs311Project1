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
	private int sLength;
	private HashTable tableS1 = new HashTable(100);
	private HashTable tableS2 = new HashTable(100);
	private HashTable union = new HashTable(300);

	/**
	 * Takes both strings and creates a hashtable for each one
	 */
	public HashCodeSimilarity(String s1, String s2, int sLength)
	{
		// implementation
		this.sLength = sLength;
		buildTable(tableS1, s1);
		buildTable(tableS2, s2);
	}

	/**
	 * Computes the vector length of string s1
	 * @return vector length of the string s1
	 */
	public float lengthOfS1()
	{
		return vectorLength(tableS1);
	}

	/**
	 * Computes the vector length of string s2
	 * @return vector length of string s2
	 */
	public float lengthOfS2()
	{
		return vectorLength(tableS2);
	}

	/**
	 * Computes the similarity of the two strings using the union between the strings
	 * @return similarity of the strings
	 */
	public float similarity()
	{
		float similarity = 0;
		for(ArrayList<Tuple> bucket : union.buckets){
			int countS1 = 0, countS2 = 0;
			if(bucket != null) {
				for (Tuple unionTuple : bucket) {
					countS1 += duplicate(unionTuple, tableS1.buckets[tableS1.getHashFunction().hash(unionTuple.getKey())]);
					countS2 += duplicate(unionTuple, tableS2.buckets[tableS2.getHashFunction().hash(unionTuple.getKey())]);
					similarity += countS1 * countS2;
				}
			}
		}
		return similarity / (vectorLength(tableS1) * vectorLength(tableS2));
	}

	/**
	 * Breaks the input into substrings of shingleLength and then if they do not exist in the unionTable
	 * they will be added.
	 * @param table table to be filled
	 * @param input input to break into substrings and add to the table
	 */
	private void buildTable(HashTable table, String input){
		double a = 6;
		int sum = 0;

		for(int i = 0; i < sLength; i++){
			sum += input.charAt(i) * Math.pow(a,sLength-i-1);
		}

		Tuple tup = new Tuple(sum,input.substring(0,sLength));
		table.add(tup);
		int index = probe(sum);
		if(union.search(index)==null)
			union.add(new Tuple(index,tup.getValue()));

		for(int i = 1; i <= input.length()-sLength; i++){
			//we found another occurrence of i for f(S,i)
			char passed = (char)(input.charAt(i-1)*Math.pow(a,sLength-1));
			char arrived = input.charAt(i+sLength-1);
			sum = (int)((sum - passed)*a) + arrived;

			//tell table we found another i
			Tuple t = new Tuple(sum,input.substring(i,i+sLength));
			table.add(t);

			//fill union
			index = probe(sum);
			if(union.search(index)==null)
				union.add(new Tuple(index,t.getValue()));
		}
	}

	/**
	 * Loops through the arraylist and determines the number of duplicates using each tuples key value
	 * @param shingle the shingle to compare with
	 * @param tuples the arraylist of tuples to compare to
	 * @return the number of times shingle appears in tuples
	 */
	private int duplicate(Tuple shingle, ArrayList<Tuple> tuples) {
		int count = 0;
		if(tuples == null){
			return 0;
		}
		for (Tuple value : tuples) {
			if (value.getKey() == shingle.getKey()) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Calculates the vector length of the given table of substrings
	 * @param table is a table of substrings
	 * @return the vector length of the table
	 */
	private float vectorLength(HashTable table){
		float vectorLength = 0;
		for(ArrayList<Tuple> bucket : table.buckets){
			if(bucket != null){
				ArrayList<String> used = new ArrayList<>();
				for(Tuple tuple : bucket){
					if(!used.contains(tuple.getValue())){
						int count = duplicate(tuple, bucket);
						vectorLength += count * count;
						used.add(tuple.getValue());
					}
				}
			}
		}
		return (float) Math.sqrt(vectorLength);
	}

	/**
	 * Used to ensure there is not a collision in union
	 * @param i key
	 * @return key
	 */
	private int probe(int i){
		ArrayList<Tuple> a = union.search(i);
		while(a!=null ){
			if(a.get(0).getKey()==i)break;
			i = i+1;
			if(i >= union.size())i=0;
			a = union.search(i);
		}
		return i;
	}

	public static void main(String[] args){
		//{1268264612
		HashCodeSimilarity hcs = new HashCodeSimilarity("aroseisaroseisarose","aroseisaflowerwhichisarose",4);
		//hcs.lengthOfS2();
		//hcs.vectorLengthHelper(hcs.s2);
		System.out.println(Math.pow(hcs.lengthOfS1(),2));
		//System.out.println("///////////////////////////////////////////////////////////////");
		//hcs.tableS2.printTable(false);
		System.out.println(Math.pow(hcs.lengthOfS2(), 2));
		System.out.println(hcs.similarity());
		//hcs.tableS1.printTable(false);
		//System.out.println("///////////////////////////////////////////////////////////////");
		//hcs.union.printTable(false);
		//System.out.println("///////////////////////////////////////////////////////////////");

		//hcs.tableS2.printTable(false);
	}
}