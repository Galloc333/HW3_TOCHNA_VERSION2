import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

public class ArrayStack<E extends Cloneable> implements Stack<E> { // E extends Cloneable meaning
    // that it restricted to be cloneable type and the class ArrayStack implements the interface Stack.
    private int maxStackNum;
    private int head;

    private E[] stack;

    public ArrayStack(int maxStackNum) {
        //there will be a try here for negative nums and shit
        if (maxStackNum < 0) {
            throw new NegativeCapacityException();
        }
        this.maxStackNum = maxStackNum;
        head = maxStackNum - 1;
        stack = (E[]) new Cloneable[maxStackNum]; // casting is needed because we want to ensure that
    }

    @Override
    public void push(E element) {
        if (this.head < 0) {
            throw new StackOverflowException();
        }
        this.stack[this.head] = element;
        this.head--;
    }

    @Override
    public E pop() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        E popedObject = stack[head + 1];
        stack[head + 1] = null;
        head++;
        return popedObject;
    }

    @Override
    public E peek() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        return this.stack[head + 1];
    }


    @Override
    public int size() {
        return this.maxStackNum - 1 - head;
    }

    @Override
    public boolean isEmpty() {
        return this.head == maxStackNum - 1;
    }
    @Override
    public ArrayStack<E> clone() {
        ArrayStack<E> cloned;
        try {
            cloned = (ArrayStack<E>) super.clone();
            cloned.stack = this.stack.clone();
            for (int i = head; i < maxStackNum; i++) {
                if (stack[i] instanceof Cloneable) {
                    Method cloneMethod = stack[i].getClass().getMethod("clone");
                    cloned.stack[i] = (E) cloneMethod.invoke(stack[i]);
                } else {
                    cloned.stack[i] = stack[i];
                }
            }
        } catch (CloneNotSupportedException | NoSuchMethodException | IllegalAccessException |
                 InvocationTargetException e) {
            return null;
        }

        return cloned;
    }


    @Override
    public Iterator<E> iterator() {
        return new ArrayStackIterator(stack, head);
    }

    private class ArrayStackIterator implements Iterator {
        private E[] stack;
        private int index;

        public ArrayStackIterator(E[] arr, int index) {
            this.stack = arr;
            this.index = index + 1;
        }

        @Override
        public boolean hasNext() {
            return index < maxStackNum;
        }

        @Override
        public Object next() {
            return stack[index++];
        }
    }
}
