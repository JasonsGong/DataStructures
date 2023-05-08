package com.atguigu.linkedlist;

import java.util.Stack;

/**
 * @author GongChangjiang
 * @version 1.0
 * @Date 2023/4/28
 * @Description 单链表的实现
 * 使用带head头的单向链表实现 –水浒英雄排行榜管理
 * 完成对英雄人物的增删改查操作
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(new HeroNode(1, "宋江", "及时雨"));
//        singleLinkedList.add(new HeroNode(2, "卢俊义", "玉麒麟"));
//        singleLinkedList.add(new HeroNode(3, "吴用", "智多星"));
//        singleLinkedList.add(new HeroNode(4, "林冲", "豹子头"));
//        //显示链表
//        singleLinkedList.list();
//        //测试修改节点的功能
//        singleLinkedList.update(new HeroNode(2, "卢俊义", "玉麒麟增强版"));
//        //显示链表的信息
//        singleLinkedList.list();
//        //测试删除节点
//        singleLinkedList.delete(2);
//        //显示链表的信息
//        singleLinkedList.list();
//        //统计有效节点的个数
//        System.out.println("有效节点的个数" + singleLinkedList.getLength(singleLinkedList.getHead()));
//        //查找单链表中的倒数第K个节点
//        int index = 2;
//        System.out.println("倒数第" + index + "个节点的位置:" + singleLinkedList.getHeroNodeByLastIndex(index, singleLinkedList.getHead()));
//        //测试单链表的反转
//        singleLinkedList.reverseHeroNode(singleLinkedList.getHead());
//        singleLinkedList.list();
//        //测试通过栈的方式逆序打印
//        System.out.println("测试通过栈的方式逆序打印头节点");
//        singleLinkedList.reversePrint(singleLinkedList.getHead());

        //测试合并两个有序的链表
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        singleLinkedList1.add(new HeroNode(1, "宋江", "及时雨"));
        singleLinkedList1.add(new HeroNode(3, "卢俊义", "玉麒麟"));
        singleLinkedList1.add(new HeroNode(5, "吴用", "智多星"));
        singleLinkedList1.add(new HeroNode(7, "林冲", "豹子头"));
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        singleLinkedList2.add(new HeroNode(2, "宋江", "及时雨"));
        singleLinkedList2.add(new HeroNode(4, "卢俊义", "玉麒麟"));
        singleLinkedList2.add(new HeroNode(6, "吴用", "智多星"));
        singleLinkedList2.add(new HeroNode(8, "林冲", "豹子头"));
        mergeHeroNode(singleLinkedList1.getHead(),singleLinkedList2.getHead(),singleLinkedList.getHead());
        singleLinkedList.list();
    }
    /**
     * 合并两个有序的单链表，合并之后的链表依然有序
     */
    public static void mergeHeroNode(HeroNode oneHead, HeroNode twoHead ,HeroNode head) {
        //先判断两个链表是否为空
        if (oneHead.next == null || twoHead.next == null) {
            System.out.println("其中有一个（两个）链表为空,无法合并");
            return;
        }
        //合并
        HeroNode oneCur = oneHead.next;
        HeroNode twoCur = twoHead.next;
        HeroNode oneNext = null;
        HeroNode twoNext = null;
        HeroNode finalHeroHead = new HeroNode(0, "", "");
        while (oneCur != null && twoCur  != null) {
            oneNext = oneCur.next;
            twoNext = twoCur.next;
            if(oneCur.no <= twoCur.no) {
                oneCur.next = finalHeroHead.next;
                finalHeroHead.next = oneCur;
                oneCur = oneNext;
            }else {
                twoCur.next = finalHeroHead.next;
                finalHeroHead.next = twoCur;
                twoCur = twoNext;
            }
        }
        head.next = finalHeroHead.next;
    }
}

//定义SingleLinkedList来管理我们的英雄任务
class SingleLinkedList {
    //初始化一个头节点 不存储具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }





    /**
     * （通过栈的方式实现单链表的逆序打印）
     * 栈的特点是先入后出  正好可以满足逆序打印
     * 单链表的逆序打印
     */
    public void reversePrint(HeroNode heroNode) {
        //先判断当前的链表是否为空  或者当前的链表只有一个节点
        if (heroNode.next == null) {
            System.out.println("当前链表为空,无法逆序打印!");
            return;
        }
        //创建一个栈，将各个节点压入栈中
        //先创建一个栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = heroNode.next;
        //循环将每一个节点添加进栈中
        while (cur != null) {
            stack.push(cur);//将节点压入栈中
            cur = cur.next;//cur后移 压入下一个节点
        }
        //将栈中节点打印
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }


