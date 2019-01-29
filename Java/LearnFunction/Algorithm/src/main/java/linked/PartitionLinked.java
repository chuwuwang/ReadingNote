package linked;

public class PartitionLinked {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(6);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(2);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        ListNode newHead = new PartitionLinked().partition(head, 4);
        while (newHead.next != null) {
            System.out.print(newHead.value + " ");
            newHead = newHead.next;
        }
        System.out.print(newHead.value + " ");
    }

    /**
     * O(N) O(1)
     */
    public ListNode partition(ListNode head, int target) {
        if (head == null) return null;
        ListNode dummy1 = new ListNode(0);
        ListNode dummy2 = new ListNode(0);
        ListNode curr1 = dummy1;
        ListNode curr2 = dummy2;
        ListNode curr = head;
        while (curr != null) {
            if (curr.value < target) {
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
