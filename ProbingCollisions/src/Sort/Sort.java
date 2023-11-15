package Sort;

import java.util.Comparator;
import java.util.ArrayList;

public class Sort{	
	public static void sort(ArrayList<Integer> list, Comparator<Integer> comp) {
		for(int i = 1; i < list.size(); i++) {
			int j = i;
			Integer temp = list.get(i);
			while(j > 0 && comp.compare(temp, list.get(j-1)) < 0){
				list.set(j, list.get(j-1));
				j--;	
			}
			list.set(j, temp);
		}	
	}
}
