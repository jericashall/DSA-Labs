import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileReader;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import MinSpanTree.Kruskals;
import MinSpanTree.Edge;
import Map.HashMap;


public class Driver {
	static PrintWriter pw;
	static Scanner scan;
	static Kruskals tree;
	static ArrayList<Edge> mst;
	static ArrayList<Edge> edges;
	static HashMap<Integer, Boolean> vertex;
	
	public static void main(String[] args) {
		for(int i = 2; i < 4; i++) {
			String in = "in" + i + "_edges.txt";
			String out = "out" + i + "_edges.txt";
			parseData(in, out);
		}

	}
	
	static void parseData(String inFile, String outFile) {
		try {
			FileReader fr = new FileReader(inFile);
			scan = new Scanner(fr);
			FileWriter fw = new FileWriter(outFile);
			pw = new PrintWriter(fw);
		
			readData();
			findMst();
			printData();
		
			fr.close();
			scan.close();
			fw.close();
			pw.close();		
		} catch (FileNotFoundException ex) {
			System.out.println("Exception: " + ex.getMessage());
			ex.printStackTrace();
		} catch (IOException ex) {
			System.out.println("Exception: " + ex.getMessage());
			ex.printStackTrace();
		} catch(Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	static void readData() {
		scan.nextLine();
		edges = new ArrayList<>();
		vertex = new HashMap<Integer, Boolean>(50);
		
		while(scan.hasNextInt()) {
			Integer[] edge = new Integer[3];
			for(int i = 0; i < edge.length; i++) {
				edge[i] = scan.nextInt();
			}
			vertex.put(edge[0], true);
			vertex.put(edge[1], true);
			Edge e = new Edge(edge[0], edge[1], edge[2]);
			edges.add(e);
		}		
	}
	
	static void findMst(){
		tree = new Kruskals(vertex.getSize());
		for(Edge e : edges) {
			tree.addEdge(e);
		}
		mst = tree.getMinTree();
	}
	
	static void printData() {
		pw.write("Graph edges: vertice1, vertice2, weight of the edge\n\n");	
		for(Edge e : edges) {
			pw.write("edge: " + e.getOne() + ", " + e.getTwo() + ", " + e.getWeight() +"\n");
		}
		
		pw.write("\nKruskal spanning tree edges: vertice1, vertice 2, weight of the edge\n\n");
		for(Edge e : mst) {
			pw.write("edge: " + e.getOne() + ", " + e.getTwo() + ", " + e.getWeight() +"\n");
		}
		
		pw.write("\nKruskal spanning tree weight is " + tree.getMstWeight());
	}
	
	
}
