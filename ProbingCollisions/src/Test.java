import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class Test {

	public static void main(String[] args) {
		parseTables("out170_tables.txt", "out170_tablesf.txt");
		
	}
	
	public static void parseTables(String one, String two) {
		try {
			FileReader frOne =  new FileReader(one);
			FileReader frTwo = new FileReader(two);
			Scanner scOne = new Scanner(frOne);
			Scanner scTwo = new Scanner(frTwo);
			Character I = Character.valueOf('i');
			Character star = Character.valueOf('*');
			String type = "";
			int errors = 0;
		
			while(scOne.hasNextLine() && scTwo.hasNextLine()) {
				String example = scOne.nextLine();
				String myAnswer = scTwo.nextLine();
				
				if(example.isEmpty()) continue;
				
				if(example.charAt(0) == star) {
					String[] arr = example.split(" ");
					type = String.join(" ", arr[1], arr[2]);
				}

				if(example.charAt(0) != I) continue;

				Integer[] exArr = new Integer[3];
				Integer[] ansArr = new Integer[3];
				
				int i = 0;
				int j = 0;
				int k = 0;
				int p = 0;
				
				while( i < example.length() && j < myAnswer.length()) {
					Character valOne = example.charAt(i);
					Character valTwo = myAnswer.charAt(j);
					String ex = "";
					String ans = "";
					
					
					while(i < example.length() && Character.isDigit(valOne) || j < myAnswer.length() && Character.isDigit(valTwo))  {
						if(i < example.length() && Character.isDigit(valOne)) {
							ex += valOne;
							i++;
							if(i < example.length()) {
								valOne = example.charAt(i);
							}
						}
						
						if(j < myAnswer.length() && Character.isDigit(valTwo)) {
							ans += valTwo;
							j++;
							if(j < myAnswer.length()) {
								valTwo = myAnswer.charAt(j);
							}
						}
					}
					

					if(!ex.isEmpty()) {
						exArr[k] = Integer.parseInt(ex);
						k++;
					}
					
					if(!ex.isEmpty()) {
						ansArr[p] = Integer.parseInt(ans);
						p++;
					}
					i++;
					j++;
					
				}
				
				if(exArr[0].compareTo(ansArr[0]) != 0) {
					System.out.println("WRONG ANSWER FOR TYPE " + type + ":");
					System.out.println("index: " + ansArr[0] + ", key: " + ansArr[1]);
					System.out.println("SHOULD BE:");
					System.out.println("index: " + exArr[0] + ", key: " + exArr[1]);
					errors++;
				}else if(exArr[1].compareTo(ansArr[1]) != 0) {
					System.out.println("WRONG ANSWER FOR TYPE " + type + ":");
					System.out.println("index: " + ansArr[0] + ", key: " + ansArr[1]);
					System.out.println("SHOULD BE:");
					System.out.println("index: " + exArr[0] + ", key: " + exArr[1]);
					errors++;
				} else if(exArr[2].compareTo(ansArr[2]) != 0) {
					System.out.println("WRONG ANSWER FOR TYPE " + type + ":");
					System.out.println("at index: " + ansArr[0] + "and, key: " + ansArr[1] + " value: " + ansArr[2] + "mistmatches");
					System.out.println("SHOULD BE:");
					System.out.println("index: " + exArr[0] + ", key: " + exArr[1] + ", value: " + exArr[2]);
					errors++;
				}

			}
		
			System.out.println("Total errors: " + errors);
		
			scOne.close();
			scTwo.close();
			frOne.close();
			frTwo.close();
		} catch (FileNotFoundException e) {
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void parseCollisions(String one, String two) {
		try {
			FileReader frOne =  new FileReader(one);
			FileReader frTwo = new FileReader(two);
			Scanner scOne = new Scanner(frOne);
			Scanner scTwo = new Scanner(frTwo);
			int errors = 0;
		
			while(scOne.hasNextLine() && scTwo.hasNextLine()) {
				String example = scOne.nextLine();
				String myAnswer = scTwo.nextLine();
				
				if(!Character.isDigit(example.charAt(0))) {
					continue;
				}
				
				String[] exArr = example.split(" ");
				String[] ansArr = myAnswer.split(" ");
				
				if(exArr[0] != ansArr[0]) {
					System.out.println("WRONG ANSWER FOR TYPE " + ":");
					System.out.println("index: " + ansArr[0] + ", key: " + ansArr[1]);
					System.out.println("SHOULD BE:");
					System.out.println("index: " + exArr[0] + ", key: " + exArr[1]);
					errors++;
				}else if(exArr[1] != ansArr[1]) {
					System.out.println("WRONG ANSWER FOR TYPE " + ":");
					System.out.println("index: " + ansArr[0] + ", key: " + ansArr[1]);
					System.out.println("SHOULD BE:");
					System.out.println("index: " + exArr[0] + ", key: " + exArr[1]);
					errors++;
				} else if(exArr[2] != ansArr[2]) {
					System.out.println("WRONG ANSWER FOR TYPE " + ":");
					System.out.println("at index: " + ansArr[0] + "and, key: " + ansArr[1] + " value: " + ansArr[2] + "mistmatches");
					System.out.println("SHOULD BE:");
					System.out.println("index: " + exArr[0] + ", key: " + exArr[1] + ", value: " + exArr[2]);
					errors++;
				}

			}
		
			System.out.println("Total errors: " + errors);
		
			scOne.close();
			scTwo.close();
			frOne.close();
			frTwo.close();
		} catch (FileNotFoundException e) {
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
