package linked;

public class PartitionLinked {

    public ListNode partition(ListNode head, int target) { // O(N) O(1)
        if (head == null) return null;

        ListNode dummy1 = new ListNode(0);
        ListNode dummy2 = new ListNode(0);
        ListNode curr1 = dummy1;
        ListNode curr2 = dummy2;
        ListNode curr = head;
        while (curr != null) {
            if (curr1.value < target) {
                curr1.next = curr;
                curr = curr.next;
                curr1 = curr1.next;
            } else {
                curr2.next = curr;
                curr = curr.next;
                curr2 = curr2.next;
            }
        }
        // dummy1: 0-->1-->3-->2-->2-->null
        //                         curr1

        // dummy2: 0-->6-->5-->2-->null
        //                 curr2

        // result: >1-->3-->2-->2-->6-->5-->null

        curr1.next = dummy2.next;
        // dummy1: 0-->1-->3-->2-->2-->6-->5-->2-->null
        //                                 curr2

        curr2.next = null;
        // dummy1: 0-->1-->3-->2-->2-->6-->5-->null

        return dummy1.next;
    }


}
