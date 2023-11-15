import Heap.Heap;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;

public class Driver {
		static PrintWriter fout;
		static ArrayList<Integer> inputData;
	
		public static void main(String[] args) {
		        try {
		        	for(int i=1; i<=4; ++i ){		        		
						String suffix = ""+i+".txt";
						testFile("in"+suffix, "out"+suffix );
		        	}
		        } catch (Exception ex) {
		            if (fout != null)  {
		            	fout.write("Exception: " + ex.getMessage() + "\n");
		            	ex.printStackTrace();
		            }
		            System.out.println("Exception: " + ex.getMessage() + "\n");
		            ex.printStackTrace();
				}
		}
		
		public static void printHeap (String description, Heap<Integer> heap) {
			fout.write(description + "\n");
		    for(int i = 1; i <= heap.size(); i++ ){
		    	fout.write(heap.getElement(i) + " ");    	
		    }
		    fout.write("\n\n");
		}

		public static boolean areHeapElementsOrdered(Heap<Integer> heap, int i, int j) {
	    		return (i >= heap.size() || j >= heap.size() || heap.getElement(i).compareTo(heap.getElement(j)) <= 0);
		}
	
		public static String heapElement(Heap<Integer> heap, int i) {
			return i<heap.size() ? "" + heap.getElement(i) : "none";
		}
		
		public static void checkHeap(Heap<Integer> heap) {
			for ( int i = 1; i <= heap.size(); ++i ) {
		        if (areHeapElementsOrdered(heap,i,i*2) && areHeapElementsOrdered(heap,i,i*2+1)) continue;
		        printHeap("Corrupted", heap);

		        fout.write("Error: heap violation at index " + i );
		        fout.write(", heap[" + i + "] = " + heapElement(heap,i));
		        fout.write(", heap[" + (i*2+1) + "] = " + heapElement(heap,i*2+1));
		        fout.write(", heap[" + (i*2+2) + "] = " + heapElement(heap,i*2+2));
		        fout.write("\n");
		        throw new RuntimeException("Not a heap");
			}
		}

		public static void insertOne(Heap<Integer> heap, Integer element) {
			heap.insert(element);
			checkHeap(heap);
		}

		public static void deleteOne(Heap<Integer> heap) {
	    	fout.write("Delete " + heap.getElement(1) + "\n");
		    heap.deleteMin();
		    checkHeap(heap);
		    printHeap("Heap after Delete min", heap);
		}

		public static void testData() {
	    	Heap<Integer> heap = new Heap<Integer>(inputData.size());
	    	for (Integer key : inputData ) {
	    		insertOne(heap, key);
	    	}

	    	printHeap("Heap", heap);  
	    	while (heap.size() > 0 ) {
	    		deleteOne(heap);
	    	}
	    	
	    	fout.write("Deleted all");
		}
	    
		public static void readData (FileReader fr) {
	    	Scanner scan = new Scanner(fr);
	    	inputData = new ArrayList<>();
	    	
	    	while(scan.hasNext()) {
	    		Integer key = scan.nextInt();
	    		inputData.add(key);
	    	}
	    	scan.close();
	    }
	
	    public static void testFile (String inputFile, String outputFile) {
	    	try {
	    		FileReader fr = new FileReader(inputFile);
	    		FileWriter fw = new FileWriter(outputFile);
		    	fout = new PrintWriter(fw);
		    	readData(fr);
		    	fr.close();
		    	testData();
		    	fout.close();
		    	fr.close();
	    	} catch(IOException er) {
	    		System.out.println(er.getMessage());
	    		er.printStackTrace();
	    	} catch(Exception er) {
	    		System.out.println(er.getMessage());
	    		er.printStackTrace();
	    	}
	    }


}
