package linked;

// merge two sorted lists into one large sorted list
// L1 = 1 --> 4 --> 6 --> null
// L2 = 1 --> 2 --> 5 --> null
// merge L1 to L2  1 --> 1 --> 2 --> 4 --> 5 --> 6 --> null

// 两个链表的合并
public class MergeLinked {

    public static void main(String[] args) {
        ListNode first = new ListNode(1);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(6);
        first.next = node2;
        node2.next = node3;

        ListNode second = new ListNode(1);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(5);
        second.next = node4;
        node4.next = node5;

        ListNode merge = new MergeLinked().merge(first, second);
        while (merge.next != null) {
            System.out.print(merge.value + " ");
            merge = merge.next;
        }
        System.out.print(merge.value + " ");
    }

    /**
     * 合并链表
     * O(m+n) O(1)
     */
    public ListNode merge(ListNode first, ListNode second) {
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
