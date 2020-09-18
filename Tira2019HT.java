import java.util.Scanner;

public class Tira2019HT{
	public void run(){	
		Scanner keyboard = new Scanner(System.in);
		ReaderAndWriter readerWriter = new ReaderAndWriter();
		System.out.println("\n\nTira 2019 course work. Welcome");
		System.out.println("The program can handle integers up to MAX_INT");
		System.out.println("On default settings, the program intialaizes a hashtable (or 2, depending on the set operation) with a size of 24.");
		System.out.println("If load factor exceeds 0.75, the table will double in size and rehash all elements.\n");
		System.out.println("Starting with OR-operation.");
		System.out.println("Reading text files and constructing a new table for OR-operation...");
		HashTable table = readerWriter.readInput("OR");		
		System.out.println("\nNow you can remove key(s) from the table if you wish");
		System.out.println("Table contains " + table.getCount() + " key/value pairs.");
		
		
		boolean keepAsking = true;		
		while(keepAsking){									
			System.out.println("Input key value to remove it from the table or input any non-numerical value to write the file: ");
			try{
				int i = keyboard.nextInt();
				if (table.delete(i))
					System.out.println("Removed the key with value: " + i);
				else
					System.out.println("No such key found");			
			
			}catch(Exception e){				
				keepAsking = false;				
			}
			
		}	
		System.out.println("\nFinal table contains " + table.getCount() + " key/value pairs.");		
		System.out.println("Trying to write OR-table as a text file named OR.txt...");
		if	(readerWriter.writeTable(table, "OR.txt"))	
			System.out.println("OR.txt file written\n");
		else
			System.out.println("Error!");

		
		System.out.println("Reading text files and constructing a new table for AND-operation...");
		table = readerWriter.readInput("AND");	
		System.out.println("\nNow you can remove key(s) from the table if you wish");
		System.out.println("Table contains " + table.getCount() + " key/value pairs.");
		keyboard = new Scanner(System.in);
		keepAsking = true;		
		while(keepAsking){									
			System.out.println("Input key value to remove it from the table or input any non-numerical value to write the file: ");
			try{
				int i = keyboard.nextInt();
				if (table.delete(i))
					System.out.println("Removed the key with value: " + i);
				else
					System.out.println("No such key found");			
			
			}catch(Exception e){				
				keepAsking = false;			
			}
			
		}
		System.out.println("Table contains " + table.getCount() + " key/value pairs.");
		System.out.println("Trying to write AND-table as a text file named AND.txt...");
		readerWriter.writeTable(table, "AND.txt");		
		System.out.println("AND.txt file written\n");		
	
		System.out.println("Reading text files and constructing a new table for XOR-operation...");
		table = readerWriter.readInput("XOR");
		System.out.println("\nNow you can remove key(s) from the table if you wish");
		keyboard = new Scanner(System.in);
		keepAsking = true;		
		while(keepAsking){									
			System.out.println("Input key value to remove it from the table or input any non-numerical value to write the file: ");
			try{
				int i = keyboard.nextInt();
				if (table.delete(i))
					System.out.println("Removed the key with value: " + i);
				else
					System.out.println("No such key found");			
			
			}catch(Exception e){				
				keepAsking = false;				
			}
			
		}
		System.out.println("Trying to write XOR-table as a text file named XOR.txt...");
		readerWriter.writeTable(table, "XOR.txt");	
		System.out.println("XOR.txt file written\n");
		System.out.println("Bye!");
	}	
		
}