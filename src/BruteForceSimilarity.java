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

public class BruteForceSimilarity {
	// member fields and other member methods
	private ArrayList<String> S = new ArrayList<String>();
	private ArrayList<String> T = new ArrayList<String>();
	private ArrayList<String> U = new ArrayList<String>();

	public static void main(String[] args) {
		String s1 = "aroseisaroseisarose";
		String s2 = "aroseisaflowerwhichisarose";
		BruteForceSimilarity test = new BruteForceSimilarity(s1, s2, 4);
		System.out.print(test.similarity() + "");
	}

	public BruteForceSimilarity(String s1, String s2, int sLength) {
		for (int i = 0; i < (s1.length() - sLength) + 1; i++) {
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
	}

	public float lengthOfS1() {
		return vectorLength(this.S);
	
	}

	public float lengthOfS2() {
		return vectorLength(this.T);
	}

	public float similarity() {
		ArrayList<String> used = new ArrayList<String>();
		float vector = 0;
		for (String shingle : this.U) {
			if (!used.contains(shingle)) {
				int temp, temp1;
				temp = duplicate(shingle, this.S);
				temp1 = duplicate(shingle, this.T);
				used.add(shingle);
				vector = vector + (temp * temp1);
			}
		}
		
		return vector /(lengthOfS1() * lengthOfS2());
	}

	private int duplicate(String shingle, ArrayList<String> list) {
		int count = 0;
		for (String check : list) {
			if (check.equals(shingle)) {
				count++;
			}
		}
		return count;
	}

	private float vectorLength(ArrayList<String> list) {
		ArrayList<String> used = new ArrayList<String>();
		float vectorLength = 0;
		for (String shingle : list) {
			if (!used.contains(shingle)) {
				float function = duplicate(shingle, list);
				vectorLength = vectorLength + (function * function);
				used.add(shingle);
			}
		}
		return (float) Math.sqrt(vectorLength);
	}
}
