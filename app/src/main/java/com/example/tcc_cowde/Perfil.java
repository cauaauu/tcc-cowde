
package com.example.tcc_cowde;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Perfil extends AppCompatActivity {

    private String idCanal = "Canal_ID";
    NotificationManagerCompat servicoNotificacao;

    Button atualizar, mapa, notificacao;
    Usuario usuario = new Usuario();
    Bundle extras;

    private void criaCanal() {
        //Verifica a versão do Android
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Cria o canal de notificação passando o ID, um nome e a prioridade
            NotificationChannel canal = new NotificationChannel(
                    idCanal, "Canal Notificações",
                    NotificationManager.IMPORTANCE_DEFAULT);
            //É possível adicionar uma descrição ao canal
            canal.setDescription("Canal utilizado para receber notificações do app cowde");
            //Cadastrar o nosso canal de notificação no serviço do Android
            NotificationManager servico =
                    getSystemService(NotificationManager.class);
            servico.createNotificationChannel(canal);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        //TextViews onde serao inseridos o nome e nome do usuario logado
        TextView nome = findViewById(R.id.txtNome);
        TextView nome_usuario = findViewById(R.id.txtNomeUsuario);
        atualizar = findViewById(R.id.buttonAtualizar);
        mapa = findViewById(R.id.buttonMapa);
        notificacao = findViewById(R.id.buttonNotificacao);

        criaCanal();
        //Iniciar o serviço de notificação
        servicoNotificacao = NotificationManagerCompat.from(this);

        //Lista que puxa dados do usuário que serão recebidos pelo webservice
        //List<Usuario> listaFinal = new ArrayList<>();

        //url do webservice
        String url = "http://localhost:5000/api/Dados/Perfil";
        extras = getIntent().getExtras();

        JSONObject dadosPerfil = new JSONObject();

        try {
            dadosPerfil.put("email", extras.getString("email"));
        } catch (JSONException exc) {
            exc.printStackTrace();
        }

        //método do webservice que lista os dados deve retornar um array
        JsonObjectRequest buscaDados = new JsonObjectRequest(
                Request.Method.POST,
                url,
                dadosPerfil,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            nome.setText(response.getString("nome").toString());
                            nome_usuario.setText(response.getString("nome_usuario").toString());

                        } catch (JSONException exc) {
                            exc.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //volley é uma biblioteca http usada para operaçoes do tipo procedimento remoto
                        //geralmente usada para preencher uma UI, como página resultado de uma pesquisa
                        error.printStackTrace();
                        Toast.makeText(Perfil.this,
                                "Erro " + error, Toast.LENGTH_SHORT).show();
                    }
                }
        );

        //enviando a requisao POST para o webservice
        RequestQueue requiQueue = Volley.newRequestQueue(Perfil.this);
        requiQueue.add(buscaDados);//adiciona o método de busca no objeto que guarda a requisição

        atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), Atualizar.class);
                it.putExtra("email", extras.getString("email"));
                startActivity(it);
            }
        });

        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(it);
            }
        });


        notificacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Como a notificação vai abrir uma tela ao ser acionada
                //Precisamos criar uma Intent e transformar ela em PendingIntent
                Intent intent = new Intent(Perfil.this, Perfil.class);
                PendingIntent pending = PendingIntent.getBroadcast(
                        Perfil.this, //Contexto
                        0, //Código de requisiçao (zero por padrão)
                        intent,  //Intent que será transformada em Pending
                        PendingIntent.FLAG_UPDATE_CURRENT); //Configuração padrão

                NotificationCompat.Builder notificacao = new NotificationCompat.Builder(
                        Perfil.this, idCanal)
                        .setContentTitle("Já viu seu progesso hoje?")
                        .setContentText("Não pare agora! Entre no Cowde e confira!")
                        .addAction(R.drawable.baseline_notifications_active_24,
                                "Bora Codar?", //Texto que vai aparecer na notificação
                                pending) //O que será executado ao clicar no texto acima
                        .setContentIntent(pending) //Tela que irá abrir ao tocar
                        .setAutoCancel(true) //Remove a notificação após tocada
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setSmallIcon(R.drawable.baseline_notifications_active_24);

                //Acessar o serviço de notificação e exibir a notificação
                if (ActivityCompat.checkSelfPermission(Perfil.this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                servicoNotificacao.notify(1, notificacao.build());
            }
        });
    }
}
