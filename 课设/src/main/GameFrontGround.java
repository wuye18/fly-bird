package main;
import static util.Constant.BIRD_IMG;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import util.GameUtil;

public class GameFrontGround {
	//云彩个数
	public static final int CLOULD_COUNT=2;
	//放云彩的容器
	private List<Cloud> clouds;
	//speed
	public static final int CLOULD_SPEED=3;
	//图片资源
	private BufferedImage[] img;
	//产生随机数->产生随机云
	private Random random;
	
	//构造方法来初始化数据
	public GameFrontGround() {
		clouds=new ArrayList<>();
		img=new BufferedImage[CLOULD_COUNT];
		for(int i=0;i<CLOULD_COUNT;i++) {
			img[i]=GameUtil.loadBufferedImage("img/cloud"+i+".png");
	}
		random=new Random();	
}
	//绘制云彩
	public void draw(Graphics g) {
		logic();
		for(int i=0;i<clouds.size();i++) {
			clouds.get(i).draw(g);
		}
	}
//控制云彩个数
private void logic() {
	if((int)(500*Math.random())<10) {
		Cloud cloud=new Cloud(img[random.nextInt(CLOULD_COUNT)],CLOULD_SPEED,600,random.nextInt(150));
		clouds.add(cloud);
	}
	for(int i=0;i<clouds.size();i++) {
		Cloud cloud=clouds.get(i);
		if(cloud.out()) {
			clouds.remove(i);
			i--;
			
		}
			
	}
}
}
