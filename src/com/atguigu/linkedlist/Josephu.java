package com.atguigu.linkedlist;

/**
 * @author GongChangjiang
 * @version 1.0
 * @Date 2023/5/8
 * @Description 约瑟夫问题
 */
public class Josephu {
    public static void main(String[] args) {
        //测试构建环形链表
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        //测试遍历环形单向链表
        circleSingleLinkedList.showBoy();
    }

}


//创建一个环形的单向链表
class CircleSingleLinkedList {
    //创建一个first节点，当前没有编号
    private Boy first = null;

    /**
     * 根据用户的输入产生一个出队编号的序列
     */


    /**
     * 遍历当前的环形链表
     */
    public void showBoy() {
        //先判断链表是不是为空
        if (first == null) {
            System.out.println("环形链表为空！");
            return;
        }
        //创建一个辅助指针
        Boy curBoy = first;
        while (true){
            System.out.printf("小孩的编号:%d \n",curBoy.getNo());
            if(curBoy.getNext() == first){//说明已经循环一圈了  就不在循环  退出了
                break;
            }
            //向后移动
            curBoy = curBoy.getNext();
        }
    }


    /**
     * 添加小孩节点 构建成一个环形的链表
     *
     * @param nums 传入几个小孩
     */
    public void addBoy(int nums) {
        //将传入的数据进行一个校验
        if (nums < 1) {//传入小孩的的数量不能小于一
            System.out.println("输入的值不正确!");
            return;
        }
        Boy curBoy = null;//辅助指针,帮助构建环形链表
        //使用循环创建我们的环形链表
        for (int i = 1; i <= nums; i++) {  //要加入几个小孩 就循环几次
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            //将小孩加入到环形链表中
            if (i == 1) {//说明是第一个小孩
                first = boy;
                first.setNext(first);//添加的第一个节点 先自己指向自己 构建成一个环
                curBoy = first; //first节点我们不能动 使用辅助节点
            } else {
                //这是在循环里面 每次循环之后  小孩节点都不一样
                curBoy.setNext(boy);
                boy.setNext(first); //形成回路
                curBoy = boy;//curBoy每次指向的都是最后一个小孩节点
            }
        }
    }

}

//创建一个boy类 表示一个节点
class Boy {
    private int no; //编号
    private Boy next;//指向下一个节点  默认是null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
