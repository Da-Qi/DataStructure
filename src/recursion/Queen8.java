package recursion;

public class Queen8 {
    //定义一共有多少皇后
    int max = 8;
    //定义数组，保存皇后的放置结果
    int[] array = new int[max];
    //定义一共多少种解法
    int count = 0;

    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.println("一共" + queen8.count + "种解法");
    }

    //打印皇后信息
    private void print() {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    //放置皇后
    private void check(int n) {
        //前8个皇后都放好了
        if (n == max) {
            count++;
            print();
        } else {
            for (int i = 0; i < max; i++) {
                array[n] = i;
                //判断当前行的放置位置是否和前面的n-1行有冲突
                if (join(n)) {
                    //无冲突，放下一个
                    check(n + 1);
                }
            }
        }
    }

    private boolean join(int n) {
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                //同一列或者同一斜线
                return false;
            }
        }
        return true;
    }
}
