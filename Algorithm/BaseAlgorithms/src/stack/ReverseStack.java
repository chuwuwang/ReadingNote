package stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 反转一个栈
 */
public class ReverseStack {

    public static void main(String[] args) {
        Stack items = new Stack();
        items.push("he");   // he is at the bottom of the stack
        items.push("saw");
        items.push("a");
        items.push("hello");
        reverseStack(items);    // now he is at the top

        // print in order pushed:
        while (items.size() > 0) System.out.println(items.pop());
    }

    public static void reverseStack(Stack stack) {
        Queue rev = new LinkedList();
        while (stack.size() > 0) rev.offer(stack.pop());
        while (rev.size() > 0) stack.push(rev.poll());
    }


}
