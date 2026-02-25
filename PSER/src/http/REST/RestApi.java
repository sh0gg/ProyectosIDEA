package http.REST;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class RestApi {

    public static final String BASE_URL = "http://localhost/clientes/rest.php";

    public static class Response {
        public final int code;
        public final String body;

        public Response(int code, String body) {
            this.code = code;
            this.body = body;
        }
    }

    public static Response request(String method, String path, String contentType, String accept, String body)
            throws IOException {
        URL url = new URL(BASE_URL + path);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod(method);
        con.setRequestProperty("Accept", accept != null ? accept : "application/json");

        if (body != null) {
            con.setDoOutput(true);
            if (contentType != null)
                con.setRequestProperty("Content-Type", contentType);

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = body.getBytes(StandardCharsets.UTF_8);
                os.write(input);
            }
        }

        int code = con.getResponseCode();

        InputStream is = (code >= 200 && code < 400) ? con.getInputStream() : con.getErrorStream();
        String responseBody = "";
        if (is != null) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null)
                    sb.append(line);
                responseBody = sb.toString();
            }
        }

        con.disconnect();
        return new Response(code, responseBody);
    }

    // --- Helpers para form-urlencoded (como tus ejemplos actuales) ---
    public static String toFormUrlEncoded(Map<String, String> data) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> e : data.entrySet()) {
            if (!first)
                sb.append("&");
            first = false;
            sb.append(URLEncoder.encode(e.getKey(), "UTF-8"));
            sb.append("=");
            sb.append(URLEncoder.encode(e.getValue(), "UTF-8"));
        }
        return sb.toString();
    }

    public static Response get(String path) throws IOException {
        return request("GET", path, null, "application/json", null);
    }

    public static Response postForm(String path, String formBody) throws IOException {
        return request("POST", path, "application/x-www-form-urlencoded; charset=UTF-8", "application/json", formBody);
    }

    public static Response putJson(String path, String jsonBody) throws IOException {
        return request("PUT", path, "application/json; charset=UTF-8", "application/json", jsonBody);
    }

    public static Response patchJson(String path, String jsonBody) throws IOException {
        return request("PATCH", path, "application/json; charset=UTF-8", "application/json", jsonBody);
    }

    public static Response delete(String path) throws IOException {
        return request("DELETE", path, null, "application/json", null);
    }
}