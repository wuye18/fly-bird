package main;
import static util.Constant.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
public class GameFrame extends Frame {
	//实例化gamebackground类
	private GameBackGround gameBackGround;
	//实例化bird类
	private Bird bird;
	//实例化GameFrontGround类
	private GameFrontGround gameFrontGround; 
	//实例化GameBarrierLayer类
	private GameBarrierLayer gameBarrierLayer; 
	//实例化Act
	private Act act;
	//实例化End类
	private End end;
	//定义组件用来定位确认对话框的位置
	JTextField t=new JTextField();
	//存放图片的图片
	private BufferedImage buffimg=new BufferedImage(FRAM_WIDTH,FRAM_HEIGHT,BufferedImage.TYPE_4BYTE_ABGR);
	private BufferedImage buffimg2=new BufferedImage(FRAM_WIDTH,FRAM_HEIGHT,BufferedImage.TYPE_4BYTE_ABGR);
	// 状态常量:0表示开始界面，1表示开始游戏
	int state;
	
		
	
	public GameFrame(){
		setVisible(true);
		setSize(FRAM_WIDTH,FRAM_HEIGHT);
		setTitle(FRAM_TITLE);
		setLocation(FRAM_X,FRAM_Y);
		setResizable(false);
		t.setBounds(280,150,10,10);
		
		//窗口关闭事件
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		initGamg();
		new run().start();
		//按键监听
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				add(e);
			}
			public void keyReleased(KeyEvent e) {
				minu(e);
			}
		});
		
	}
	public void initGamg() {
		gameBackGround=new GameBackGround();
		bird=new Bird();
		gameFrontGround =new GameFrontGround();
		gameBarrierLayer=new GameBarrierLayer();
		act=new Act();	
		end=new End();
	}
	
	class run extends Thread{
		public void run(){
			while(true) {
			
			repaint();//调用update方法重画
			try {
				Thread.sleep(33);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}
		}
	}
	  public void update(Graphics g) {
		this.add(t);
		if(state==0){
			Graphics graphics = buffimg2.getGraphics();
			act.draw(graphics);
			g.drawImage(buffimg2,0,0,null);
			g.setColor(Color.black);
			g.setFont(new Font("微软雅黑",1,30));
            g.drawString("ENTER开始",200,420); 
		}
		else if(bird.life==true&&state==1) {
		//得到图片画笔(解决屏幕闪烁问题)
		Graphics graphics = buffimg.getGraphics();
		//将其他画到buffimg上
		gameBackGround.draw(graphics);//其他画添加在BackGround后，否则会被覆盖
		bird.draw(graphics);
		gameFrontGround.draw(graphics);
		gameBarrierLayer.draw(graphics,bird);
		//使用buffimg一次性将图片绘制到屏幕上(解决屏幕闪烁问题)l
		g.drawImage(buffimg, 0, 0, null);
		
		}
		else if(state==2) {
			
		}
		else {
			g.setColor(Color.black);
			g.setFont(new Font("微软雅黑",1,30));
			String reset = "空格重新开始";
            end.draw(g);
            g.drawString(reset,210,250); 
            
		}
	}
	  //按键
	  public void add(KeyEvent e) {
		  switch(e.getKeyCode()) {
		  case KeyEvent.VK_UP:
			  bird.fly(1);
			  break;
		  case KeyEvent.VK_W:
			  bird.fly(1);
			  break;
		  case KeyEvent.VK_SPACE:
              if (bird.life ==false) {
                  restart();
              }
              break;
		  case KeyEvent.VK_ENTER:
			  start();
			  break;
		  case KeyEvent.VK_ESCAPE:
			  gameover();
			  break;
		  }
	  }
	  //抬键
	  public void minu(KeyEvent e) {
		  switch(e.getKeyCode()) {
		  case KeyEvent.VK_UP:
			  bird.fly(5);
			  break;
		  case KeyEvent.VK_W:
			  bird.fly(5);
			  break;
		  }
	  }
	  
	  //开始游戏
	  public void start() {
		  state=1;
	  }
	  //重置游戏
	    public void restart(){
	        gameBarrierLayer.restant();
	        bird.restartDraw();
	    }  
	  //结束游戏
	   public void gameover() {
		   	int oldstate=state;
		   	state=2;
	    	int n=JOptionPane.showConfirmDialog(t, "确认是否退出游戏","退出游戏",JOptionPane.YES_NO_OPTION);
	    	if(n==JOptionPane.YES_OPTION) {
	 	        System.exit(0);
	    	}
	    	else {
	    		state=oldstate;
	    	}
	   }

}
