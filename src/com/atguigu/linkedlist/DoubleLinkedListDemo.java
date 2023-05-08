package com.atguigu.linkedlist;

/**
 * @author GongChangjiang
 * @version 1.0
 * @Date 2023/5/7
 * @Description 双向链表的使用
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        //测试添加链表的功能
        doubleLinkedList.add(new HeroNode2(1, "宋江", "及时雨"));
        doubleLinkedList.add(new HeroNode2(2, "卢俊义", "玉麒麟"));
        doubleLinkedList.add(new HeroNode2(3, "吴用", "智多星"));
        doubleLinkedList.add(new HeroNode2(4, "林冲", "豹子头"));
        //测试显示链表的功能
        doubleLinkedList.list();
        //测试删除双向链表中的节点
        doubleLinkedList.delete(2);
        doubleLinkedList.list();
        //测试修改双向链表中的节点
        doubleLinkedList.update(new HeroNode2(2, "卢俊义修改版", "玉麒麟"));
        doubleLinkedList.list();
        System.out.println("--------------");
        doubleLinkedList.orderAdd(new HeroNode2(3, "小卢", "玉麒麟"));
        doubleLinkedList.list();
    }
}

class DoubleLinkedList {
    //初始化一个头节点 不存储具体的数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    //返回头节点的信息
    public HeroNode2 getHead() {
        return head;
    }


    /**
     * 双向链表的顺序添加
     * 应该有问题  不是参考答案
     */
    public void orderAdd(HeroNode2 heroNode) {
        //找到当前链表的最后一个节点
        HeroNode2 temp = head;
        boolean isSuccess = false;
        while (temp.next != null) {
            if (temp.next.no >= heroNode.no) {
                heroNode.next = temp.next;
                temp.next.pre = heroNode;
                temp.next = heroNode;
                heroNode.pre = temp;
                isSuccess = true;
                break;
            }
            temp = temp.next;
        }
        if (!isSuccess) {//没有添加成功 说明当前链表的序号超过了链表里面已有节点序号的最高值 直接添加到最后
            this.add(heroNode);
        }

    }


    /**
     * 删除双向链表中的一个节点
     */
    public void delete(int no) {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空,无法删除!");
            return;
        }
        //找到要删除的节点的信息
        HeroNode2 temp = head.next;
        boolean isDelete = false;//是否删除
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                temp.pre.next = temp.next;
                if (temp.next != null) {
                    temp.next.pre = temp.pre;//这句话有个条件  temp不能是最后一个节点
                }
                isDelete = true;
                break;
            }
            temp = temp.next;
        }
        System.out.println(isDelete ? "删除成功" : "没有找到该节点的信息");
    }


    /**
     * 修改双向链表的信息
     */
    public void update(HeroNode2 newHeroNode) {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空,无法修改该节点!");
            return;
        }
        //找到需要修改的节点
        //首先找到头节点的位置
        HeroNode2 temp = head.next;
        boolean idFind = false;  //是否找到节点的信息
        while (true) {
            if (temp == null) {
                break;//已经到了链表的结尾,就退出循环
            }
            if (temp.no == newHeroNode.no) {//找到修改的节点
                temp.name = newHeroNode.name;
                temp.nickname = newHeroNode.nickname;
                idFind = true; //找到了节点并且修改了节点的信息
                break;
            }
            temp = temp.next;
        }
        //做出判断,返回结果
        System.out.println(idFind ? "修改成功" : "没有找到该节点的信息");
    }


    /**
     * 向双向链表中添加节点信息
     */
    public void add(HeroNode2 heroNode) {
        //找到当前链表的最后一个节点
        HeroNode2 temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = heroNode;
        heroNode.pre = temp;
    }


    /**
     * 显示链表
     */
    public void list() {
        //先判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空!");
            return;
        }
        HeroNode2 temp = head;
        while (temp.next != null) {
            System.out.println(temp.next);
            temp = temp.next;
        }
    }


}

//定义一个heroNode,每个heroNode对象都是一个节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;  //指向下一个节点
    public HeroNode2 pre;//指向上一个节点

    //构造器
    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}