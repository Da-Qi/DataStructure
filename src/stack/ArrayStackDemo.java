package stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true; //控制是否退出菜单

        Scanner scan = new Scanner(System.in);
        while (loop){
            System.out.println("show: 显示栈");
            System.out.println("exit: 退出栈");
            System.out.println("push: 添加数据到栈");
            System.out.println("pop: 将栈顶数据弹出");
            System.out.println("请输入你的选择");
            key = scan.next();
            switch (key){
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入您想加入的数据：");
                    int i = scan.nextInt();
                    stack.push(i);
                    break;
                case "pop":
                    try {
                        int pop = stack.pop();
                        System.out.println("出栈的数据为"+pop);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    scan.close();
                    loop = false;
                    break;
            }

        }
        System.out.println("程序退出了");

    }
}

//定义一个栈
class ArrayStack {
    private int maxSize; //栈的大小
    private int[] stack; //数组，模拟栈
    private int top = -1; //表示栈顶

    //构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //判断栈是否满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //判断栈是否空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        //先判断栈是否满
        if (isFull()) {
            System.out.println("栈已满");
        } else {
            top++;
            stack[top] = value;
        }
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据");
        } else {
            for (int i = top; i >= 0; i--) {
                System.out.printf("stack[%d]=%d",i,stack[i]);
            }
        }
    }
}