package sparsearray;

import java.io.*;

/**
 * 稀疏矩阵
 */
public class SparseArray {
    public static void main(String[] args) throws IOException {
        //创建一个原始的二维数组11*11
        //0表示没有棋子，1表示黑子，2表示蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        System.out.println("原始的二维数组");
        for (int[] ints : chessArr1) {
            for (int data : ints) {
                System.out.print(data + "\t");
            }
            System.out.println();
        }
        //将二维数组转为稀疏数组
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        //System.out.println(sum);
        int sparseArr[][] = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;



        //遍历二维数组，将非0值放入
        //count记录是第几个非0数据
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        //输出稀疏数组
        System.out.println("稀疏数组为");
        for (int i=0;i<sparseArr.length;i++){
            System.out.println(sparseArr[i][0]+"\t"+sparseArr[i][1]+"\t"+sparseArr[i][2]);
        }

        File file = new File("map.data");
        BufferedWriter bw = new BufferedWriter(new FileWriter("src\\sparsearray\\map.data"));
        for (int i = 0; i < sparseArr.length; i++) {
            String s = sparseArr[i][0] +"\t"+ sparseArr[i][1] +"\t"+sparseArr[i][2];
            bw.write(s);
            bw.newLine();
            bw.flush();
        }
        bw.close();

        //将稀疏数组恢复为原数组
        /*//1. 读取稀疏数组的第一行，建造原始二维数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        //2. 读取其他数组存入原始二维数组
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        System.out.println("恢复后的二维数组为：");
        for (int[] ints : chessArr2) {
            for (int data : ints) {
                System.out.print(data + "\t");
            }
            System.out.println();
        }*/
        BufferedReader br = new BufferedReader(new FileReader("src\\sparsearray\\map.data"));
        //存储每一行数据
        String line;
        //存储行号
        int countLine = 0;
        System.out.println("通过读取稀疏数组恢复到原始二维数组");
        //第一行需要拿出来创建数组
        line=br.readLine();
        String[] HeaderLine = line.split("\t");
        int[][] chessArr2 = new int[Integer.parseInt(HeaderLine[0])][Integer.parseInt(HeaderLine[1])];

        while((line=br.readLine())!=null){
            countLine++;
            String[] split = line.split("\t");
            //2. 读取其他数组存入原始二维数组
            for (int i = 0; i < sparseArr.length; i++) {
                chessArr2[Integer.parseInt(split[0])][Integer.parseInt(split[1])] = Integer.parseInt(split[2]);
            }
        }
        for (int[] ints : chessArr2) {
            for (int data : ints) {
                System.out.print(data + "\t");
            }
            System.out.println();
        }
        br.close();
    }
}
