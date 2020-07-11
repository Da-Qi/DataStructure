package linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表的测试");
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        doubleLinkedList.add(hero1);
//        doubleLinkedList.add(hero2);
//        doubleLinkedList.add(hero3);
//        doubleLinkedList.add(hero4);

        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.list();

        //修改
        HeroNode2 heroNode2 = new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(heroNode2);
        System.out.println("修改后表的情况");
        doubleLinkedList.list();

        //删除
        doubleLinkedList.del(4);
        System.out.println("删除后的链表情况");
        doubleLinkedList.list();
    }
}

class DoubleLinkedList {
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    //第二种方式添加英雄，按照排名将英雄插入到指定位置
    public void addByOrder(HeroNode2 heroNode) {
        //首先要找到的是要插入的位置
        //为便于遍历，将head存入temp
        HeroNode2 temp = head;
        boolean flag = true;
        while (flag) {
            //确定该位置是否是正确位置
            if (temp.next == null || temp.next.no > heroNode.no) {
                heroNode.pre = temp;
                heroNode.next = temp.next;
                if (temp.next != null){
                    temp.next.pre=heroNode;
                }
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
    //遍历双向链表的提醒
    //显示链表
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
        } else {
            HeroNode2 temp = head.next;
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

    //添加节点
    public void add(HeroNode2 heroNode2) {
        HeroNode2 temp = head;
        //遍历链表，找到最后
        while (true) {
            //找到了链表的最后
            if (temp.next == null) {
                break;
            } else {
                //没有找到最后，将temp后移
                temp = temp.next;
            }
        }
        //此时temp指向链表的最后
        //形成一个双向链表
        temp.next = heroNode2;
        heroNode2.pre = temp;
    }

    //修改一个节点的内容
    public void update(HeroNode2 heroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
        } else {
            //定义辅助节点
            HeroNode2 temp = head.next;
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
    //直接找到要删除的节点
    public void del(int no) {
        if (head.next == null) {
            System.out.println("链表为空");
        } else {
            //定义辅助节点
            HeroNode2 temp = head.next;
            boolean flag = true;
            while (flag) {
                //判断链表是否遍历完
                if (temp == null) {
                    flag = false;
                    System.out.printf("编号为 %d 的英雄不存在 \n", no);
                } else {
                    //找到
                    if (temp.no == no) {
                        temp.pre.next = temp.next;

                        //如果是最后一个节点，就不需要执行这一句话
                        if (temp.next != null){
                            temp.next.pre = temp.pre;
                        }
                        flag = false;
                    } else {
                        //没有找到
                        temp = temp.next;
                    }
                }
            }
        }
    }
}

//定义HeroNode，每个HeroNode对象就是一个节点
class HeroNode2 {
    public int no; //编号
    public String name; //姓名
    public String nickname; //称号
    public HeroNode2 next; // 指向下一个节点
    public HeroNode2 pre; //指向前一个节点


    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}