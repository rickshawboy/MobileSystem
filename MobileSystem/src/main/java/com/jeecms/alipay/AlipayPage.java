package com.jeecms.alipay;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.jeecms.util.DateUtils;

/**
 * 阿里PC端网页支付测试
 * @since 18:04
 * @author ljw
 * @date: 2018年8月29日 下午3:58:47 Notice 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
@RestController
@RequestMapping(value = "alipaypage")
public class AlipayPage {

    // 支付宝网关（固定）
    public static String URL = "https://openapi.alipay.com/gateway.do";
    // APPID 即创建应用后生成
    public static String APP_ID = "2016090701863111";
    // 开发者私钥，由开发者自己生成
    public static String APP_PRIVATE_KEY =
        "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCScR5EeE8wW+ok+lHG8B4SpiUOqpPdJZur2A73V14IWgLRHge2EwSiVeVK9jH3QTGaGnyqUQ8RbehbqFZv5sPnLJBK1+t1FCcjM75YuOLZ/SI+YfpyF1JTSY4KZL/HKU33cX+8aj1P4noFqxhKEc404Xrcmdnli+Wv3/jaWugdudDPXyRAyrnl3XUm48NRlAIMXaA+mF5iQJzugJp8V9gwiRaLnRvFEKze48OobfnGgaPr0g9it/wy5KO1f0IWNrqpZ13tVyEchoGCmAeEgLikv6fJvU+upg0v3EcZl345Ng0pMCKo7ahfCF3VPj5Q3Z+KWF0TU6h8+Uvn2FXVpcXHAgMBAAECggEASr5lvnjRolMnnRwOpUVldAtNGsFBmzxYyksKyH/VRNn8WhPDx9DJKrpAwBQMyLCDyq3+N0gMMs8wK6guBgt4rgN22lx+l1Fj8Hm2i75COxUSZ1jG5Xi10kDWuMNXhyWz37oUgmyPQrjLox0C1rtjzmXPuczlBF51K2FbuMdxdcnc0e33XiQQ571PW4A2DIGAjHZDffIXEq6502djmEOV1vMD5ueyxitCSqUiq/j6YSEBv3OwNXgg3kvd2XFtewl2J+Enj4krgmynPpLAqjxlexjVUFQqBFm6WT1rLiKEi6d3Q4B8X6SaxE7Bs8c+6D+WTunErbstS5eVMNqzkgSwgQKBgQDX3eX8uFJ33MYSZNoAPllGNZ7v+CrIdjVcA4oWpaAbxEyo1poImgfSGvlPb+ukxBPlUuwsBNX3ZDJJnpuMv9FNREJC+NmCklxwSDbruH/WsK4zsSAIQIyTkwNBd6sp06fWox5AwIz9TBrkisz7UgEHp7kx/xA51Gm6bYL5+hcspwKBgQCtqvdBEDoPV5TAmHBFyVlG/WcXGiJsW/Nm++HvfdMC47YjhHaMjJEfyF/YHggbiHTa9ODudHzm5TjdGjdtbKEVKJkWHOjNXXvOYCAAOPJMOtPPtibxQD5P8PFqz7jpAWfNWIj/fnryPelm9wPgcPuNz03wsF/wthXA9H6u1Aoh4QKBgHXx6BeNHoIfPpoOQI6hgmW6Jn03OykMbVs5E9oL5M9HEhuuJbtNbbh+xHmCzV3ZRIWoKSJkAgSJ2nAfGR5uMA3nncy56x5MmfU/f8KLrE3Be20C+l2H50c6rCy5FexkmO/NcFow2tgLHSaCMD2hMK7eFXIbyuHODXavH0E2RDNBAoGARoGlll2vJLFq6/I3iq2fOEoCw4dRuGpwXDwg0yljNMYcGTlbI96hwKSJ/OTcZ8kzn/1RIJ5cYyVmu6U8IYm7sCpmVGryPQYaEIRIGa8E99veSHLsRn/NjIkGilXA/aAbc2vhWtEPuy9Vhf79LO7RwhYKz4/bs49JL6hTkkj1wCECgYA8ohqx3hcS35ZzPyWLBrCN3aE1mB4iFQr9UeRaRGbXjLxgaOWLWuzd8dzDb1/wtk/boxPbcnB7qaLL15G2WJGzYm7uuaTqJOxmk9AcTC/mY8dtLF4QsSbI8s72Y64Elz/3DOOefaiC7ZJ+M8bOg5i2tWyRzdFYv8QU1+jINuFbRg==";
    // 参数返回格式，只支持json
    public static String FORMAT = "json";
    // 编码集，支持GBK/UTF-8
    public static String CHARSET = "UTF-8";
    // 支付宝公钥，由支付宝生成
    public static String ALIPAY_PUBLIC_KEY =
        "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzTYAodj65K7dETKjfBMCpVd1J5p7lM2FA2AbKqfAeIYLN/91g7v8FvIG6eBg3c2dQTeFO6uO520v5zU5HDyhsDJD3iUdNkenWfuCK8QvzGpKRWbL0Dfj3u/3XemT6SnhHT81Q6jRQJF87NHtBg5fAEt4W9NXWsxMIjYiyiHF30qQRbAFGOXmZbpa+jpHmGRMSQa/r+lD2lSrUqPsrX0laZzSQKu4aqn3WTxQ9kpCQf+2Xm+x+gZQNu9GlxIwVhCNhfoK1AnvBZa1o72sNlylFU0nRVKZOBqsOWbJ3N7DtgqyXq2gKZjkMARVoC2EZTE8AAEccDkvkmq9e/64C38g8wIDAQAB";
    // 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
    public static String SIGN_TYPE = "RSA2";

    // 服务器异步通知路径,需要http://格式的完整路径,必须外网可以访问
    public static String notify_url = "http://ljw.ngrok.xiaomiqiu.cn/alipaypage/notify";
    // 页面跳转同步通知页面路径,需要http://格式的完整路径,必须外网可以访问
    public static String return_url = "http://ljw.ngrok.xiaomiqiu.cn/return.html?appid=132";

    @RequestMapping(value = "pay", method = RequestMethod.GET)
    public void pay(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 商户订单号
        String out_trade_no = request.getParameter("out_trade_no").toString();
        // 订单总金额，单位为元，精确到小数点后两位
        String total_amount = request.getParameter("total_amount").toString();
        // 订单标题
        String subject = request.getParameter("subject").toString();
        // 订单描述
        String body = request.getParameter("body").toString();

        AlipayClient alipayClient =
            new DefaultAlipayClient(URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);

        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();// 创建API对应的request
        alipayRequest.setNotifyUrl(notify_url);// 在公共参数中设置回跳和通知地址
        alipayRequest.setReturnUrl(return_url);

        JSONObject object = new JSONObject();

        object.put("out_trade_no", out_trade_no);
        object.put("product_code", "FAST_INSTANT_TRADE_PAY");
        object.put("total_amount", new BigDecimal(total_amount));
        object.put("subject", subject);
        object.put("body", body);
        object.put("timeout_express", "1m");

        alipayRequest.setBizContent(object.toJSONString());// 填充业务参数
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        System.out.println(form);
        response.setContentType("text/html;charset=" + CHARSET);
        response.getWriter().write(form);// 直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();

    }
    
   /**
    * 电脑网站支付结果异步通知 
    * @param request
    * @param response
    * @throws AlipayApiException 
    * @throws IOException 
    */
    @RequestMapping(value = "notify", method = RequestMethod.POST)
    public void notify(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException, IOException {
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        System.out.println(DateUtils.formatDate(new Date(),DateUtils.COM_Y_M_D_H_M_S_PATTERN)+"------"+params.toString());
        //调用SDK验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, CHARSET, SIGN_TYPE); 
       
        if(signVerified) {//验证成功
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
        
            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
            
            if(trade_status.equals("TRADE_FINISHED")){//交易完成
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                    
                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
                PrintWriter writer = response.getWriter();
                writer.println("success");
                writer.flush();            
                
            }else if (trade_status.equals("TRADE_SUCCESS")){//支付成功
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                //店铺缴费
                
                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
                PrintWriter writer = response.getWriter();
                writer.println("success");
                writer.flush();           
            }
           
        }else {//验证失败
            PrintWriter writer = response.getWriter();
            writer.println("failure");
            writer.flush();
            writer.close();              
        }
    }
    
}
