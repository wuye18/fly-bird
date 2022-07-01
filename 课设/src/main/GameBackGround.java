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
	//分数背景图片
	private BufferedImage score;
	//构造方法初始化资源
	public GameBackGround() {
		bkimg=GameUtil.loadBufferedImage(Constant.BK_IMG_OATH);
		score=GameUtil.loadBufferedImage(Constant.SCORE_IMG);
	}
	//绘制图片
	public void draw(Graphics g) {
		//背景颜色
		g.setColor(BK_COLOR);
		g.fillRect(0, 0, FRAM_WIDTH, FRAM_HEIGHT);
		g.setColor(Color.black);
		//图片宽与高
		int height = bkimg.getHeight();
		int weight = bkimg.getWidth();
		//图片张数
		int count = Constant.FRAM_WIDTH/weight+1;
		for(int x=0;x<count;x++) {
			g.drawImage(bkimg, weight*x,Constant.FRAM_HEIGHT-height,null);
		}
		
		g.drawImage(score, 0, 0,200,100, null);
	}
	
}
