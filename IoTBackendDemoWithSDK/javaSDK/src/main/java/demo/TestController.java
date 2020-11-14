package demo;

import cmcc.iot.onenet.javasdk.api.cmds.SendCmdsApi;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.response.cmds.NewCmdsResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class TestController {
    public  String beep(){
        RestTemplate restTemplate=new RestTemplate();
        String url="https://open.iot.10086.cn/zh/developer/uniprotocol/v1/mqtt/device/sendcmd?qos=0&sms=beep%3A1&timeout=&pid=384573&did=647489963";
        HttpHeaders requestheader= new HttpHeaders();
        requestheader.add("Content-Type","application/x-www-form-urlencoded");
        requestheader.add("Cookie","saltkey=D4p4E7i0; lastvisit=1604406902; _gscu_1555403782=05182711poe6pt18; lastact=1605237492%09forum.php%09viewthread; visitedfid=50D45; LANG=zh_CN; Hm_lvt_fb3a66a8ee8dcd5e16c7cae035393beb=1604410503,1605180034,1605237460,1605270299; l_s=41325abd6971e8624259e0b26faadb58; sk=c9c5f5968780fde7dcd6af453baa74fd; _gscbrs_1555403782=1; _gscs_1555403782=05273812e05hlh19|pv:1; session=212235--QJOKHFYOE9gQVu--1605360237-1605273837-2b3679d7ebf572b4b7fcfacfcee264ee; zg_did=%7B%22did%22%3A%20%221758e5170593a8-07701f8c662842-303464-144000-1758e51705b9b3%22%7D; zg_63df7be07da847be8e46cf4ae33c7c40=%7B%22sid%22%3A%201605273809683%2C%22updated%22%3A%201605273838378%2C%22info%22%3A%201605182854924%2C%22superProperty%22%3A%20%22%7B%7D%22%2C%22platform%22%3A%20%22%7B%7D%22%2C%22utm%22%3A%20%22%7B%7D%22%2C%22referrerDomain%22%3A%20%22%22%2C%22zs%22%3A%200%2C%22sc%22%3A%200%2C%22firstScreen%22%3A%201605273809683%7D; Hm_lpvt_fb3a66a8ee8dcd5e16c7cae035393beb=1605273838; cmccIot=d3e088a9315a6a777015136110e5de45");
        requestheader.add("token","X3q5U+UU17UEOSCLv7yweL2zG6aOO1l4+WqM6DpUAvM7g6s0xbgY3Fzdau/vOwStg+2/y9rTEpFMIlOTOvc/D335v0Z+80eMNDdRigMix/X/Rgz0R7YnmRW9oORi9fkSnaAQJ2B2C8O8oNnvxmIgo9R+uKmwd8t32PlK6aF6XHbpxtN2aNFE2rXx29d77AW7IVrMbKinoxYzf1i8gLqj+u/Z4IDzqFT7gdCAvwnBhZ/bcBwb/QRIUc5oBNy8NzopUAV4O9ZrfsNKZT3iY+NwSM3quQt3WsZOR1o3ymefz1I=");
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("qos","0");
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<MultiValueMap>(requestBody, requestheader);
        String res= restTemplate.postForObject(url, requestEntity, String.class);
        return res;
    }
    @RequestMapping(value="/test",method = RequestMethod.GET)
    public String test(){
        System.out.println(beep());
        return "123";
    }

    @RequestMapping(value="/testTrigger",method = RequestMethod.GET)
    public String testTrigger(){
        String devId = "647489963";
        String key = "vGqwqzAwLdfel0eDQ1qrMan2k4E=";
        String text = "beep:1";
        /**
         * 发送命令
         * @param devId：接收该数据的设备ID（必选），String
         * @param qos:是否需要响应，默认为0,Integer
         * 0：不需要响应，即最多发送一次，不关心设备是否响应；
         * 1：需要响应，如果设备收到命令后没有响应，则会在下一次设备登陆时若命令在有效期内(有效期定义参见timeout参数）则会继续发送。
         * 对响应时间无限制，多次响应以最后一次为准。
         * 本参数仅当type=0时有效；
         * @param timeOut:命令有效时间，默认0,Integer
         * 0：在线命令，若设备在线,下发给设备，若设备离线，直接丢弃；
         *  >0： 离线命令，若设备在线，下发给设备，若设备离线，在当前时间加timeout时间内为有效期，有效期内，若设备上线，则下发给设备。单位：秒，有效围：0~2678400。
         *  本参数仅当type=0时有效；
         * @param type://默认0。0：发送CMD_REQ包，1：发送PUSH_DATA包
         * @param contents:用户自定义数据：json、string、二进制数据（小于64K）
         * @param key:masterkey或者设备apikey
         */
        SendCmdsApi api = new SendCmdsApi(devId, null, null, null, text, key);
        BasicResponse<NewCmdsResponse> response = api.executeApi();
        System.out.println(response.getJson());
        return "123";
    }


}
