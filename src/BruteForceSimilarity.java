import java.util.ArrayList;

// LEAVE THIS FILE IN THE DEFAULT PACKAGE
//  (i.e., DO NOT add 'package cs311.pa1;' or similar)

// DO NOT MODIFY THE EXISTING METHOD SIGNATURES
//  (you may, however, add member fields and additional methods)

// DO NOT INCLUDE LIBRARIES OUTSIDE OF THE JAVA STANDARD LIBRARY
//  (i.e., you may include java.util.ArrayList etc. here, but not junit, apache commons, google guava, etc.)

/**
* @author Hugh Potter
*/


public class BruteForceSimilarity
{
	
	// member fields and other member methods
	//private String s1,s2;
	private int sLength;
	private ArrayList<String> S = new ArrayList<String>();
	private ArrayList<String> T = new ArrayList<String>();
	
	public BruteForceSimilarity(String s1, String s2, int sLength)
	{
		this.sLength = sLength;
		
		for(int i = 0; i < (s1.length() - sLength); i++) {
			S.add(s1.substring(i, i + sLength));
			System.out.println(s1.substring(i,i+sLength));
		}
		for(int i = 0; i < (s2.length() - sLength); i++) {
			T.add(s2.substring(i,i + sLength));
		}
	}
	
	public float lengthOfS1()
	{
		// implementation
		// vector = sqrt (F(shingle,occurance)^2)
		//
		return 0;
	}

	public float lengthOfS2()
	{
		// implementation
		return 0;
	}

	public float similarity()
	{
		// implementation
		return 0;
		
		
	}
}