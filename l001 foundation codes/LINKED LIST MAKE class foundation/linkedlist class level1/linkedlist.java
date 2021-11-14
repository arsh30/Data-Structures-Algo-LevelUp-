public class linkedlist{
    public class Node{
        int data = 0;
        Node next = null;

        Node(int data){
            this.data = data;  // cnst coz niche data same use kr paye
        }
    }

    private Node head = null;
    private Node tail = null;
    private int sizeOfLL = 0;

    public boolean isEmpty(){
        return this.sizeOfLL == 0;
    }
    public int size(){
        return this.sizeOfLL;
    }
    public void display(){
        Node curr = this.head;
        while(curr != null){
            System.out.print(curr.data + "->");
          curr = curr.next;
        }
        System.out.println();
    }

    //EXCEPTION HANDLING
    public void EmptyException() throws Exception{
        if(this.sizeOfLL == 0){
            throw new Exception("Linked list is empty:-1");
        }
    }
    public void IndexOutOfBoundSizeExclusive(int idx) throws Exception{
        if(this.sizeOfLL < 0 || idx >= this.sizeOfLL)
        throw new Exception("Index Out Of Bound : -1");
    }
    public void IndexOutOfBoundSizeInclusive(int idx) throws Exception{
        if(this.sizeOfLL < 0 || idx > this.sizeOfLL)
        throw new Exception("Index Out Of Bound : -1");
    }

    //get====================================

    public int getFirst() throws Exception{
        EmptyException();
        return this.head.data;

    }

    public int getlast() throws Exception{
        EmptyException();
        return this.tail.data;
    }
    private Node getNodeAt(int idx){
        Node curr = this.head;
        while(idx -- > 0){
            curr = curr.next;
        }
        return curr;
    }
    public int getAt(int idx) throws Exception{
        IndexOutOfBoundSizeExclusive(idx);
        Node node = getNodeAt(idx);
        return node.data;
    }

    //ADD==============
    
    private void addfirstNode(Node node){
        if(this.head == null){
            this.head = node;       //means node pr ham first tym visit hoye h
            this.tail = node;
        }
        else {
            node.next = this.head;
            this.head = node;
        }
        this.sizeOfLL++;
    }
    public void addfirst(int data){
        Node node = new Node(data);  //dta jo const tha use use kra
        addfirstNode(node);
    }

    public void addlastNode(Node node){
        if(this.head == null){
            this.head = node;
            this.tail = node;
        }else {
            this.tail.next = node;  //link bnaya
            this.tail = node;
        }
        this.sizeOfLL++;
    }
    public void addlast(int data){
        Node node = new Node(data);
        addlastNode(node);
    }
    private void addAtNode(int idx,Node node){
        if(this.sizeOfLL == 0) {
            addfirstNode(node);
        }
        else if (idx == this.sizeOfLL){
            addlastNode(node);
        } 
        else{
            Node prev = getNodeAt(idx-1);
            Node forw = prev.next;
          
            prev.next = node;
            node.next = forw;

            this.sizeOfLL++;
        }
    }
    public void addAt(int idx, int data) throws Exception{
        IndexOutOfBoundSizeInclusive(idx);
        Node node = new Node(data);
        addAtNode(idx,node);
    }

    //remove======================================
    
    private Node removeFirstNode(){
       Node removeNode = this.head;
        if(this.sizeOfLL == 1){
            this.head = null;
            this.tail = null;
        }else {
            Node forw = this.head.next;
            removeNode.next = null;
            this.head = forw;
        }
        this.sizeOfLL--;
        return removeNode;
    }

    public int removeFirst() throws Exception{
        EmptyException();
            Node node = removeFirstNode();
            return node.data;
    }

    private Node removeLastNode() {
        Node removeNode = this.tail;
        if(this.sizeOfLL == 1){
            this.head = null;
            this.tail = null;
        }else{
            Node secondLastNode = getNodeAt(this.sizeOfLL - 2);
            this.tail = secondLastNode;
            this.tail.next = null;
        }
        this.sizeOfLL--;
        return removeNode;
    }
    public int removeLast() throws Exception{
        EmptyException();
        Node node = removeLastNode();
        return node.data;

    }
    private Node removeNodeAt(int idx){
        if(idx == 0){
          return  removeFirstNode();
        }else if(idx == this.sizeOfLL - 1){
           return removeLastNode();
        }else{
            Node prev = getNodeAt(idx -1);
            Node curr = prev.next;
            Node forw = curr.next;

            prev.next = forw;
            curr.next = null;

            this.sizeOfLL--;
            return curr;
        }
    }
    public int removeAt(int idx) throws Exception{
        IndexOutOfBoundSizeExclusive(idx);
        Node node = removeNodeAt(idx);
        return node.data;
    }

}