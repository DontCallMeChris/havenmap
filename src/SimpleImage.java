import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;


public class SimpleImage {

	public static BufferedImage resize(BufferedImage img00, BufferedImage img10, BufferedImage img01, BufferedImage img11, int newW, int newH) {  
		int w = 100;  
		int h = 100;
		int miss = 0;
		BufferedImage dimg = new BufferedImage(w, h, 2);  
		Graphics2D g = dimg.createGraphics();  
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		if(img00 != null)
			g.drawImage(img00, 0, 0, newW, newH, 0, 0, w, h, null);
		else
			miss++;
		if(img10 != null)
			g.drawImage(img10, newW, 0, w, newH, 0, 0, w, h, null);
		else
			miss++;
		if(img01 != null)
			g.drawImage(img01, 0, newH, newW, h, 0, 0, w, h, null);
		else
			miss++;
		if(img11 != null)
			g.drawImage(img11, newW, newH, w, h, 0, 0, w, h, null);
		else
			miss++;
		if(miss>=4)
			return null;
		g.dispose();
		return dimg;  
	}

	private static BufferedImage preloadImage(String fileName) {
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				//return new BufferedImage(100, 100, 2); 
				return null;
			}
			BufferedImage img = ImageIO.read(file);

			return img;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//	private static boolean checkdataImage(int x, int y, int zoom) {
//		File _file;
//		_file = new File(zoom + "/tile_" + x*2 + "_" + y*2 + ".png");
//		if (_file.exists())
//			return true;
//
//		_file = new File(zoom + "/tile_" + (x*2 + 1) + "_" + y*2 + ".png");
//		if (_file.exists())
//			return true;
//
//		_file = new File(zoom + "/tile_" + x*2 + "_" + (y*2 + 1) + ".png");
//		if (_file.exists())
//			return true;
//
//		_file = new File(zoom + "/tile_" + (x*2 + 1) + "_" + (y*2 + 1) + ".png");
//		if (_file.exists())
//			return true;
//		System.out.println("No data:"+x+","+y+","+zoom+";");
//		return false;
//	}

//	private static int loadImage(int x, int y, int zoom) {
//
//
//
//		try {
//			BufferedImage img = resize(
//					preloadImage(zoom + "/tile_" + x*2 + "_" + y*2 + ".png"),
//					preloadImage(zoom + "/tile_" + (x*2 + 1) + "_" + y*2 + ".png"),
//					preloadImage(zoom + "/tile_" + x*2 + "_" + (y*2 + 1) + ".png"),
//					preloadImage(zoom + "/tile_" + (x*2 + 1) + "_" + (y*2 + 1) + ".png"),
//					50, 50);
//			if(img == null)
//				return 0;
//
//			String fileName = (zoom - 1) + "/tile_" + x + "_" + y + ".png";         
//			File file = new File(fileName); 
//			ImageIO.write(img, "png", file);
//			return 1;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return 0;
//	}

//	public static class Init{
//		public int min_x, max_x, min_y, max_y;
//
//		Init(){
//			min_x = 0;
//			max_x = 0;
//			min_y = 0;
//			max_y = 0;
//		}
//	}
//
//	private static Init init(int z){
//		Init i = new Init();
//		File folder = new File(String.valueOf(z));
//		System.out.println("Start init()");	
//		for (File f : folder.listFiles()) {
//			System.out.println("init: " + f.getName());
//			if(f.getName().indexOf("tile_")==0 && f.getName().indexOf(".png")>=0){
//				String[] filename = f.getName().substring(0, f.getName().indexOf(".png")).split("_");
//				if (i.min_x > Integer.parseInt(filename[1]))
//					i.min_x = Integer.parseInt(filename[1]);
//				if (i.max_x < Integer.parseInt(filename[1]))
//					i.max_x = Integer.parseInt(filename[1]);
//				if (i.min_y > Integer.parseInt(filename[2]))
//					i.min_y = Integer.parseInt(filename[2]);
//				if (i.max_y < Integer.parseInt(filename[2]))
//					i.max_y = Integer.parseInt(filename[2]);
//			}					    
//		}
//		System.out.println("Finish init()");
//		return i;
//	}
	
	private static int pair (int a){
		if(a%2 == 0)
			return ++a;
		else
			return --a;
		
	}
	
	private static boolean minpair(int a){
		if(a < pair(a))
			return true;
		return false;
	}
	
	
	private static int loadImage(int x, int y, int zoom) {


		try {			
			BufferedImage img = resize(
					preloadImage(zoom + "/tile_" + (minpair(x) ? x : pair(x)) + "_" + (minpair(y) ? y : pair(y)) + ".png"),
					preloadImage(zoom + "/tile_" + (minpair(x) ? pair(x) : x) + "_" + (minpair(y) ? y : pair(y)) + ".png"),
					preloadImage(zoom + "/tile_" + (minpair(x) ? x : pair(x)) + "_" + (minpair(y) ? pair(y) : y) + ".png"),
					preloadImage(zoom + "/tile_" + (minpair(x) ? pair(x) : x) + "_" + (minpair(y) ? pair(y) : y) + ".png"),
					50, 50);
			
			if(img == null)
				return 0;

			String fileName = (zoom - 1) + "/tile_" + (int)((minpair(x) ? x : pair(x))/2) + "_" + (int)((minpair(y) ? y : pair(y))/2) + ".png";
			File file = new File(fileName); 
			ImageIO.write(img, "png", file);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


	private static void DoIt(){
		
		
		System.out.println("Start");				
		for (int z = 9; z > 3; z--){
			
			File folder = new File(String.valueOf(z));
			System.out.println("Start init()");	
			for (File f : folder.listFiles()) {
				//System.out.println("init: " + f.getName());
				if(f.getName().indexOf("tile_")==0 && f.getName().indexOf(".png")>=0){
					String[] filename = f.getName().substring(0, f.getName().indexOf(".png")).split("_");
					loadImage(Integer.parseInt(filename[1]), Integer.parseInt(filename[2]), z);
				}					    
			}


		}
		System.out.println("Finish");




	}


	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		
//		URL myURL = new URL("http://example.com/");
//	    URLConnection myURLConnection = myURL.openConnection();
//	    myURLConnection.connect();

//		String url_open ="http://havenmap.rizen.pp.ua/";
//		java.awt.Desktop.getDesktop().browse(java.net.URI.create(url_open));
//
//
//		
//	    System.out.println("myURLConnection.connect");
		
		DoIt();
		
		
	}

}

