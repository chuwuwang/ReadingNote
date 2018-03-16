package linked;


// Q4: Insert a node in a sorted linked list(simple)
//         1 --> 3 --> 6 --> 9 --> null target == 7

public class InsertLinked {

    public ListNode insert(ListNode head, int target) {
        if (head == null || head.value >= target) {
            ListNode newHead = new ListNode(target);
            newHead.next = head;
            return newHead;
        }

        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            if (curr.value < target) {
                prev = curr;
                curr = curr.next;
            } else {
                break;
            }
        }

        ListNode newHead = new ListNode(target);
        prev.next = newHead;
        newHead.next = curr;
        return head;
    }

}
