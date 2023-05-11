package com.atguigu.stack;

/**
 * @author GongChangjiang
 * @version 1.0
 * @Date 2023/5/10
 * @Description 综合计算器
 */
public class Calculator {
    public static void main(String[] args) {
        /**
         * 1. 通过一个 index  值（索引），来遍历我们的表达式
         * 2. 如果我们发现是一个数字, 就直接入数栈
         * 3. 如果发现扫描到的是一个符号,  就分如下情况
         * 3.1 如果发现当前的符号栈为 空，就直接入栈
         * 3.2 如果符号栈有操作符，就进行比较,如果当前的操作符的优先级小于或者等于栈中的操作符， 就需要从数栈中pop出两个数,在从符号栈中pop出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈， 如果当前的操作符的优先级大于栈中的操作符， 就直接入符号栈.
         * 4. 当表达式扫描完毕，就顺序的从 数栈和符号栈中pop出相应的数和符号，并运行.
         * 5. 最后在数栈只有一个数字，就是表达式的结果
         */
        String expression = "30+2*6-2";
        //创建两个栈 一个是数栈  一个是符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int result = 0;
        char ch = ' '; //将每次扫描得到的char保存在ch中
        String keepNum = "";//用于拼接多位数的
        while (true) {
            //得到expression的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是什么 做相应的处理
            if (operStack.isOper(ch)) {
                //先符号栈判断是不是为空
                if (!operStack.isEmpty()) { //判断符号栈是否为空
                    //不为空的情况
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        //如果当前的操作符的优先级小于或者等于栈中的操作符， 就需要从数栈中pop出两个数,
                        // 在从符号栈中pop出一个符号，进行运算，将得到结果，入数栈
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        result = numStack.cal(num1, num2, oper);
                        //将运算的结果入数栈
                        numStack.push(result);
                        //将当前的操作符入符号栈
                        operStack.push(ch);
                    } else {
                        //如果当前的操作符的优先级大于栈中的操作符， 就直接入符号栈.
                        operStack.push(ch);
                    }
                } else {
                    //如果为空  直接入栈
                    operStack.push(ch);
                }
            } else {
                //如果是数字 就直接入数栈
                //要考虑是多位数的情况
                keepNum += ch;
                //如果ch是表达式的最后一位 就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //判断下一个字符是不是数字  如果是数字 就继续扫描 如果是运算符 则入栈
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {//下一位是操作符
                        //直接将当前为添加到数栈
                        numStack.push(Integer.parseInt(keepNum));
                        //清空keepNum
                        keepNum = "";
                    }
                }
            }
            //让index + 1 ，并判断是否扫描到表达式的最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        //当表达式扫描完毕，就顺序的从 数栈和符号栈中pop出相应的数和符号，并运行.
        while (true) {
            //如果符号栈为空 则计算结束 数栈中只有 一个数字
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            result = numStack.cal(num1, num2, oper);
            numStack.push(result);
        }
        System.out.printf("表达式:%s = %d", expression, numStack.pop());
    }
}

//定义一个ArrayStack表示栈
class ArrayStack2 {
    private int maxSize;//栈的大小
    private int[] stack;//数组 数组模拟栈 数据放在数组中
    private int top = -1;  //top表示栈顶 初始化为1

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        //完成数组的初始化
        stack = new int[maxSize];
    }

    /**
     * 判断是否栈满
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 判断栈是否为空
     */
    public boolean isEmpty() {
        return top == -1;
    }


    /**
     * 入栈的操作
     */
    public void push(int value) {
        //先判断栈是否为满
        if (isFull()) {
            System.out.println("栈满,无法添加数据!");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈的操作
     * 将栈顶的数据返回
     */
    public int pop() {
        //先判断栈是否为空
        if (isEmpty()) {
            throw new RuntimeException("栈空,无法取出数据!");
        }
        //取出数据
        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 显示栈的情况
     */
    public void list() {
        //先判断栈是否为空
        if (isEmpty()) {
            System.out.println("栈空,无法取出数据!");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d \n", i, stack[i]);
        }
    }


    /**
     * 返回运算符的优先级
     * 优先级由数字表示
     * 数字大 优先级高
     */
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }


    /**
     * 判断是不是一个运算符
     */
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    /**
     * 计算方法
     */
    public int cal(int num1, int num2, int oper) {
        int result = 0;
        switch (oper) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num2 - num1;  //注意顺序
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num2 / num1;
        }
        return result;
    }

    /**
     * 查看栈顶数据的方法
     * 返回栈顶的值  不是pop出来
     */
    public int peek() {
        return stack[top];
    }


}

