package HashTables;

public class DoubleHashingProbingHash<Key extends Comparable<Key>, Value> extends HashBase<Key,Value> {
	@SuppressWarnings("unchecked")
	public DoubleHashingProbingHash(int size) {
		super(size);
	}
	
	@SuppressWarnings("unchecked")
	public DoubleHashingProbingHash() {
		super();
	}
	
	private int hash2(int key) {
		int num = (int) key;
		return 181 - (num % 181);
	}
	
	@Override
	//returns -1 if not found
	protected int lookUp(Key key) {
		final int hashOne = hash(key);
		final int hashTwo = hash2(hashOne);
		final int startIndex = hashIndex(hashOne);
		int i = startIndex;
		int j = 1;
		
		while(table[i] != null && table[i].key.compareTo(key) != 0) {
			collisions++;
			i = (hashOne + (j*hashTwo))%size;
			j++;
				
			if(i == startIndex) {
				return -1;
			}
		}
		
		return i;
	}
}