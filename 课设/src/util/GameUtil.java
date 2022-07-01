package util;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
public class GameUtil {
	//º”‘ÿÕº∆¨
	public static BufferedImage loadBufferedImage(String imgPath) {
		try {
			return ImageIO.read(new FileInputStream(imgPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
