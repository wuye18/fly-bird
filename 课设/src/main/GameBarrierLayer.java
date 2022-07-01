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
	//产生随机数
	private Random random=new Random();
		
	private List<Barrier> barriers;
	private GameTime gameTime;
	public int txt;
	
	
	public GameBarrierLayer() {
		barriers=new ArrayList<>();
		gameTime=new GameTime();
		
	}
	//绘制障碍物
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
			g.setFont(new Font("微软雅黑",1,20));
	        g.drawString(differ+"秒",40,60);
	        
	       
	        txt=getTxt();
	        if(differ<=txt) {
	        	g.drawString(txt+"秒", 120,60);
	        }
	        else {
	        	setTxt(String.valueOf(differ));
	        	g.drawString(differ+"秒", 120,60);
	        	
	        }
			//判断最后一个障碍物是否出现在屏幕内
			Barrier last=barriers.get(barriers.size()-1);
			if(last.isInFrame()) {
				ran();
				if(number<50) {
					insert(600,100,200,4);//产生中间的概率
				}
				else if(number>400) {
					insert(600,125,200,6);//产生上下移动的概率
				}
				else {
				insert(600,0,numberTop,0);
				insert(600,500-numberDown,numberDown,2);
				}
				
			}
		}
	}
	
	 
    //用于从池中获取对象
    public void insert(int x,int y ,int num,int type){
        Barrier top = Barrierpool.getPool();
        top.setX(x);
        top.setY(y);
        top.setHeight(num);
        top.setType(type);
        top.setVisible(true);
        barriers.add(top);
    }
	
	//上方障碍物高度
    private int numberTop;
    //下方障碍物高度
    private int numberDown;
    private int number;
    //产生两个100-500之间的随机高度
    public void ran(){
        numberTop = random.nextInt(400)+100;
        numberDown = random.nextInt(400)+100;
        number=random.nextInt(500);
        //如果管道重合，则重新随机
        if (numberTop + numberDown > 410) {
            ran();
        }
    }
    
   
    // 判断障碍物和小鸟发生碰撞
    public boolean collideBird(Bird bird){
        for (int i = 0; i < barriers.size(); i++) {
            Barrier barrier = barriers.get(i);
            //判断矩形是否相交
            if (barrier.getRect().intersects(bird.getRect())){
               
            	   bird.life=false;
               }
              
            
        }
        return false;
    }

    
     //用于清空障碍物的池子
     public void restant(){
         barriers.clear();
     }

     //用于储存游戏数据
     File file = new File("D:\\eclipse\\workspace\\课设\\game.txt");

     
     //从文件获取数据
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

     
     //\写入数据到文件
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
