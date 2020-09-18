import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/*
 * TIRA2019 coursework
 * Okko Partanen 423161, okko.partanen@tuni.fi
 * ReaderAndWriter class is responsible for reading the input texts, and writing
 * the output texts.
 */

public class ReaderAndWriter{
	//Reads a text file and constructs a hashtable, then returns it. Takes as a input
	//the wanted set operation.
	public HashTable readInput(String operation){			
		if (operation.equals("OR")){ //If we're doing OR operation.
			HashTable table = new HashTable();
			String line;
			try{
				//Read setA.txt line by line. If the integer(key) is already in the table
				//increment it's value by one. Otherwise add it to the table with 1 as a value.
				BufferedReader br = new BufferedReader(new FileReader("setA.txt"));			
				while ((line = br.readLine()) != null) {				
					String[] values=line.split("\n");
					int result = Integer.parseInt(values[0]);
					if (table.contains(result)){
						Node node = table.get(result);
						int val = node.getValue();
						node.setValue(val + 1);					
					}else{
						table.put(result, 1);
					}
				}
				br.close();
			}catch(IOException e){
				System.out.println("File not found.");
			}
			//Read setB.txt line by line. Logic here is the same as with setA.txt.
			try{
				BufferedReader br = new BufferedReader( new FileReader("setB.txt"));
				while ((line = br.readLine()) != null) {				
					String[] values=line.split("\n");
					int result = Integer.parseInt(values[0]);
					if (table.contains(result)){
						Node node = table.get(result);
						int val = node.getValue();
						node.setValue(val + 1);					
					}else{
						table.put(result, 1);
					}
				}
				br.close();
			}catch(IOException e){
				System.out.println("File not found.");
			}
				
			return table;
		}	
		
		if (operation.equals("AND")){ //If we're doing AND operation
			HashTable table = new HashTable();//Construct 2 tabels
			HashTable table2 = new HashTable();
			String line;
			int lineCounter = 0;
			try{
				BufferedReader br = new BufferedReader( new FileReader("setA.txt"));
				while ((line = br.readLine()) != null) {				
					String[] values=line.split("\n");
					int result = Integer.parseInt(values[0]);					
					if (!table.contains(result)){				
						table.put(result, lineCounter);						
					}
					lineCounter++;					
				}
				br.close();
			}catch(IOException e){
				System.out.println("File not found.");
			}
			
			try{
				BufferedReader br = new BufferedReader( new FileReader("setB.txt"));
				while ((line = br.readLine()) != null) {				
					String[] values=line.split("\n");
					int result = Integer.parseInt(values[0]);
					if (table.contains(result) && ! table2.contains(result)){
						Node node = table.get(result);					
						table2.put(node.getKey(), node.getValue());					
					}
				}
				br.close();
			}catch(IOException e){
				System.out.println("File not found.");
			}
			return table2;
		}
		
		if (operation.equals("XOR")){ //If we're doing XOR operation, construct 2 tabels.
			HashTable table = new HashTable();	
			HashTable deleted = new HashTable();
			String line;
			//Reading setA.txt. If integer is not in the table, put it and set 1 as value.
			try{
				BufferedReader br = new BufferedReader( new FileReader("setA.txt"));
				while ((line = br.readLine()) != null) {				
					String[] values=line.split("\n");
					int result = Integer.parseInt(values[0]);					
					if (!table.contains(result)){				
					table.put(result, 1);
					}				
				}
				br.close();
			}catch(IOException e){
				System.out.println("File not found.");
			}
			//Reading setB.txt. If table already contains the integer, and it's from setA.txt
			//set its value to 3. If not, it's only present in setB.txt. In that case put in the table, and set 2 as value.
			try{				
				BufferedReader br = new BufferedReader( new FileReader("setB.txt"));
				while ((line = br.readLine()) != null) {				
					String[] values=line.split("\n");
					int result = Integer.parseInt(values[0]);
					if (!table.contains(result) && !deleted.contains(result)){						
						table.put(result, 2);
					}else if (table.contains(result)){
						Node node = table.get(result);						
						if (node.getValue() == 1){
							deleted.put(node.getKey(),0);
							table.delete(node.getKey());
						}						
					}
				}
				br.close();
			}catch(IOException e){
				System.out.println("File not found.");
			}			
			return table;
		}
		return null;
	}
	
	
	public HashTable readInput(String operation){			
		if (operation.equals("OR")){ //If we're doing OR operation.
			HashTable table = new HashTable();
			String line;
			try{
				//Read setA.txt line by line. If the integer(key) is already in the table
				//increment it's value by one. Otherwise add it to the table with 1 as a value.
				BufferedReader br = new BufferedReader(new FileReader("setA.txt"));			
				while ((line = br.readLine()) != null) {				
					String[] values=line.split("\n");
					int result = Integer.parseInt(values[0]);
					if (table.contains(result)){
						Node node = table.get(result);
						int val = node.getValue();
						node.setValue(val + 1);					
					}else{
						table.put(result, 1);
					}
				}
				br.close();
			}catch(IOException e){
				System.out.println("File not found.");
			}
			//Read setB.txt line by line. Logic here is the same as with setA.txt.
			try{
				BufferedReader br = new BufferedReader( new FileReader("setB.txt"));
				while ((line = br.readLine()) != null) {				
					String[] values=line.split("\n");
					int result = Integer.parseInt(values[0]);
					if (table.contains(result)){
						Node node = table.get(result);
						int val = node.getValue();
						node.setValue(val + 1);					
					}else{
						table.put(result, 1);
					}
				}
				br.close();
			}catch(IOException e){
				System.out.println("File not found.");
			}
				
			return table;
		}	
		
		if (operation.equals("AND")){ //If we're doing AND operation
			HashTable table = new HashTable();//Construct 2 tabels
			HashTable table2 = new HashTable();
			String line;
			int lineCounter = 0;
			try{
				BufferedReader br = new BufferedReader( new FileReader("setA.txt"));
				while ((line = br.readLine()) != null) {				
					String[] values=line.split("\n");
					int result = Integer.parseInt(values[0]);					
					if (!table.contains(result)){				
						table.put(result, lineCounter);						
					}
					lineCounter++;					
				}
				br.close();
			}catch(IOException e){
				System.out.println("File not found.");
			}
			
			try{
				BufferedReader br = new BufferedReader( new FileReader("setB.txt"));
				while ((line = br.readLine()) != null) {				
					String[] values=line.split("\n");
					int result = Integer.parseInt(values[0]);								
					table.put(node.getKey(), node.getValue());					
					}
				}
				br.close();
			}catch(IOException e){
				System.out.println("File not found.");
			}
			return table2;
		}
		if (operation.equals("XOR")){ //If we're doing XOR operation, construct 2 tabels.
			HashTable table = new HashTable();	
			HashTable deleted = new HashTable();
			String line;
			//Reading setA.txt. If integer is not in the table, put it and set 1 as value.
			try{
				BufferedReader br = new BufferedReader( new FileReader("setA.txt"));
				while ((line = br.readLine()) != null) {				
					String[] values=line.split("\n");
					int result = Integer.parseInt(values[0]);					
					if (!table.contains(result)){				
					table.put(result, 1);
					}				
				}
				br.close();
			}catch(IOException e){
				System.out.println("File not found.");
			}
			//Reading setB.txt. If table already contains the integer, and it's from setA.txt
			//set its value to 3. If not, it's only present in setB.txt. In that case put in the table, and set 2 as value.
			try{				
				BufferedReader br = new BufferedReader( new FileReader("setB.txt"));
				while ((line = br.readLine()) != null) {				
					String[] values=line.split("\n");
					int result = Integer.parseInt(values[0]);
					if (!table.contains(result) && !deleted.contains(result)){						
						table.put(result, 2);
					}else if (table.contains(result)){
						Node node = table.get(result);						
						if (node.getValue() == 1){
							deleted.put(node.getKey(),0);
							table.delete(node.getKey());
						}						
					}
				}
				br.close();
			}catch(IOException e){
				System.out.println("File not found.");
			}			
			return table;
		}
		return null;
	}
	

	//Function for writing a table in to a text file.
	//Takes a table, and filename as a parameter.
	public boolean writeTable(HashTable table, String filename){
		//Get the array holding all nodes.
		Node[] nodeArray = table.getArray();		
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
			//Iterate the array and all the nodes. Write them in to the file.
			for(Node node : nodeArray){
				while(node != null){
					String outputrow = node.getKey() + " " + node.getValue();				
					bw.write(outputrow);
					bw.newLine();
					node = node.getNext();
				}
											
			}
			bw.close();
			
		}catch(IOException e) {			
			return false;
		}return true;		
	}
	
	
	
	
}