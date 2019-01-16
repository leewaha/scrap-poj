package com.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.nio.CharBuffer;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.entity.pojo.It06Qcodeinfo;

import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;

public class ClientUtil {
	private static final String BASE_URL = "http://117.184.199.40:8877/authenticationService/httpserver.do";
	private static String qrid = "";
	private static HttpClient client = HttpClientBuilder.create().build();
	private static Map<String, String> BASE_DATA = new HashMap<>();
	static {
		BASE_DATA.put("syscode", "SAIC_YWXT_3100Y8");
		BASE_DATA.put("authcode", "ca45d017211f4dc885bf8130343304b9");
	}
	private static JSONObject jbo = null;
	static JSONObject json = new JSONObject();
	static Map<String, String> param1 = new HashMap<>();
	static Map<String, String> param2 = new HashMap<>();

	public static void main(String[] args) {

		int[] steps = new int[] {1,2,3};
		try {
			for(int i :steps) {
				if(i==1) {
					step1Method();
				}else if(i==2) {
					step2Method();
				}else if(i==3) {
					step3Method();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void step1Method() throws Exception {
		param1.putAll(BASE_DATA);
		param1.put("businesstype", "003");
		// content
		param2.put("qrtype", "1101");
		param2.put("rettype", "1");

		json.put("message_header", param1);
		json.put("message_content", param2);
		System.out.println("-------------------------------- post 参数设置完毕");
		/**
		 * 获得业务请求唯一标识
		 */
		String step1 = postExchange(json.toString());
		System.out.println("----------------------------------响应post信息");
		jbo = JSONObject.fromBean(step1);
		qrid = (String) JSONObject.fromBean(jbo.get("message_content")).get("qrid");
		if (StringUtils.isEmpty(qrid)) {
			throw new Exception("---------------------- qrid 错误,登陆二维码返回失败 ！！");
		}
		decodeBase64img(JSONObject.fromBean(jbo.get("message_content")).getString("qrimage"), "d://1.jpg");
	}

	private static void step2Method() throws Exception {
		System.out.println("--------------- 开始查询扫码登录用户授权状态 ！！");
//		System.out.println(jbo.get("message_content"));
		param1.clear();
		param2.clear();
		param1.putAll(BASE_DATA);
		param1.put("businesstype", "004");
		// content
		param2.put("qrtype", "1101");
		param2.put("qrid", qrid);

		json.put("message_header", param1);
		json.put("message_content", param2);
		boolean breakWhile = false;
		while (true) {
			String step2 = postExchange(json.toString());
			jbo = JSONObject.fromBean(step2);
			Object msheader2 = jbo.get("message_header");
			if (!"0".equals(JSONObject.fromBean(msheader2).get("errorCode"))) {
				throw new Exception(
						"---------------------- 查询登陆授权信息 失败，错误编号 : " + JSONObject.fromBean(msheader2).get("errorCode"));
			}
			Object mscontnet2 = jbo.get("message_content");
			String operflag = JSONObject.fromBean(mscontnet2).getString("operflag");
			switch (operflag) {
			case "0":
				breakWhile = true;
				break;
			case "1":
				// 三秒后再次查询
				Thread.sleep(5000);
				break;

			default:
				break;
			}
			// 授权成功 结束轮询
			if (breakWhile) {
				System.out.println("------------entity  --- :"+JSONObject.fromObject( jbo.get("message_content")).get("entity").toString().length());
				System.out.println("------------tragerSn  --- :"+JSONObject.fromObject( jbo.get("message_content")).get("tragerSn").toString().length());
				System.out.println("************** 二维码登录 授权完成--------------");
//				System.out.println("************ 授权成功,轮询退出 *************");
				break;
			}else {
				System.out.println(System.currentTimeMillis() + "----------待用户授权。。。");
			}
		}

	}

	private static void step3Method() throws Exception {
		param1.clear();
		param2.clear();
		param1.putAll(BASE_DATA);
		param1.put("businesstype", "011");
		// content
		//1402电子营业执照信息、1403电子营业执照实体、1404电子营业执照公钥、1405授权信息、1406影印件和电子营业执照信息
		param2.put("qrtype", "1405");
		param2.put("qrid", qrid);

		json.put("message_header", param1);
		json.put("message_content", param2);

		String step3 = postExchange(json.toString());
		
		FileOutputStream fos = new FileOutputStream("resultdata1.json");
		fos.write(step3.getBytes());
		fos.close();
		
		jbo = JSONObject.fromBean(step3);
		try {
			Object wscontent3 = jbo.get("message_content");
//			System.out.println("------------licenceentity  --- :"+JSONObject.fromObject( jbo.get("message_content")).get("licenceentity").toString().length());
//			System.out.println("************ 授权登录用户企业信息 " + JSONObject.fromObject(wscontent3));
//			decodeBase64img(JSONObject.fromBean(jbo.get("message_content")).getString("image"), "d://2.jpg");
			
		} catch (Exception e) {
			System.out.println("***************** 响应头信息 -- " + JSONObject.fromObject(jbo.get("message_header")));
			System.out.println("***************** 响应体信息 -- " + JSONObject.fromObject(jbo.get("message_content")));
			e.printStackTrace();
		}
//		Object wscontent3 = jbo.get("message_content");
//		Iterator keys = json.fromObject(wscontent3).keys();
//		while(keys.hasNext()) {
//			Object next = keys.next();
//			System.out.println("----------------response body, the key : "+next);
//			//jbo.get(next);
//		}
		It06Qcodeinfo	it06obj = new It06Qcodeinfo();
		
		 JSONObject mscontent = JSONObject.fromObject(jbo
					.get("message_content"));
			
//			System.out.println("------------------- response content image size : "+mscontent.getString("image"));
			//json map<jsonkey,jsonval>
			Map<String, String> kvpair = new HashMap<>();
			Iterator<?> keys = mscontent.keys();
			while (keys.hasNext()) {
				String jsonkey = (String) keys.next();
				System.out.println("--------- json key : "+jsonkey);
				String jsonvalue = mscontent.getString(jsonkey);
				kvpair.put(jsonkey.replace("_", "").toLowerCase(), jsonvalue);
			}

			System.out
					.println("--------------------------- 授权用证详情  : 授权书截止日期 ~"
							+ mscontent.getString("entname"));
			//method map <lowername,method>
			Map<String, Method> methodMap = new HashMap<String, Method>();
			Class<?> it06info = it06obj.getClass();// this.getClass().getClassLoader().loadClass("com.tf.common.pojo.It06Qcodeinfo");
			// System.out.println(it06info.getSimpleName()+","+it06info.getSimpleName());
			for (Method method : it06info.getMethods()) {
				String methodname = method.getName();
				if (methodname.startsWith("set")) {
					String lowerMeName = methodname.substring(3).toLowerCase();
					methodMap.put(lowerMeName, method);
				}
			}
			Method method = null;
			for (Entry<?, ?> entry : kvpair.entrySet()) {
				if (methodMap.containsKey(entry.getKey())) {

					method = methodMap.get(entry.getKey());
					if (entry.getValue()!=null && !StringUtils.isEmpty((String)entry.getValue())) {
						method.invoke(it06obj, entry.getValue());
						System.out.println("-----------成功调用赋值方法"+method.getName()+",赋值 ： "+entry.getValue());
					} else
						System.out.println("-----------"+entry.getKey()+" 这里是空值,将不会调用赋值方法 : "+method.getName());
				} else {
					System.out.println("------------ "+entry.getKey()+",这是一个pojo上没有的字段 !! ----- ");
				}

			}
			
		
		System.out.println("************ 流程结束 !!");
System.out.println("response obj info : "+it06obj);
	}

	public ClientUtil() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * http请求接口
	 * 
	 * @param json
	 * @return
	 */
	static String postExchange(String jsonStr) throws Exception {
		HttpPost httpPost = new HttpPost(BASE_URL);
		StringEntity entity = new StringEntity(jsonStr, "UTF-8");
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		httpPost.setEntity(entity);
		HttpResponse resp = client.execute(httpPost);
		HttpEntity hpentity = resp.getEntity();
		// System.out.println(hpentity.getContentType());
		InputStream input = hpentity.getContent();
		byte[] b = new byte[1024];
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		int len = 0;
		while ((len = input.read(b)) > -1) {
			os.write(b, 0, len);
			// System.out.println("********More info----");
		}
		System.out.println("response info : " + new String(os.toByteArray(), "UTF-8"));
				
		return new String(os.toByteArray(), "UTF-8");
	}

	/**
	 * 图片解码
	 * 
	 * @param code64img
	 * @param outputpath
	 */
	public static void decodeBase64img(String code64img, String outputpath) {
//		byte[] filebyte = Base64.getDecoder().decode(code64img);
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] filebyte =decoder.decodeBuffer(code64img);
			FileOutputStream fo = new FileOutputStream(new File(outputpath));
			int len = 0;
			byte[] buff = new byte[1024];
			InputStream istream = new ByteArrayInputStream(filebyte);
			while ((len = istream.read(buff)) > 0) {
				fo.write(buff, 0, len);
			}
			fo.flush();
			fo.close();
			istream.close();
			System.out.println("-------文件接收完毕 保存位置  : " + outputpath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 对图片进行base64编码
	 * @param file
	 */
	public static void fileEncode(File file) {
		try {
			FileReader fr = new FileReader(file);

			CharBuffer cbuf = null;// [2048] ;
			char[] cbuf1 = new char[2048];
			StringBuffer str = new StringBuffer();
			while (fr.read(cbuf1) > -1) {
				str.append(cbuf1.toString());
			}
			System.out.println("------------base64Encode : " + str);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
