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
<<<<<<< HEAD
		return this.keyP;
=======
		return 0;
>>>>>>> 04257d0b93fd59aa6afcc34f6dcedf55dad05f9e
	}

	public String getValue()
	{
		// implementation
<<<<<<< HEAD
		return this.valueP;
=======
		return null;
>>>>>>> 04257d0b93fd59aa6afcc34f6dcedf55dad05f9e
	}

	public boolean equals(Tuple t)
	{
		// implementation
<<<<<<< HEAD
		if(this.keyP == t.keyP && this.valueP == t.valueP) {
			return true;
		}
		else {
			return false;
		}
=======
		return false;
>>>>>>> 04257d0b93fd59aa6afcc34f6dcedf55dad05f9e
	}
}