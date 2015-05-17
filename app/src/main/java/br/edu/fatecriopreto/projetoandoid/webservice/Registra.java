package br.edu.fatecriopreto.projetoandoid.webservice;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Andrey on 16/05/2015.
 */
public class Registra {
    public static String cadastraJson(String nome,String usuario, String email, String senha, Context context) {
        final String[] resposta = {"Sem conexao"};
        String url = "http://192.168.0.131:82/login/index.php?nome="+nome+"&user="+usuario+"&email="+email+"&senha="+senha;
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET,url,null,new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            //Log.d("RESPOSTA", jsonObject.toString());
                            resposta[0] = jsonObject.getString("resposta");

                            // String errorMsg = jObj.getString("error_msg");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Log.d("RESPOSTA", jsonObject.toString());

                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Error.Response.Aki", volleyError.getMessage());
            }
        });
        queue.add(getRequest);

        return resposta[0];
    }
}
