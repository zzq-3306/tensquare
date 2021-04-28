package util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
/*
pom.xml
<dependency>
  <groupId>com.aliyun</groupId>
  <artifactId>aliyun-java-sdk-core</artifactId>
  <version>4.5.16</version>
</dependency>

*/
public class SendSms {

    public static String sendCode(String phone) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-qingdao", "LTAI4G3TLBJukszTtVwXy5vF", "LjfSvpAPrc8fYi1xF34Udc1o7yULkK");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "小辣鸡");
        request.putQueryParameter("TemplateCode", "SMS_209827449");
        String code = randomCode();
        request.putQueryParameter("TemplateParam", "{\"code\":+"+code+"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        //System.out.println(code);
        return code;
    }

    public static String randomCode() {
        String num = "";
        for (int i = 0; i < 6; i++) {
            num += Math.round(Math.random()*9);
        }
        return num;
    }

}