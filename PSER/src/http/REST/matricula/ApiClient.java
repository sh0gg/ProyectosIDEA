package http.REST.matricula;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class ApiClient {

    private final String base;

    public ApiClient(String base) {
        this.base = base;
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
        return read(con);
    }

    public Response post(String path, String body) throws IOException {
        HttpURLConnection con = open(path, "POST");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        try (OutputStream os = con.getOutputStream()) {
            os.write(body.getBytes(StandardCharsets.UTF_8));
        }
        return read(con);
    }

    public Response delete(String path) throws IOException {
        HttpURLConnection con = open(path, "DELETE");
        return read(con);
    }

    private Response read(HttpURLConnection con) throws IOException {
        int code = con.getResponseCode();
        InputStream is = (code >= 200 && code < 400) ? con.getInputStream() : con.getErrorStream();

        String body = "";
        if (is != null) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) sb.append(line);
            body = sb.toString();
        }
        return new Response(code, body);
    }

    public static class Response {
        public final int status;
        public final String body;
        public Response(int s, String b) { status=s; body=b; }
    }

    public static String enc(String s) {
        try { return URLEncoder.encode(s, "UTF-8"); }
        catch(Exception e) { return s; }
    }
}