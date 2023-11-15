package MinSpanTree;

public class Edge implements Comparable<Edge>{
	Integer verOne ;
	Integer verTwo;
	Integer weight;
	
	public Edge(int one, int two, int w){
		verOne = one;
		verTwo = two;
		weight = w;
	}
	
	public int getOne() {
		return verOne;
	}
	
	public int getTwo() {
		return verTwo;
	}
	
	public int getWeight() {
		return weight;
	}
	
	@Override
	public int compareTo(Edge b) {
		return this.weight - b.getWeight();
	}
}
