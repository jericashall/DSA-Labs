package Huffman;

public class HuffmanTree implements Comparable<HuffmanTree> {
	private Integer byte_;
    private int frequency;
    private HuffmanTree left;      
    private HuffmanTree right;  
		
    public HuffmanTree(Integer byte_, int frequency) {
    	this.byte_ = byte_;
    	this.frequency = frequency;
    	this.left = null;      
    	this.right = null;    
    }
        
    public HuffmanTree(HuffmanTree first, HuffmanTree second){
    	this.byte_ = null;
    	if(first == null) {
    		this.left = second;
    		this.frequency = second.getFrequency();
    	} else if(second == null) {
    		this.left = first;
    		this.frequency = first.getFrequency();
    	} else {
    		this.frequency = first.getFrequency() + second.getFrequency();
        	if(first.getFrequency() <= second.getFrequency()) {
        		this.left = first;
        		this.right = second;
        	} else {
        		this.left = second;
        		this.right = first;
        	}
    	}
    }

    public int getFrequency() {
    	return frequency;
    }

    public Integer getByte() {
    	return byte_;
    }

    public HuffmanTree getLeft() {
    	return left;
    }

    public HuffmanTree getRight(){
    	return right;
    }

    public boolean isTerminal() {
    	return left == null && right == null;
    }
    
    @Override
    public int compareTo(HuffmanTree b) {
    	int freq = this.getFrequency() - b.getFrequency();
    	
    	if(freq == 0 && this.getByte() != null && b.getByte() != null) {
    		return this.getByte() - b.getByte();
    	} else {
    		return freq;
    	}
    }
}

