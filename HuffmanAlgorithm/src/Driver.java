import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import Huffman.Utilities;
import Huffman.HuffmanCodec;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Driver {
	static BufferedWriter debugStream;

	static void compareFiles(String firstFile, String secondFile) {
		try {
			FileInputStream  frs1 = new FileInputStream(firstFile);
			FileInputStream  frs2 = new FileInputStream(secondFile);
			BufferedInputStream fin1 = new BufferedInputStream(frs1, 1024);
			BufferedInputStream  fin2 = new BufferedInputStream(frs2, 1024);

			int blockCount = 0;
			int BLOCKSIZE  = 1024;
			byte[] buffer1 = new byte[BLOCKSIZE];
			byte[] buffer2 = new byte[BLOCKSIZE];
			
			while(fin1.available() > 0) {
	           int length1 = fin1.read(buffer1);
		       int length2 = fin2.read(buffer2);

		       if ( length1 != length2 ) {
		    	   int size1 = length1+BLOCKSIZE*blockCount;
		    	   int size2 = length2+BLOCKSIZE*blockCount;
		           throw new RuntimeException(
		                   "Sizes of the files are different"
		                 + ", file " + firstFile  + " has size " + size1
		                 + ", file " + secondFile + " has size " + size2
		           );
		       }

		       for (int i=0; i < length1; ++i) {
		           if (buffer1[i]!=buffer2[i]) {
		        	   int pos = i+BLOCKSIZE*blockCount;
		               throw new RuntimeException(
		                      "Files are different at position " + pos
		                    + ", file " + firstFile  + " has character " + Utilities.safeString(buffer1[i])
		                    + ", file " + secondFile + " has character " + Utilities.safeString(buffer2[i])
		               );
   					}
   			    }
   
		       if (length1 == 0) break;
       		    ++blockCount;
			}

		fin1.close();
		fin2.close();
		frs1.close();
		frs2.close();
	} catch(IOException e) {
		e.printStackTrace();
	}
}

	static void testEncoding(HuffmanCodec huffmanCodec, String inputFile, String encodedFile) {
		try {		
			FileWriter fr = new FileWriter(encodedFile);
			BufferedWriter fout = new BufferedWriter(fr);

			debugStream.write("Encoding " + inputFile + " -> " + encodedFile + "\n\n");
			debugStream.flush();

			huffmanCodec.encodeStream(inputFile, fout);

			fout.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void testDecoding(HuffmanCodec huffmanCodec, String  encodedFile, String decodedFile) {
		try {
			FileInputStream frs = new FileInputStream(encodedFile);
			BufferedInputStream fin = new BufferedInputStream(frs, 1024);
			FileOutputStream fout = new FileOutputStream(decodedFile);

			debugStream.write("Decoding " + encodedFile + " -> " + decodedFile + "\n");
			debugStream.flush();

			huffmanCodec.decodeStream(fin, fout);

			fin.close();
			frs.close();
			fout.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	static void testFile(int number) {
		String debugFile = number +"_debug.txt";
		String inputFile = number+"_in.txt";
		String encodedFile = number+"_encoded.txt";
		String decodedFile = number+"_decoded.txt";
  
		try {
			FileWriter fr = new FileWriter(debugFile);
			debugStream = new BufferedWriter(fr);

			System.out.println("*** Testing file " + inputFile + ", debug output file " + debugFile + " ***\n" );

			HuffmanCodec huffmanCodec = new HuffmanCodec(debugStream);

			testEncoding(huffmanCodec, inputFile, encodedFile);
			testDecoding(huffmanCodec, encodedFile, decodedFile);
			compareFiles(inputFile, decodedFile);

			debugStream.write("\n!!! Files " + inputFile + " and " + decodedFile + " are equal.\n" );
			debugStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("\nOK\n\n" ); 
   }

	public static void main(String[] args) {

		try {
			for ( int i=1; i<=11; ++i ) testFile(i);
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage() + "\n");
			ex.printStackTrace();

		}

	}
}



