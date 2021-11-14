public class client{
    public static void main(String[] args) throws Exception{
        linkedlist ll = new linkedlist();
        for(int i = 1; i <= 10 ; i++){
            ll.addlast(i * 10);
        }
        // System.out.println(ll.getlast());
        ll.display();
        ll.removeAt(9);
        ll.display();
    }
    
}