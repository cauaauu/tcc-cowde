package com.example.tcc_cowde;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Atualizar extends AppCompatActivity {

    Button atualizar;
    EditText editTextNome, editTextNomeUsuario;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar);

        atualizar = findViewById(R.id.btAtualizar);
        editTextNome = findViewById(R.id.edtNomeAtualizar);
        editTextNomeUsuario = findViewById(R.id.edtNomeUsuarioAtualizar);

        RequestQueue requisicao = Volley.newRequestQueue(this);
        String url = "http://localhost:5000/api/Dados/Atualizar";

        extras = getIntent().getExtras();

        atualizar.setOnClickListener(view -> {
            if(editTextNome.getText().toString().isEmpty() || editTextNomeUsuario.getText().toString().isEmpty()){
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            } else {
                JSONObject dadosLogin = new JSONObject();
                try {
                    dadosLogin.put("email", extras.getString("email"));
                    dadosLogin.put("nome", editTextNome.getText().toString());
                    dadosLogin.put("nome_usuario", editTextNomeUsuario.getText().toString());
                } catch (JSONException exc) {
                    exc.printStackTrace();
                }
                JsonObjectRequest lista = new JsonObjectRequest(Request.Method.POST, url, dadosLogin, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response.has("mensagem")) {
                            Handler h = new Handler();
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        if (response.getString("mensagem").equals("Aprovado")) {
                                            Toast.makeText(Atualizar.this, "Atualizado(a)", Toast.LENGTH_SHORT).show();
                                            Intent it = new Intent(Atualizar.this, Perfil.class);
                                            it.putExtra("email", extras.getString("email"));
                                            startActivity(it);
                                        } else {
                                            Toast.makeText(Atualizar.this, "Tente novamente", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }, 300);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Log.d("abacaxi", "erro:" + error.getMessage());
                        Toast.makeText(Atualizar.this, "erro: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                requisicao.add(lista);
            }
        });
    }

}
