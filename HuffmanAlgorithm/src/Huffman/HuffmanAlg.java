package Huffman;

import java.util.PriorityQueue;
import java.io.IOException;
import java.io.BufferedWriter;

public class HuffmanAlg {
    private HuffmanTree huffmanTree;
    private HuffmanTree traversalTree;
    private int[] frequencyTable;
    private String[] encodingTable;
    private static BufferedWriter bw;

  public HuffmanAlg(BufferedWriter debug) { 
        frequencyTable = null;
        encodingTable  = null;
        huffmanTree    = null;
        bw = debug;
	}

    void initFrequencyTable() {
        frequencyTable = new int[256];                                
        for (int i=0; i< frequencyTable.length; ++i ) frequencyTable [i] = 0;  
    }

    void countFrequency(int originalByte) {
        frequencyTable[originalByte] += 1;
      }
        
    void finishFrequencyTable() {
        // TODO Just debug output
        printFrequencyTable();

    }

    void buildHuffmanTree() {
    	PriorityQueue<HuffmanTree> huffmanQueue = new PriorityQueue<>();
        
    	for(int i = 0; i < frequencyTable.length; i++) {
    		if(frequencyTable[i] > 0) {
    			HuffmanTree singleTree = new HuffmanTree(i, frequencyTable[i]);
            	huffmanQueue.add(singleTree);
    		}
        }    	 
    	
    	printQueue(huffmanQueue);
    	
    	if(huffmanQueue.size() == 0) return;
    	
    	do {
    		HuffmanTree left = huffmanQueue.poll();
    		HuffmanTree right = huffmanQueue.poll();
    		HuffmanTree curr = new HuffmanTree(left, right);
    		huffmanQueue.add(curr);
    		
    		huffmanTree = curr;
    	} while(huffmanQueue.size() > 1);
    	traversalTree = huffmanTree;
    }

    void makeEncodingTable() {  	
        encodingTable = new String[256];                                  
        for(int i = 0; i < encodingTable.length; i++) {
        	encodingTable[i] = ""; 
        }
      
        encodeTable(huffmanTree, "");

        printEncodingTable();   
    }
    
    void encodeTable(HuffmanTree tree, String code) {
    	if(tree == null) return;
    	Integer byte_ =  tree.getByte();
    	if(byte_ != null) encodingTable[byte_] =  code;
   	
    	encodeTable(tree.getLeft(), code+"0");
    	encodeTable(tree.getRight(), code+"1");
    }

    String encodeByte(int originalByte) {
        return encodingTable[originalByte];
    }

    Object[] decodeByte(int encodingByte) {	
    	if(encodingByte == 48) {
    		traversalTree = traversalTree.getLeft();
    	} else {
    		traversalTree = traversalTree.getRight();
    	}
    	
    	Integer bit = traversalTree.getByte();
    	if(bit != null) {
    		traversalTree = huffmanTree;
    		return new Object[]{true, bit};
    	} else {
    		return new Object[]{false, 0};
    	}
    }
    
  
  private void printFrequencyTable() {
        try {
			 bw.write("printFrequencyTable:\n\n");
			 for (int i=0; i< frequencyTable.length; ++i ) {
		        	if ( frequencyTable[i] == 0 ) continue;
		        	bw.write(i + "  " +  (char)i + " {" + frequencyTable[i] +  "}\n");    
		      }  
		        
		      bw.write("\n");
		      bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
       
  }

    void printEncodingTable() {
        try {
			bw.write("printEncodingTable:\n\n");
			for (int i=0; i< encodingTable.length; i++) {
	            if (frequencyTable[i] > 0) {
	            	bw.write(i + "  " +  (char)i + " {" + encodingTable[i] +  "}\n");
	            }
	        }
	        
	        bw.write("\n");
	        bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    private static void printFrequency(int character, int frequency ) {
    	try {
			bw.write(character + "  " +  (char)character + " {" + frequency +  "}\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private static void printQueue(PriorityQueue<HuffmanTree> huffmanQueue) {
        try {
			bw.write("printQueue:\n\n");
			for (PriorityQueue<HuffmanTree> q = new PriorityQueue<>(huffmanQueue); !q.isEmpty(); q.poll()) { 
	            printFrequency(q.peek().getByte(), q.peek().getFrequency());
	        }

	        bw.write("\n");
	        bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}


