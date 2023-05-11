package com.atguigu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author GongChangjiang
 * @version 1.0
 * @Date 2023/5/11
 * @Description 逆波兰计算器的设计与实现
 */
public class PolandNotation {
    public static void main(String[] args) {
        //先定义一个逆波兰表达式
        //(3+4)×5-6 的逆波兰表达式是 3 4 + 5 × 6 -
        //为了方便 逆波兰表达式的数字和符号有空格隔开
        String suffixExpression = "3 4 + 5 * 6 -";//中间有空格隔开
        //1.先将suffixExpression放入到List集合中
        //2.将List集合传递给一个方法 配合栈 完成计算
        List<String> rpnList = getListString(suffixExpression);
        System.out.println("计算的结果是:" + calculate(rpnList));

        //测试将中缀表达式转化成逆波兰表达式
        //完成一个将中缀表达式 转换为一个后缀表达式
        //1.  1+((2+3)×4)-5(中缀表达式) -> 1 2 3 + 4 × + 5 –(后缀表达式)
        //2.  将中缀表达式转化成List集合
        String expression = "1+((2+3)*4)-5";
        List<String> list = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的List:" + list);
        //3.将得到的中缀表达式对应的List 转换成一个逆波兰表达式(后缀表达式)对应的List
        List<String> stringList = parseSuffixExpressionList(list);
        System.out.println("中缀表达式对应的List:" + stringList);
        System.out.println("计算的结果:" + calculate(stringList));


    }

    /**
     * 输入一个运算符 返回对应的优先级
     */
    public static int getVal(String operation) {
        int res = 0;
        switch (operation) {
            case "+":
            case "-":
                res = 1;
                break;
            case "/":
            case "*":
                res = 2;
                break;
            default:
                break;
        }
        return res;
    }

    /**
     * 将中缀表达式对应的List转化成后缀表达式对应的list
     * tips:
     * 1)初始化两个栈：运算符栈s1和储存中间结果的栈s2；
     * 2)从左至右扫描中缀表达式；
     * 3)遇到操作数时，将其压s2；
     * 4)遇到运算符时，比较其与s1栈顶运算符的优先级：
     * (1)如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
     * (2)否则，若优先级比栈顶运算符的高，也将运算符压入s1；
     * (3)否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4-1)与s1中新的栈顶运算符相比较
     * 5)遇到括号时：
     * (1) 如果是左括号“(”，则直接压入s1
     * (2) 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
     * 6)重复步骤2至5，直到表达式的最右边
     * 7)将s1中剩余的运算符依次弹出并压入s2
     * 8)依次弹出s2中的元素并输出，**结果的逆序即为中缀表达式对应的后缀表达式**
     *
     * @param ls 中缀表达式对应的list
     * @return 后缀表达式对应的list
     */
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //定义栈
        Stack<String> s1 = new Stack<>();//符号栈 s1栈
        //说明:因为思路分析中使用的s2的栈在整个的运算的过程中没有进行pop的操作  我们直接使用list集合代替s2栈 （同时也为了方便后面逆序的输出）
        ArrayList<String> s2 = new ArrayList<>();//用于存储中间结果的list
        //遍历ls
        for (String item : ls) {
            //如果是一个数 就加入到s2栈
            if (item.matches("\\d+")) {//正则匹配
                s2.add(item);
            } else if (item.equals("(")) { //如果是s1的话就直接入符号栈
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//将（弹出s1栈,这里的操作就是消除括号
            } else {//加减乘除的操作
                //当item的优先级小于栈顶的优先级，
                // 将s1栈顶的运算符弹出并加入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较
                while (s1.size() != 0 && getVal(s1.peek()) >= getVal(item)) {
                    s2.add(s1.pop());
                }
                //还需要将item压入栈中
                s1.push(item);
            }
        }
        //将s1中剩余的运算符依次弹出并加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }

    /**
     * 将中缀表达式转化成对应的list
     *
     * @param expression 中缀表达式
     * @return 中缀表达式转化成的一个list集合
     */
    public static List<String> toInfixExpressionList(String expression) {
        //定义一个集合，存储中缀表达式对应的list集合
        List<String> list = new ArrayList<>();
        int i = 0;//这个是一个指针 用于遍历中缀表达式的字符串
        String str;//做多位数的拼接工作 ,因为我们要考虑多位数的情况
        char c;//每遍历一个字符就放入到c中
        do {
            if ((c = expression.charAt(i)) < 48 || (c = expression.charAt(i)) > 57) { //如果c是一个非数字的字符需要加入到ls中
                list.add(c + ""); //直接将这个字符添加到list集合中
                i++;
            } else {//如果是一个数 要考虑多位数的问题
                str = "";//先将str置空
                //这个只要遍历的有一个字符不是数字 就会退出while循环
                while (i < expression.length() && (c = expression.charAt(i)) >= 48 && (c = expression.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                list.add(str);
            }
        } while (i < expression.length());
        return list;
    }


    //将一个逆波兰表达式依次将数据和运算符放入到List集合中
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression按照空格分割
        String[] strings = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String string : strings) {
            list.add(string);
        }
        return list;
    }

    /**
     * 通过一个后缀表达式的list集合得到表达式最终的计算结果
     *
     * @param list 后缀表达式的list集合
     * @return 根据后缀表达式的集合计算出的表达式的结果信息
     */
    public static int calculate(List<String> list) {
        //创建一个栈
        Stack<String> stack = new Stack<>();
        //遍历list
        for (String item : list) {
            //使用正则表达式取出数字
            if (item.matches("\\d+")) { //匹配的是多位数
                //入栈
                stack.push(item);
            } else {
                //弹出两个数 并运算 再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                //运算
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误!");
                }
                //把结果入栈
                stack.push(res + "");
            }
        }
        //最后留在stack中的数就是运算结果
        return Integer.parseInt(stack.pop());
    }
}
