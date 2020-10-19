/******************************************************************************
* CREATETIME : 2020年8月13日 下午4:57:12
******************************************************************************/
package ins.webeye.project.eye.api.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import ins.webeye.common.utils.HttpUtils;
import ins.webeye.project.eye.vo.OcrVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class EyeALiCloudApiService {

    @Value("${aliyun.ocr.host}")
    private String host;
    @Value("${aliyun.ocr.path}")
    private String path;

    /**
     * 阿里ocr身份证识别
     * @param ocrVo
     * @return
     */
    public JSONObject getOcrAuthInfo(OcrVo ocrVo) throws Exception {
        String appcode = ocrVo.getAppcode();
        String imgFile = ocrVo.getImageURL();
        String method = "POST";
        JSONObject res_obj = new JSONObject();

        try {
            Map<String, String> headers = new HashMap<String, String>();
            //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
            headers.put("Authorization", "APPCODE " + appcode);
            //根据API的要求，定义相对应的Content-Type
            headers.put("Content-Type", "application/json; charset=UTF-8");

            Map<String, String> querys = new HashMap<String, String>();
            // 对图像进行base64编码
            String imgBase64 = null;

            //区分图片类型是url还是base64
            if(null!=ocrVo.getImageType()&&ocrVo.getImageType().equals("imgBase64")){
                imgBase64 = ocrVo.getImageURL();
            }else{
                imgBase64 = img_base64(imgFile);
            }

            //configure配置
            JSONObject configObj = new JSONObject();
            configObj.put("side", ocrVo.getSide());

            String config_str = configObj.toString();

            // 拼装请求body的json字符串
            JSONObject requestObj = new JSONObject();
            requestObj.put("image", imgBase64);
            if(configObj.size() > 0) {
                requestObj.put("configure", config_str);
            }
            String bodys = requestObj.toString();

            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            int stat = response.getStatusLine().getStatusCode();
            if(stat != 200){
                String errMsg = EntityUtils.toString(response.getEntity());
                log.error("Http code:{}",stat);
                log.error("http header error msg:{}",response.getFirstHeader("X-Ca-Error-Message"));
                log.error("Http body error msg:{}",errMsg);
                res_obj.put("stat",stat);
                res_obj.put("errMsg",errMsg);
                return res_obj;
            }
            String res = EntityUtils.toString(response.getEntity());
            res_obj = JSON.parseObject(res);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return res_obj;
    }

    public static String img_base64(String path) {
        /**
         *  对path进行判断，如果是本地文件就二进制读取并base64编码，如果是url,则返回
         */
        String imgBase64="";
        if (path.startsWith("http")){
            imgBase64 = path;
        }else {
            try {
                File file = new File(path);
                byte[] content = new byte[(int) file.length()];
                FileInputStream finputstream = new FileInputStream(file);
                finputstream.read(content);
                finputstream.close();
                imgBase64 = new String(Base64.encodeBase64(content));
            } catch (IOException e) {
                e.printStackTrace();
                return imgBase64;
            }
        }

        return imgBase64;
    }





}
