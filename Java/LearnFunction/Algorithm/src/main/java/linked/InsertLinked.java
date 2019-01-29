package linked;


// Q4: Insert a node in a sorted linked list(simple)
//         1 --> 3 --> 6 --> 9 --> null target == 7

public class InsertLinked {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(5);
        ListNode node4 = new ListNode(9);
        ListNode node5 = new ListNode(11);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode merge = new InsertLinked().insert(head, 6);
        while (merge.next != null) {
            System.out.print(merge.value + " ");
            merge = merge.next;
        }
        System.out.print(merge.value + " ");
    }

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
