package com.example.healthcareproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuyMedicineDetailsActivity extends AppCompatActivity {


    TextView tvPackageName,tvTotalCost;
    EditText edDetails;
    Button btnBack,btnAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_details);

        tvPackageName=findViewById(R.id.textViewBuyCartMedicine);
        tvTotalCost=findViewById(R.id.textViewBuyCartMedicineTotalCost);

        edDetails=findViewById(R.id.editTextMultilineBuyCartMedicine);
        edDetails.setKeyListener(null);

        btnBack=findViewById(R.id.buttonBuyCartMedicineBack);
        btnAddToCart=findViewById(R.id.buttonBuyCartMedicineCheckout);



        Intent intent=getIntent();
        tvPackageName.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("Total Cost :"+ intent.getStringExtra("text3")+"/-");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineDetailsActivity.this,BuyMedicineActivity.class));
            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        SharedPreferences sharedPreferences= getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","".toString());
        String product = tvPackageName.getText().toString();
        float price = Float.parseFloat(intent.getStringExtra("text3").toString());



        Database db = new Database(getApplicationContext(),"Healthcare",null,1);
        if(db.checkCart(username,product)==1){
            Toast.makeText(getApplicationContext(),"Product Already Added",Toast.LENGTH_SHORT).show();

        }else{

            db.addCart(username,product,price,"medicine");
            Toast.makeText(getApplicationContext(),"Record Inserted to Cart",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(BuyMedicineDetailsActivity.this,BuyMedicineActivity.class));
        }

            }
        });






    }
}