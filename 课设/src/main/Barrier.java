package main;

import static util.Constant.BIRD_IMG;

import java.awt.*;
import java.awt.image.BufferedImage;

import util.Constant;
import util.GameUtil;

public class Barrier {
	//speed
	private int speed=2;
	//����ϰ���ͼƬ
	private static BufferedImage[] imgs;
	//�ϰ����״̬
    private boolean visible;
    //���β���(�ж���ײ)
    private Rectangle rect;
    
    private boolean mob=true;
    
	static {
		final int COUNT=3;
		//����ص�ʱ��ͼƬ��ʼ��
		imgs=new BufferedImage[COUNT];
		for(int i=0;i<COUNT;i++) {
			imgs[i]=GameUtil.loadBufferedImage(Constant.BARRIER_IMG_PATH[i]);
		}
	}
	
	//λ��
	private int x,y;
	//�����߶�
	private int width,height;
	//�ϰ�������
	private int type;
	public static final int TYPE_TOP_NORMAL=0;
	public static final int TYPE_BOTTOM_NORMAL=2;
	public static final int TYPE_HOVER_NORMAL=4;
	 public static final int TYPE_MOBILE = 6;
	
	//��ÿ����
	public static final int BARRIER_WIDTH=imgs[0].getWidth();
	public static final int BARRIER_HEIGHT=imgs[0].getHeight();
	public static final int BARRIER_HEAD_WIDTH=imgs[1].getWidth();
	public static final int BARRIER_HEAD_HEIGHT=imgs[1].getHeight();
	
	
	public Barrier() {
		rect = new Rectangle();

	}
	public Barrier(int x,int y,int height,int type) {
		this.x=x;
		this.y=y;
		this.height=height;
		this.type=type;
		this.width=BARRIER_WIDTH;
	}
	
	//���Ʋ�ͬ�����ϰ���
	public void draw(Graphics g) {
		switch (type) {
        case TYPE_TOP_NORMAL:
            drawTopMormal(g);
            break;
        case TYPE_BOTTOM_NORMAL:
            drawNomalTop(g);
            break;
        case TYPE_HOVER_NORMAL:
        	drawHoverNormal(g);
            break;
        case TYPE_MOBILE:
        	drawMobile(g);
            break;
		}
    }
	//�����ϵ��������ϰ���
	public void drawTopMormal(Graphics g) {
		//�����
		int count=(height-BARRIER_HEAD_HEIGHT)/BARRIER_HEIGHT+1;
		//�����ϰ���
		for(int i=0;i<count;i++) {
			g.drawImage(imgs[0], x, y+i*BARRIER_HEIGHT,null);
		}
		//����ͷ
		int y=height-BARRIER_HEAD_HEIGHT;
		g.drawImage(imgs[2], x-(BARRIER_WIDTH-BARRIER_HEAD_WIDTH)/2, y,null);
		x-=speed;
		if(x<-20) {
			visible=false;
		}
		 rect(g);
	}
	
	//���ƴ������ϵ��ϰ���
    private void drawNomalTop(Graphics g) {
        //�������Ҫ���ϰ���Ŀ���
        int count = height / BARRIER_HEIGHT + 1;
        //forѭ�������ϰ���
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, Constant.FRAM_HEIGHT - i * BARRIER_HEIGHT, null);
        }
        //����ͷ
        int y = Constant.FRAM_HEIGHT - height;
        g.drawImage(imgs[1], x - (BARRIER_HEAD_WIDTH - BARRIER_WIDTH) / 2, y, null);
        x-=speed;
        if(x<-20) {
			visible=false;
		}
        rect(g);
    }
    
    //�����м���ϰ���
    private void drawHoverNormal(Graphics g) {
        //�������Ҫ���ϰ���Ŀ���
        int count = (height-BARRIER_HEAD_HEIGHT)/BARRIER_HEIGHT;
        //������ͷ
        g.drawImage(imgs[1],x,y,null);
        //forѭ�������ϰ���
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, y+BARRIER_HEAD_HEIGHT+i*BARRIER_HEIGHT, null);
        }
        rect(g);
        //������ͷ
        int y11 = y+height-BARRIER_HEAD_HEIGHT;
        g.drawImage(imgs[2],x,y11,null);
        x -= speed;
        if (x < -50) {
            visible = false;
        }
    }

    //�����ƶ����ϰ���
    private void drawMobile(Graphics g) {
    	 //�������Ҫ���ϰ���Ŀ���
        int count = (height-BARRIER_HEAD_HEIGHT)/BARRIER_HEIGHT;
        //������ͷ
        g.drawImage(imgs[1],x,y,null);
        //forѭ�������ϰ���
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, y+BARRIER_HEAD_HEIGHT+i*BARRIER_HEIGHT, null);
        }
        rect(g);
        //������ͷ
        int y11 = y+height-BARRIER_HEAD_HEIGHT;
        g.drawImage(imgs[2],x,y11,null);
        x -= speed;
        
        if (x < -50) {
            visible = false;
        }

        if (mob) {
            y+=5;
            if (y >= 250) {
                mob=false;
            }
        }else if (!mob){
            y-=5;
            if (y <= 100) {
                mob=true;
            }
        }
    }
    
    
    //�ϰ������ײ���β���
    public void setRecyangle(int x,int y,int width,int height){
        rect.x=x;
        rect.y=y;
        rect.width=width;
        rect.height=height;
    }
    
     
    // �����ϰ�����ײ����
    public void rect(Graphics g){
        int x1 = this.x;
        int y1 = this.y;
        int w1 = imgs[0].getWidth();
       // g.setColor(Color.blue);
       // g.drawRect(x1,y1,w1,height);
        setRecyangle(x1,y1,w1,height);
    }
    
    
    
	//�ж�ʲôʱ�������һ���ϰ���
    public boolean isInFrame(){
        return 600-x>200;
    }
    
    
    
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    } 
    public Rectangle getRect() {
        return rect;
    }
}

