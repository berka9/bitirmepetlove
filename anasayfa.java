package com.example.pet1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Anasayfa extends AppCompatActivity {

    public ImageButton kpkbtnilan;
    public ImageButton ekleimgbtn;
    public ImageButton kedibtnilan;
    public Button deneme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa);

        kpkbtnilan=findViewById(R.id.kpkilanbtn);


        kpkbtnilan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kpkilan = new Intent(Anasayfa.this,kopek_ilan.class);
                startActivity(kpkilan);
            }



        });

        kedibtnilan=findViewById(R.id.kedibtnilan);

        kedibtnilan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kedi_ilan= new Intent(Anasayfa.this, com.example.pet1.kedi_ilan.class);
                startActivity(kedi_ilan);
            }
        });

        ekleimgbtn=findViewById(R.id.ekleimgbtn);

        ekleimgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ekle = new Intent(Anasayfa.this, ilan_verme.class);
                startActivity(ekle);
            }
        });

        deneme=findViewById(R.id.deneme);

        deneme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent deneme = new Intent(Anasayfa.this, listkopek.class);
                startActivity(deneme);
            }
        });


    }
}
