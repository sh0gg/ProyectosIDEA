package examenRESTDBR;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class APIClient {
    public static final String BASE_URL = "http://localhost/reservas/reservas.php";

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

    public static JSONObject toJSON(Map<String, String> data) throws JSONException {
        JSONObject obj = new JSONObject();
        for (Map.Entry<String, String> e : data.entrySet()) {
            obj.put(e.getKey(), e.getValue());
        }
        return obj;
    }

    public static Response get(String path) throws IOException {
        return request("GET", path, null, "application/json", null);
    }

    public static Response postJSON(String path, JSONObject obj) throws IOException {
        String jsonBody = obj.toString();
        return request("POST", path, "application/x-www-form-urlencoded; charset=UTF-8", "application/json", jsonBody);
    }

    public static Response delete(String path) throws IOException {
        return request("DELETE", path, null, "application/json", null);
    }
}
