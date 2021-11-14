import java.util.List;

public class ques {
    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) { // constructor
            this.val = val;
        }
    }

    // LINKED LIST CLASS 1
    public static ListNode midNode(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static ListNode midNode2(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode curr = head;
        ListNode prev = null;

        while (curr != null) {
            ListNode forw = curr.next; // Backup create
            curr.next = prev; // Link attach
            prev = curr; // Move
            curr = forw;
        }
        return prev;
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;

        ListNode mid = midNode(head);
        ListNode nhead = mid.next; // link aatach between nhead and mid
        mid.next = null;

        nhead = reverse(nhead); // reverse break k baad head yehi hoga

        ListNode c1 = head, c2 = nhead;

        boolean res = true;
        while (c2 != null) {
            if (c1.val != c2.val) {
                res = false;
                break;
            }
            c1 = c1.next;
            c2 = c2.next;
        }
        nhead = reverse(nhead);
        mid.next = nhead;

        return res;
    }

    public static void fold(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode mid = midNode(head);
        ListNode nhead = mid.next;
        mid.next = null;

        nhead = reverse(nhead);
        ListNode c1 = head, c2 = nhead;

        while (c2 != null) {
            ListNode f1 = c1.next, f2 = c2.next;

            c1.next = c2; // link attach
            c2.next = f1;

            c1 = f1;
            c2 = f2;
        }
    }

    public static void unfold(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode l1 = new ListNode(-1);
        ListNode l2 = new ListNode(-1);
        ListNode p1 = l1, p2 = l2, c1 = head, c2 = head.next;

        while (c1 != null && c2 != null) {
            p1.next = c1;
            p2.next = c2;

            p1 = p1.next;
            p2 = p2.next; // move p pointer

            if (c2 != null) // for odd length
                c1 = c2.next;
            if (c1 != null) // for even length
                c2 = c1.next;
        }
        p1.next = null;
        l2.next = reverse(l2.next);
        p1.next = l2.next;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null)
            return (l1 != null) ? l1 : l2;

        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy, c1 = l1, c2 = l2;

        while (c1 != null && c2 != null) {
            if (c1.val < c2.val) {
                prev.next = c1;
                c1 = c1.next;
            } else {
                prev.next = c2;
                c2 = c2.next;
            }
            prev = prev.next;
        }
        prev.next = c1 != null ? c1 : c2;
        return dummy.next;
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0)
            return null;

        ListNode head = null;
        for (ListNode node : lists) {
            head = mergeTwoLists(head, node);
        }

        return head;
    }

    // ALTERNATE APPROACH TIME COMPLEXITY IS BETTER THAN PREV

    public ListNode mergeKLists(ListNode[] lists, int si, int ei) {
        if (si == ei)
            return lists[si];

        int mid = (si + ei) / 2;
        ListNode leftMergeList = mergeKLists(lists, si, mid); // faith
        ListNode rightMergeList = mergeKLists(lists, mid + 1, ei); // faith

        return mergeTwoLists(leftMergeList, rightMergeList);
    }

    // LINKED LIST CLASS - 02

    // merge sort linkedlists
    public static ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode mid = midNode(head);
        ListNode nHead = mid.next;
        mid.next = null;

        return mergeTwoLists(mergeSort(head), mergeSort(nHead)); // believe faith that it it will give it 2 list
    }

    public static ListNode removeNthNodeFromEndOfLinkedList(ListNode head, int n) { // n th node milegi
        if (head == null || n >= 0) // n ki value 0 ya 0 se choti h to return head
            return head;

        ListNode slow = head, fast = head;
        while (n-- > 0) { // fast ko move krwaya jitne steps krwane the pehle
            fast = fast.next;
            if (fast == null && n > 0) {
                return head; // agar n ki value given value se bdi hogyi to rokdo
            }
        }
        if (fast == null) {
            ListNode rnode = slow;
            head = rnode.next;
            rnode.next = null;

            return head;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        ListNode rnode = slow.next;
        slow.next = rnode.next;
        rnode.next = null;

        return head;
    }

    public static ListNode segregateEvenAndOddList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode even = new ListNode(-1);
        ListNode odd = new ListNode(-1);
        ListNode ep = even, op = odd, curr = head;

        while (curr != null) {
            if (curr.val % 2 != 0) {
                op.next = curr;
                op = op.next;
            } else {
                ep.next = curr;
                ep = ep.next;
            }
            curr = curr.next;
        }
        ep.next = odd.next;
        op.next = null;
        head = even.next;

        even.next = odd.next = null;
        return head;
    }

    public static ListNode segregate01(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode zero = new ListNode(-1);
        ListNode one = new ListNode(-1);
        ListNode zp = zero, op = one, curr = head;
        while (curr != null) {
            if (curr.val != 0) {
                op.next = curr;
                op = op.next;
            } else {
                zp.next = curr;
                zp = zp.next;
            }
            curr = curr.next;
        }

        zp.next = one.next;
        op.next = null;

        head = zero.next;
        zero.next = one.next = null;
        return head;
    }

    public static ListNode segregate012(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode one = new ListNode(-1);
        ListNode zero = new ListNode(-1);
        ListNode two = new ListNode(-1);
        ListNode op = one, zp = zero, tp = two, curr = head;

        while (curr != null) {
            if (curr.val == 0) {
                zp.next = curr;
                zp = zp.next;
            } else if (curr.val == 1) {
                op.next = curr;
                op = op.next;
            } else {
                tp.next = curr;
                tp = tp.next;
            }
            curr = curr.next;
        }
        op.next = two.next;
        zp.next = one.next;
        tp.next = null;

        head = zero.next;
        zero.next = one.next = two.next = null;

        return head;
    }

    public static ListNode segereOnLastIndex(ListNode head, int pivotIdx) {
        if (head == null || head.next == null)
            return head;
    }

    public static ListNode segregateOverPivotIndex(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode small = new ListNode(-1);
        ListNode large = new ListNode(-1);
        ListNode sp = small, lp = large, curr = head;

        // 1st make pivote elemnt or agar uspr curr aaya to kuch nhi krege simple aage
        // move honge
        ListNode pivotNode = head;
        while (pivotIdx-- > 0) {
            pivotNode = pivotNode.next;
        }
        if (curr == pivotNode) { // isko empty bhi chorh skte h k farak ni pdhega address ko compare krna h
                                 // isliye (.val) nhi likha
            // //nothing do
            // curr = curr.next;
        }
    }

    // ===========================================================
    // linked list class 3 ADD TWO LINKED LIST

    public static ListNode reverse11(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            ListNode forw = curr.next;

            curr.next = prev; // link attach

            prev = curr; // move
            curr = forw;
        }
        return prev; // head hoga yeh
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverse11(l1);
        l2 = reverse11(l2);

        ListNode dummy = new ListNode(-1);
        ListNode c1 = l1, c2 = l2, prev = dummy;

        int carry = 0;
        while (c1 != null || c2 != null || carry != 0) {
            int sum = carry + (c1 != null ? c1.val : 0) + (c2 != null ? c2.val : 0);

            carry = sum / 10;
            sum %= 10;

            prev.next = new ListNode(sum);
            prev = prev.next;

            if (c1 != null)
                c1 = c1.next;
            if (c2 != null)
                c2 = c2.next;
        }

        ListNode head = dummy.next;
        head = reverse11(head);
        l1 = reverse11(l1);
        l2 = reverse11(l2);

        return head;
    }

    public static ListNode subtractTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);

        ListNode dummy = new ListNode(-1);
        ListNode c1 = l1, c2 = l2, prev = dummy;

        int borrow = 0;
        while (c1 != null || c2 != null) {
            int diff = borrow + (c1 != null ? c1.val : 0) + (c2 != null ? c2.val : 0);
            if (diff < 0) {
                diff += 10;
                borrow = -1;
            } else {
                borrow = 0;
            }
            prev.next = new ListNode(diff);
            prev = prev.next;

            if (c1 != null)
                c1 = c1.next;
            if (c2 != null)
                c2 = c2.next;
        }

        ListNode head = dummy.next;
        head = reverse(head);

        while (head != null && head.val == 0) {
            head = head.next;
        }
        l1 = reverse(l1);
        l2 = reverse(l2);
        return head;

    }

    // is cycle present
    public static boolean isCyclePresentInLL(ListNode head) {
        if (head == null || head.next == null)
            return false;

        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (fast == slow) // jb dono equal ho means cycle is present
                return true;
        }
        return false;
    }

    public static ListNode cycleNode(ListNode head) {
        if (head == null || head.next == null)
            return null;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (fast == slow) // means meeting point
                break;
        }
        // agar loop se bhar 2 scenario me aayege ya cycle exist hi nahi krti
        if (fast != slow)
            return null;

        // ListNode meetingNode = fast;
        slow = head;
        while (slow != fast) { // to check JUmps equal no ke sath jump kri
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public static ListNode cycleNode2(ListNode head) {
        if (head == null || head.next == null)
            return null;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) { // if size of ll is 2
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) // means meeting point
                break;
        }
        // agar loop se bhar 2 scenario me aayege ya cycle exist hi nahi krti
        if (fast != slow)
            return null;

        ListNode meetingNode = fast;
        int a = 0, b = 0, c = 0, bc = 0, nDash = 0, n = 0; // bc is b + c

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;

            if (fast == meetingNode)
                nDash++; // yeh n dash and 'a'find krke dega correct
            a++;
        }

        fast = meetingNode; // yeh bc correct find krke dega whether it give us ans or not
        fast = fast.next;

        bc = 1;
        while (fast != meetingNode) {
            fast = fast.next;// yeh bc correct find krke dega whether it give us tail or not
            bc++;
        }

        n = nDash + 1;
        c = a - bc * nDash;
        b = bc - c;

        System.out.println("length of tail is:" + a);
        System.out.println("length of b is:" + b);
        System.out.println("length of c is:" + c);
        System.out.println("no of rotation by fast pointer before meeting point:" + n);
        System.out.println("no of rotation by fast pointer after meeting point:" + nDash);

        return slow;
    }

    public static ListNode IntersectionNodeInTwoLL(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;

        ListNode tail = headA;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = headB;
        ListNode ans = cycleNode(headA);

        // we will make it like before
        tail.next = null;

        return ans;
    }

    public static int length(ListNode head) {
        ListNode curr = head;
        int len = 0;
        while (curr != null) {
            curr = curr.next;
            len++;
        }
        return len;
    }

    // leetcode -> 160
    public static ListNode IntersectionNodeInTwoLL_(ListNode headA, ListNode headB) {
        int lenA = length(headA);
        int lenB = length(headB);

        ListNode biggerList = lenA > lenB ? headA : headB;
        ListNode smallerList = lenB < lenA ? headB : headA;

        int diff = Math.abs(lenA - lenB);

        while (diff-- > 0) {
            biggerList = biggerList.next;
        }
        while (biggerList != smallerList) {
            biggerList = biggerList.next;
            smallerList = smallerList.next;
        }
        return biggerList;
    }

    // leetcode 25

    public static ListNode th = null, tt = null;

    public static void addFirstNode(ListNode node) {
        if (th == null)
            th = tt = node;
        else {
            node.next = th;
            th = node;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        int len = length(head);

        ListNode curr = head, ph = null, pt = null;
        while (curr != null && len >= k) {
            int itr = k;

            while (curr != null && itr-- > 0) { // sbse phle 3 baar loop chlana h k times
                ListNode forw = curr.next;
                curr.next = null;
                addFirstNode(curr);
                curr = forw; // 1 list reverse hogyi h
            }

            if (ph == null) { // means hm 1st time visit hoye
                ph = th;
                pt = tt;
            } else {
                pt.next = th;
                pt = tt;
            }
            th = tt = null;
            len -= k;
        }
        pt.next = curr;
        return ph;
    }

    // leetcode 2==============================================

    // static ListNode th = null , tt = null;

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head.next == null || m == n)
            return head;

        int i = 1;
        ListNode curr = head, prev = null;
        while (curr != null) {
            while (i >= m && i <= n) {
                ListNode forw = curr.next;
                curr.next = null;
                addFirstNode(curr);
                curr = forw;
                i++;
            }
            if (i > n) { // means jisko reverse krna h vo kr chuke h reverse
                if (prev == null) { // means 1st part tha vo reverse ka
                    tt.next = curr;
                    return th;
                } else {
                    prev.next = th;
                    tt.next = curr;
                    return head;
                }
            }
            prev = curr;
            curr = curr.next;
            i++;
        }
        return null;
    }

    //remove duplicates from sorted ll============================
    public static ListNode removeDuplicates(ListNode head) {
        if(head == null || head.next == null)
            return head;
         
        ListNode curr = head.next;
        ListNode prev = head;

        while (curr != null) {
            while (curr.val == prev.val) {
                ListNode forw = curr.next;  // to link break
                curr.next = null;
                curr = forw;
                
            }
        }
    }

}
