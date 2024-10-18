package com.miracakkoyun.isimsehiroyunu;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;
public class NormalOyunActivity extends AppCompatActivity {
    private TextView txtSehirBilgi,txtSehirAdUzunlugu,txtSkor;
    private EditText editTxtTahmin;
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
    private int rndIlNumber,rndNumberHarf,baslangicHarfSayisi;
    private String gelenIl,ilBoyutu,editTxtGelenTahmin;
    private ArrayList<Character> ilHarfleri;
    private double totalPuan=0,soruPuan=100,azaltilacakPuan=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_normal_oyun);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtSehirBilgi=findViewById(R.id.txtSehirBilgi);
        txtSehirAdUzunlugu=findViewById(R.id.txtSehirAdUzunlugu);
        editTxtTahmin=findViewById(R.id.editTxtTahmin);
        txtSkor=findViewById(R.id.txtSkor);
        rndHarf=new Random();
        randomDegerleriBelirle();

    }
    public void btnTahmin(View view){
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
    public void btnHarfAl(View view){
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
    private void randomDegerleriBelirle(){

        ilBoyutu="";
        rndIl=new Random();
        rndIlNumber=rndIl.nextInt(iller.length);
        gelenIl=iller[rndIlNumber];
        System.out.println(gelenIl);
        txtSehirBilgi.setText(gelenIl.length()+" Harfli İlimiz");

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
    public void btnexits(View view){
        Intent exitMainMenuS=new Intent(this,MainActivity.class);
        finish();
        startActivity(exitMainMenuS);
    }

}
