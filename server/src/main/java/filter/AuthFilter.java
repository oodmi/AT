package filter;

import com.sun.deploy.net.HttpRequest;
import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        if (Objects.equals(httpRequest.getMethod(), "OPTIONS")){
            httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            httpResponse.setHeader("Access-Control-Allow-Headers", "authorization, content-type");
        }
        else {
            String authorization1 = httpRequest.getHeader("authorization");
            if (authorization1 != null) {
                String authorization = authorization1.split(" ")[1];
                byte[] decode = Base64.getDecoder().decode(authorization);
                String credentials = "";
                for (int i = 0; i < decode.length; i++) {
                    credentials += (char) decode[i];
                }

                String[] split = credentials.split(":");

                HashMap<String, String> postDataParams = new HashMap<>();
                postDataParams.put("username", split[0]);
                postDataParams.put("password", split[1]);

                URL url = new URL("http://localhost:8082/auth/");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));
                writer.flush();
                writer.close();
                os.close();
                int responseCode = conn.getResponseCode();
                if (responseCode == HttpServletResponse.SC_OK)
                    httpResponse.setStatus(responseCode);
                else {
                    httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            } else {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }

    @Override
    public void destroy() {

    }
}
