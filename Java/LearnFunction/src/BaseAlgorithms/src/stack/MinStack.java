package stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 最小栈实现
 */
public class MinStack {

    private Deque<Integer> stack = new LinkedList<>();
    private Deque<Integer> min = new LinkedList<>();

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }

    public void push(int value) {
        stack.push(value);
        if (min.isEmpty() || value <= min.peek()) {
            min.push(value);
        }
    }

    public Integer pop() {
        if (stack.isEmpty()) {
            return null;
        }
        int t = stack.pop();
        if (t == min.peek()) {
            min.pop();
        }
        return t;
    }

    /**
     * return top
     */
    public Integer peek() {
        if (stack.isEmpty()) {
            return null;
        }
        return stack.peek();
    }

    /**
     * return min
     */
    public Integer min() {
        if (min.isEmpty()) {
            return null;
        }
        return min.peek();
    }

}
