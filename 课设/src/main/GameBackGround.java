package main;
import util.*;
import java.awt.image.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.*;
import static util.Constant.*;
public class GameBackGround {
	
	private BufferedImage bkimg;
	//��������ͼƬ
	private BufferedImage score;
	//���췽����ʼ����Դ
	public GameBackGround() {
		bkimg=GameUtil.loadBufferedImage(Constant.BK_IMG_OATH);
		score=GameUtil.loadBufferedImage(Constant.SCORE_IMG);
	}
	//����ͼƬ
	public void draw(Graphics g) {
		//������ɫ
		g.setColor(BK_COLOR);
		g.fillRect(0, 0, FRAM_WIDTH, FRAM_HEIGHT);
		g.setColor(Color.black);
		//ͼƬ�����
		int height = bkimg.getHeight();
		int weight = bkimg.getWidth();
		//ͼƬ����
		int count = Constant.FRAM_WIDTH/weight+1;
		for(int x=0;x<count;x++) {
			g.drawImage(bkimg, weight*x,Constant.FRAM_HEIGHT-height,null);
		}
		
		g.drawImage(score, 0, 0,200,100, null);
	}
	
}
