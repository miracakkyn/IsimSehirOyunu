package com.miracakkoyun.isimsehiroyunu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;

public class SureliOyunActivity extends AppCompatActivity {
    private TextView txtSure,txtSehirAdUzunlugu,txtSkor,textGameOver;
    private EditText editTxtTahmin;
    private Button btnHarfAlS,btnTahminS,btnTekrarOyna,btnExitS;
    private String[] iller = {
            "Adana", "Adıyaman", "Afyonkarahisar", "Ağrı", "Aksaray", "Amasya", "Ankara", "Antalya", "Ardahan", "Artvin",
            "Aydın", "Balıkesir", "Bartın", "Batman", "Bayburt", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur",
            "Bursa", "Çanakkale", "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Düzce", "Edirne", "Elazığ", "Erzincan",
            "Erzurum", "Eskişehir", "Gaziantep", "Giresun", "Gümüşhane", "Hakkari", "Hatay", "Iğdır", "Isparta", "İstanbul",
            "İzmir", "Kahramanmaraş", "Karabük", "Karaman", "Kars", "Kastamonu", "Kayseri", "Kırıkkale", "Kırklareli", "Kırşehir",
            "Kilis", "Kocaeli", "Konya", "Kütahya", "Malatya", "Manisa", "Mardin", "Mersin", "Muğla", "Muş",
            "Nevşehir", "Niğde", "Ordu", "Osmaniye", "Rize", "Sakarya", "Samsun", "Siirt", "Sinop", "Sivas",
            "Şanlıurfa", "Şırnak", "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Uşak", "Van", "Yalova", "Yozgat",
            "Zonguldak"
    };
    private Random rndIl,rndHarf;
    private int rndIlNumber,rndNumberHarf,baslangicHarfSayisi,toplamSure=180000,yedekTime=9;
    private String gelenIl,ilBoyutu,editTxtGelenTahmin;
    private ArrayList<Character> ilHarfleri;
    private double totalPuan=0,soruPuan=100,azaltilacakPuan=0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sureli_oyun);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtSure=findViewById(R.id.txtSure);
        txtSehirAdUzunlugu=findViewById(R.id.txtSehirAdUzunluguS);
        editTxtTahmin=findViewById(R.id.editTxtTahminS);
        txtSkor=findViewById(R.id.txtSkorS);
        rndHarf=new Random();
        btnHarfAlS=findViewById(R.id.btnHarfAlS);
        btnTahminS=findViewById(R.id.btnTahminS);
        btnTekrarOyna=findViewById(R.id.btnTekrarOyna);
        btnTekrarOyna.setVisibility(View.INVISIBLE);
        randomDegerleriBelirle();
        textGameOver=findViewById(R.id.oyunBitti);
        textGameOver.setVisibility(View.INVISIBLE);
        btnExitS=findViewById(R.id.btnExitS);
        new CountDownTimer(toplamSure, 1000) {
            @Override
            public void onTick(long l) {
                if(((l%60000)/1000)<10){
                    txtSure.setText((l/60000)+":0"+yedekTime);
                    yedekTime-=1;
                }else{
                    txtSure.setText((l/60000)+":"+(l%60000)/1000);
                    yedekTime=9;
                }


            }

            @Override
            public void onFinish() {
                btnHarfAlS.setVisibility(View.INVISIBLE);
                btnTahminS.setVisibility(View.INVISIBLE);
                editTxtTahmin.setVisibility(View.INVISIBLE);
                textGameOver.setVisibility(View.VISIBLE);
                btnTekrarOyna.setVisibility(View.VISIBLE);

            }
        }.start();
    }
    public void btnHarfAlS(View view){
        if(ilHarfleri.size()>0){
            soruPuan-=10;
            rndNumberHarf=rndHarf.nextInt(ilHarfleri.size());
            String[] txtHarfler=txtSehirAdUzunlugu.getText().toString().split(" ");
            char[] gelenIlHarfler=gelenIl.toCharArray();
            for(int i =0;i<gelenIl.length();i++){
                if(txtHarfler[i].equals("_")&&gelenIlHarfler[i]==ilHarfleri.get(rndNumberHarf)){
                    txtHarfler[i]=String.valueOf(ilHarfleri.get(rndNumberHarf));
                    ilBoyutu="";
                    for(int j=0;j<gelenIl.length();j++){
                        if(j==i){

                            ilBoyutu+=txtHarfler[j]+" ";
                        }else if(j<gelenIl.length()-1){
                            ilBoyutu+=txtHarfler[j]+" ";
                        }else {
                            ilBoyutu+=txtHarfler[j];
                        }
                    }
                    break;
                }
            }

            txtSehirAdUzunlugu.setText(ilBoyutu);
            ilHarfleri.remove(rndNumberHarf);

        }
    }
    public void btnTahminS(View view){
        editTxtGelenTahmin=editTxtTahmin.getText().toString();
        if(!TextUtils.isEmpty(editTxtGelenTahmin)){
            if(editTxtGelenTahmin.equals(gelenIl)){
                totalPuan+=soruPuan;
                txtSkor.setText("Skor : "+totalPuan);
                randomDegerleriBelirle();
            }else {
                System.out.println("Hatalı Tahmin");

            }
        }else{
            System.out.println("Tahmin boş olamaz");
        }
        editTxtTahmin.setText("");
    }
    private void randomDegerleriBelirle(){

        ilBoyutu="";
        rndIl=new Random();
        rndIlNumber=rndIl.nextInt(iller.length);
        gelenIl=iller[rndIlNumber];
        System.out.println(gelenIl);


        if(gelenIl.length()>=5 && gelenIl.length()<=7){
            baslangicHarfSayisi=1;
        }else if(gelenIl.length()>7 && gelenIl.length()<=10){
            baslangicHarfSayisi=2;

        }else if(gelenIl.length()>10){
            baslangicHarfSayisi=3;
        }
        for (int i=0;i < gelenIl.length();i++){
            if(i<gelenIl.length()-1){
                ilBoyutu+="_ ";
            }else{
                ilBoyutu+="_";
            }

        }

        txtSehirAdUzunlugu.setText(ilBoyutu);
        ilHarfleri=new ArrayList<>();
        for (char c:gelenIl.toCharArray()) {
            ilHarfleri.add(c);

        }
        for (int c = 0; c <baslangicHarfSayisi; c++) {
            baslangicHarfGetir();
        }
        azaltilacakPuan=100/ilHarfleri.size();
        soruPuan=100-(azaltilacakPuan*baslangicHarfSayisi);
    }
    private void baslangicHarfGetir(){
        rndNumberHarf=rndHarf.nextInt(ilHarfleri.size());
        String[] txtHarfler=txtSehirAdUzunlugu.getText().toString().split(" ");
        char[] gelenIlHarfler=gelenIl.toCharArray();
        for(int i =0;i<gelenIl.length();i++){
            if(txtHarfler[i].equals("_")&&gelenIlHarfler[i]==ilHarfleri.get(rndNumberHarf)){
                txtHarfler[i]=String.valueOf(ilHarfleri.get(rndNumberHarf));
                ilBoyutu="";
                for(int j=0;j<gelenIl.length();j++){
                    if(j==i){

                        ilBoyutu+=txtHarfler[j]+" ";
                    }else if(j<gelenIl.length()-1){
                        ilBoyutu+=txtHarfler[j]+" ";
                    }else {
                        ilBoyutu+=txtHarfler[j];
                    }
                }
                break;
            }
        }

        txtSehirAdUzunlugu.setText(ilBoyutu);
        ilHarfleri.remove(rndNumberHarf);

    }
    public void btnTekrarOynaS(View view){
        Intent tekrarOyna=new Intent(this, SureliOyunActivity.class);
        finish();
        startActivity(tekrarOyna);
    }
    public void btnexit(View view){
        Intent exitMainMenu=new Intent(this,MainActivity.class);
        finish();
        startActivity(exitMainMenu);
    }


}