package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Cloud {
	//云图片
	private BufferedImage img;
	//云的位置
	private int x,y;
	//云的速度
	private int speed;
	
	public Cloud() {
		
	}
	public Cloud(BufferedImage img,int speed,int x,int y) {
		this.img=img;
		this.speed=speed;
		this.x=x;
		this.y=y;
	}
	
	public void draw(Graphics g) {
		x-=speed;
		g.drawImage(img, x, y,null);
	}
	//判断云彩是否在屏幕内
	public boolean out() {
		if(x<-15) {
			return true;
		}
		else
		return false;
	}
}
