package stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 最小栈实现
 */
public class MinStack {

    private Deque<Integer> min = new LinkedList<>();
    private Deque<Integer> stack = new LinkedList<>();

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }

    public void push(int value) {
        stack.push(value);
        boolean bool = min.isEmpty() || value <= min.peek();
        if (bool) {
            min.push(value);
        }
    }

    public Integer pop() {
        boolean bool = stack.isEmpty();
        if (bool) return null;
        int t = stack.pop();
        if (min.peek() == t) {
            min.pop();
        }
        return t;
    }

    /**
     * return top
     */
    public Integer peek() {
        boolean bool = stack.isEmpty();
        if (bool) return null;
        return stack.peek();
    }

    /**
     * return min
     */
    public Integer min() {
        boolean bool = min.isEmpty();
        if (bool) return null;
        return min.peek();
    }


}
