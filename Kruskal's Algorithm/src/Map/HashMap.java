package Map;

import java.util.Arrays;

public class HashMap<Key extends Comparable<Key>, Value> {
	@SuppressWarnings("hiding")
	public class Record<Key, Value>{
		public Key key;
		public Value value;
		
		public Record(Key key, Value value){
			this.key = key;
			this.value = value;
		}
	}
	
	public Record<Key,Value>[] table;
	public int collisions;
	public int size;
	public int items;
	
	@SuppressWarnings("unchecked")
	public HashMap(int size, Record<Key,Value>... dummy) {
		table = Arrays.copyOf(dummy, size);
		this.size = size;
		this.collisions = 0;
	}
	
	@SuppressWarnings("unchecked")
	public HashMap(Record<Key,Value>... dummy) {
		table = Arrays.copyOf(dummy, size);
		this.size = 191;
		this.collisions = 0;
	}
	
	public Value get(Key key) {
		final int index = lookUp(key);
		if(index == -1) {
			return null;
		}
		
		Record<Key,Value> p = table[index];

		return p != null ? p.value : null;
	}
	
	public void put(Key key, Value value) {
		final int index = lookUp(key);
		if(index == -1) {
			throw new RuntimeException("Table is full");
		}
		
		Record<Key,Value> p = table[index];
		if (p == null) {
			table[index] = new Record<Key,Value>(key, value);
			items++;
		} else {
			p.value = value;
		}
	}
	
	public int getCollisions() {
		return collisions;
	}
	
	public void printTable() {
		System.out.println("print table.size()=" + size);
		
		for(int i = 0; i < size; i++) {
			Record<Key,Value> p = table[i];
			if(table[i] != null) {
				System.out.println("index=" + i + " key=" + p.key + " value=" + p.value);
			}
		}
	}
	
	public int getSize() {
		return items;
	}
	
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
	
	protected static <Key> int hash(Key key) {
		int num = (int) key;
		return (num>>8)|((num&0xff)<<16);
	}
	
	protected int hashIndex(int hashCode) {
		return hashCode%size;
	}
}
