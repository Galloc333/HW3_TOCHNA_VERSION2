import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

class MyCloneable implements Cloneable {
    private int num;

    public MyCloneable(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "MyCloneable: " + num;
    }

    @Override
    public MyCloneable clone() {
        try {
            return (MyCloneable) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}


public class Main {
    public static void main(String[] args) {
        testPartA();
    }

    /**
     * Tests for part A.
     */
    private static void testPartA() {
        try {
            new ArrayStack<>(-25);
        } catch (NegativeCapacityException e) {
            System.out.println("Negative capacity!");
        }

        Stack<MyCloneable> stack1 = new ArrayStack<>(5);
        ArrayStack<MyCloneable> stack2 = (ArrayStack<MyCloneable>) stack1;

        try {
            stack1.peek();
        } catch (EmptyStackException e) {
            System.out.println("The stack is empty");
        }

        try {
            stack2.peek();
        } catch (StackException e) {
            System.out.println("The stack is empty");
        }
        System.out.println();

        iterateStack(stack1, "stack1");

        MyCloneable c1 = new MyCloneable(1);
        stack1.push(c1);
        iterateStack(stack1, "stack1");
        System.out.println("Peek: " + stack1.peek());
        System.out.println("Pop: " + stack1.pop());
        System.out.println();
        iterateStack(stack1, "stack1");

        MyCloneable c2 = new MyCloneable(2);
        stack1.push(c1);
        stack1.push(c2);

        iterateStack(stack2, "stack2");

        ArrayStack<MyCloneable> stack3 = stack2.clone();
        Stack<MyCloneable> stack4 = stack1.clone();

        System.out.println("Is stack1 == stack3? " + (stack1 == stack3));
        System.out.println("Is stack2 == stack3? " + (stack2 == stack3));
        System.out.println("Is stack1 == stack4? " + (stack1 == stack4));
        System.out.println("Is stack2 == stack4? " + (stack2 == stack4));
        System.out.println();
        iterateStack(stack3, "stack3");

        c1.setNum(15);
        iterateStack(stack1, "stack1");
        iterateStack(stack2, "stack2");
        iterateStack(stack3, "stack3");
        iterateStack(stack4, "stack4");

        stack1.push(new MyCloneable(3));
        iterateStack(stack1, "stack1");
        iterateStack(stack2, "stack2");
        iterateStack(stack3, "stack3");
        iterateStack(stack4, "stack4");

        stack1.push(new MyCloneable(4));
        iterateStack(stack1, "stack1");

        stack1.push(new MyCloneable(5));
        iterateStack(stack1, "stack1");
        iterateStack(stack2, "stack2");
        iterateStack(stack3, "stack3");
        iterateStack(stack4, "stack4");

        try {
            stack1.push(c1);
        } catch (StackOverflowException e) {
            System.out.println("The stack reached its full capacity.");
        }

        try {
            stack2.push(new MyCloneable(21));
        } catch (StackException e) {
            System.out.println("The stack reached its full capacity.");
        }

        System.out.println("\nTesting of part A is over!\n\n");

    }


    /**
     * Iterates over a given stack.
     */
    private static void iterateStack(Stack<?> stack, String name) {
        System.out.println(name + " size: " + stack.size());
        System.out.println("Is empty? " + stack.isEmpty());

        System.out.println("Starts iterating " + name + "...");
        for (Cloneable element : stack) {
            System.out.println(element);
        }
        System.out.println("Done iterating " + name + ".");
        System.out.println();
    }


}
