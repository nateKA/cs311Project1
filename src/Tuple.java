// LEAVE THIS FILE IN THE DEFAULT PACKAGE
//  (i.e., DO NOT add 'package cs311.pa1;' or similar)

// DO NOT MODIFY THE EXISTING METHOD SIGNATURES
//  (you may, however, add member fields and additional methods)

// DO NOT INCLUDE LIBRARIES OUTSIDE OF THE JAVA STANDARD LIBRARY
//  (i.e., you may include java.util.ArrayList etc. here, but not junit, apache commons, google guava, etc.)

/**
* @author Hugh Potter
*/

public class Tuple
{
	// member fields and other member methods
	private int keyP;
	private String valueP;
	
	public Tuple(int keyP, String valueP)
	{
		// implementation
		this.keyP = keyP;
		this.valueP = valueP;
	}

	public int getKey()
	{
		// implementation
		return this.keyP;
	}

	public String getValue()
	{
		// implementation
		return this.valueP;
	}

	public boolean equals(Tuple t)
	{
		// implementation
		if(this.keyP == t.keyP && this.valueP == t.valueP) {
			return true;
		}else {
			return false;
		}
	}

	public String toString(){
		return keyP+", "+valueP;
	}
}