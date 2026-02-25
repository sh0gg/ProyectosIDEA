package http.REST.matricula;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class ApiClient {
    private final String base; // ej: "http://localhost/cursos/rest.php"

    public ApiClient(String base) {
        this.base = base.endsWith("/") ? base.substring(0, base.length()-1) : base;
    }

    private HttpURLConnection open(String path, String method) throws IOException {
        URL url = new URL(base + path);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(method);
        con.setRequestProperty("Accept", "application/json");
        return con;
    }

    public Response get(String path) throws IOException {
        HttpURLConnection con = open(path, "GET");
        return readResponse(con);
    }

    public Response delete(String path) throws IOException {
        HttpURLConnection con = open(path, "DELETE");
        return readResponse(con);
    }

    public Response postForm(String path, String formUrlEncoded) throws IOException {
        HttpURLConnection con = open(path, "POST");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        try (OutputStream os = con.getOutputStream()) {
            os.write(formUrlEncoded.getBytes(StandardCharsets.UTF_8));
        }
        return readResponse(con);
    }

    public static class Response {
        public final int status;
        public final String body;
        public Response(int status, String body) { this.status = status; this.body = body; }
    }

    private Response readResponse(HttpURLConnection con) throws IOException {
        int code = con.getResponseCode();
        InputStream is = (code >= 200 && code < 400) ? con.getInputStream() : con.getErrorStream();
        String body = "";

        if (is != null) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) sb.append(line);
                body = sb.toString();
            }
        }
        con.disconnect();
        return new Response(code, body);
    }

    public static String enc(String s) {
        try { return URLEncoder.encode(s, "UTF-8"); }
        catch (Exception e) { return s; }
    }
}