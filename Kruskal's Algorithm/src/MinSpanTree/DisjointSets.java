package MinSpanTree;

public class DisjointSets {
	int[] sets;
	
	public DisjointSets(int num) {
		sets = new int[num];
		for(int i = 0; i < sets.length; i++) {
			 sets[i] = -1;
		}		
	}
	
	public int find(int v) {
		if(sets[v] < 0) {
			return v;
		} else {
			return sets[v] = find(sets[v]);
		}
	}
	
	public void union(int a, int b) {
		if(sets[b] < sets[a]) {
			sets[a] = sets[b];
		} else {
			/*if(sets[a] == sets[b]) {
				sets[a]--;
			}*/
			sets[b] = a;
		}
	}
}
