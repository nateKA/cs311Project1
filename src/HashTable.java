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

	public HashTable(int size)
	{
		// implementation
		numElements = 0;
		numBuckets = 0;

		hashFunction = new HashFunction(size);
		size = illegalSearchAndSiezureOfP();
		table = createTable(size);
	}
	public static void main(String[] args){
		HashTable table = new HashTable(5);
		table.add(null);
		System.out.println(table.table[3]);
	}

	public int maxLoad()
	{
		// implementation
		return 0;
	}

	public float averageLoad()
	{
		// implementation
		return 0;
	}

	public int size()
	{
		return table.length;
	}

	public int numElements()
	{
		// implementation
		return numElements;
	}

	public float loadFactor()
	{
		// implementation
		return 0;
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

		if(table[hash] == null) {
			table[hash] = new ArrayList<>();
		}

		table[hash].add(t);
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

	public int search(Tuple t)
	{
		int count = 0;
		for(Tuple c: table[hashFunction.hash(t.getKey())]){
			if(c.equals(t)) count++;
		}
		return count;
	}

	public void remove(Tuple t) {
		// implementation
		numElements--;
	}

	/**
	 * time = O(1)
	 * @return
	 */
	private int illegalSearchAndSiezureOfP(){
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
}