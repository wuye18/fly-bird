package main;
import static util.Constant.BIRD_IMG;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import util.GameUtil;

public class GameFrontGround {
	//�Ʋʸ���
	public static final int CLOULD_COUNT=2;
	//���Ʋʵ�����
	private List<Cloud> clouds;
	//speed
	public static final int CLOULD_SPEED=3;
	//ͼƬ��Դ
	private BufferedImage[] img;
	//���������->���������
	private Random random;
	
	//���췽������ʼ������
	public GameFrontGround() {
		clouds=new ArrayList<>();
		img=new BufferedImage[CLOULD_COUNT];
		for(int i=0;i<CLOULD_COUNT;i++) {
			img[i]=GameUtil.loadBufferedImage("img/cloud"+i+".png");
	}
		random=new Random();	
}
	//�����Ʋ�
	public void draw(Graphics g) {
		logic();
		for(int i=0;i<clouds.size();i++) {
			clouds.get(i).draw(g);
		}
	}
//�����Ʋʸ���
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
