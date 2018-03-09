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
	private ArrayList<String> U = new ArrayList<>();
	private HashTable tableS1 = new HashTable(50);
	private HashTable tableS2 = new HashTable(50);

    /**
     * Splits each string into substrings of given shingle length.
     * Each substring is added to it's own arraylist, and if it is not already in the union it will be added.
     * @param s1 input string1
     * @param s2 input string2
     * @param sLength shingle length
     */
	public HashStringSimilarity(String s1, String s2, int sLength)
	{
		for (int i = 0; i < (s1.length() - sLength) + 1; i++){
			String toAdd = s1.substring(i, i + sLength);
			tableS1.add(new Tuple(strValue(toAdd), toAdd));
			if (!U.contains(toAdd)) {
				U.add(toAdd);
			}
		}
		for (int i = 0; i < (s2.length() - sLength) + 1; i++) {
			String toAdd = s2.substring(i, i + sLength);
			tableS2.add(new Tuple(strValue(toAdd), toAdd));
			if (!U.contains(toAdd)) {
				U.add(toAdd);
			}
		}
	}

    /**
     * get method for vector length of first string
     * @return vector length
     */
	public float lengthOfS1()
	{
		return vectorLength(tableS1);
	}

    /**
     * get method for vector length of second string
     * @return vector length
     */
	public float lengthOfS2()
	{
		return vectorLength(tableS2);
	}

    /**
     * calculates the similarity between the two strings using their hashtables
     * @return similarity
     */
	public float similarity()
	{
		float similarity = 0;
		for(String substring : U){
			similarity += similarityHelper(substring);
		}
		return similarity / (vectorLength(tableS1) * vectorLength(tableS2));
	}

    /**
     * calculates the vector length of a given table
     * @param table is a table of substrings
     * @return vector length
     */
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

    /**
     * counts the number of times a tuple appears in a list of tuples
     * @param shingle tuple to compare to
     * @param tuples list of tuples to compare with
     * @return number of duplicates
     */
	private int duplicate(Tuple shingle, ArrayList<Tuple> tuples) {
		int count = 0;
		for (Tuple value : tuples) {
			if (value.equals(shingle)) {
				count++;
			}
		}
		return count;
	}

    /**
     * counts the number of times a string appears in each table.
     * @param str to search for
     * @return number of repetitions
     */
	private int similarityHelper(String str){
		int count = 0;
		Tuple tuple = new Tuple(strValue(str), str);
		count += tableS1.search(tuple);
		count *= tableS2.search(tuple);
		return count;
	}

    /**
     * Adds the ascii value of each character in a string
     * @param str to be calculated
     * @return value of each char in str
     */
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