package ins.webeye.common.utils;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.SocketException;

public class FtpUtils {

    private static Logger log  = LoggerFactory.getLogger(FtpUtils.class);

    /**
     * 路径分隔符
     */
    public static final String SPT = "/";

    public static void main(String[] args) throws FileNotFoundException {
        String username = "ftpuser";
        String password = "ftpuser@123";

        String remote = "/changan/data/u/cms/www/202101/04171646ugse.png";
        String filePath = "D:\\work\\IdeaProjects\\changancms\\job_twang\\jeecms-parent-official-20210104\\jeecms-front\\src\\main\\webapp\\u\\cms\\www\\202101\\04171646ugse.png";
        InputStream in = new FileInputStream(new File(filePath));

        store(username,password,remote,in);
    }


    private static int store(String username,String password,String remote, InputStream in) {
        try {
            FTPClient ftp = getClient(username,password);
            if (ftp != null) {
                String filename =  remote;
                String name = FilenameUtils.getName(filename);
                String path = FilenameUtils.getFullPath(filename);
                if (!ftp.changeWorkingDirectory(path)) {
                    String[] ps = StringUtils.split(path, SPT);
                    String p = SPT;
                    ftp.changeWorkingDirectory(p);
                    for (String s : ps) {
                        p += s + SPT;
                        if (!ftp.changeWorkingDirectory(p)) {
                            ftp.makeDirectory(s);
                            ftp.changeWorkingDirectory(p);
                        }
                    }
                }
                ftp.storeFile(name, in);
                ftp.logout();
                ftp.disconnect();
            }
            return 0;
        } catch (SocketException e) {
            log.error("ftp store error", e);
            return 3;
        } catch (IOException e) {
            log.error("ftp store error", e);
            return 4;
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }


    private static FTPClient getClient(String username,String password) throws SocketException, IOException {
        String ip = "47.99.183.216";
        FTPClient ftp = new FTPClient();
        ftp.enterLocalPassiveMode();
//        FTPClientConfig config = new FTPClientConfig();
//        config.setLenientFutureDates(true);
//        ftp.configure(config);
        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        ftp.setDefaultPort(21);
        ftp.connect(ip);
        int reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            log.error("FTP server refused connection: {}",ip);
            ftp.disconnect();
            return null;
        }
        if (!ftp.login(username, password)) {
            System.out.println("FTP server refused login: {"+ip+"}, user: {"+username+"}");
            ftp.logout();
            ftp.disconnect();
            return null;
        }
        ftp.setControlEncoding("UTF-8");
        ftp.setFileType(FTP.BINARY_FILE_TYPE);
//        ftp.enterLocalPassiveMode();
        return ftp;
    }

}
