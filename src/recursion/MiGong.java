package recursion;

public class MiGong {
    public static void main(String[] args) {
        //创建二维数组模拟迷宫
        //地图
        int[][] map = new int[8][7];
        //1表示墙
        //上下全部置1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右全部置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
//        map[2][2] = 1;
        //输出地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        //测试递归回溯
        setWay(map, 1, 1);
        //输出新的地图
        System.out.println("小球走过的地图情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    //使用递归回溯来给小球找路，约定当值为0，表示该点没有走过，1表示墙，2表示通路,3表示走过但是不通
    //在走迷宫时，需要确定一个策略，下->右->上->左，如果该点走不通则回溯

    /**
     * @param map 表示地图
     * @param j   从哪个位置开始找
     * @return 找到通路为true，否则为false
     */
    public static boolean setWay(int[][] map,int i,int j){
        if (map[6][1] == 2){
            //通路已经找到
            return true;
        }else {
            if (map[i][j] == 0){
                //如果这个点没有走过 下->右->上->左
                //假定该点可以走通
                map[i][j] = 2;
                if (setWay(map,i+1,j)){
                    //向下走
                    return true;
                }else if (setWay(map,i,j+1)){
                    //向右走
                    return true;
                }else if (setWay(map,i-1,j)){
                    //向上走
                    return true;
                }else if (setWay(map,i,j-1)){
                    //向左走
                    return true;
                }else{
                    //说明该点走不通
                    map[i][j] = 3;
                    return false;
                }
            }else {
                //如果map[i][j] ！= 0，可能是1，2，3
                return false;
            }
        }
    }
/*
    //修改策略 上右下左
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            //通路已经找到
            return true;
        } else {
            if (map[i][j] == 0) {
                //如果这个点没有走过 下->右->上->左
                //假定该点可以走通
                map[i][j] = 2;
                if (setWay(map, i - 1, j)) {
                    //向上走
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    //向右走
                    return true;
                } else if (setWay(map, i + 1, j)) {
                    //向下走
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    //向左走
                    return true;
                } else {
                    //说明该点走不通
                    map[i][j] = 3;
                    return false;
                }
            } else {
                //如果map[i][j] ！= 0，可能是1，2，3
                return false;
            }
        }
    }*/
}

