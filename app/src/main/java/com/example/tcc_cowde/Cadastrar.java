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

public class Cadastrar extends AppCompatActivity {

    Button cadastrar;
    EditText editTextEmail, editTextSenha, editTextNome, editTextNomeUsuario, editTextIdade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        cadastrar = findViewById(R.id.btAtualizar);
        editTextNome = findViewById(R.id.edtNome);
        editTextNomeUsuario = findViewById(R.id.edtNomeUsuario);
        editTextEmail = findViewById(R.id.edtNomeAtualizar);
        editTextIdade = findViewById(R.id.edtIdade);
        editTextSenha = findViewById(R.id.edtNomeUsuarioAtualizar);

        RequestQueue requisicao = Volley.newRequestQueue(this);
        String url = "http://localhost:5000/api/Dados/Cadastrar";

        cadastrar.setOnClickListener(view ->{
            if(editTextEmail.getText().toString().isEmpty() || editTextSenha.getText().toString().isEmpty() || editTextIdade.getText().toString().isEmpty()
                    || editTextNome.getText().toString().isEmpty() || editTextNomeUsuario.getText().toString().isEmpty()){
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            } else {
                JSONObject dadosCadastrar = new JSONObject();
                try {
                    dadosCadastrar.put("nome", editTextNome.getText().toString());
                    dadosCadastrar.put("nome_usuario", editTextNomeUsuario.getText().toString());
                    dadosCadastrar.put("email", editTextEmail.getText().toString());
                    dadosCadastrar.put("idade", editTextIdade.getText().toString());
                    dadosCadastrar.put("senha", editTextSenha.getText().toString());
                } catch (JSONException exc) {
                    exc.printStackTrace();
                }
                Log.d("abacaxi", "Senha: " + dadosCadastrar);
                JsonObjectRequest lista = new JsonObjectRequest(Request.Method.POST, url, dadosCadastrar, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response.has("mensagem")) {
                            Handler h = new Handler();
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        if (response.getString("mensagem").equals("Aprovado")) {
                                            Toast.makeText(Cadastrar.this, "Cadastrado(a)!", Toast.LENGTH_SHORT).show();
                                            Intent it = new Intent(Cadastrar.this, MainActivity.class);
                                            startActivity(it);
                                        } else {
                                            Toast.makeText(Cadastrar.this, "Tente Novamente", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }, 400);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Log.d("abacaxi", "erro:" + error.getMessage());
                        Toast.makeText(Cadastrar.this, "erro:" + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
            }
            );
                requisicao.add(lista);
            }
        });
    }
}
