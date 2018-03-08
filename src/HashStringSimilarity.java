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
	private ArrayList<String> S = new ArrayList<>();
	private ArrayList<String> T = new ArrayList<>();
	private ArrayList<String> U = new ArrayList<>();
	private HashTable tableS1 = new HashTable(50);
	private HashTable tableS2 = new HashTable(50);

	public HashStringSimilarity(String s1, String s2, int sLength)
	{
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

	public float lengthOfS1()
	{
		return vectorLength(tableS1);
	}

	public float lengthOfS2()
	{
		return vectorLength(tableS2);
	}

	public float similarity()
	{
		float similarity = 0;
		for(String substring : U){
			similarity += similarityHelper(substring);
		}
		return similarity / (vectorLength(tableS1) * vectorLength(tableS2));
	}

	private float vectorLength(HashTable table){
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

	private void createTables(){
		for(String shingle : S){
			tableS1.add(new Tuple(strValue(shingle), shingle));
		}
		for(String shingle : T){
			tableS2.add(new Tuple(strValue(shingle), shingle));
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

	private int similarityHelper(String str){
		int count = 0;
		Tuple tuple = new Tuple(strValue(str), str);
		count += tableS1.search(tuple);
		count *= tableS2.search(tuple);
		return count;
	}

	private int strValue(String str){
		int key = 0;
		for(int i = 0; i < str.length(); i++){
			key += str.charAt(i);
		}
		return key;
	}

    public static void main(String[] args){
       HashStringSimilarity test = new HashStringSimilarity("aroseisaroseisarose", "aroseisaflowerwhichisarose", 4);
       System.out.println(Math.pow(test.lengthOfS1(), 2));
       System.out.println(Math.pow(test.lengthOfS2(), 2));
       System.out.println(test.similarity());
    }
}