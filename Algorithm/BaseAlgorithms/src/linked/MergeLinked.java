package linked;

// merge two sorted lists into one large sorted list
// L1 = 1 --> 4 --> 6 --> null
// L2 = 1 --> 2 --> 5 --> null
// merge L1 to L2  1 --> 1 --> 2 --> 4 --> 5 --> 6 --> null

// 两个链表的合并
public class MergeLinked {

    public static void main(String[] args) {

    }

    public ListNode merge(ListNode first, ListNode second) { // O(m+n) O(1)
        ListNode curr1 = first;
        ListNode curr2 = second;
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (curr1 != null && curr2 != null) {
            if (curr1.value >= curr2.value) {
                curr.next = curr2;
                curr2 = curr2.next;
                curr = curr.next;
            } else {
                curr.next = curr1;
                curr1 = curr1.next;
                curr = curr.next;
            }
        }
        if (curr1 != null) {
            curr.next = curr1;
        } else {
            curr.next = curr2;
        }
        return dummy.next;
    }

}
