import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

    private static String endDate = "2022-10-07" + " ";
    private static String endTime = endDate + "19:00";
    private static Map<String, String> map = new HashMap<String, String>();

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        String response = "";
        try {
            boolean flag = true;
            while (flag) {
                File file = new File("/Users/rytlockbrimstone/Downloads/datetime.txt");
                if (file.exists()) {
                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr);
                    String str = null;
                    int count = 0;
                    while ((str = br.readLine()) != null) {
                        String[] split = str.split("=");
                        if (count == 1) {
                            endTime = endDate + " " + split[1];
                            break;
                        }
                        endDate = split[1];
                        count++;
                    }
                }
                Thread.sleep(5000L);
                response = test();
                Map respMap = JSONUtil.toBean(response, Map.class);
                if (null == respMap) {
                    throw new RuntimeException("响应体respMap为空");
                }
                Integer httpstatus = (Integer) respMap.get("httpstatus");
                if (null == httpstatus || 200 != httpstatus) {
                    System.out.println(respMap);
                    throw new RuntimeException("请求接口没有状态码或状态码不为200");
                }
                Map data = (Map) respMap.get("data");
                if (null == data) {
                    System.out.println(respMap);
                    throw new RuntimeException("data为null");
                }
                List<String> result = null;
                try {
                    result = (List<String>) data.get("result");
                    if (CollectionUtil.isEmpty(result)) {
                        System.out.println(respMap);
                        throw new RuntimeException("result为null");
                    }
                } catch (Exception e) {
                    System.out.println(respMap);
                    throw new RuntimeException("请求接口出现错误");
                }
                StringBuffer buffer = new StringBuffer();
                for (String s : result) {
                    String[] split = s.split("\\|");
                    // 车次3
                    // 发车时间8
                    String goTime = "2022-10-07 " + split[8];
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date parse = format.parse(goTime);
                    Date parse2 = format.parse(endTime);
                    // 到达时间9
                    // 经历时间10
                    //30 商务
                    //31 一等
                    //32 二等
                    if (parse.after(parse2)) {
                        if (!"无".equals(split[30]) || !"无".equals(split[31]) || !"无".equals(split[32])) {
                            buffer.append("车次:").append(split[3]).append(",")
                                    .append("发车时间:")
                                    .append(split[8]).append(",")
                                    .append("到达时间:")
                                    .append(split[9]).append(",")
                                    .append("经历时间:")
                                    .append(split[10]).append(",")
                                    .append("商务:")
                                    .append(split[32]).append(",")
                                    .append("一等:")
                                    .append(split[31]).append(",")
                                    .append("二等:")
                                    .append(split[30]).append("\n");
                        } else {
//                                System.out.println("车次:" + split[3] + " ,发车时间:" + split[8] + " 还没有车空余车票");
                        }
                    }
                }
                if (!"".equals(buffer.toString())) {
                    clearSrcpt();
                    buffer.append("================================分割线================================");
                    System.out.println(buffer);
                    continue;
                }
                clearSrcpt();
                System.out.print("记录时间" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"   没有车票");
            }
        } catch (Exception e) {
//            LogService.writeLog(e);
            System.out.println(response);
            e.printStackTrace();
        }
//        System.out.println();
    }

    public static String test() throws URISyntaxException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet();
        URIBuilder uriBuilder = new URIBuilder("https://kyfw.12306.cn/otn/leftTicket/queryZ");
        uriBuilder.addParameter("leftTicketDTO.train_date", "2022-10-07");
        uriBuilder.addParameter("leftTicketDTO.from_station", "QAK");
        uriBuilder.addParameter("leftTicketDTO.to_station", "VNP");
        uriBuilder.addParameter("purpose_codes", "ADULT");
        URI uri = uriBuilder.build();
        httpGet.setURI(uri);
        httpGet.setHeaders(new Header[]{
                new BasicHeader("Pragma", "no-cache"),
                new BasicHeader("Accept", "*/*"),
                new BasicHeader("If-Modified-Since", "0"),
                new BasicHeader("Accept-Language", "zh-CN,zh-Hans;q=0.9"),
                new BasicHeader("Accept-Encoding", "gzip, deflate, br"),
                new BasicHeader("Cache-Control", "no-cache"),
                new BasicHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/15.4 Safari/605.1.15"),
                new BasicHeader("Connection", "keep-alive"),
                new BasicHeader("Referer", "https://kyfw.12306.cn/otn/leftTicket/init?linktypeid=dc"),
                new BasicHeader("Host", "kyfw.12306.cn"),
                new BasicHeader("Cookie", "_uab_collina=166502261322561752295658; JSESSIONID=2AE3A473A2CC94E1B74475BFB599AC45; _jc_save_fromDate=2022-10-07; _jc_save_fromStation=%u66F2%u961C%u4E1C%2CQAK; _jc_save_toDate=2022-10-06; _jc_save_toStation=%u5317%u4EAC%u5357%2CVNP; _jc_save_wfdc_flag=dc; guidesStatus=off; route=495c805987d0f5c8c84b14f60212447d; fo=ioex6fthy7nl898qkjABmETApPDxSfwmJ3xPj_FNgIf9_DH4-5zPeB4lfg4IJ0SruhywvaUxrSaP8s_HK3MsHWWDi7xuRLFzz3k1NMxW9PFw1gI_yHLi1HjN9UvuNrhYZxU_hOSbpcfLgoB9e461d3VY5nOJJC9twLtH1ml5rU2W1syyra2kj6FWAgY9Oe2OM_QsdLteNzRAOyzP; RAIL_DEVICEID=qkuDi76PPD_tIeJRH21tW3lDzP_9qQapAv4WfMuzIiTP-vNSNo2ptyXIXcLQ3MhvQEl5y2XmG6EKojQoW_pFEG4ims7it-idO-xlTi_gu1V2I2t6wRRfXUyqRprJ_q8_5Q-QMyRVqnwhqWEEj-0jUP-AKOhplYow; RAIL_EXPIRATION=1665288171669; BIGipServerotn=368050698.50210.0000; cursorStatus=off; highContrastMode=defaltMode; BIGipServerpassport=870842634.50215.0000"),
                new BasicHeader("X-Requested-With", "XMLHttpRequest"),
        });
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String msg = EntityUtils.toString(entity);
        return msg;
    }

    public static void clearSrcpt() {
        System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b");
        System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b");
        System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b");
        System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b");
    }

    public static class Item {
        private String name;
        private String info;
    }

}
