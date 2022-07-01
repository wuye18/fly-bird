package util;
import java.util.*;
import javax.swing.*;
import java.awt.*;
public class Constant {
	//窗口大小
	public static final int FRAM_WIDTH = 600;
	public static final int FRAM_HEIGHT = 500;
	//窗口标题
	public static final String FRAM_TITLE = "飞翔的小鸟";
	//窗口位置
	public static final int FRAM_X=200;
	public static final int FRAM_Y=200;
	//图片路径
	public static final String BK_IMG_OATH = "img/bird_bk.png";
	//游戏背景颜色
	public static final Color BK_COLOR=new Color(0x4B4CF);
	//小鸟的图片资源
	public static final String[] BIRD_IMG= {"img/bird_normal.png","img/bird_up.png","img/bird_down.png"};
	//障碍物图片资源
	public static final String[] BARRIER_IMG_PATH= {"img/barrier.png","img/barrier_up.png","img/Barrier_down.png"};
	//开始图片
	public static final String [] BE_IMG= {"img/title.png","img/start.png","img/bg.png","img/bird_normal.png","img/bird_up.png","img/bird_down.png"};
	//结束图片
	public static final String EN_IMG= "img/over.png";
	//得分图片
	public static final String SCORE_IMG= "img/score.png";
}
