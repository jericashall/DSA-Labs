package HashTables;

public interface HashInterface<Key, Value> {
	Value get(Key key);
	void put(Key key, Value value);
	int getCollisions();	
}
