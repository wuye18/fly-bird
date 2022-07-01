package main;

import static util.Constant.BK_COLOR;
import static util.Constant.FRAM_HEIGHT;
import static util.Constant.FRAM_WIDTH;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import util.Constant;
import util.GameUtil;

public class End {
	private BufferedImage image;
	
	public End() {
		image=GameUtil.loadBufferedImage(Constant.EN_IMG);
	}
	
	public void draw(Graphics g) {
		//±³¾°ÑÕÉ«
		g.setColor(BK_COLOR);
		g.fillRect(0, 0, FRAM_WIDTH, FRAM_HEIGHT);
		g.setColor(Color.black);
		g.drawImage(image,200,300,null);
		
	}
}
