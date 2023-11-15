package HashTables;

public class QuadraticProbingHash<Key extends Comparable<Key>, Value> extends HashBase<Key,Value> {
	@SuppressWarnings("unchecked")
	public QuadraticProbingHash(int size) {
		super(size);
	}
	
	@SuppressWarnings("unchecked")
	public QuadraticProbingHash() {
		super();
	}
	
	@Override
	//returns -1 if not found
	protected int lookUp(Key key) {
		final int startIndex = hashIndex(hash(key));
		int i = startIndex;
		int offset = 1;
		
		while(table[i] != null && table[i].key.compareTo(key) != 0) {
			collisions++;
			i += offset;
			offset += 2;
			i %= size;
			
			if(i == startIndex) {
				break;
			}
		}
		
		return i;
	}

}
