package queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(5);
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

//使用数组来模拟队列
class ArrayQueue {
    private int maxSize; //数组的最大容量
    private int front; //队列头
    private int rear; //队列尾
    private int[] arr; //存放数据，模拟队列

    public ArrayQueue(int arrMaxSize) {
        this.maxSize = arrMaxSize;
        this.arr = new int[maxSize];
        this.front = -1;
        this.rear = -1;
    }

    //判断队列是否满
    public boolean isFull() {
        return rear == maxSize - 1;
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
            rear++;
            arr[rear] = n;
        }
    }

    //获取队列的数据
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能取数据");
        } else {
            front++;
            return arr[front];
        }
    }

    //显示队列的所有数据
    public void showQueue() {
        //遍历
        if (isEmpty()) {
            System.out.println("队列为空，没有数据");
        } else {
            for (int i = 0; i < arr.length; i++) {
                System.out.printf("arr[%d]=%d\t", i, arr[i]);
            }
            System.out.println();
        }
    }

    //显示队列的头数据
    public int peekQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据");
        } else {
            return arr[front+1];
        }
    }

}
