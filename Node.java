/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
