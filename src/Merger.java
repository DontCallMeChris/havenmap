import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;


public class Merger {

	static Map<String, File> maphashes;
	static String root_folder_path = "/eclipsews/simpleimage/newmaps/";
	//static String root_folder_path = "/eclipsews/simpleimage/newmaps_sort/mapz/";
	static File root_folder = new File(root_folder_path);
	static String basic_folder_path = "/eclipsews/simpleimage/9/";
	static File basic_folder = new File(basic_folder_path); 


	/**
	 * @param args
	 * @throws IOException 
	 * @throws NoSuchAlgorithmException 
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		
		//copy(root_folder, new Coord(-77, 79), new Coord(-27, 129));
		
		Init();
	}

	private static void Init() throws NoSuchAlgorithmException, IOException {
		maphashes = new TreeMap<String, File>();
		CreateMapHash(basic_folder);

		File[] files = root_folder.listFiles();
		
		
		
		
		for(File folder : files)
			if (folder.isDirectory()) {
				System.out.println(folder.getName() + " is folder and length = " + folder.listFiles().length);
				if (folder.listFiles().length < 21)
					deleteFile(folder);
			}
		

		//		Arrays.sort(files, new Comparator<File>(){
		//		    public int compare(File f1, File f2)
		//		    {
		//		        return Long.valueOf(f1.lastModified()).compareTo(f2.lastModified());
		//		    } });

		for(File folder : files){
			if(folder.isDirectory()){
				System.out.println(folder.getName() + " is folder and length = " + folder.listFiles().length);
				
				if(folder.listFiles().length > 20)
					if(Merge(folder)){
						System.out.println("Merget: " + folder.getName() + " \n");
						deleteFile(folder);
					}
				//break;
			}
		}
	}
	
	private static void copy (File folder, Coord copy_from, Coord copy_to){
		for(File file : folder.listFiles()){
			if(file.isDirectory())
				continue;
			if(file.getName().indexOf("tile_")!=0 || file.getName().indexOf(".png")<0)
				continue;
			String[] s = file.getName().substring(0, file.getName().indexOf(".png")).split("_");
			Coord workCoord = new Coord(Integer.parseInt(s[1]), Integer.parseInt(s[2]));
			if(workCoord.isect2(copy_from, copy_to)){
				File newfile = new File(root_folder.getAbsoluteFile() + "/copy/tile_" 
						+ String.valueOf(workCoord.x) + "_"
						+ String.valueOf(workCoord.y) + ".png");
				boolean success = file.renameTo(newfile);
				if (!success) {
					System.out.println("cant rename file success: " + newfile);
				}
			}
		}		
	}
	
	private static void deleteFile(File file)
	  {
	    if(!file.exists())
	      return;
	    if(file.isDirectory())
	    {
	      for(File f : file.listFiles())
	    	 deleteFile(f);
	      file.delete();
	    }
	    else
	    {
	      file.delete();
	    }
	  }

	private static boolean Merge(File folder){
		System.out.println("Merge: " + folder);
		Coord relativeCoords = null;
		Coord lastrelativeCoords = null;
		int findoutmatch = 0;
		for(File file : folder.listFiles()){
			//			System.out.println("Merge: get file in forder: " + file);
			if(file.getName().indexOf("tile_")!=0 || file.getName().indexOf(".png")<0)
				continue;
			//			System.out.println("Merge: get md5 for: " + file);
			String md5 = null;
			try {
				md5 = getmd5(file);
				//				System.out.println("Merge: try get md5: " + file + " " + md5);
			} catch (NoSuchAlgorithmException | IOException e) {
				//				System.out.println("CATCH" + e);
				e.printStackTrace();
			}
			if(md5!=null){
				if(md5.matches("2fe2c64c739ffb1d6a9fbdfbc9fa6dd3")) // water
					continue;
				if(md5.matches("e90b2db63415d74804ff2d6927aa5b11")) // some forest
					continue;
				if(md5.matches("25ce0ac0ee4a998e53b4743d02fc58fd")) // some forest
					continue;
				if(md5.matches("2836b8e5da9838cd11088cf020be2291")) // some forest
					continue;
				if(md5.matches("f4802655316d99a42a6a17195163b42")) // some forest
					continue;
				if(md5.matches("f5ca7750e1822e1df38e87f9e02ab5f")) // some forest
					continue;
				if(md5.matches("ad2a60c3275ca6779fb175ee3fb21460")) // some forest
					continue;
				if(md5.matches("7bc2852aeb1a4db3261079a41d58186")) // some forest
					continue;
				if(md5.matches("f5d2f0e5b21b6fcc89cc83a75f53feb1")) // some forest
					continue;
				if(md5.matches("f51c03f2a5717e8634737234d7444956")) // some forest
					continue;
				if(md5.matches("23a6eee4de9e0456fe986dacf7a1cead")) // some forest
					continue;
				if(md5.matches("2836b8e5da9838cd11088cf020be2291")) // some forest
					continue;
				if(md5.matches("ea9ce48d05ca4a8ccc30733805f339f2")) // some forest
					continue;
				if(md5.matches("f51c03f2a5717e8634737234d7444956")) // some forest
					continue;
				if(md5.matches("99bd099f2ff14f891bf686b9c773b39f")) // some forest
					continue;
				if(md5.matches("5936252bb30695b203a3a69068a29307")) // some forest
					continue;
				if(md5.matches("7189caf87c5fd7a1b636982fb7f05fd6")) // some forest
					continue;
				if(md5.matches("bbec8c5524b5013d22c3dd6a3626be2a")) // some forest
					continue;
				if(md5.matches("2057e28839eddbc0cb8c206404f62882")) // some forest
					continue;
				if(md5.matches("602ac2f9147d9b81357e347767648cfb")) // some forest
					continue;
				if(md5.matches("74df6a1f797b2b0b14733d58d20f5efc")) // some forest
					continue;
				if(md5.matches("65e9f39d8fb34b202e4318066d3dc5c4")) // some forest
					continue;
				if(md5.matches("36343f261d1b95733073867acc250004")) // some forest
					continue;
				if(md5.matches("f321f1fdaa387239fa707e33d5bff0dd")) // some forest
					continue;
				if(md5.matches("b891a139aba97ce0ffc54d665dc4c330")) // some forest
					continue;

				if(md5.matches("b599b8f8c819ee339a154eaec172a2aa")) // some forest
					continue;
				if(md5.matches("2a8b6576149f88825e3d174afecb40f5")) // some forest
					continue;
				if(md5.matches("5d092dc7aafcab70f68ad63047b483c9")) // some forest
					continue;
				if(md5.matches("b599b8f8c819ee339a154eaec172a2aa")) // some forest
					continue;








				if(maphashes.get(md5)!=null){
					System.out.println("Find match out: " + file.getName() + " - " + md5);
					findoutmatch++;
					File f = maphashes.get(md5);
					String[] s = f.getName().substring(0, f.getName().indexOf(".png")).split("_");
					Coord realCoord = new Coord(Integer.parseInt(s[1]), Integer.parseInt(s[2]));
					s = file.getName().substring(0, file.getName().indexOf(".png")).split("_");
					Coord workCoord = new Coord(Integer.parseInt(s[1]), Integer.parseInt(s[2]));

					relativeCoords = realCoord.sub(workCoord);
					if(lastrelativeCoords == null)
						lastrelativeCoords = relativeCoords;
					System.out.println("Coord: " + realCoord + ";" + workCoord + ";" + relativeCoords);
					if(lastrelativeCoords.sub(relativeCoords) == new Coord(0,0)) {
						System.out.println("Error in lastrelativeCoords != relativeCoords");
						return false;
					}
				}
			}
		}
		if(relativeCoords==null || findoutmatch < 6){
			return false;
		}else{
			for(File file : folder.listFiles()){
				if(file.getName().indexOf("tile_")==0 && file.getName().indexOf(".png")>=0){

					String[] filename = file.getName().substring(0, file.getName().indexOf(".png")).split("_");

					File newfile = new File(basic_folder.getAbsoluteFile() + "/tile_" 
							+ String.valueOf(relativeCoords.x + (Integer.parseInt(filename[1]))) + "_"
							+ String.valueOf(relativeCoords.y + (Integer.parseInt(filename[2]))) + ".png");

					if(newfile.exists()){
						//System.out.println("file exists: " + newfile);
						if(newfile.lastModified()>file.lastModified()){
							file.delete();
							System.out.println("File: " + file + " is older");
							continue;
						}							

						newfile.delete();
					}
					// Rename file (or directory)

					boolean success = file.renameTo(newfile);
					if (!success) {
						// File was not successfully renamed
						System.out.println("cant rename file success: " + newfile);
					}
				}
			}
			folder.deleteOnExit();
		}
		return true;
	}

	private static boolean CreateMapHash(File folder) {
		for(File file : folder.listFiles()){
			if(file.getName().indexOf("tile_")!=0 || file.getName().indexOf(".png")<0)
				continue;
			String md5 = null;
			try {
				md5 = getmd5(file);
			} catch (NoSuchAlgorithmException | IOException e) {
				e.printStackTrace();
			}
			if(md5!=null){
				maphashes.put(md5, file);
				//System.out.println("Hash put: " + file.getName() + " - " + md5);
			}
		}
		return false;
	}

	private static String getmd5(File f) throws NoSuchAlgorithmException, IOException {
		String md5 = null;
		if (f.exists()) {
			MessageDigest digest = MessageDigest.getInstance("MD5");                
			InputStream is = new FileInputStream(f);                
			byte[] buffer = new byte[819200];                
			int read = 0;                
			while ((read = is.read(buffer)) > 0) {
				digest.update(buffer, 0, read);
			}                
			byte[] md5sum = digest.digest();                
			BigInteger bigInt = new BigInteger(1, md5sum);               
			md5 = bigInt.toString(16);
			is.close();
		}
		return md5;
	}
}