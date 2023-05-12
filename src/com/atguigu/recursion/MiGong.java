package com.atguigu.recursion;

/**
 * @author GongChangjiang
 * @version 1.0
 * @Date 2023/5/12
 * @Description 递归解决迷宫问题
 */
public class MiGong {
    public static void main(String[] args) {
        //创建一个二维数组模拟迷宫
        //地图
        int[][] map = new int[8][7];
        //使用1表示墙的位置
        //把四周变成墙
        //将上下变成墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //将左右变成墙
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置自定义的墙的位置
        map[3][1] = 1;
        map[3][2] = 1;

        //输出地图
        showMap(map);
        //使用递归回溯给小球找路
        getWay(map, 1, 1);
        System.out.println("标识过的路");
        showMap(map);


    }

    /**
     * 遍历输出地图的信息
     *
     * @param map 地图组成的二维数组
     */
    public static void showMap(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }


    /**
     * 1.表示墙  2.表示通路 可以走   3.表示该位置已经走过 但是走不通
     * 在就走迷宫的时候我们要确定一个策略  比如下->右->上->左  如果该点走不通  再回溯
     * 使用递归的方法给小球找路
     *
     * @param map 传进来的地图信息
     * @param i   从哪个位置开始找
     * @param j   从哪个位置开始找
     * @return 是否找到路
     */
    public static boolean getWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) { //说明通路已经找到了 就直接退出
            return true;
        } else {
            if (map[i][j] == 0) {//如果当前这个点还没有走过
                //按照策略走 下->右->上->左
                map[i][j] = 2;//先假定这个点可以走通
                if (getWay(map, i + 1, j)) { //向下走
                    return true;
                } else if (getWay(map, i, j + 1)) {//向右走
                    return true;
                } else if (getWay(map, i - 1, j)) {//向上走
                    return true;
                } else if (getWay(map, i, j - 1)) {//向左走
                    return true;
                } else {//四个方向都走不通的话说明不是一个通路 设置成false
                    map[i][j] = 3;
                    return false;
                }
            } else {//不为0 可以是1,2,3
                //可以理解为只走没有走过的
                return false;
            }

        }
    }


}
