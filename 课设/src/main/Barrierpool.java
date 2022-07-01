package main;

import java.util.ArrayList;
import java.util.List;

/*
 * ����ÿ�ζ���Ҫnewһ���µĶ���Ϊ��ʡ�ڴ棬
 * ��pool����ǰ��һЩ����ʹ�ú�黹
 */
public class Barrierpool {
	//���ڹ���������ж��������
    private static List<Barrier> pool = new ArrayList<>();
    //���г�ʼ�Ķ������
    public static final int initCount = 16;
    //�������������
    public static final int maxCOunt = 20;

    static {
        //��ʼ�����еĶ���
        for (int i = 0; i < initCount; i++) {
            pool.add(new Barrier());
        }
    }
    
    //�ӳ��л�ȡһ������ 
    public static Barrier getPool(){
        int size = pool.size();
        //��������ж���ſ�����
        if (size > 0) {
            //�Ƴ������ض���
           
            return pool.remove(size-1);
        }else {
            //����û�ж����� ֻ��new
            
            return new Barrier();
        }
    }


    //������黹������
    public static void setPool(Barrier barrier){
        if (pool.size() < maxCOunt) {
            pool.add(barrier);
            
        }
    }


}
