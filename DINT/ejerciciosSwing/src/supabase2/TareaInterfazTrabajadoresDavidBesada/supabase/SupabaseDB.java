package supabase;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class SupabaseDB {

    // --- INSERT (CREATE) ---
    public static String insert(String table, JSONObject data) {
        OkHttpClient client = SupabaseConfig.getHttpClient();

        Request request = new Request.Builder()
            .url(SupabaseConfig.SUPABASE_URL + "/rest/v1/" + table)
            .addHeader("apikey", SupabaseConfig.SUPABASE_KEY)
            .addHeader("Authorization", "Bearer " + SupabaseConfig.SUPABASE_KEY)
            .addHeader("Content-Type", "application/json")
            .addHeader("Prefer", "return=representation") // Devuelve el registro insertado
            .post(RequestBody.create(MediaType.parse("application/json"), data.toString()))
            .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            System.err.println("Error de red al insertar: " + e.getMessage());
            return "Error de conexión: " + e.getMessage();
        } catch (Exception e) {
            System.err.println("Error inesperado en insert: " + e.getMessage());
            e.printStackTrace();
            return "Error inesperado: " + e.getMessage();
        }
    }

    // --- GET (READ) ---
    public static String get(String table, String filter) {
        OkHttpClient client = SupabaseConfig.getHttpClient();

        // Ejemplo de filter: "id=eq.1" o "email=eq.email@email.com"
        String url = SupabaseConfig.SUPABASE_URL + "/rest/v1/" + table;
        if (filter != null && !filter.isEmpty()) {
            url += "?" + filter;
        }
        System.out.println("Filter applicado: "+ filter);

        Request request = new Request.Builder()
            .url(url)
            .addHeader("apikey", SupabaseConfig.SUPABASE_KEY)
            .addHeader("Authorization", "Bearer " + SupabaseConfig.SUPABASE_KEY)
            .addHeader("Content-Type", "application/json")
            .get()
            .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            System.err.println("Error de red al obtener datos: " + e.getMessage());
            return "Error de conexión: " + e.getMessage();
        } catch (Exception e) {
            System.err.println("Error inesperado en get: " + e.getMessage());
            e.printStackTrace();
            return "Error inesperado: " + e.getMessage();
        }
    }

    // --- UPDATE ---
    public static String update(String table, String filter, JSONObject newData) {
        OkHttpClient client = SupabaseConfig.getHttpClient();

        String url = SupabaseConfig.SUPABASE_URL + "/rest/v1/" + table;
        if (filter != null && !filter.isEmpty()) {
            url += "?" + filter;
        }

        Request request = new Request.Builder()
            .url(url)
            .addHeader("apikey", SupabaseConfig.SUPABASE_KEY)
            .addHeader("Authorization", "Bearer " + SupabaseConfig.SUPABASE_KEY)
            .addHeader("Content-Type", "application/json")
            .addHeader("Prefer", "return=representation")
            .patch(RequestBody.create(MediaType.parse("application/json"), newData.toString()))
            .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            System.err.println("Error de red al actualizar: " + e.getMessage());
            return "Error de conexión: " + e.getMessage();
        } catch (Exception e) {
            System.err.println("Error inesperado en update: " + e.getMessage());
            e.printStackTrace();
            return "Error inesperado: " + e.getMessage();
        }
    }

    // --- DELETE ---
    public static String delete(String table, String filter) {
        OkHttpClient client = SupabaseConfig.getHttpClient();

        String url = SupabaseConfig.SUPABASE_URL + "/rest/v1/" + table;
        if (filter != null && !filter.isEmpty()) {
            url += "?" + filter;
        }

        Request request = new Request.Builder()
            .url(url)
            .addHeader("apikey", SupabaseConfig.SUPABASE_KEY)
            .addHeader("Authorization", "Bearer " + SupabaseConfig.SUPABASE_KEY)
            .addHeader("Content-Type", "application/json")
            .addHeader("Prefer", "return=representation")
            .delete()
            .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            System.err.println("Error de red al eliminar: " + e.getMessage());
            return "Error de conexión: " + e.getMessage();
        } catch (Exception e) {
            System.err.println("Error inesperado en delete: " + e.getMessage());
            e.printStackTrace();
            return "Error inesperado: " + e.getMessage();
        }
    }

    // --- MAIN DE PRUEBA ---
    public static void main(String[] args) {
        try {
            String table = "tabla_usuarios";

            // INSERT
            JSONObject data = new JSONObject();
            data.put("nombre", "Juan1");
            data.put("email", "juan1@example.com");
            data.put("edad", "11");
            String insertRes = insert(table, data);
            System.out.println("INSERT:\n" + insertRes);
            
            // GET
            String getRes = get(table, "email=eq.juan@example.com");
            System.out.println("GET:\n" + getRes);
            // https://<tu-proyecto>.supabase.co/rest/v1/tabla_usuarios?email=eq.juan@example.com
            
            // UPDATE
            JSONObject updateData = new JSONObject();
            updateData.put("nombre", "Juan Actualizado");
            String updateRes = update(table, "email=eq.juan@example.com", updateData);
            System.out.println("UPDATE:\n" + updateRes);

            // DELETE
            String deleteRes = delete(table, "email=eq.juan@example.com");
            System.out.println("DELETE:\n" + deleteRes);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
