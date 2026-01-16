package supabase;
import okhttp3.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

import org.json.JSONObject;


public class SupabaseAuth {   
    public static String login(String email, String password) throws Exception {
    	
    	OkHttpClient client = SupabaseConfig.getHttpClient();

        JSONObject json = new JSONObject();
        json.put("email", email);
        json.put("password", password);
        String body = json.toString();

        Request request = new Request.Builder()
            .url(SupabaseConfig.SUPABASE_URL + "/auth/v1/token?grant_type=password")
            .addHeader("apikey", SupabaseConfig.SUPABASE_KEY)
            .addHeader("Authorization", "Bearer " + SupabaseConfig.SUPABASE_KEY)
            .addHeader("Content-Type", "application/json")
            .post(RequestBody.create(MediaType.parse("application/json"), body))
            .build();

        try (Response response = client.newCall(request).execute()) {

            return response.body().string();

        } catch (IOException e) {  	// Si hay un error de red (timeout, sin conexión, etc.)
            System.err.println("Error de red al intentar iniciar sesión: " + e.getMessage());
            return "Error de conexión: " + e.getMessage();

        } catch (Exception e) { 	// Cualquier otro error inesperado
            System.err.println("Error inesperado en login: " + e.getMessage());
            e.printStackTrace();
            return "Error inesperado: " + e.getMessage();
        }
    }

    public static String register(String email, String password) throws Exception {
    	
    	OkHttpClient client = SupabaseConfig.getHttpClient();

        JSONObject json = new JSONObject();
        json.put("email", email);
        json.put("password", password);
        String body = json.toString();

        Request request = new Request.Builder()
            .url(SupabaseConfig.SUPABASE_URL + "/auth/v1/signup")
            .addHeader("apikey", SupabaseConfig.SUPABASE_KEY)
            .addHeader("Authorization", "Bearer " + SupabaseConfig.SUPABASE_KEY)
            .addHeader("Content-Type", "application/json")
            .post(RequestBody.create(MediaType.parse("application/json"), body))
            .build();

        try (Response response = client.newCall(request).execute()) {
        	
            return response.body().string();

        } catch (IOException e) {	// Si hay un error de red (timeout, sin conexión, etc.)
            System.err.println("Error de red al intentar iniciar sesión: " + e.getMessage());
            return "Error de conexión: " + e.getMessage();

        } catch (Exception e) {		// Cualquier otro error inesperado
            System.err.println("Error inesperado en login: " + e.getMessage());
            e.printStackTrace();
            return "Error inesperado: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        try {
            // Registro
            String signupResult = register("email@email.com", "contraseña_segura123");
            System.out.println("Registro:\n" + signupResult);
            
         // Registro
            signupResult = register("cabestro@email.com", "contraseña_segura123");
            System.out.println("Registro:\n" + signupResult);
            
         // Registro
            signupResult = register("manolito@email.com", "contraseña_segura123");
            System.out.println("Registro:\n" + signupResult);
            
         // Registro
            signupResult = register("manolita@email.com", "contraseña_segura123");
            System.out.println("Registro:\n" + signupResult);
            
         // Registro
            signupResult = register("jeremias@email.com", "contraseña_segura123");
            System.out.println("Registro:\n" + signupResult);

            // Login
            String loginResult = login("email@email.com", "contraseña_segura123");
            System.out.println("Login:\n" + loginResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

