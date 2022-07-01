package main;
import java.util.*;
import javax.swing.*;
import util.GameUtil;
import java.awt.*;
import java.awt.image.BufferedImage;
import static util.Constant.*;

public class Bird {
	//���С��ͼƬ
	private BufferedImage[] images;
	public static final int BIRD_IMG_COUNT=3;
	//���״̬
	private int state;
	public static final int START_NORMAR=0;//ƽ��
	public static final int START_UP=1;//���Ϸ�
	public static final int START_DOWN=2;//���·�
	//С���λ��
	private int x=200,y=200;
	//С�������ƶ�
	private boolean up=false,down=false;
	//С����ٶ�
	private int speed=4;
	//С����ζ���(������ײ)
    private Rectangle rect;
    //С��ļ��ٶ�
    private int a;
    //С�������
    public boolean life=true ;
    
	public Bird() {
		images=new BufferedImage[BIRD_IMG_COUNT];
		for(int i=0;i<BIRD_IMG_COUNT;i++) {
			images[i]=GameUtil.loadBufferedImage(BIRD_IMG[i]);
		}
		
		 int w = images[0].getWidth();
	     int h = images[0].getHeight();
	     rect = new Rectangle(w,h);

	}
	//����С��
	public void draw(Graphics g) {
		flyLogic();
		g.drawImage(images[state], x, y,null);
		
		//����С��ľ���
        //g.drawRect(x,y,(int)rect.getWidth(), rect.height);
        rect.x=this.x;
        rect.y=this.y;
	}
	
	//����С���ƶ�����
	public void flyLogic() {
		if(up) {
			a--;
			y+=a;
			if(a<-6) {
				a=-6;
			}
			if(y<25) {
				y=25;
				a=0;
			}
		}
		if(!up) {
			a++;
			y+=a;
			if(a>3) {
				a=3;
			}
			if(y>465) {
				y=465;
				a=0;
			}
		}
	}
	public void fly(int fly) {
		switch(fly) {
		case 1:
			state=1;
			up=true;
			break;
		case 5:
			state=2;
			up=false;
			break;
			
		}
	}
	
	public Rectangle getRect() {
        return rect;
    }
	
	
	//���»���С���λ��
	public void  restartDraw(){
	    
	    x=200;
	    y=200;
	    life=true;
	    
	}
}
