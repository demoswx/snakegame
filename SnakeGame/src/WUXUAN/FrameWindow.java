package WUXUAN;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class FrameWindow  extends JFrame {
    private int fhigh=695;
    private int fwidth=705;
    private  Snake snake;
    private JPanel jPanel;//游戏棋盘
    private Timer time;//调用蛇的移动方法，规定时间
    private Node food;
    public FrameWindow() throws HeadlessException {
        //初始化窗体的参数
        initDrawWindow();
        //初始化游戏棋盘
        initDrawBoard();
        initfood();
        //初始化蛇
        initSnake();
        initTimer();//初始化定时器
        initkeymove();
    }
    private void initfood(){
        food=new Node();
        food.random();
    }
//键盘侦测
    private void initkeymove(){
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) { //键盘按下时会自动调用此方法
                switch (e.getKeyCode()){
                    case KeyEvent.VK_UP:
                        if(snake.getD()!=direction.down)
                            snake.setD(direction.up);
                        break;
                    case KeyEvent.VK_DOWN:
                        if(snake.getD()!=direction.up)
                            snake.setD(direction.down);
                        break;
                    case KeyEvent.VK_LEFT:
                        if(snake.getD()!=direction.right)
                            snake.setD(direction.left);
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(snake.getD()!=direction.left)
                            snake.setD(direction.right);
                        break;
                }
            }
        });
    }
    //初始化定时器
    private void initTimer(){
        time=new Timer();
        //初始化定时器
        TimerTask T=new TimerTask(){
            @Override
            public void run() {
                snake.move();
                Node head=snake.getBody().getFirst();
                if(head.getX()==food.getX()&&head.getY()==food.getY()){//判断蛇头是否和食物重合
                    snake.eat(food);
                    food.random();}
                jPanel.repaint();//重新绘制
            }
        };
        //100毫秒动一次,执行一次定时任务
        time.scheduleAtFixedRate(T,0,100);
    }
    private void initSnake(){
      snake =new Snake();

    }
    private void initDrawWindow(){
        //设置窗体大小
        setSize(fwidth,fhigh);
        //设置窗体位置
        setLocation(400,100);
        //关闭按钮
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗体大小不可变
        setResizable(false);
        setTitle("GreedySnake");
    }

    private void initDrawBoard(){

        jPanel=new JPanel(){
            //绘制游戏棋盘
            @Override    //重写
            public void  paint(Graphics g){//Graphics g 一个画笔对象
                //清空棋盘
                g.clearRect(0,0,fwidth,fhigh);
//                for(int i=0;i<=46;i++) {
//                    g.drawLine(0, i * 15, 705, i * 15);//绘制row
//                    g.drawLine(i * 15,0 ,i*15,705);    //绘制col
//                }
                //绘制蛇
                LinkedList<Node> body =snake.getBody();
                for(Node node: body){
                    g.setColor(Color.green);
                    g.fillRect(node.getX()*15,node.getY()*15,15,15);//绘制蛇的位置和大小
                }
                //绘制食物
                g.setColor(Color.red);
                g.fillRect(food.getX()*15,food.getY()*15,15,15);

            }
        };
        add(jPanel);

    }
    public static void main(String[] args){
        //创建窗体对象并且显示
        new FrameWindow().setVisible(true);
    }
}