    /**
     * 单链表的反转
     *
     * @param heroNode 需要反转单链表的头节点
     */
    public void reverseHeroNode(HeroNode heroNode) {
        //先判断当前的链表是否为空  或者当前的链表只有一个节点
        if (heroNode.next == null || heroNode.next.next == null) {
            System.out.println("当前链表为空或者只有一个节点,无需反转");
            return;
        }
        //定义一个辅助的变量  帮助我们遍历原来的链表
        HeroNode cur = heroNode.next;
        HeroNode next = null;//指向当前节点的下一个节点 我们要在挪动当前节点之前帮当前指针 下移
        HeroNode reverseHeroNode = new HeroNode(0, "", "");//新链表的头节点
        //遍历原先的列表 确定每一个链表的指向关系
        while (cur != null) {
            next = cur.next;//保存当前节点的下一个节点
            //reverseHeroNode.next表示头节点的下一个节点   我们让cur的下一个节点（cur.next）指向头节点的下一个节点（reverseHeroNode.next） 就把当前的节点（cur）穿插进去了
            //下一句将头节点和当前节点建立关系reverseHeroNode.next = cur  整个节点就连接起来了
            cur.next = reverseHeroNode.next;//将cur的下一个节点指向新的链表的最前端 相当于在头节点和头节点的下一个节点之间穿插了一个节点
            reverseHeroNode.next = cur;//将cur连接到新的链表上
            cur = next; //cur像后移动
        }
        //将head.next指向reverseHeroNode.next 实现单链表的反转
        heroNode.next = reverseHeroNode.next;
    }


    /**
     * 查找单链表中的倒数第K个节点
     *
     * @param index    倒数第几
     * @param heroNode 头节点
     * @return 倒数第K个节点的信息
     */
    public HeroNode getHeroNodeByLastIndex(int index, HeroNode heroNode) {
        //先判断节点是否为空
        if (heroNode.next == null) {
            return null;
        }
        //首先获取有效节点的个数
        int length = this.getLength(heroNode);//使用求节点有效个数的方法
        //求倒数第k个节点就是在求正数第（length - index + 1）节点
        index = length - index;  //求出正向的索引位置
        if (index < 0 || index > length) {
            System.out.println("所求的在链表中不存在");
            return null;
        }
        int count = 0;
        HeroNode temp = heroNode.next;
        while (true) {
            if (count == index) {
                return temp;
            }
            count++;
            temp = temp.next;
        }
    }

    /**
     * 获取有效节点的个数（如果是带头节点的链表，需求不统计头节点）
     *
     * @param heroNode 头节点
     * @return 有效节点的个数
     */
    public int getLength(HeroNode heroNode) {
        //先判断节点是否为空
        if (heroNode.next == null) {
            return 0;
        }
        //遍历节点进行统计
        int length = 0;//有效节点的个数
        HeroNode temp = heroNode.next;//这里没有统计头节点
        while (temp != null) {//遍历 统计个数
            length++;
            temp = temp.next;
        }
        return length;
    }


    /**
     * 删除链表中的节点
     * 根据节点的编号删除节点的信息
     */
    public void delete(int no) {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空,无法删除!");
            return;
        }
        //找到要删除的节点的信息
        HeroNode temp = head;
        boolean isDelete = false;//是否删除
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {//这里的temp指的是要删除节点的上一个节点  temp.next.next指的是要删除节点的下一个节点
                //将当前节点的上一个节点指向当前节点的下一个节点
                temp.next = temp.next.next;  //将要删除的节点的上一个节点指向要删除节点的下一个节点  这样要删除的节点没有引用指向 会被垃圾回收机制回收
                isDelete = true;
                break;
            }
            temp = temp.next;
        }
        //做出判断,返回结果
        System.out.println(isDelete ? "删除成功" : "没有找到该节点的信息");
    }


    /**
     * 修改节点的信息
     * 根据新的节点的编号进行修改
     */
    public void update(HeroNode newHeroNode) {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空,无法修改该节点!");
            return;
        }
        //找到需要修改的节点
        //首先找到头节点的位置
        HeroNode temp = head.next;
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
     * 添加节点到单向链表
     */
    public void add(HeroNode heroNode) {
        //找到当前链表的最后一个节点
        HeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = heroNode;
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
        HeroNode temp = head;
        while (temp.next != null) {
            System.out.println(temp.next);
            temp = temp.next;
        }
    }


}

//定义一个heroNode,每个heroNode对象都是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;  //指向下一个节点

    //构造器
    public HeroNode(int no, String name, String nickname) {
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
