package linked;

//  How to reverse a listed list ?

public class ReverseLinked {

    /**
     * 循环反转链表
     * O(n) O(1)
     */
    public ListNode loopReverse(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            curr.next = prev;
            prev = curr;
            curr = curr.next;
        }
        return prev;
    }

    /**
     * 递归反转链表
     * O(n) O(n)
     */
    public ListNode recursiveReverse(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode newHead = recursiveReverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

}

