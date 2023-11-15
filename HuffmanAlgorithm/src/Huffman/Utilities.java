package Huffman;

public class Utilities {

	static public String safeString( final byte character )
	{
	    if ( character == '\n' ) return "\\n";
	    if ( character == '\r' ) return "\\r";
	    if ( character == '\t' ) return "\\t";
	
	    if ( character >= 32 && character < 127 )
	        return "'"+character+"'";
	
	    return "\\x" + String.format("%02x", character);
	}
}

