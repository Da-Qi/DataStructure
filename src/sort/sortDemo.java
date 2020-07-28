package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class sortDemo {
    public static void main(String[] args) {
        int[] arr = new int[80];
        for (int i = 0; i < 80; i++) {
            arr[i] = (int) (Math.random() * 800);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        System.out.println("执行前的时间：" + format);

        //Bubble.BubbleSort(arr);
        //Select.SelectSort(arr);
        //Insert.InsertSort(arr);
        //arr = Shell.ShellSort(arr);
        //Quick.QuickSort(arr, 0, 7);
        //int[] temp = new int[arr.length];
        //Merge.MergeSort(arr, 0, arr.length - 1, temp);
        Radix.radixSort(arr);
        //int[] arr2 = {4,6,8,5,9};
        //Heap.heapSort(arr);


        Date date2 = new Date();
        String format1 = simpleDateFormat.format(date2);
        System.out.println("执行后的时间：" + format1);
        for (int i : arr) {
            System.out.print(i + " ");
        }

    }

}

class Bubble {
    //80000个数据测试结果 9秒
    public static int[] BubbleSort(int[] arr) {
        int temp = 0;
        //外层for循环确定从头开始比较的趟数
        for (int i = 0; i < arr.length - 1; i++) {
            //j是从头开始遍历，然后每一次遍历完后，都会确定一个最大数，因此需要在length-1的基础上在 -i
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //如果前一个比后一个就交换位置
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }
}

class Select {
    public static int[] SelectSort(int[] arr) {
        //每次确定最小数的位置，和未排序的数组首交换位置
        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[index] > arr[j]) {
                    index = j;
                }
            }
            //如果index不是第一个遍历的数，就互换；
            if (index != i) {
                int temp = arr[index];
                arr[index] = arr[i];
                arr[i] = temp;
            }

        }
        return arr;
    }
}

class Insert {
    public static int[] InsertSort(int[] arr) {
        //插入排序,按顺序每一趟都会将对应增长的index处的数据插入有序数组；从小到大
        //长度为n的数组，从index 1 -- n-1 插入n-1次
        for (int i = 1; i < arr.length; i++) {
            //将arr[i]与前i-1个数据 逆序 比较，一旦遇到比它小的就插到它的后面，如若没有就插到第0个位置
            //待插入的值
            int insertVal = arr[i];
            //前面排好序的数组的最后一位下标
            int insertIndex = i - 1;

            //insertIndex>= 0 保证不越界
            //insertVal < arr[insertIndex] 还没有找到插入位置
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }

            //优化
            if (insertIndex + 1 != i) {
                //跳出循环后即可得到插入位置i
                arr[insertIndex + 1] = insertVal;
            }

        }
        return arr;
    }
}

//希尔排序
class Shell {
    public static int[] ShellSort(int[] arr) {
        //第二轮排序，将10个数据分成2组
        /*for(int i=2;i<arr.length;i++){
            for (int j=i-2;j>=0;j-=2){
                if (arr[j+2] < arr[j]){
                    int temp = arr[j+2];
                    arr[j+2] = arr[j];
                    arr[j] = temp;
                }
            }
        }*/

        //交换法
        //整合
        //步长
        /*int len = arr.length/2;
        //中间值
        int temp = 0;
        while (len > 0){
            for (int i=len;i<arr.length;i++){
                for (int j = i - len;j>=0;j-= len){
                    if (arr[j+len] < arr[j]){
                        temp = arr[j+len];
                        arr[j+len] = arr[j];
                        arr[j] = arr[j+len];
                    }
                }
            }
            len/=2;
        }*/

        //移位法
        int gap = arr.length / 2;
        while (gap > 0) {
            //执行同一个步长下的数的排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                //如果当前数，比同一组的前一个数小，就有必要进行移位操作；
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //后移，把j的位置让给前一个gap，空出来的就是j-gap赋值给j
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //跳出循环就是j为空值；
                    arr[j] = temp;
                }

            }
            gap /= 2;
        }

        return arr;
    }


}

