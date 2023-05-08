package com.atguigu.sparsearray;

/**
 * @author GongChangjiang
 * @version 1.0
 * @Date 2023/3/18
 * @Description 稀疏数组的代码实现  稀疏数组和二维数组之间的互转
 */
public class SparseArray {

    public static void main(String[] args) {
        //创建一个原始的二维数组
        //0:表示没有棋子   1:表示黑子   2:表示白子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 1;
        //遍历输出
        System.out.println("原始的二维数组");
        for (int[] row : chessArr1) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }


        //将二维数组转化成稀疏数组
        //获取原始二维数组中非零元素的个数
        int sum = 0;
        for (int[] row : chessArr1) {
            for (int num : row) {
                if (num != 0) {
                    sum++;
                }
            }
        }
        System.out.println("非零元素的个数:" + sum);
        //根据非零的元素的个数创建对应的稀疏数组
        int[][] sparseArr2 = new int[sum + 1][3];
        //给稀疏数组赋值
        //第一行的赋值操作
        sparseArr2[0][0] = 11;
        sparseArr2[0][1] = 11;
        sparseArr2[0][2] = sum;
        //其余行的赋值操作
        int row = 1; //充当计数器的作用
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    sparseArr2[row][0] = i;
                    sparseArr2[row][1] = j;
                    sparseArr2[row][2] = chessArr1[i][j];
                    row++;
                }
            }
        }


        //遍历稀疏数组
        System.out.println("得到的稀疏数组如下");
        for (int[] ints : sparseArr2) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }


        //将稀疏数组转化成二维数组
        //获取原先的二维数组的大小
        //读取第一行 获取原始二维数组的行列值
        int[][] sparseArr3 = new int[sparseArr2[0][0]][sparseArr2[0][1]];
        //遍历稀疏数组第二行之后的值 赋值给原始的数组 从第二行开始算
        for (int i = 1; i < sparseArr2.length; i++) {
            sparseArr3[sparseArr2[i][0]][sparseArr2[i][1]] = sparseArr2[i][2];
        }

        //遍历还原之后的二维数组
        System.out.println("稀疏数组还原成二维数组");
        for (int[] ints : sparseArr3) {
            for (int anInt : ints) {
                System.out.print(anInt+" ");
            }
            System.out.println();
        }


    }
}
