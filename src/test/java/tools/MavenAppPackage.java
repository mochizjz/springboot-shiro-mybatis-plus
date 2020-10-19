/**
 * 
 */
package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * <pre>
 * 提交要更新的文件 使用方法：
 *  1.配置local.properties文件
 *  2.将要提取的文件路径放到 filelist.txt，
 *   支持的3种形式（******代表任意字符）：
 *    ******DHSystem/prpins/src/com/sinosoft/prpall/blsvr/cb/BLCPolicy.java
 *    ******eclipseWorkSpace/DH-prpins/src/com/sinosoft/prpall/blsvr/cb/BLCPolicy.java 
 *    DH-prpins/src/com/sinosoft/prpall/blsvr/cb/BLCPolicy.java 
 *    再处理每一行时，如果有DHSystem或eclipseWorkSpace就截取这两种字符后面的那些字符处理，
 *    如果没有DHSystem或eclipseWorkSpace，就取整行进行处理
 *  3.运行本类 
 *  4.在eclipseWorkSpace/archive/目录下得到提取的文件， 
 *  5.将eclipseWorkSpace/archive/ 目录下的所有文件打包
 * </pre>
 * 
 * @author ★LiuPing
 * @modified: ☆LiuPing(2008-7-25 下午04:23:25): <br>
 */
public class MavenAppPackage {

	private static String eclipseWorkSpace = null;// 斜杠结尾
	private static String fileName = "filelist.txt";
	private static File delfile = null;
	private static File addfile = null;
	private static File bakFile = null;
	// key = svn log路径项目名称 value = 本地项目名称
	private static Map<String,String> projectMap = new HashMap<String,String>();
	private static Map<String,String> jarConfigMap = new HashMap<String,String>();
	static{
		// 加载配置文件
		loadPorperties();
	}

