package com.example.consumir_json1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnClic_btnIngresar(View view){
        final EditText Usuario = (EditText)findViewById(R.id.txtUsuario);
        final EditText Clave = (EditText)findViewById(R.id.txtClave);
        Usuario.setError(null);
        Clave.setError(null);
        String Url="http://uealecpeterson.net/ws/login.php?usr="+ Usuario.getText() + "&pass=" + Clave.getText();

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("Login Correcto!")){
                    Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                    Bundle b = new Bundle();
                    b.putString("Usuario", Usuario.getText().toString());
                    //Añadimos la información al intent
                    intent.putExtras(b);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Usuario o Contraseña incorrectos", Toast.LENGTH_SHORT).show();
                    Usuario.setError("Usuario desconocido");
                    Clave.setError("Contraseña incorrecta");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                }
        });
        requestQueue.add(stringRequest);
    }
}