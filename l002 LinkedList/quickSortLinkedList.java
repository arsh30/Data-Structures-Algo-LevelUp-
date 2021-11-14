
public class quickSortLinkedList {
    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val; // we make a constructor
        }
    }

    // st hm tail mangwaeg
    public static ListNode getTail(ListNode node) {
        if (node == null || node.next == null)
            return node;

        ListNode tail = node;
        while (tail.next != null) {
            tail = tail.next; // jb tak tail ko aage move krte rho
        }
        return tail;
    }

    public static int length(ListNode node) {
        //for finding index
        if (node == null)
            return 0;
        int len = 0;
        ListNode curr = node;
        while (curr != null) {
            len++;
            curr = curr.next;
        }
        return len;
    }

    public static ListNode[] segregate(ListNode head, ListNode tail, int pivotIdx) { // segregate over pivot index becose in ll it is
        ListNode small = new ListNode(-1);
        ListNode large = new ListNode(-1);
        ListNode pivotNode = head, sp = small, lp = large, curr = head; //so swap over pivot index me possible nhi h to we use 3 pointer (small,large,pivotNode)
                                                                        // jese pivot Node milega hm usko point krke rakh lenege
        while (pivotIdx-- > 0) { //hme data milega ,; so pehle pta lgana pdhega ki data ko kidr milega vo hme pta lgega by using  loop 
            pivotNode = pivotNode.next; //hm pivot node ko correct location pr bhjege
        }
        while (curr != null) {
            if (curr != null) {
                if (curr.val <= pivotNode.val) {
                    sp.next = curr;
                    sp = sp.next;
                } else {
                    lp.next = curr;
                    lp = lp.next;
                }
            }
            curr = curr.next;
        }
        pivotNode.next = null; //aate hi yeh kra
        sp.next = null;
        lp.next = null; //coz agar last me 3 mila to vo bhi hoga

        //we have to return 5 thing lh lt pn rh rt o uske liye hm ek arrayList mangwayege
        ListNode lh = small.next;
        ListNode lt = lh != null ? sp : null;
        ListNode rh = large.next;
        ListNode rt = rh != null ? lp : null;

        return new ListNode[] { lh, lt, pivotNode, rh, rt };
    }

    public static ListNode[] mergeElements(ListNode[] left, ListNode pivotNode, ListNode[] right) {
        
        ListNode head = null;
        ListNode tail = null; //initaially
        if (left[0] != null && right[0] != null) {
            head = left[0];
            tail = right[1];
            left[1].next = pivotNode;
            pivotNode.next = right[0]; 
        }else if(left[0] != null){
            head = left[0];
            tail = pivotNode;
            left[1].next = pivotNode;
        }else if(right[0] != null){
            head = pivotNode;
            tail = right[1];
            pivotNode.next = right[0];
        } else {
            head = tail = pivotNode;
        }
        return new ListNode[] { head, tail };

    }

    public static ListNode[] quickSort(ListNode head, ListNode tail) {

        if (head == null || head == tail) //agar ik hi node h to use segregate krne ki need nhi h
            return new ListNode[] { head, tail };

        int len = length(head);
        int pivotIdx = len / 2;
        ListNode[] segregatedElements = segregate(head, tail, pivotIdx);

        //yeh return krega lh , lt faith
        ListNode[] left = quickSort(segregatedElements[0], segregatedElements[1]);
        ListNode[] right = quickSort(segregatedElements[3], segregatedElements[4]);

        // cases to be handled jab sab aajega
        return mergeElements(left, segregatedElements[2], right);

    }
    
    public static ListNode quickSort(ListNode head) {
        ListNode tail = getTail(head);
        return quickSort(head, tail)[0];  //head tail return or head
    }
}