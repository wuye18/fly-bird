package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Cloud {
	//��ͼƬ
	private BufferedImage img;
	//�Ƶ�λ��
	private int x,y;
	//�Ƶ��ٶ�
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
	//�ж��Ʋ��Ƿ�����Ļ��
	public boolean out() {
		if(x<-15) {
			return true;
		}
		else
		return false;
	}
}
