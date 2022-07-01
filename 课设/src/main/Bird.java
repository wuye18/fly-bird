package main;
import java.util.*;
import javax.swing.*;
import util.GameUtil;
import java.awt.*;
import java.awt.image.BufferedImage;
import static util.Constant.*;

public class Bird {
	//存放小鸟图片
	private BufferedImage[] images;
	public static final int BIRD_IMG_COUNT=3;
	//鸟的状态
	private int state;
	public static final int START_NORMAR=0;//平飞
	public static final int START_UP=1;//向上飞
	public static final int START_DOWN=2;//向下飞
	//小鸟的位置
	private int x=200,y=200;
	//小鸟上下移动
	private boolean up=false,down=false;
	//小鸟的速度
	private int speed=4;
	//小鸟矩形对象(处理碰撞)
    private Rectangle rect;
    //小鸟的加速度
    private int a;
    //小鸟的生命
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
	//绘制小鸟
	public void draw(Graphics g) {
		flyLogic();
		g.drawImage(images[state], x, y,null);
		
		//绘制小鸟的矩形
        //g.drawRect(x,y,(int)rect.getWidth(), rect.height);
        rect.x=this.x;
        rect.y=this.y;
	}
	
	//控制小鸟移动方向
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
	
	
	//重新绘制小鸟的位置
	public void  restartDraw(){
	    
	    x=200;
	    y=200;
	    life=true;
	    
	}
}
