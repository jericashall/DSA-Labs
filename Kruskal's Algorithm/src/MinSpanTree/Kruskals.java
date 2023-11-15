package MinSpanTree;
import java.util.ArrayList;

public class Kruskals {
	PriorityQueue q;
	DisjointSets sets;
	ArrayList<Edge> mst;
	int mstWeight = 0;
	int numVertices;
	
	public Kruskals(int v){
		q = new PriorityQueue(50);
		numVertices = v;
	}
	
	public void addEdge(Edge e) {		
		q.insert(e);
	}
	
	public ArrayList<Edge> getMinTree() {
		if(mst == null) {
			findMinTree();
		}
		
		return mst;
	}
	
	public int getMstWeight() {
		if(mstWeight > 0) {
			return mstWeight;
		}
		if(mst == null) {
			findMinTree();
		}
		
		int weight = 0;
		
		for( Edge e : mst) {
			weight += e.getWeight();
		}
		
		mstWeight = weight;
		
		return mstWeight;
	}
	
	
	private void findMinTree() {
		sets = new DisjointSets(numVertices);
		mst = new ArrayList<>();
		
		while(mst.size() != numVertices-1) {
			Edge e = q.deleteMin();
			
			int aset = sets.find(e.getOne());
			int bset = sets.find(e.getTwo());
			
			if(aset != bset) {
				mst.add(e);
				sets.union(aset, bset);
			}
		}
	}
}