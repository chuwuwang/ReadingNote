package linked;


// 快慢指针的问题

// Q1: How to find  the middle node of a linked list ?
// N1 --> N2 --> N3 --> N4 --> N5 --> N6 --> NULL

public class MiddleLinked {

    public ListNode getMiddle(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

}
