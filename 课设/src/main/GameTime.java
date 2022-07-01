package main;

public class GameTime {
	//计时
    private long beginTime;
    private long endTime;
    //时间差 
    private long differ;

    public GameTime() {
    }

    public void begin() {
        beginTime = System.currentTimeMillis();
    }

    public long differ() {
        endTime = System.currentTimeMillis();
        return differ = (endTime - beginTime) / 1000;
    }
    public long shortDiffer() {
        endTime = System.currentTimeMillis();
        return differ = (endTime - beginTime) / 100;
    }

}
