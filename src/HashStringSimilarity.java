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

public class HashStringSimilarity
{
	// member fields and other member methods
	String s1, s2;
	int sLength;
	float s1Len, s2Len;
	final int ALPHA = 31;
	private ArrayList<String> S = new ArrayList<String>();
	private ArrayList<String> T = new ArrayList<String>();
	private ArrayList<String> U = new ArrayList<String>();
	HashTable tableS1 = new HashTable(50);
	HashTable tableS2 = new HashTable(50);

	public HashStringSimilarity(String s1, String s2, int sLength)
	{
		this.sLength = sLength;
		for (int i = 0; i < (s1.length() - sLength) + 1; i++){
			String toAdd = s1.substring(i, i + sLength);
			S.add(toAdd);
			if (!U.contains(toAdd)) {
				U.add(toAdd);
			}
		}
		for (int i = 0; i < (s2.length() - sLength) + 1; i++) {
			String toAdd = s2.substring(i, i + sLength);
			T.add(toAdd);
			if (!U.contains(toAdd)) {
				U.add(toAdd);
			}
		}
		createTables();
	}

	private void createTables(){
		int key;
		for(String shingle : S){
			key = 0;
			for(int i = 0; i < shingle.length(); i++){
				key += shingle.charAt(i);
			}
			Tuple tuple = new Tuple(key, shingle);
			tableS1.add(tuple);
		}
		for(String shingle : T){
			key = 0;
			key = strValue(shingle);
			Tuple tuple = new Tuple(key, shingle);
			tableS2.add(tuple);
		}
	}

	private int duplicate(Tuple shingle, ArrayList<Tuple> tuples) {
		int count = 0;
		for (Tuple value : tuples) {
			if (value.equals(shingle)) {
				count++;
			}
		}
		return count;
	}

	public float vectorLength(HashTable table){
		float vectorLength = 0;
		for(ArrayList<Tuple> bucket : table.buckets){
			if(bucket != null){
				ArrayList<String> used = new ArrayList<String>();
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

	private int strValue(String str){
		int key = 0;
		for(int i = 0; i < str.length(); i++){
			key += str.charAt(i);
		}
		return key;
	}

	public float lengthOfS1()
	{
		return vectorLength(tableS1);
	}

	public float lengthOfS2()
	{
		return vectorLength(tableS2);
	}

	private int helper(String str){
		int count = 0;
		Tuple tuple = new Tuple(strValue(str), str);
		count += tableS1.search(tuple);
		tuple = new Tuple(strValue(str), str);
		count *= tableS2.search(tuple);
		return count;
	}

	public float similarity()
	{
		float similarity = 0;
		for(String substring : U){
			similarity += helper(substring);
		}
		return similarity / (vectorLength(tableS1) * vectorLength(tableS2));
	}

    public static void main(String[] args){
       HashStringSimilarity test = new HashStringSimilarity("aroseisaroseisarose", "aroseisaflowerwhichisarose", 4);
       test.tableS1.printTable(false);
       System.out.println(test.lengthOfS1());
       System.out.println(test.similarity());
    }
}