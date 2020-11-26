/*
 * TIRA 2019 Coursework
 * Okko Partanen
 *
 * Simple class representing nodes of hashtables.
 * Every node has a value, a key and pointer to the next node 
 */

public class Node {
    
    private int value;
    private int key;
    private Node next;
    
    public Node(int key, int value){
        this.value = value;
        this.key = key;
        this.next = null;
    }
    
    public int getKey(){
        return key;
    }
    
    public int getValue(){
        return value;
    }
	
	public void setValue(int value){
		this.value = value;
	}
    
    public Node getNext(){
        return this.next;
    }
    
    public void setNext(Node next){
        this.next = next;
    }
    
}
