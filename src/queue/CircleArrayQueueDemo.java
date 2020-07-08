package queue;

import java.util.Scanner;

/**
 * 环形数组模拟队列
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        System.out.println("测试数组模拟环形队列：");
        CircleArray queue = new CircleArray(4);
        char key = ' '; //用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("p(peek):查看队列头的数据");
            //接收一个字符
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'p':
                    try {
                        System.out.printf("队列头数据为:%d\n", queue.peekQueue());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'a':
                    System.out.println("请输入您想加入的数据：");
                    queue.addQueue(scanner.nextInt());
                    break;
                case 'g':
                    try {
                        System.out.printf("取出数据为:%d\n", queue.getQueue());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    loop = false;
                    scanner.close();
                    break;
            }
        }
        System.out.println("程序退出！");
    }
}

class CircleArray {
    private int maxSize; //数组的最大容量
    private int front; //队列头，初始值为0
    private int rear; //指向队列最后一个元素的后一个位置，会空出一个空间，初始值也为0
    private int[] arr; //存放数据，模拟队列

    //构造器
    public CircleArray(int arrMaxSize) {
        this.maxSize = arrMaxSize;
        this.arr = new int[maxSize];
    }

    //队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据
    public void addQueue(int n) {
        //1.判断队列是否满
        if (isFull()) {
            System.out.println("队列已满，无法加入");
        } else {
            //因为rear指向元素的后一个位置，所有可以直接加入数据
            arr[rear] = n;
            rear = (rear + 1) % maxSize;
        }
    }

    //获取队列的数据
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能取数据");
        } else {
            //1.先把front保存到一个临时变量
            //2.front后裔
            //3.将临时变量保存；
            int value = arr[front];
            front = (front + 1) % maxSize;
            return value;
        }
    }

    //显示队列的所有数据
    public void showQueue() {
        //遍历
        if (isEmpty()) {
            System.out.println("队列为空，没有数据");
        } else {
            for (int i = front; i < front + size(); i++) {
                System.out.printf("arr[%d]=%d\t", i % maxSize, arr[i % maxSize]);
            }
            System.out.println();
        }
    }

    //求出当前队列的有效数据的个数
    public int size() {
        return (rear - front + maxSize) % maxSize;
    }

    //显示队列的头数据
    public int peekQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据");
        } else {
            return arr[front];
        }
    }
}
