package tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1,"吕布");
        HeroNode hero2 = new HeroNode(2,"典韦");
        HeroNode hero3 = new HeroNode(3,"赵云");
        HeroNode hero4 = new HeroNode(4,"关羽");

        hero1.leftNode = hero2;
        hero1.rightNode = hero3;
        hero3.rightNode = hero4;

        tree tree = new tree(hero1);
        tree.preOrder();
        System.out.println();
        tree.InOrder();
        System.out.println();
        tree.PostOrder();
    }
}
class tree{
    private HeroNode root;

    public tree(HeroNode root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder(){
        if (this.root != null){
            this.root.PreTraversal();
        }
    }
    //中序遍历
    public void InOrder(){
        if (this.root != null){
            this.root.InTraversal();
        }
    }
    //后序遍历
    public void PostOrder(){
        if (this.root != null){
            this.root.PostTraversal();
        }
    }


}
class HeroNode{
    public int no;
    public String name;
    public HeroNode leftNode;
    public HeroNode rightNode;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历
    public void PreTraversal(){
        System.out.println(this);
        if (this.leftNode != null){
            this.leftNode.PreTraversal();
        }
        if (this.rightNode != null){
            this.rightNode.PreTraversal();
        }
    }

    //中序遍历
    public void InTraversal(){
        if (this.leftNode != null){
            this.leftNode.InTraversal();
        }

        System.out.println(this);

        if (this.rightNode != null){
            this.rightNode.InTraversal();
        }
    }

    //后序遍历
    public void PostTraversal(){
        if (this.leftNode != null){
            this.leftNode.PostTraversal();
        }
        if (this.rightNode != null){
            this.rightNode.PostTraversal();
        }
        System.out.println(this);
    }
}