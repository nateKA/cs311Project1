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
	private String s1,s2;
	private int sLength;
	private ArrayList<String> S = new ArrayList<String>();
	private ArrayList<String> T = new ArrayList<String>();
	
	public static void main(String[] args){ 
		String s1 = "rosesarered";
		String s2 = "rosesarenot";
		BruteForceSimilarity x = new BruteForceSimilarity(s1,s2,3);
		//x.printS();
	} 
	
	public BruteForceSimilarity(String s1, String s2, int sLength)
	{
		this.s1 = s1;
		this.s2 = s2;
		this.sLength = sLength;
	}
	
	public void printS() {
		for(String x: this.S) {
			System.out.print(x);
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