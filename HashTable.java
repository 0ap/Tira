/*
 * HashTable implementation for TIRA2019 course.
 * Okko Partanen 423161
 * okko.partanen@tuni.fi
 * 
 * The hashtable resizes dynamically, when loadfactor is > 0.75.
 * The table can be constructed without parameters, in this case the default loadfactor is 0.75 and size 24.
 * To construct it with custom loadfactor and size use overloaded constructor.
 */
public class HashTable {
    
    private int size;
    private double loadFactor;
    //Array for nodes
    private Node[] table;
    private int count;
	
    //If you wish to construct the table with custom loadfactor and size
    public HashTable (int size, double loadFactor){
        this.size = size;
        this.loadFactor = loadFactor;
        this.table = new Node[size];
    }
    
    public HashTable(){
        this.size = 24;
        this.loadFactor = 0.75;
        this.table = new Node[size];        
    }


    //Add function for adding a integer in to the hashtable
	
    public void put(int key, int value){        
        int bucket = key % size; //Calculate the correct bucket. Use the key as hash value.       
        Node listNode = this.table[bucket];     
        if (!contains(key)){ //Check that the table dosen't contain the key already.
            if (listNode == null){
                Node newNode = new Node(key,value); //New node that holds key and value.
                this.table[bucket] = newNode; //Put the node in to bucket.
                this.count = count + 1;
            }else{ //If bucket already contains a key, traverse the nodes untill we find empty spot.
                Node helperNode = null;
                while (listNode != null){
                    helperNode = listNode;
                    listNode = listNode.getNext();                
                }
                Node newNode = new Node(key,value);
                helperNode.setNext(newNode); 
                this.count = count + 1;                
            }
            if (new Double(count) / new Double(size) > loadFactor) //Call resizeTable if table is getting full.
                resizeTable();           
        }
    }
    //Delete function.
    public boolean delete(int key){
        int bucket = key % size;
        if (table[bucket] == null)
            return false; //If the bucket was empty, return false.
        
	//If head of the bucket contains the right key, delete it and set the next node as head of bucket.
        if (table[bucket].getKey() == key){
            table[bucket] = table[bucket].getNext();
            this.count--;
            return true;         
        }
        
	//If the right key is not the head of bucket search for it while keeping tracking of previous node.
        Node prev = table[bucket];
        Node current = prev.getNext();
        while (current != null && current.getKey() != key){
			prev = current;
            current = current.getNext();            
        }
        
	//If the wanted node was found, delete it.
        if (current != null){
            prev.setNext(current.getNext());
            this.count--;
            return true;
        }
	//Return false if key was not found.
        return false;
    }
    
    //Returns node associated with key
    public Node get(int key){       
       int bucket = key % size;
       Node listNode = table[bucket];
       //Iterate nodes untill we hit null node.
       while (listNode != null){
	   //If node equals key we're searching return it    
           if (listNode.getKey() == key)
               return listNode;
           listNode = listNode.getNext();
       }       
       return null;
   }
	
    //Function for checking if hashtable contains a key.
    public boolean contains(int key){
       int bucket = key % size;
       Node listNode = table[bucket];
       while (listNode != null){
           if (listNode.getKey() == key)
               return true;
           listNode = listNode.getNext();
       }       
       return false;
   }
    
   
    //Function for resizing the table. Doubles the space in the table.
    public void resizeTable(){
        this.size = size * 2;
        Node[] tempTable = new Node[size];        
           
        for (int i = 0; i < this.table.length; i++){
            Node current = this.table[i];
            while (current != null){
                Node next = current.getNext();
                int bucket = current.getKey() % tempTable.length;
                current.setNext(tempTable[bucket]);
                tempTable[bucket] = current;
                current = next;
            }
        }
        this.table = tempTable;      
    }
    
    //Function for checking collisions in the hashtable.
    public double distribution(){
        int counter = 0;
        for (int i = 0; i < this.table.length; i++){
            if (table[i] != null)
                counter = counter + 1;           
        }
        return new Double(counter)/new Double(this.count);
    }
	
    //Prints key and value pairs of the table.
    void print(){
		System.out.println("---------------------------");
        for (int i = 0; i < table.length; i++){             
            Node listNode = table[i];
            while (listNode != null){				
                System.out.println("Key: " + listNode.getKey() + " Value: " + listNode.getValue());
                listNode = listNode.getNext();
            }                
        }System.out.println("---------------------------");
    }
	
	public Node[] getArray(){
		return this.table;
    }
	
	public int getSize(){
        return this.size;
    }
    
    public int getCount(){
        return this.count;
    }
}
    

