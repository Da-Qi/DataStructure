package linkedlist;

import java.util.Stack;

/**
 * 以单链表的形式管理英雄
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero4);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);

//        singleLinkedList.addByOrder(hero1);
//        singleLinkedList.addByOrder(hero4);
//        singleLinkedList.addByOrder(hero2);
//        singleLinkedList.addByOrder(hero3);
//
//        System.out.println("修改前的链表情况：");
//        //显示
//        singleLinkedList.list();

        //测试修改节点的代码
//        HeroNode newhero = new HeroNode(2, "卢sir", "火麒麟");
//        singleLinkedList.update(newhero);

//        singleLinkedList.del(1);
//        System.out.println("修改后的链表情况：");
//        System.out.println("链表长度为" + singleLinkedList.getLength());
//        //显示
//        singleLinkedList.list();
//
//        //测试查找倒数第i个节点
//        HeroNode res = singleLinkedList.findLastIndexNode(4);
//        System.out.println("倒数第i个节点为：");
//        System.out.println(res);

        //测试反转单链表
//        singleLinkedList.reverseList();
//        System.out.println("反转后的链表情况：");
//        singleLinkedList.list();

        //测试逆序打印单链表
//        System.out.println("逆序打印单链表后的链表情况：");
//        singleLinkedList.reversePrint();

        //测试合并单链表
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);

        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        singleLinkedList2.addByOrder(hero2);
        singleLinkedList2.addByOrder(hero3);

        System.out.println("合并前的两个链表：");
        singleLinkedList.list();
        singleLinkedList2.list();

        System.out.println("合并后的链表：");
        SingleLinkedList newLinkedList = new SingleLinkedListDemo().mergeSingleLinkedList(singleLinkedList, singleLinkedList2);

        newLinkedList.list();

    }

    //合并两个有序单链表，组成的新的单链表依然有序
    public SingleLinkedList mergeSingleLinkedList(SingleLinkedList list1, SingleLinkedList list2) {
        //新链表的头节点
        HeroNode newListNodeHead = new HeroNode(0, "", "");
        //next作为新链表的尾节点
        HeroNode last = new HeroNode(0, "", "");
        newListNodeHead.next = last;
        //既然是有序，那么只用比较两个链表的第一个节点大小便可
        HeroNode listhead1 = list1.getHead().next;
        HeroNode listhead2 = list2.getHead().next;
        boolean flag = true;
        while (flag) {
            //判断特殊情况
            if (listhead1 == null) {
                last.next = listhead2;
                flag = false;
            } else if (listhead2 == null) {
                last.next = listhead1;
                flag = false;
            } else {
                //正常两者非空时，比较大小
                if (listhead1.no < listhead2.no) {
                    //将新节点存入last位置
                    last.next = listhead1;
                    last = last.next;
                    listhead1 = listhead1.next;
                } else if (listhead1.no > listhead2.no){
                    //将新节点存入last位置
                    last.next = listhead2;
                    last = last.next;
                    listhead2 = listhead2.next;
                }else {
                    //相等时
                    last.next = listhead1;
                    last = last.next;
                    listhead1 = listhead1.next;
                    listhead2 = listhead2.next;
                }
            }
        }
        newListNodeHead.next = newListNodeHead.next.next;
        SingleLinkedList newLinkedList = new SingleLinkedList();
        newLinkedList.setHead(newListNodeHead);
        return newLinkedList;
    }

}

//定义SingleLinkedList来管理英雄
class SingleLinkedList {


    public HeroNode getHead() {
        return head;
    }

    public void setHead(HeroNode head) {
        this.head = head;
    }

    //1. 初始化头节点，头节点不存储数据
    private HeroNode head = new HeroNode(0, "", "");

    //第一种方式添加英雄，插入顺序添加
    //2. 添加节点到单向链表（找到当前链表的最后节点，在该节点后插入节点）
    public void add(HeroNode heroNode) {
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true) {
            //找到了链表的最后
            if (temp.next == null) {
                break;
            } else {
                //没有找到最后，将temp后裔
                temp = temp.next;
            }
        }
        //此时temp指向链表的最后
        temp.next = heroNode;
    }

    //第二种方式添加英雄，按照排名将英雄插入到指定位置
    public void addByOrder(HeroNode heroNode) {
        //首先要找到的是要插入的位置
        //为便于遍历，将head存入temp
        HeroNode temp = head;
        boolean flag = true;
        while (flag) {
            //确定该位置是否是正确位置
            if (temp.next == null || temp.next.no > heroNode.no) {
                heroNode.next = temp.next;
                temp.next = heroNode;
                flag = false;
            } else if (temp.next.no == heroNode.no) {
                //说明该英雄已经存在
                System.out.printf("英雄编号 %d 已经存在，不可重复添加 \n", heroNode.no);
                flag = false;
            } else {
                //如果不是
                temp = temp.next;
            }
        }
    }

    //修改节点的信息，根据no来修改(no不可以改)
    public void update(HeroNode heroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
        } else {
            //定义辅助节点
            HeroNode temp = head.next;
            boolean flag = true;
            while (flag) {
                //判断链表是否遍历完
                if (temp == null) {
                    flag = false;
                    System.out.printf("编号为 %d 的英雄不存在 \n", heroNode.no);
                } else {
                    //找到
                    if (temp.no == heroNode.no) {
                        temp.name = heroNode.name;
                        temp.nickname = heroNode.nickname;
                        flag = false;
                    } else {
                        //没有找到
                        temp = temp.next;
                    }
                }
            }
        }
    }

    //删除节点
    //思路：先找到待删除节点的头一个节点
    public void del(int no) {
        if (head.next == null) {
            System.out.println("链表为空");
        } else {
            //定义辅助节点
            HeroNode temp = head;
            boolean flag = true;
            while (flag) {
                //判断链表是否遍历完
                if (temp.next == null) {
                    flag = false;
                    System.out.printf("编号为 %d 的英雄不存在 \n", no);
                } else {
                    //找到
                    if (temp.next.no == no) {
                        temp.next = temp.next.next;
                        flag = false;
                    } else {
                        //没有找到
                        temp = temp.next;
                    }
                }
            }
        }
    }

    public int getLength() {
        if (head.next == null) {
            return 0;
        } else {
            int count = 0;
            HeroNode temp = head.next;
            while (temp != null) {
                count++;
                temp = temp.next;
            }
            return count;
        }

    }

    //查找单链表中的倒数第k个节点【新浪面试题】
    //思路：先把链表从头到尾遍历，得到链表的总长度getlength，得到size后，我们
    //从链表的第一个开始遍历（size-k)个
    public HeroNode findLastIndexNode(int index) {
        //判断非空
        if (head == null) {
            return null;
        } else {
            //链表总长度
            int size = getLength();
            //校验index
            if (index <= 0 || index > size) {
                return null;
            }
            //定义辅助变量
            HeroNode cur = head.next;
            for (int i = 0; i < size - index; i++) {
                cur = cur.next;
            }
            return cur;
        }
    }

    //使用栈，实现单链表的逆序打印
    public void reversePrint() {
        if (head.next == null) {
            System.out.println("链表为空");
        } else {
            Stack<HeroNode> stack = new Stack<>();
            HeroNode cur = head.next;
            //将链表节点压入栈
            while (cur != null) {
                stack.push(cur);
                cur = cur.next;
            }
            //将栈输出
            while (stack.size() > 0) {
                System.out.println(stack.pop());
            }
        }
    }

    //反转单链表
    public void reverseList() {
        //如果当前链表为空，或者只有一个节点，无需反转直接返回
        if (head.next != null && head.next.next != null) {
            //当前节点
            HeroNode cur = head.next;
            //保存当前节点的下一个节点
            HeroNode next = null;
            //新链表的头节点
            HeroNode reverseHead = new HeroNode(0, "", "");
            while (cur != null) {
                next = cur.next;
                cur.next = reverseHead.next;
                reverseHead.next = cur;
                //后移一位
                cur = next;
            }
            head.next = reverseHead.next;
        }
    }

    //显示链表
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
        } else {
            HeroNode temp = head.next;
            while (true) {
                //判断链表是否到了最后
                if (temp == null) {
                    break;
                } else {
                    //输出节点信息
                    System.out.println(temp);
                    //后移
                    temp = temp.next;
                }
            }
        }
    }
}

//定义HeroNode，每个HeroNode对象就是一个节点
class HeroNode {
    public int no; //编号
    public String name; //姓名
    public String nickname; //称号
    public HeroNode next; // 指向下一个节点


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