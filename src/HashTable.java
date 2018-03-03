// LEAVE THIS FILE IN THE DEFAULT PACKAGE
//  (i.e., DO NOT add 'package cs311.pa1;' or similar)

// DO NOT MODIFY THE EXISTING METHOD SIGNATURES
//  (you may, however, add member fields and additional methods)

// DO NOT INCLUDE LIBRARIES OUTSIDE OF THE JAVA STANDARD LIBRARY
//  (i.e., you may include java.util.ArrayList etc. here, but not junit, apache commons, google guava, etc.)

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
* @author Hugh Potter
*/

public class HashTable
{
	// member fields and other member methods

	private HashFunction hashFunction;

	public HashTable(int size)
	{
		// implementation
		hashFunction = new HashFunction(size);
		size = illegalSearchAndSiezureOfP();
	}
	public static void main(String[] args){
		HashTable table = new HashTable(5);
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
		// implementation
		return 0;
	}

	public int numElements()
	{
		// implementation
		return 0;
	}

	public float loadFactor()
	{
		// implementation
		return 0;
	}

	public void add(Tuple t)
	{
		// implementation
	}

	public ArrayList<Tuple> search(int k)
	{
		// implementation
		return null;
	}

	public int search(Tuple t)
	{
		// implementation
		return 0;
	}

	public void remove(Tuple t)
	{
		// implementation
	}

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
}