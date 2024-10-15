package com.lingnuokeji.ClassManagementSystemJava.utils;


import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

public class IpUtil {
    /**
     * 1.通过HttpServletRequest获取IP地址
     * @Username 程序员-Su_xiaoxiang
     * @date 2024/9/10 16:25
     * @return String
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            if (ipAddress != null && ipAddress.length() > 15) {
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress;
    }
    /**
     * 2.通过调用接口的方式获取IP
     */
    public static String getIp() {
        try {
            URL realUrl = new URL("http://whois.pconline.com.cn/ipJson.jsp");
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setUseCaches(false);
            conn.setReadTimeout(6000);
            conn.setConnectTimeout(6000);
            conn.setInstanceFollowRedirects(false);
            int code = conn.getResponseCode();
            StringBuilder sb = new StringBuilder();
            String ip = "";
            if (code == 200) {
                InputStream in = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                //控制台输出乱码,就指定编码为GBK即可。
                //BufferedReader reader = new BufferedReader(new InputStreamReader(in, "GBK"));
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                ip = sb.substring(sb.indexOf("ip") + 5, sb.indexOf("pro") - 3);
            }
            return ip;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 3.通过调用接口根据ip获取归属地
     */
    public static String getAddress(String ip) {
        try {
            URL realUrl = new URL("http://whois.pconline.com.cn/ipJson.jsp?ip=" + ip + "&json=true");
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setUseCaches(false);
            conn.setReadTimeout(6000);
            conn.setConnectTimeout(6000);
            conn.setInstanceFollowRedirects(false);
            int code = conn.getResponseCode();
            StringBuilder sb = new StringBuilder();
            String ipaddr = "";
            if (code == 200) {
                InputStream in = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                ipaddr = ip + "=" + sb.substring(sb.indexOf("addr") + 7, sb.indexOf("regionNames") - 3);
            }
            return ipaddr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

