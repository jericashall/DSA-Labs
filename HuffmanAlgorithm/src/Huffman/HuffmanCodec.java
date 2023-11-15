package Huffman;

import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HuffmanCodec {
  private HuffmanAlg huffmanAlgorithm;
  	
    void makeFrequencyTable(BufferedInputStream fin) {
    	huffmanAlgorithm.initFrequencyTable();
    	int originalByte;
    	
    	try {
			while((originalByte  = fin.read()) !=  -1) {
				huffmanAlgorithm.countFrequency(originalByte);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

        huffmanAlgorithm.finishFrequencyTable();
    }

    void encodeData(BufferedInputStream fin, BufferedWriter fout) {
    	int originalByte;
    	
    	try {
			while((originalByte  = fin.read()) !=  -1) {
				String encodedString = huffmanAlgorithm.encodeByte(originalByte);
				fout.write(encodedString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    void decodeData(BufferedInputStream fin, FileOutputStream fout) {     	
        boolean decodingComplete = true;
        int encodedByte;
        int decodedByte = 0;
        
        try {
			while((encodedByte  = fin.read()) !=  -1) {
				Object[] res =  huffmanAlgorithm.decodeByte(encodedByte);
				decodingComplete = (boolean) res[0];
				decodedByte =  (int) res[1];
				if(decodingComplete) fout.write(decodedByte);
			}
			
			if (!decodingComplete) throw new RuntimeException( "Incomplete encoding at the file end");
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}

    public HuffmanCodec(BufferedWriter bw) {
        huffmanAlgorithm = new HuffmanAlg(bw);
    }

    public void encodeStream(String input, BufferedWriter fout) {
    	try {
    		FileInputStream frs = new FileInputStream(input);
    		BufferedInputStream fin = new BufferedInputStream(frs, 40000);
    		makeFrequencyTable(fin);
    		huffmanAlgorithm.buildHuffmanTree();
    		huffmanAlgorithm.makeEncodingTable();
        
    		fin.close();
    		frs.close();
        
    		FileInputStream frs2 = new FileInputStream(input);
    		BufferedInputStream fin2 = new BufferedInputStream(frs2, 40000);
 
    		encodeData(fin2, fout);
    		fin2.close();
    		frs2.close();
    		fout.close();
       } catch(FileNotFoundException e) {
    	    e.printStackTrace();
       } catch (IOException e) {
    	    e.printStackTrace();
       }

    }
    
    public void decodeStream(BufferedInputStream fin, FileOutputStream fout ) {
        decodeData(fin, fout);
        
        try {
			fin.close();
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
