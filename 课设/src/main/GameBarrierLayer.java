package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import util.Constant;
import util.GameUtil;

public class GameBarrierLayer {
	//���������
	private Random random=new Random();
		
	private List<Barrier> barriers;
	private GameTime gameTime;
	public int txt;
	
	
	public GameBarrierLayer() {
		barriers=new ArrayList<>();
		gameTime=new GameTime();
		
	}
	//�����ϰ���
	 public void draw(Graphics g,Bird bird){
	    for (int i = 0; i < barriers.size(); i++) {
	         Barrier barrier = barriers.get(i);
	         if(barrier.isVisible()) {
	        	 barrier.draw(g);
	         }
	         else {
	        	 Barrier remove = barriers.remove(i);
	             Barrierpool.setPool(remove);
	             i--;
	         }
	        }
	    collideBird(bird);
	    logic(g);

	    }
	 
	public void logic(Graphics g) {
		if(barriers.size()==0) {
			ran();
			gameTime.begin();
			insert(600,0,numberTop,0);
			insert(600,500-numberDown,numberDown,2);
		}
		else {
			long differ=gameTime.differ();
			g.setColor(Color.gray);
			g.setFont(new Font("΢���ź�",1,20));
	        g.drawString(differ+"��",40,60);
	        
	       
	        txt=getTxt();
	        if(differ<=txt) {
	        	g.drawString(txt+"��", 120,60);
	        }
	        else {
	        	setTxt(String.valueOf(differ));
	        	g.drawString(differ+"��", 120,60);
	        	
	        }
			//�ж����һ���ϰ����Ƿ��������Ļ��
			Barrier last=barriers.get(barriers.size()-1);
			if(last.isInFrame()) {
				ran();
				if(number<50) {
					insert(600,100,200,4);//�����м�ĸ���
				}
				else if(number>400) {
					insert(600,125,200,6);//���������ƶ��ĸ���
				}
				else {
				insert(600,0,numberTop,0);
				insert(600,500-numberDown,numberDown,2);
				}
				
			}
		}
	}
	
	 
    //���ڴӳ��л�ȡ����
    public void insert(int x,int y ,int num,int type){
        Barrier top = Barrierpool.getPool();
        top.setX(x);
        top.setY(y);
        top.setHeight(num);
        top.setType(type);
        top.setVisible(true);
        barriers.add(top);
    }
	
	//�Ϸ��ϰ���߶�
    private int numberTop;
    //�·��ϰ���߶�
    private int numberDown;
    private int number;
    //��������100-500֮�������߶�
    public void ran(){
        numberTop = random.nextInt(400)+100;
        numberDown = random.nextInt(400)+100;
        number=random.nextInt(500);
        //����ܵ��غϣ����������
        if (numberTop + numberDown > 410) {
            ran();
        }
    }
    
   
    // �ж��ϰ����С������ײ
    public boolean collideBird(Bird bird){
        for (int i = 0; i < barriers.size(); i++) {
            Barrier barrier = barriers.get(i);
            //�жϾ����Ƿ��ཻ
            if (barrier.getRect().intersects(bird.getRect())){
               
            	   bird.life=false;
               }
              
            
        }
        return false;
    }

    
     //��������ϰ���ĳ���
     public void restant(){
         barriers.clear();
     }

     //���ڴ�����Ϸ����
     File file = new File("D:\\eclipse\\workspace\\����\\game.txt");

     
     //���ļ���ȡ����
     public int getTxt()   {
         BufferedReader in = null;
         try {
             in = new BufferedReader(new FileReader(file));
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }
         int read = 0;
         try {
             read = Integer.parseInt(in.readLine());
         } catch (IOException e) {
             e.printStackTrace();
         }
         try {
             in.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
         return read;
     }

     
     //\д�����ݵ��ļ�
     public void setTxt(String str)   {
         FileWriter out = null;
         try {
             out = new FileWriter(file);
         } catch (IOException e) {
             e.printStackTrace();
         }
         try {
             out.write(str);
         } catch (IOException e) {
             e.printStackTrace();
         }
         try {
             out.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }


}
