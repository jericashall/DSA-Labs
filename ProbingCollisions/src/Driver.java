import HashTables.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;
import Sort.*;

public class Driver {
	final static int tableSize = 191;
	final static int doubleFactor = 181;
	
	static PrintWriter pw1;
	static PrintStream pw2;
	static ArrayList<Integer> data;
	
	static void testKeyValue(String description, HashInterface<Integer, Integer> hashTable, Integer key, Integer value) {
		final int previousCollisions = hashTable.getCollisions();
		
		hashTable.put(key, value);
		
		final Integer retrievedValue = hashTable.get(key);
		
		pw1.write("" + key + " : " + value + " -> " + retrievedValue + ", collisions " + (hashTable.getCollisions()-previousCollisions) +"\n");
		
		if(retrievedValue == null || retrievedValue.compareTo(value) != 0) {
			pw1.write("Retrieved value " + retrievedValue + " does not match stored value " + value + " for key " + key + "\n");
			throw new RuntimeException("value mismatch");
		}		
	}
	
	static void testInputKey(Integer key, HashInterface<Integer, Integer> lph, HashInterface<Integer, Integer> qph, HashInterface<Integer, Integer> dhph) {
		final Integer value = key *2;
		
		testKeyValue("Linear ", lph, key, value);
		testKeyValue("Quadratic ", qph, key, value);
		testKeyValue("Linear ", dhph, key, value);
		
		pw1.write("\n");
	}
	
	static void testData(String description) {
		pw1.write("Format of output\n");
		pw1.write("key:  value  ->  retrievedValue , collisions number Collisions\n");
		pw1.write("Linear total number Collisions\n");
		pw1.write("Quadratic total number Collisions\n");
		pw1.write("Double hashing total number Collisions\n");
		pw1.write("\n*** " + description + " Start *** " + "\n\n");
		
		LinearProbingHash<Integer,Integer> lph = new LinearProbingHash<>(tableSize);
		QuadraticProbingHash<Integer,Integer> qph = new QuadraticProbingHash<>(tableSize);
		DoubleHashingProbingHash<Integer,Integer> dhph = new DoubleHashingProbingHash<>(tableSize);
		
		for(Integer key : data) {
			testInputKey(key, lph, qph, dhph);
		}
		
		pw1.write("Linear    " + lph.getCollisions() + " collisions\n");		
		pw1.write("Quadratic " + qph.getCollisions() + " collisions\n");	
		pw1.write("Double    " + dhph.getCollisions() + " collisions\n");
		pw1.write("\n*** " + description + " End ***\n\n");
		
		PrintStream stdout = System.out;
		System.setOut(pw2);
		pw2.append("\n*** Linear probing " + description + " Start ***\n\n");
		lph.printTable();
		pw2.append("\nLinear probing " + lph.getCollisions() +" collisions\n");
		pw2.append("\n*** Linear probing " + description + " End ***\n\n");
		pw2.append("\n*** Quadratic probing " + description + " Start ***\n\n");
		qph.printTable();
		pw2.append("\nQuadratic probing " + qph.getCollisions() +" collisions\n");
		pw2.append("\n*** Quadratic probing " + description + " End ***\n\n");	
		pw2.append("\n*** Double hashing " + description + " Start ***\n\n");
		dhph.printTable();
		pw2.append("\nDouble hashing " + dhph.getCollisions() +" collisions\n");
		pw2.append("\n*** Double hashing " + description + "  End ***\n\n");
		System.setOut(stdout);
	}
	
	static void readData(String inputFile) {
		try {
			FileReader fr = new FileReader(inputFile);
			Scanner in = new Scanner(fr);
			
			data = new ArrayList<>();
			
			while(in.hasNext()) {
				data.add(in.nextInt());
			}
			
			in.close();			
		} catch (FileNotFoundException e) {
			System.out.println("Exeception: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exeception: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	static void testFile(String inputFilename, String outputFilename1, String outputFilename2) {
		System.out.println("Input file " + inputFilename + ", output file 1 " + outputFilename1 + ", output file 2 " + outputFilename2 + "\n");
		
		readData(inputFilename);
		
		try {
			FileWriter fw1 = new FileWriter(outputFilename1);
			pw1 = new PrintWriter(fw1);
			pw2 = new PrintStream(outputFilename2);
			
			testData("Random Order");
			
			//sort the data
			Comparator<Integer> comp = (Integer a, Integer b) -> {return a.compareTo(b) < 0 ? -1 : 1 ;};
			Sort.sort(data, comp);
			testData("Ascending Order");
			
			//reverse sort the data
			Comparator<Integer> comp2 = (Integer a, Integer b) -> {return a.compareTo(b) < 0 ? 1 : -1 ;};
			Sort.sort(data, comp2);
			testData("Descending Order");
			
			pw1.close();
			pw2.close();
		} catch (IOException e) {
			System.out.println("Exeception: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exeception: " + e.getMessage());
			e.printStackTrace();
		}
		
		System.out.println("Done");
		
	}
	
	public static void main(String[] args) {
			testFile("in150.txt", "out150_collisions.txt", "out150_tables.txt");
			testFile("in160.txt", "out160_collisions.txt", "out160_tables.txt");
			testFile("in170.txt", "out170_collisions.txt", "out170_tables.txt");
			testFile("in180.txt", "out180_collisions.txt", "out180_tables.txt");

	}

}
