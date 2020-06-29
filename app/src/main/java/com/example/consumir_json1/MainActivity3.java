package com.example.consumir_json1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        TextView Usuario = (TextView)findViewById(R.id.lblUsuario2);
        Bundle bundle = this.getIntent().getExtras();
        Usuario.setText(bundle.getString("Usuario"));

        final ListView ListaBancos = (ListView) findViewById(R.id.ltMostarBancos);
        Retrofit retrofit   = new Retrofit.Builder()
                .baseUrl(ApiRetrofit.Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiRetrofit apiRetrofit = retrofit.create(ApiRetrofit.class);
        Call<List<Banco>> call = apiRetrofit.getBankList();
        call.enqueue(new Callback<List<Banco>>(){
            @Override
            public void onResponse(Call<List<Banco>> call, retrofit2.Response<List<Banco>> response) {
                List<Banco> bancos = response.body();

                for(Banco b: bancos){
                    Log.d("name",b.getName());
                }

                String[] NombreBanco = new String[bancos.size()];
                for (int i =0; i<bancos.size();i++){
                    NombreBanco[i]=bancos.get(i).getName();
                }

                ListaBancos.setAdapter(
                        new ArrayAdapter<String>(getApplicationContext(),R.layout.lista,NombreBanco)
                );
            }

            @Override
            public void onFailure(Call<List<Banco>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}