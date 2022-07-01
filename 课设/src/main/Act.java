package main;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import static util.Constant.*;

import util.Constant;
import util.GameUtil;

public class Act{
	
	private BufferedImage[] images;
	public static final int BIRD_IMG_COUNT=6;
	//图片位置
	int x=200,y=200;
	//小鸟位置
	int z=300,s=50;
	//计时让小鸟运动
	private GameTime time=new GameTime();
	
	//加载图片资源
	public Act() {
		images=new BufferedImage[BIRD_IMG_COUNT];
		for(int i=0;i<BIRD_IMG_COUNT;i++) {
			images[i]=GameUtil.loadBufferedImage(BE_IMG[i]);
		}
		time.begin();
	}
	
	
	public void draw(Graphics g) {
		//g.setColor(BK_COLOR);
		//g.fillRect(0, 0, FRAM_WIDTH, FRAM_HEIGHT);
		//g.setColor(Color.black);
		
		//第三张图片的大小
		int height =images[2].getHeight();
		int weight =images[2].getWidth();
		//第三张图片的数目
		int count = Constant.FRAM_WIDTH/weight+1;
		for(int x=0;x<count;x++) {
			g.drawImage(images[2],weight*x,Constant.FRAM_HEIGHT-height,null);
		}
		g.drawImage(images[0],x ,y ,null);
		g.drawImage(images[1],x ,y-100,null);
		//小鸟的运动
		if(s<620) {
		if(time.differ()%2<1) {
			s+=2;
			z-=1;
			g.drawImage(images[5],s,z,null);
		}
		else {
			s+=2;
			z+=1;
			g.drawImage(images[4],s,z,null);
		}
	}
		else {
			s=0;
		}
		
	
	}
	
}

