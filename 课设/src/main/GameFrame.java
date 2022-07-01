package main;
import static util.Constant.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
public class GameFrame extends Frame {
	//ʵ����gamebackground��
	private GameBackGround gameBackGround;
	//ʵ����bird��
	private Bird bird;
	//ʵ����GameFrontGround��
	private GameFrontGround gameFrontGround; 
	//ʵ����GameBarrierLayer��
	private GameBarrierLayer gameBarrierLayer; 
	//ʵ����Act
	private Act act;
	//ʵ����End��
	private End end;
	//�������������λȷ�϶Ի����λ��
	JTextField t=new JTextField();
	//���ͼƬ��ͼƬ
	private BufferedImage buffimg=new BufferedImage(FRAM_WIDTH,FRAM_HEIGHT,BufferedImage.TYPE_4BYTE_ABGR);
	private BufferedImage buffimg2=new BufferedImage(FRAM_WIDTH,FRAM_HEIGHT,BufferedImage.TYPE_4BYTE_ABGR);
	// ״̬����:0��ʾ��ʼ���棬1��ʾ��ʼ��Ϸ
	int state;
	
		
	
	public GameFrame(){
		setVisible(true);
		setSize(FRAM_WIDTH,FRAM_HEIGHT);
		setTitle(FRAM_TITLE);
		setLocation(FRAM_X,FRAM_Y);
		setResizable(false);
		t.setBounds(280,150,10,10);
		
		//���ڹر��¼�
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		initGamg();
		new run().start();
		//��������
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
			
			repaint();//����update�����ػ�
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
			g.setFont(new Font("΢���ź�",1,30));
            g.drawString("ENTER��ʼ",200,420); 
		}
		else if(bird.life==true&&state==1) {
		//�õ�ͼƬ����(�����Ļ��˸����)
		Graphics graphics = buffimg.getGraphics();
		//����������buffimg��
		gameBackGround.draw(graphics);//�����������BackGround�󣬷���ᱻ����
		bird.draw(graphics);
		gameFrontGround.draw(graphics);
		gameBarrierLayer.draw(graphics,bird);
		//ʹ��buffimgһ���Խ�ͼƬ���Ƶ���Ļ��(�����Ļ��˸����)l
		g.drawImage(buffimg, 0, 0, null);
		
		}
		else if(state==2) {
			
		}
		else {
			g.setColor(Color.black);
			g.setFont(new Font("΢���ź�",1,30));
			String reset = "�ո����¿�ʼ";
            end.draw(g);
            g.drawString(reset,210,250); 
            
		}
	}
	  //����
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
	  //̧��
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
	  
	  //��ʼ��Ϸ
	  public void start() {
		  state=1;
	  }
	  //������Ϸ
	    public void restart(){
	        gameBarrierLayer.restant();
	        bird.restartDraw();
	    }  
	  //������Ϸ
	   public void gameover() {
		   	int oldstate=state;
		   	state=2;
	    	int n=JOptionPane.showConfirmDialog(t, "ȷ���Ƿ��˳���Ϸ","�˳���Ϸ",JOptionPane.YES_NO_OPTION);
	    	if(n==JOptionPane.YES_OPTION) {
	 	        System.exit(0);
	    	}
	    	else {
	    		state=oldstate;
	    	}
	   }

}
