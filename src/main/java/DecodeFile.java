import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.Base64;

import net.sf.json.JSONObject;

public class DecodeFile {
public static void main(String[] args) {
	try {
		InputStream ins=	DecodeFile.class.getResourceAsStream("resultdata1.json");
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		int len;
		byte[] buff = new byte[1024];
		
		while((len=ins.read(buff, 0, 1024))>0) {
			bao.write(buff, 0, len);
		}
		String json = new String(bao.toByteArray(),"UTF-8");
		Object content= JSONObject.fromObject(json).get("message_content");
		String image= JSONObject.fromObject(content).getString("image");
		System.out.println("image size:"+image.length()+" | "+image);
		byte [] imgbyte = Base64.getDecoder().decode(image);
		
		FileWriter fw = new FileWriter(new File("e://img.jpg"));
		fw.write(imgbyte.toString());
		
		fw.close();
		bao.close();
		ins.close();
//		int len1;
//		byte[] buff1 = new byte[1024];
//		
//		while((len1=ins.read(buff1, 0, 1024))>0) {
//			bao.write(buff, 0, len);
//		}
		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
