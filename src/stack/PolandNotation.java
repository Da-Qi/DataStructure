package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 将一个中缀表达式转成后缀表达式
 * 1. 中缀表达式转由数组集合存储
 * 2. 然后将该list转为后缀表达式
 */
public class PolandNotation {
    public static void main(String[] args) {
        //先定义逆波兰表达式
        String suffixExpression = "30 4 + 5 * 6 - ";
        //思路
        //1. 先将"3 4 + 5 * 6 - "放到ArrayList中
        //2. 将ArrayList传递给一个方法，遍历list配合栈完成计算
        /*List<String> listString = getListString(suffixExpression);
        System.out.println(listString);
        int calculate = calculate(listString);
        System.out.println(calculate);*/

        String expression = "1+((2+3)*4)-5";
        List<String> strings = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的list: "+strings);

        List<String> list = parseSuffixExpressionList(strings);
        System.out.println("后缀表达式对应的list："+list);

        System.out.println("expression = "+calculate(list));
    }

    //ArrayList [1, +, (, (, 2, +, 3, ), *, 4, ), -, 5] --> ArrayList[1,2,3,+,4,*,,+,5,-]
    //list转为后缀表达式
    public static List<String> parseSuffixExpressionList(List<String> list) {
        //定义两个栈
        Stack<String> s1 = new Stack<>();   //符号栈
        //s2栈没有pop操作，需要逆序输出，如果用栈结构比较麻烦，所以可以用数组实现
        //Stack<String> s2 = new Stack<>();   //  存储结果
        ArrayList<String> s2 = new ArrayList<>();   //存储结果
        for (String ele : list) {
            //如果是一个数，则加入s2
            if (ele.matches("\\d+")) {
                s2.add(ele);
            } else if (ele.equals("(")) {
                s1.push(ele);
            } else if (ele.equals(")")) {
                //如果是右括号，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，且这对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                //左括号被弹出（消除括号）
                s1.pop();
            } else {
                //操作符的优先级
                //当ele的优先级小于等于s1栈顶运算符，将s1栈顶的运算符弹出并加入到s2，再次将ele与新的栈顶运算符比较
                while (s1.size() != 0 && Operation.getValue(ele) <= Operation.getValue(s1.peek())){
                    s2.add(s1.pop());
                }
                s1.push(ele);
            }
        }
        //将s1中的剩余的符号依次弹出并加入到s2;
        while (!s1.empty()){
            s2.add(s1.pop());
        }

        return s2;

    }

    //将中缀表达式转成对应的list
    public static List<String> toInfixExpressionList(String s) {
        //定义一个List，存放中缀表达式对应的内容
        List<String> list = new ArrayList<String>();
        int i = 0; //用于遍历
        String str;  //多位数的拼接
        char c;     //存放遍历到的字符
        do {
            //如果c是一个非数字，则加入到list
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                list.add("" + c);
                i++;
            } else {
                //c是一个数字
                str = "";
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                list.add(str);
            }
        } while (i < s.length());
        return list;
    }

    //将一个逆波兰表达式的数据和运算符依次放入ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //将suffixexpression分割
        String[] s = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele : s) {
            list.add(ele);
        }
        return list;
    }

    //完成对逆波兰表达式的运算
    public static int calculate(List<String> list) {
        //创建栈
        Stack<String> stack = new Stack<>();
        //遍历list
        for (String s : list) {
            if (s.matches("\\d+")) {
                //匹配是多位数
                stack.push(s);
            } else {
                //pop出两个数进行运算
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0; //存放结果
                if (s.equals("+")) {
                    res = num1 + num2;
                } else if (s.equals("-")) {
                    res = num1 - num2;
                } else if (s.equals("*")) {
                    res = num1 * num2;
                } else if (s.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push(res + "");
            }
        }
        //最后留在stack中的数据是结果
        return Integer.parseInt(stack.pop());
    }
}

//编写一个类 Operation，返回运算符对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 3;

    //返回对应的优先级
    public static int getValue(String operation){
        int result = 0;
        switch (operation){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}