	private static void interFiles() {
		// svn文件清单
		InputStream fr = MavenAppPackage.class.getResourceAsStream(fileName);
		BufferedReader in = new BufferedReader(new InputStreamReader(fr));
		FileControl fc = new FileControl();
		// 清空原来的archive文件夹
		File folderPath = new File(eclipseWorkSpace,"archive");
		fc.delFolder(folderPath);
		folderPath.mkdirs();// 创建文件夹
		bakFile = new File(eclipseWorkSpace+"archive/bakfile.sh");
		addfile = new File(eclipseWorkSpace+"archive/addList.sh");
		delfile = new File(eclipseWorkSpace+"archive/delfilelist.sh");
		File filelist = new File(eclipseWorkSpace+"archive/"+fileName);
		String oneLine = new String();

		try{
			if( !bakFile.exists()) bakFile.createNewFile();// 创建文件列表
			if( !filelist.exists()) filelist.createNewFile();// 创建文件列表
			while(( oneLine = in.readLine() )!=null){

				// System.out.println(oneLine);
				FileWriter fwr = new FileWriter(filelist.getPath(),true);
				if(oneLine==null||oneLine.length()<2) continue;
				if(oneLine.startsWith("//")) continue;
				if(oneLine.indexOf("/")<0) continue;
				boolean isAdd = false;// 是否是添加的文件
				if(oneLine.contains("已添加 :")||oneLine.contains("已增加 :")||oneLine.contains("Added :")||oneLine.contains("	A ")){
					isAdd = true;
				}
				// 将svn 文件转换为当前路径
				oneLine = replaceToLocalPath(oneLine);
				String targetProjectName = getTargetProjectName(oneLine);
				if(oneLine==null||targetProjectName==null){
					continue;
				}
				if(oneLine.indexOf("/")<0) continue;
				if(targetProjectName.endsWith(".jar")){
					// 复制jar文件
					copyJarFile(targetProjectName);
					continue;
				}
				String localProjectName = oneLine.substring(0,oneLine.indexOf("/"));
				String targetFilePath = oneLine.replaceAll("target","WEB-INF").replaceAll("misc","WEB-INF").replaceAll("lib.*/","lib/")
						.replaceAll(localProjectName,targetProjectName).replaceAll("src/main/webapp/","").replaceAll("src/main/resources/","WEB-INF/classes/");// 目标路径

				copySRCFile(oneLine,targetFilePath,localProjectName);// 先复制源文件
				copyFile(oneLine,targetFilePath);

				// java文件不读取
				if( !targetFilePath.endsWith(".java")){
					fwr.write(targetFilePath+"\r\n");
					fwr.close();
				}
				if(isAdd){
					if( !addfile.exists()) addfile.createNewFile();// 创建添加的文件列表
					// 往添加文件列表中写入
					writeToAddList(oneLine,targetFilePath);
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}

	}

	private static void copyJarFile(String targetProjectName) {
		String soruceFilePath = jarConfigMap.get(targetProjectName+".srcpath");
		if(soruceFilePath==null) return;// jar只复制一次
		jarConfigMap.remove(targetProjectName+".srcpath");//
		String soruceFile = soruceFilePath+targetProjectName;
		String[] toPaths = jarConfigMap.get(targetProjectName+".topath").split(",");
		String targetFile = null;
		for(String toPath:toPaths){
			targetFile = toPath+targetProjectName;
			copyFile(soruceFile,targetFile);
		}
	}

	/**
	 * 将一行文件替换成本地标准的文件
	 * @param oneLine
	 */
	private static String replaceToLocalPath(String oneLine) {

		for(String key:projectMap.keySet()){
			String svnProjectName = key;
			String webappRootName = "";// 项目部署的根目录
			if(key.contains(",")){
				String[] keys = key.split(",");
				svnProjectName = keys[0];
				webappRootName = keys[1]+"/";
			}

			// 如果包含svn 相关项目名称
			if(oneLine.contains(svnProjectName)){
				// 去掉svn log 前字符
				int idx = oneLine.indexOf(svnProjectName+"/");
				if(idx<0) return "";
				oneLine = oneLine.substring(idx);
				if(oneLine.indexOf("/")<0) return oneLine;

				String localProjectName = projectMap.get(key);

				if(localProjectName==null){
					System.err.println("#本行格式不正确，未进行处理："+oneLine);
					return null;
				}

				if(oneLine.endsWith(".java")){// 转换 .java .class
					String WEB_INF = webappRootName+"WEB-INF/classes/";

					oneLine = oneLine.replaceAll("src/main/java/",WEB_INF);
					oneLine = oneLine.replaceAll("src/main/",WEB_INF);
					oneLine = oneLine.replaceAll("src/interf/",WEB_INF);
					oneLine = oneLine.replaceAll("src/",WEB_INF);

					oneLine = oneLine.replaceAll("java_src/main/",WEB_INF);
					oneLine = oneLine.replaceAll("java_src/remote/",WEB_INF);
					oneLine = oneLine.replaceAll("java_src/test/",WEB_INF);

					oneLine = oneLine.replaceAll(".java",".class");
					// 如果当前文件不存在，则换为 maven 的 target 目录
					File file = new File(eclipseWorkSpace+svnProjectName+oneLine);
					if( !file.exists()){
						oneLine = oneLine.replaceAll(WEB_INF,"target/classes/");
					}
				}
				oneLine = oneLine.substring(oneLine.indexOf("/"));// 去掉svn log 项目名称，最后加上本地项目名称
				String filePath = svnProjectName+oneLine;
				return filePath;
			}
		}
		return oneLine;
	}

	/**
	 * 获取目标项目名称
	 * @param oneLine
	 * @return
	 * @modified: ☆qianxin(2015年11月20日 下午5:48:05): <br>
	 */
	private static String getTargetProjectName(String oneLine) {

		for(String key:projectMap.keySet()){
			String svnProjectName = key;
			if(key.contains(",")){
				String[] keys = key.split(",");
				svnProjectName = keys[0];
			}
			// 如果包含svn 相关项目名称
			if(oneLine.contains(svnProjectName)){
				// 去掉svn log 前字符
				oneLine = oneLine.substring(oneLine.indexOf(svnProjectName+"/"));
				if(oneLine.indexOf("/")<0) return oneLine;

				String localProjectName = projectMap.get(key);

				if(localProjectName==null){
					System.err.println("#本行格式不正确，未进行处理："+oneLine);
					return null;
				}
				return localProjectName;
			}
		}
		return null;
	}

	/**
	 * 复制src里面的文件到 archive/SRCFile/
	 * @param oneLine
	 * @param projectName
	 */
	private static void copySRCFile(String filefullName,String targetFilePath,String localProjectName) {

		File srcFile = new File(eclipseWorkSpace+filefullName);
		if(srcFile.isDirectory()) return;
		if(filefullName.indexOf("classes/test")>0) return;// 测试文件不打包
		if( !srcFile.isFile()){
			System.err.println("#源文件不存在：【"+eclipseWorkSpace+filefullName+"】");
			return;
		}
		if(filefullName.endsWith(".jar")) return;

		String filepath = eclipseWorkSpace+"archive/SRCFile/"+targetFilePath.substring(0,targetFilePath.lastIndexOf("/"));
		File saveFloder = new File(filepath);
		if( !saveFloder.exists()){
			saveFloder.mkdirs();// 创建文件夹
		}

		File copyFile = new File(eclipseWorkSpace+"archive/SRCFile/"+targetFilePath);

		FileControl fc = new FileControl();
		fc.copyOneFile(srcFile,copyFile);
		// System.out.println("复制SRC文件到=="+copyFile.getPath());

	}

	private static void copyFile(String filefullName,String targetFilePath) {
		targetFilePath = targetFilePath.replaceAll("src/main/webapp/","");
		File srcFile = new File(eclipseWorkSpace+filefullName);
		if(srcFile.isDirectory()) return;
		if(filefullName.indexOf("classes/test")>0) return;// 测试文件不打包
		if( !srcFile.isFile()){
			System.err.println("#目标文件不存在：【"+eclipseWorkSpace+filefullName+"】");
			if(filefullName.indexOf(".")>0){
				writeToDelList(targetFilePath);
			}
			return;
		}

		String filepath = eclipseWorkSpace+"archive/"+targetFilePath.substring(0,targetFilePath.lastIndexOf("/"));
		File saveFloder = new File(filepath);
		if( !saveFloder.exists()){
			saveFloder.mkdirs();// 创建文件夹
		}

		File copyFile = new File(eclipseWorkSpace+"archive/"+targetFilePath);
		if(filefullName.endsWith(".class")){
			String dirPath = eclipseWorkSpace+filefullName.substring(0,filefullName.lastIndexOf("/"));
			File dirFile = new File(dirPath);
			String indexName = filefullName.substring(filefullName.lastIndexOf("/")+1);
			indexName = indexName.substring(0,indexName.lastIndexOf("."));
			for(File file:dirFile.listFiles()){
				String fileName = file.getName();

				if(fileName.startsWith(indexName)&&fileName.contains("$")){
					File target = new File(filepath+"/"+fileName);
					FileControl fc = new FileControl();
					fc.copyOneFile(file,target);
					System.out.println("复制文件=="+file.getPath());
				}
			}
		}

		FileControl fc = new FileControl();
		fc.copyOneFile(srcFile,copyFile);
		writeToBakList(targetFilePath);
		System.out.println("复制文件=="+srcFile.getPath());
	}

	@SuppressWarnings({"rawtypes"})
	private static void loadPorperties() {
		InputStream in = new MavenAppPackage().getClass().getResourceAsStream("local.properties");
		Properties props = new Properties();
		try{
			props.load(in);
		}catch(IOException e){
			e.printStackTrace();
		}
		Iterator propsIt = props.keySet().iterator();
		while(propsIt.hasNext()){
			String realProject = (String)propsIt.next();
			if(realProject.equals("eclipseWorkSpace")){
				eclipseWorkSpace = props.getProperty(realProject);
				continue;
			}else if(realProject.indexOf(".jar.")>0){
				jarConfigMap.put(realProject,props.getProperty(realProject));
				continue;
			}
			String localProjiec = props.getProperty(realProject);
			projectMap.put(localProjiec,realProject);
		}
	}

	private static void writeToBakList(String filefullName) {
		String fileName = filefullName;

		try{
			FileWriter fw = new FileWriter(bakFile.getPath(),true);
			fw.write("cp "+fileName+" "+fileName+".bak\n");
			fw.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	private static void writeToDelList(String filefullName) {
		String fileName = filefullName;// .substring(filefullName.indexOf("/")+1);

		try{
			FileWriter fw = new FileWriter(delfile.getPath(),true);
			fw.write("rm "+fileName+"\n");
			fw.close();
		}catch(IOException e){
			e.printStackTrace();
		}

	}

	private static void writeToAddList(String filefullName,String targetProjectName) {
		// String fileName=filefullName.substring(filefullName.indexOf("/")+1);

		try{
			FileWriter fw = new FileWriter(addfile.getPath(),true);
			fw.write("chmod 755 "+targetProjectName+"\n");
			fw.close();
		}catch(IOException e){
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {
		MavenAppPackage.interFiles();
		System.out.println("处理完成，文件保存在"+eclipseWorkSpace+"archive/");
		java.awt.Desktop.getDesktop().open(new File(eclipseWorkSpace+"archive"));
	}

}
