package main;

import java.util.ArrayList;
import java.util.List;

/*
 * 避免每次都需要new一个新的对象，为节省内存，
 * 用pool来提前建一些对象，使用后归还
 */
public class Barrierpool {
	//用于管理池中所有对象的容器
    private static List<Barrier> pool = new ArrayList<>();
    //池中初始的对象个数
    public static final int initCount = 16;
    //对象池中最大个数
    public static final int maxCOunt = 20;

    static {
        //初始化池中的对象
        for (int i = 0; i < initCount; i++) {
            pool.add(new Barrier());
        }
    }
    
    //从池中获取一个对象 
    public static Barrier getPool(){
        int size = pool.size();
        //如果池中有对象才可以拿
        if (size > 0) {
            //移除并返回对象
           
            return pool.remove(size-1);
        }else {
            //池中没有对象了 只能new
            
            return new Barrier();
        }
    }


    //将对象归还容器中
    public static void setPool(Barrier barrier){
        if (pool.size() < maxCOunt) {
            pool.add(barrier);
            
        }
    }


}
