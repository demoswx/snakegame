package WUXUAN;
import javax.swing.JOptionPane;
import java.util.LinkedList;

public class Snake {
    private LinkedList<Node> body;
    //蛇的运动方向默认
    private direction D=direction.left;
    //蛇是否活着
    private boolean isliving=true;
    public Snake() {
        initSnake();
    }
    private void initSnake(){//给蛇制造身体
        body=new LinkedList<>();
        body.add(new Node(16,20));
        body.add(new Node(17,20));
        body.add(new Node(18,20));
        body.add(new Node(19,20));
        body.add(new Node(20,20));
        body.add(new Node(21,20));
    }
    public void move(){//移动蛇头
        if(isliving){
            Node head=body.getFirst();
            switch (D){
                case up:
                    //在蛇头上添加一个节点
                    body.addFirst(new Node(head.getX(),head.getY()-1));
                    break;
                case down:
                    body.addFirst(new Node(head.getX(),head.getY()+1));
                    break;
                case left:
                    body.addFirst(new Node(head.getX()-1,head.getY()));
                    break;
                case right:
                    body.addFirst(new Node(head.getX()+1,head.getY()));
                    break;
            }
            //删除尾巴
            body.removeLast();
            //判断蛇是否撞墙
            head=body.getFirst();
            if(head.getX()<0||head.getY()<0||head.getX()>=46||head.getY()>=46)
                isliving=false;
            //判断蛇是否撞到自己的身体
            for (int i=1;i<body.size();i++){
                Node node=body.get(i);
                if(head.getX()==node.getX()&&head.getY()==node.getY()){
                    isliving=false;
                }
            }
        }
        if(isliving==false){JOptionPane.showMessageDialog(null, "游戏结束", "Message", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public LinkedList<Node> getBody() {
        return body;
    }

    public void setBody(LinkedList<Node> body) {
        this.body = body;
    }

    public direction getD() {
        return D;
    }


    public void setD(direction d) {
        D = d;
    }
    //吃食物：沿着蛇头方向添加一个节点
    public void eat(Node food) {
        Node head=body.getFirst();
        switch (D) {
            case up:
                //在蛇头上添加一个节点
                body.addFirst(new Node(head.getX(), head.getY() - 1));
                break;
            case down:
                body.addFirst(new Node(head.getX(), head.getY() + 1));
                break;
            case left:
                body.addFirst(new Node(head.getX() - 1, head.getY()));
                break;
            case right:
                body.addFirst(new Node(head.getX() + 1, head.getY()));
                break;
        }
    }
}