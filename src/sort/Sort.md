#冒泡排序
1. 时间复杂度：O(n^2)
2. 描述：每次前后交换将最大的数放到数组尾部
3. 实现：
       
       public static int[] BubbleSort(int[] arr){
              int temp = 0;
              //外层for循环确定从头开始比较的趟数
              for(int i = 0;i< arr.length-1;i++){
                //j是从头开始遍历，然后每一次遍历完后，都会确定一个最大数，因此需要在length-1的基础上在 -i
                for(int j = 0;j < arr.length-1-i; j++){
                    //如果前一个比后一个就交换位置
                    if(arr[j] > arr[j+1]){
                        temp = arr[j];
                        arr[j] = arr[j+1];
                        arr[j+1] = temp;
                    }
                }
              }
              return arr;
       }                
4. 优化：
    1. 如果在某一趟的排序中没有数的位置发生变化，说明该数组已经被排序好了。我们可以放一个boolean变量来判断这某趟排序的过程中是否发生了交换，记得重置状态就好；
 
#选择排序
1. 时间复杂度：O(n^2)
2. 描述：每次会找出最小值的index与数组首进行交换
3. 实现

        public static int[] SelectSort(int[] arr) {
                //每次确定最小数的位置，和未排序的数组首交换位置
                for (int i = 0; i < arr.length - 1; i++) {
                    int index = i;
                    for (int j = i + 1; j < arr.length; j++) {
                        if (arr[index]>arr[j]){
                            index = j;
                        }
                    }
                    //如果index不是第一个遍历的数，就互换；
                    if (index != i){
                        int temp = arr[index];
                        arr[index] = arr[i];
                        arr[i] = temp;
                    }
                }
                return arr;
        }

#插入排序
1. 时间复杂度：O(n^2)
2. 描述：按顺序每一趟都会将对应增长的index处的数据插入有序数组
3. 实现

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
                    //跳出循环后即可得到插入位置i
                    arr[insertIndex+1] = insertVal;
                }
                return arr;
        }
4. 优化：不用每一次都插入

        if (insertIndex + 1 != i){
             //跳出循环后即可得到插入位置i
             arr[insertIndex+1] = insertVal;
        }
##希尔排序
1. 描述：分组对数据进行插入排序（缩小增量排序）
2. 实现
    1. 交换法：不同步长gap组中，数据从后向前比较并交换（注意这里的重点在于交换）
      
            //交换法
            //步长
            int gap = arr.length/2;
            //中间值
            int temp = 0;
            while (gap > 0){
                //对当前gap进行排序
                for (int i=gap;i<arr.length;i++){
                    for (int j = i - gap;j>=0;j-= gap){
                        if (arr[j+gap] < arr[j]){
                            temp = arr[j+gap];
                            arr[j+gap] = arr[j];
                            arr[j] = arr[j+gap];
                        }
                    }
                }
                gap/=2;
            }
    2. 移位法：不同步长gap组中，数据从后向前比较通过移位实现插入数据。
    
            //移位法
            int gap = arr.length / 2;
            while (gap > 0) {
                //执行同一个步长下的数的排序
                for (int i = gap; i < arr.length; i++) {
                    int j = gap;
                    int temp = arr[j];
                    //如果当前数，比同一组的前一个数小，就有必要进行移位操作；
                    if (arr[j] < arr[j - gap]) {
                        while (j - gap >= 0 && arr[j] < arr[j - gap]) {
                            //后移，把j的位置让给前一个gap，空出来的就是j-gap赋值给j
                            arr[j] = arr[j-gap];
                            j -= gap;
                        }
                        //跳出循环就是j为空值；
                        arr[j] = temp;
                    }
    
                }
                gap /= 2;
            }
##快速排序
1. 描述：选取一个数作为枢轴，对数组内的数组进行排序，right指针从右向左遍历，小于枢轴的数放在左边，left指针指针从左向右遍历，大于枢轴的数放在右边；最后将枢轴插入left和right重合的地方；然后分别对左边和右边的数进行递归
2. 代码实现：

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
##归并排序
1. 描述：运用递归，将一个数组不断的拆分，然后再将这些数组进行有序合并，结果层层回送最终形成一个有序数组。
2. 图示：
![Image text](/image/MergeSort.png)
3. 实现：

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
            System.out.println("left= "+left+" mid="+mid+" right="+right);
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
            t = 0;
            while (left <= right) {
                arr[left] = temp[t];
                left++;
                t++;
            }
    
        }
        }