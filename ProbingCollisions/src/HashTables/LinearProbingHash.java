package HashTables;

public class LinearProbingHash<Key extends Comparable<Key>, Value> extends HashBase<Key,Value> {
	@SuppressWarnings("unchecked")
	public LinearProbingHash(int size) {
		super(size);
	}
	
	@SuppressWarnings("unchecked")
	public LinearProbingHash() {
		super();
	}
	
	@Override
	//returns -1 if not found
	protected int lookUp(Key key) {
		final int startIndex = hashIndex(hash(key));
		int i = startIndex;
		
		while(table[i] != null && table[i].key.compareTo(key) != 0) {
			collisions++;
			i++;
			if(i > size-1) {
				i %= size;
			}
			
			if(i == startIndex) {
				return -1;
			}
		}
		
		return i;
	}

}
