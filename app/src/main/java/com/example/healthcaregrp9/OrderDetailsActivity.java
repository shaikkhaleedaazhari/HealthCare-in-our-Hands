package com.example.healthcaregrp9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderDetailsActivity extends AppCompatActivity {

    private  String[][] order_details={};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btn;
    Button msg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        btn = findViewById(R.id.buttonHABack);
        lst = findViewById(R.id.listViewHACart);
        msg= findViewById(R.id.buttonBK);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderDetailsActivity.this,HomeActivity.class));
            }
        });

        Database db= new Database(getApplicationContext(),"healthcare",null,1);
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();
        ArrayList dbData = db.getorderData(username);

        order_details= new String[dbData.size()][];
        for(int i=0;i<order_details.length;i++){
        order_details[i]= new String[5];
        String arrData = dbData.get(i).toString();
        String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
        order_details[i][0]= strData[0];
        order_details[i][1]= strData[1];
        if(strData[7].compareTo("medicine")==0){
            order_details[i][3]= "Del:"+strData[4];
        }
        else{
            order_details[i][4] = "Del:"+ strData[4] + " " +strData[5];
        }
        order_details[i][2]= "Rs."+strData[6];
        order_details[i][3]=strData[7];

        }
        list = new ArrayList();
        for (int i = 0; i <  order_details.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1",  order_details[i][0]);
            item.put("line2",  order_details[i][1]);
            item.put("line3",  order_details[i][2]);
            item.put("line4",  order_details[i][3]);
            item.put("line5",  order_details[i][4]);
            list.add(item);
        }
        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "Line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d,R.id.line_e});
        lst.setAdapter(sa);

      msg.setOnClickListener(new View.OnClickListener() {
          @Override

              public void onClick(View v) {
                  // Phone number without '+' and country code
                  String phoneNumber = "7093225490";

                  // Default message
                  String message =  "LAB TEST\n" +
                          "FUll Body Checkup\n" +
                          "patient: khaleeda , Date:14/10/2023\n"+
                          "Blood Glucose Fasting\n" +
                          "patient: khaleeda , Date:15/10/2023\n" +
                          "Kidney Function Test\n" +
                          "\n" +
                          "Medicine\n" +
                          " Uprise-D3 1000IU Capsule\n" +
                          "Khaleeda Delivery Date : 15/10/2023 \n" +
                          "Dolo 650 Tablet\n" +
                          "\n" +
                          "Doctor Appointemnts\n" +
                          "Khaleeda Delivery Date : 15/10/2023 \n" +
                          " HOspital Name: sangamitra \n" +
                          " Patient Name:Khaleeda ,Del Date : 15/10/2023 , Time: 10:00 , RS: 600\n" +
                          " HOspital Name: Blosooms\n" +
                          " Patient Name:Khaleeda ,Del Date : 15/10/2023 , Time: 10:00 \n"
                          ;

                  // Create an Intent with the appropriate action and data
                  Intent intent = new Intent(Intent.ACTION_VIEW);
                  String url = "https://api.whatsapp.com/send?phone=" + phoneNumber + "&text=" + message;
                  intent.setData(Uri.parse(url));
                    startActivity(intent);}
      });



    }


}