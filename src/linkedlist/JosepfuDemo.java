package linkedlist;

public class JosepfuDemo {
    public static void main(String[] args) {
        //测试
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        list.addBoy(5);
        list.showBoy();
        list.countBoy(1,2,5);
    }
}

//创建一个环形的单向链表
class CircleSingleLinkedList {
    //创建一个first节点，指向第一个小孩
    private Boy first = new Boy(-1);
    //添加nums个节点
    public void addBoy(int nums){
        //数据校验
        if (nums < 1){
            System.out.println("nums不正确");
        }else {
            //辅助节点，指向最后一个小孩，方便插入
            Boy curBoy = null;
            for (int i = 1; i <= nums; i++) {
                Boy boy = new Boy(i);
                //如果是第一个小孩
                if (i == 1){
                    first = boy;
                    first.next = first; //构成环
                    curBoy = first;
                }else {
                    curBoy.next = boy;
                    boy.next = first;
                    curBoy = boy;
                }
            }
        }
    }
    //遍历当前环形链表
    public void showBoy(){
        //判断链表是否为空
        if (first.next == null){
            System.out.println("环形链表为空");
        }else {
            Boy curBoy = first;
            while (true){
                System.out.printf("小孩的编号为 %d \n",curBoy.no);
                if (curBoy.next == first){
                    break;
                }
                curBoy = curBoy.next;
            }
        }
    }

    //根据用户的输入，计算出小孩的出圈顺序

    /**
     *
     * @param startNo 表示从第几个小孩开始数
     * @param countNum 表示数几下
     * @param nums 表示最初一共有多少小孩
     */
    public void countBoy(int startNo,int countNum,int nums){
        //校验
        if (first == null || startNo < 1 || startNo > nums){
            System.out.println("参数输入有误，请重新输入");
        }else {
            //让helper指向环形链表的最后一个节点，便于出圈
            Boy helper = first;
            while (true){
                if (helper.next == first){
                    break;
                }
                helper = helper.next;
            }
            //小孩报数前，先让first和helper移动 startNo-1次
            for (int i = 0; i < startNo-1; i++) {
                first = first.next;
                helper = helper.next;
            }
            //小孩报数时，让first和helper移动 countNum-1次，然后出圈
            while (true){
                //当圈中只有一个节点
                if (helper == first){
                    break;
                }
                //先移动，再出圈
                for (int i = 0; i < countNum-1; i++) {
                    first = first.next;
                    helper = helper.next;
                }
                System.out.printf("小孩 %d 出圈 \n",first.no);
                //first指向小孩出圈
                first = first.next;
                helper.next = first;
            }
            System.out.printf("最后留在圈中小孩的编号 %d\n",first.no);
        }
    }
}

//创建Boy对象
class Boy {
    public int no; //编号
    public Boy next;   //指向下一个节点，默认为null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
}
