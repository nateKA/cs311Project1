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
	ArrayList<Tuple>[] buckets;

	public HashTable(int size)
	{
		// implementation
		numElements = 0;
		numBuckets = 0;

		hashFunction = new HashFunction(size);
		size = illegalSearchAndSeizureOfP();
		buckets = createTable(size);
	}
	public static void main(String[] args){
		HashTable table = new HashTable(5);
		Tuple t1 = new Tuple(1, "hello");
		Tuple t2 = new Tuple(2, "world");
		table.add(t1);
		table.add(t2);
		table.printTable(true);
	}

	public ArrayList<Tuple> getBucket(int i ){
		return buckets[i];
	}

	public int maxLoad()
	{
		ArrayList<Tuple> largest = buckets[0];
		for(int i = 1; i < buckets.length; i++){
			if(largest == null || (buckets[i] != null && buckets[i].size() >= largest.size())){
				largest = buckets[i];
			}
		}
		return largest.size();

	}

	public float averageLoad()
    {
        int average = 0;

        for(int i = 1; i < buckets.length; i++){
        	if(buckets[i] == null)continue;
            average += buckets[i].size();
        }
        return average / buckets.length;
	}

	public int size()
	{
		return buckets.length;
	}

	public int numElements()
	{
		return numElements;
	}

	public float loadFactor()
	{
		return (float)numElements / (float)buckets.length ;
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
		if(buckets[hash] == null) {
			buckets[hash] = new ArrayList<>();
			buckets[hash].add(t);
			numBuckets++;
		}else {
			if(buckets[hash].size() == 0)numBuckets++;
			buckets[hash].add(t);
		}

		if(loadFactor() >= .7){
			resize();
		}
	}

	/**
	 * time = O(1)
	 * @param k
	 * @return
	 */
	public ArrayList<Tuple> search(int k)
	{
		return buckets[hashFunction.hash(k)];
	}

	/**
	 * time = 0(b) where b=size of bucket
	 * @param t
	 * @return
	 */
	public int search(Tuple t)
	{
		int count = 0;
		if(t == null){
			return 0;
		}
		ArrayList<Tuple> tuples = buckets[hashFunction.hash(t.getKey())];
		if(tuples == null){
			return 0;
		}
		for(Tuple c: tuples){
			if(c == null){
				break;
			}
			if(c.equals(t)) count++;
		}
		return count;
	}

	public void remove(Tuple t)
	{
		int hash = hashFunction.hash(t.getKey());
		if (buckets[hash] == null || t==null) {
			return;
		}

		for(int i = 0; i < buckets[hash].size(); i++){
			if(buckets[hash].get(i).equals(t)) {
				buckets[hash].remove(i);
				break;
			}
		}

        numElements--;
		if(buckets[hash].size() == 0){
			numBuckets--;
		}
	}

	public void findLargestBucket(){

    }

	/**
	 * time = O(n*m(i)) where n=size of table; m(i)=size of bucket in index i
	 */
	private void resize(){
		HashTable newTable = new HashTable(buckets.length*2+1);
		for(ArrayList<Tuple> bucket: buckets){
			if(bucket==null)continue;
			for(Tuple t: bucket){
				newTable.add(t);
			}
		}

		buckets = newTable.buckets;
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

	public void printTable(boolean printNulls){
		for(ArrayList<Tuple> bucket : buckets){
			if(bucket != null || printNulls)
			System.out.println(bucket);
		}
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
		return buckets;
	}

}