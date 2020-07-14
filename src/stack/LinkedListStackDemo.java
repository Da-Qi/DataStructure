package stack;


import java.util.Scanner;

public class LinkedListStackDemo {
    public static void main(String[] args) {
        LinkedListStack stack = new LinkedListStack(4);
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
                    stack.show();
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

class LinkedListStack {
    private int maxSize; //栈的大小
    private LinkedList list = new LinkedList(); //链表模拟栈
    private StackNode first; //栈顶元素

    //构造器
    public LinkedListStack(int maxSize) {
        this.maxSize = maxSize;
        this.first = list.getFirst();
    }

    //判断空
    public boolean isEmpty() {
        return first.no == -1;
    }

    //判断满
    public boolean isFull() {
        return first.no == maxSize - 1;
    }

    //添加元素
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈已满，无法加入元素");
        } else {
            StackNode temp = new StackNode(first.no + 1);
            temp.next = first;
            temp.value = value;
            first = temp;
        }
    }

    //弹出元素
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空，无法弹出元素");
        } else {
            StackNode temp;
            temp = first;
            first = first.next;
            return temp.value;
        }
    }

    //遍历栈
    public void show() {
        StackNode temp = first;
        while (temp.no != -1) {
            System.out.printf("stack[%d]= %d", temp.no, temp.value);
            temp = temp.next;
        }
    }
}

class LinkedList {
    private StackNode first = new StackNode(-1);

    public StackNode getFirst() {
        return first;
    }

}

class StackNode {
    public int no;
    public int value;
    public StackNode next;

    public StackNode(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "StackNode{" +
                "no=" + no +
                ", value=" + value +
                ", next=" + next +
                '}';
    }
}