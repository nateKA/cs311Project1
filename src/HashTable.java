// LEAVE THIS FILE IN THE DEFAULT PACKAGE
//  (i.e., DO NOT add 'package cs311.pa1;' or similar)

// DO NOT MODIFY THE EXISTING METHOD SIGNATURES
//  (you may, however, add member fields and additional methods)

// DO NOT INCLUDE LIBRARIES OUTSIDE OF THE JAVA STANDARD LIBRARY
//  (i.e., you may include java.util.ArrayList etc. here, but not junit, apache commons, google guava, etc.)

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
* @author Hugh Potter
*/

public class HashTable
{
	// member fields and other member methods

	private HashFunction hashFunction;
	int numElements;
	int numBuckets;
	ArrayList<Tuple>[] table;
	ArrayList<Tuple> largestBucket;

	public HashTable(int size)
	{
		// implementation
		numElements = 0;
		numBuckets = 0;
		largestBucket = null;

		hashFunction = new HashFunction(size);
		size = illegalSearchAndSeizureOfP();
		table = createTable(size);
	}
	public static void main(String[] args){
		HashTable table = new HashTable(5);
		table.add(null);
		System.out.println(table.table[3]);
	}

	public int maxLoad()
	{
		return largestBucket.size();
	}

	public float averageLoad()
    {
        int average = 0;

        for(int i = 1; i < table.length; i++){
            average += table[i].size();
        }
        return average / table.length;
	}

	public int size()
	{
		return table.length;
	}

	public int numElements()
	{
		return numElements;
	}

	public float loadFactor()
	{
		return numElements / numBuckets;
	}

	/**
	 * time = O(1)
	 * @param t
	 */
	public void add(Tuple t)
	{
		// implementation
		if(t==null)return;

		numElements++;
		int hash = hashFunction.hash(t.getKey());

		//update table and bucket count
		if(table[hash] == null) {
			table[hash] = new ArrayList<>();
			table[hash].add(t);
			numBuckets++;
		}else {
			if(table[hash].size() == 0)numBuckets++;
			table[hash].add(t);
		}

		//update largest bucket
		if(largestBucket == null
				|| largestBucket.size() < table[hash].size()){
			largestBucket = table[hash];
		}
	}

	/**
	 * time = O(1)
	 * @param k
	 * @return
	 */
	public ArrayList<Tuple> search(int k)
	{
		return table[hashFunction.hash(k)];
	}

	/**
	 * time = 0(b) where b=size of bucket
	 * @param t
	 * @return
	 */
	public int search(Tuple t)
	{
		int count = 0;
		for(Tuple c: table[hashFunction.hash(t.getKey())]){
			if(c.equals(t)) count++;
		}
		return count;
	}

	public void remove(Tuple t)
	{
		int hash = hashFunction.hash(t.getKey());
		if (table[hash] == null) {
			return;
		}
		table[hash].remove(t);
        numElements--;
		if(table[hash].size() == 0){
			numBuckets--;
		}
		else if(table[hash].equals(largestBucket)) {
            findLargestBucket();
        }
	}

	public void findLargestBucket(){
	    ArrayList<Tuple> largest = table[0];
	    for(int i = 1; i < table.length; i++){
	        if(table[i].size() > largest.size()){
	            largest = table[i];
            }
        }
        largestBucket = largest;
    }

	/**
	 * time = O(n*m(i)) where n=size of table; m(i)=size of bucket in index i
	 */
	private void resize(){
		HashTable newTable = new HashTable(table.length*2+1);
		for(ArrayList<Tuple> bucket: table){
			for(Tuple t: bucket){
				newTable.add(t);
			}
		}

		table = newTable.table;
		largestBucket = newTable.largestBucket;
		numElements = newTable.numElements;
		numBuckets = newTable.numBuckets;
		hashFunction = newTable.hashFunction;
	}

	/**
	 * time = O(1)
	 * @return
	 */
	private int illegalSearchAndSeizureOfP(){
		try {
			Class c = HashFunction.class;
			Field f = c.getDeclaredField("p");

			//this is the illegal part
			f.setAccessible(true);
			int p = f.getInt(hashFunction);

			//we were never here
			f.setAccessible(false);

			return p;
		}catch (Exception e){
			e.printStackTrace();
		}
		return -1;
	}

	private ArrayList<Tuple>[] createTable(int size){
		return (ArrayList<Tuple>[]) Array.newInstance(ArrayList.class, size);
	}

	public HashFunction getHashFunction() {
		return hashFunction;
	}

	public int getNumElements() {
		return numElements;
	}

	public int getNumBuckets() {
		return numBuckets;
	}

	public ArrayList<Tuple>[] getTable() {
		return table;
	}

	public ArrayList<Tuple> getLargestBucket() {
		return largestBucket;
	}
}