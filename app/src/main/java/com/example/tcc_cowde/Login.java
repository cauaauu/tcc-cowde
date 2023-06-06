package com.example.tcc_cowde;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    Button login, cadastrar;
    EditText editTextEmail, editTextSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.btAtualizar);
        cadastrar = findViewById(R.id.btCadastrar);
        editTextEmail = findViewById(R.id.edtNomeAtualizar);
        editTextSenha = findViewById(R.id.edtNomeUsuarioAtualizar);

        RequestQueue requisicao = Volley.newRequestQueue(this);

        // Start the queue
        requisicao.start();

        String url = "http://localhost:5000/api/Dados/Login";

        login.setOnClickListener(view -> {
            if(editTextEmail.getText().toString().isEmpty() || editTextSenha.getText().toString().isEmpty()){
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        } else {
                JSONObject dadosLogin = new JSONObject();
                try {
                        dadosLogin.put("email", editTextEmail.getText().toString());
                        dadosLogin.put("senha", editTextSenha.getText().toString());
                } catch (JSONException exc) {
                        exc.printStackTrace();
                }
                Log.d("abacaxi", "Senha: " + dadosLogin);
                JsonObjectRequest lista = new JsonObjectRequest(Request.Method.POST, url, dadosLogin,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                if (response.has("mensagem")){
                                    Handler h = new Handler();
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                if (response.getString("mensagem").equals("Aprovado")){
                                                    Toast.makeText(Login.this, "Bem Vindo(a)", Toast.LENGTH_SHORT).show();
                                                    Intent it = new Intent(Login.this, Perfil.class);
                                                    it.putExtra("email", editTextEmail.getText().toString());
                                                    startActivity(it);
                                                } else {
                                                    Toast.makeText(Login.this, "Verifique as Informações e Tente Novamente", Toast.LENGTH_SHORT).show();
                                                }
                                            } catch (JSONException e){
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
                        Toast.makeText(Login.this, "erro: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                );
                requisicao.add(lista);
            }
        });


        cadastrar.setOnClickListener(view ->{
            Intent intent = new Intent(getApplicationContext(), Cadastrar.class);
            startActivity(intent);
        });
    }
}

