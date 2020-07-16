package stack;

public class Calculator {
    public static void main(String[] args) {
        String expression = "70+2*3+1";
        //创建两个栈，数栈和符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0; //用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';  //将每次扫描得到的char保存到ch
        String keepNum = "";
        //开始while循环的扫描
        while (true) {
            //依次得到expression的每一个字符
            ch = expression.charAt(index);
            //判断ch
            if (operStack.isOper(ch)) {
                //如果是运算符
                if (operStack.isEmpty()) {
                    //如果为空则直接入栈
                    operStack.push(ch);
                } else {
                    //不为空，则分两种情况讨论
                    //1. 优先级小于或等于栈中的操作符，就需要从数栈和符号栈中pop值
                    //2. 如果优先级大于栈中的操作符，就直接入栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //结果入栈
                        numStack.push(res);
                        operStack.push(ch);
                    } else {
                        operStack.push(ch);
                    }
                }
            } else {
                //如果是数，则直接入数栈
                //numStack.push(ch-48);

                //处理多位数
                keepNum += ch;
                //如果ch已经是expression的最后一位，就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //判断下一个字符是不是数字，如果是数字，继续扫描，如果是运算符就入栈
                    if (operStack.isOper(expression.charAt(index + 1))) {
                        numStack.push(Integer.parseInt(keepNum));
                        //keepNum情况
                        keepNum = "";
                    }
                }
            }
            //index加1，并判断是否扫描到了最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号并运行
        while (true) {
            //符号栈为空，则计算到最后的结果，数栈中只有一个值
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res); //入栈
        }

        System.out.println("表达式 " + expression + " 的结果为" + numStack.pop());
    }

}

//定义一个栈
class ArrayStack2 {
    private int maxSize; //栈的大小
    private int[] stack; //数组，模拟栈
    private int top = -1; //表示栈顶


    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //返回栈顶的元素，但不弹出
    public int peek() {
        return stack[top];
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
                System.out.printf("stack[%d]=%d", i, stack[i]);
            }
        }
    }

    //返回运算符的优先级；优先级使用数字表示，数字越大优先级越高
    public int priority(int oper) {
        //假定只有加减乘除
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;  //注意顺序
                break;
            case '*':
                res = num2 * num1;
                break;
            case '/':
                res = num2 / num1;  //注意顺序
                break;
            default:
                break;
        }
        return res;
    }
}
