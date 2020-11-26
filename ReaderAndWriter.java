import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/*
 * TIRA2019 coursework
 * Okko Partanen 423161, okko.partanen@tuni.fi
 * ReaderAndWriter class is responsible for reading the input files and writing the output files.
 */

public class ReaderAndWriter{
	
	/*
	* readInput reads two text files (containg only whole numbers seperated by line) and performs set operations on the numbers - with a help of hashtable. 
	* Takes as an String input the wanted set operation (AND, OR, XOR). 
	* Returns a hashtable that contains the result of the set operation.
	*
	* Numbers are stored as keys in the hashtable and values depend on the current operation.
	* In OR operation value is how many times the integer (key) is present in both files.
	*
	* In AND operation the value is the line number on which the integer (key) was present for the first time in setA.txt.
	*
	* IN XOR operation the value is either 1 or -- 1 if the integer is from setA.txt and 2 if it's from setB.txt	
	*/

	public HashTable readInput(String operation){
		
		/*
		* In OR-operation iterate through both text files checking on every line if the integer is already in the hashtable.
		* If it is: increase the value by one. If it's not in the table yet: add it there and set 1 as the value.		
		*/
		
		if (operation.equals("OR")){
			HashTable table = new HashTable();
			String line;
			//Read setA.txt line by line.
			try{
				BufferedReader br = new BufferedReader(new FileReader("setA.txt"));			
				while ((line = br.readLine()) != null) {				
					String[] values=line.split("\n");
					int result = Integer.parseInt(values[0]);
					//If integer is in the hashtable increment it's value by one.
					if (table.contains(result)){
						Node node = table.get(result);
						int val = node.getValue();
						node.setValue(val + 1);	
					//If integer is not in the hashtable put it there and set 1 as value.
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
		
		/*
		* In AND-operation construct 2 hashtables. Iterate first through setA.txt and check if Integer is in the hashtable.
		* If integer is not in the hashtable put it there and set the linenumber as value.
		* After that we iterate through setB.txt. On every line check if integer is present in hashtable that contains setA.txt integers.
		* If the integer is present in both setA.txt and setB.txt put it in the second hashtable.
		* Returns the second hashtable (containing integers which were present in both 'setA.txt' and 'setB.txt')
		*/
		
		if (operation.equals("AND")){		
			HashTable table = new HashTable();
			HashTable table2 = new HashTable();
			String line;
			//Variable for keeping count of the linenumber
			int lineCounter = 0;
			try{
				BufferedReader br = new BufferedReader( new FileReader("setA.txt"));
				while ((line = br.readLine()) != null) {				
					String[] values=line.split("\n");
					int result = Integer.parseInt(values[0]);
					//If integer is not in the hashtable put it there
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
				BufferedReader br = new BufferedReader(new FileReader ("setB.txt"));
				while ((line = br.readLine()) != null) {				
					String[] values=line.split("\n");
					int result = Integer.parseInt(values[0]);
					//If we find an integer that was also in setA.txt: put it in second hashtable.
					if (table.contains(result) && ! table2.contains(result)){
						Node node = table.get(result);					
						table2.put(node.getKey(), node.getValue());					
					}
				}
				br.close();
			}catch(IOException e){
				System.out.println("File not found.");
			}
			//Return the second hashtable
			return table2;
		}
		
		
		/*
		* In XOR-operation construct 2 hashtables. First go through setA.txt and put every integer (key) it contains to hashtable and set 1 as value.
		* Iterate setB.txt. Check if integer is already deleted once or if it's in hashtable and from setA.txt (if value == 1). If the integer is present in both text files
		* delete it from the table. Also add it in 'deleted' hashtable. This prevents an Integer that is in setA.txt and multiple times in setB.txt
		* from being added to the hashtable after being deleted once.
		* If the Integer is only present in setB.txt add it to table and set value as 2.
		*
		* Returns a hashtable containing result of XOR-operation on these 2 textfiles. 
		*/
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
			//Read setB.txt.
			try{				
				BufferedReader br = new BufferedReader( new FileReader("setB.txt"));
				while ((line = br.readLine()) != null) {				
					String[] values=line.split("\n");
					int result = Integer.parseInt(values[0]);
					//If integer is not in the table and its not already deleted once add it
					if (!table.contains(result) && !deleted.contains(result)){						
						table.put(result, 2);
					//If the table contains the integer check if it's from setA.txt or setB.txt
					}else if (table.contains(result)){
						Node node = table.get(result);
						//If it's from setA.txt delete the integer from hashtable and put it into deleted hashtable.
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
	

	/*
	* A function for writing a hashtable in to a text file.
	* Takes the hashtable and wanted filename as a parameter
	*/
	
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
