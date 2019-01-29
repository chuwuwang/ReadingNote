package stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 反转一个栈
 */
public class ReverseStack {

    public static void main(String[] args) {
        Stack<String> items = new Stack<>();
        items.push("he");   // he is at the bottom of the stack
        items.push("saw");
        items.push("a");
        items.push("hello");
        reverseStack(items);    // now he is at the top

        // print in order pushed:
        while (items.size() > 0) {
            String obj = items.pop();
            System.out.println(obj);
        }
    }

    public static void reverseStack(Stack<String> stack) {
        Queue<String> rev = new LinkedList<>();
        while (stack.size() > 0) {
            String obj = stack.pop();
            rev.offer(obj);
        }
        while (rev.size() > 0) {
            String obj = rev.poll();
            stack.push(obj);
        }
    }


}