//快速排序
class Quick {
    public static void QuickSort(int[] arr, int left, int right) {
        if (left < right) {
            //枢轴
            int pivot = arr[left];
            int l = left;
            int r = right;
            //这里有个很重要的地方，为什么先排右侧的数且可以直接赋值给arr[l]，因为我是选择的left作为枢，它的值已经被存起来的，所以在位置可以直接被覆盖
            while (l < r) {
                //右侧中，找到比枢小的数就放在其左侧
                while (l < r && arr[r] >= pivot) {
                    r--;
                }
                //跳出这个循环，r指向一个小于pivot的数
                if (l != r) {
                    arr[l] = arr[r];
                }

                //左侧中，找到比枢大的数放在右侧
                while (l < r && arr[l] <= pivot) {
                    l++;
                }
                //跳出这个循环，l指向一个大于pivot的数
                if (l != r) {
                    arr[r] = arr[l];
                }


            }

            //枢到指定位置
            arr[l] = pivot;


            //跳出循环后，对枢轴前面的数 和 后面的数 分别进行判断
            QuickSort(arr, left, l - 1);
            QuickSort(arr, r + 1, right);

        }
    }
}

class Merge {
    //拆分+合并
    public static void MergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //拆分
            MergeSort(arr, left, mid, temp);
            MergeSort(arr, mid + 1, right, temp);
            //合并
            Merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 把从left到right的以mid为分界的两个数组排序
     *
     * @param arr   原数组
     * @param left  左下标
     * @param right 右下标
     * @param mid   中值
     * @param temp  存储中间结果
     */
    public static void Merge(int[] arr, int left, int mid, int right, int[] temp) {
        //System.out.println("left= "+left+" mid="+mid+" right="+right);
        int l = left;
        int j = mid + 1;
        int t = 0;
        //将左边和右边的有序数组进行传输
        while (l <= mid && j <= right) {
            if (arr[l] <= arr[j]) {
                temp[t] = arr[l];
                t++;
                l++;
            } else {
                temp[t] = arr[j];
                t++;
                j++;
            }
        }
        //弹出这个循环后，可能左边或者右边的数组剩余
        //左边有剩余

        while (l <= mid) {
            temp[t] = arr[l];
            t++;
            l++;
        }

        //右边有剩余

        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }


        //此时temp里面是left->right的有序数组，赋值给arr
        //int leftTemp = left;
        t = 0;
        while (left <= right) {
            arr[left] = temp[t];
            left++;
            t++;
        }

    }
}

//基数排序
class Radix {
    public static void radixSort(int[] arr) {
        //首先要确定做多少次排序
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        int times = (max + "").length();

        //创建二维数组，作为桶来存储数据
        int[][] bucket = new int[10][arr.length];
        //创建一个数组，来存储每个桶有多少数据，方便取出
        int[] bucketNum = new int[10];

        for (int i = 0, n = 1; i < times; i++, n *= 10) {
            //将同位下各个数放入桶
            for (int numIndex = 0; numIndex < arr.length; numIndex++) {
                //每一个数末位
                int lastNum = arr[numIndex] / n % 10;
                //存入桶
                bucket[lastNum][bucketNum[lastNum]] = arr[numIndex];
                bucketNum[lastNum]++;
            }

            //原数组下标
            int t = 0;
            //从桶中取出并放入arr
            for (int outIndex = 0; outIndex < 10; outIndex++) {
                //判断当前桶是否有数据
                if (bucketNum[outIndex] != 0) {
                    //有数据
                    for (int o = 0; o < bucketNum[outIndex]; o++) {
                        arr[t] = bucket[outIndex][o];
                        t++;
                    }
                    //桶中的数据一定要记得清零
                    bucketNum[outIndex] = 0;
                }

            }
        }
    }
}

//堆排序
class Heap {
    public static void heapSort(int[] arr){
        //将数组调整为大顶堆
        for (int i= arr.length/2-1;i>=0;i--){
            adjustHeap(arr,i,arr.length);
        }
        int temp = 0;
        //调整完毕后，交换堆顶元素与末尾元素
        for (int j=arr.length-1;j>0;j--){
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            //最后一位已经是最大了，不必参与调整
            adjustHeap(arr,0,j);
        }
        //System.out.println(Arrays.toString(arr));
    }
    //调整为大顶堆
    public static void adjustHeap(int[] arr, int i, int length) {
        //取出当前节点值保存
        int temp = arr[i];
        for (int k = 2 * i + 1; k < length; k = k * 2 + 1) {
            //比较其子节点的大小
            //如果右子节点比左子节点大，则k指向右子节点
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            //比较父节点与较大子节点的大小关系
            if (temp < arr[k]) {
                //把子节点的值赋值给父节点
                arr[i] = arr[k];
                //同时这个父节点为了便于接下来的子节点继续和它进行比较，把k值赋值给了i
                i = k;
            }else{
                break;
            }
        }
        arr[i] = temp;

    }
}
