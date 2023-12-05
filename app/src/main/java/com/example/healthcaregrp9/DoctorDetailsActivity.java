package com.example.healthcaregrp9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1={
            {"Doctor Name:vishnu","Hospital Name: sangamithra", "Exp: 5yrs","Mobile No:6302352643","600"},
            {"Doctor Name:usha","Hospital Name: Kiims", "Exp: 7yrs","Mobile No:6302352643","900"},
            {"Doctor Name:chaitanya", "Hospital Name: Ramesh","Exp: 5yrs","Mobile No:6302352643","300"},
            {"Doctor Name:sai dhanush","Hospital Name: apollo", "Exp: 6yrs","Mobile No:6302352643","700"},
            {"Doctor Name:Afsha", "Hospital Name: blosooms","Exp: 8yrs","Mobile No:6302352643","1200"},
    };
    private String[][] doctor_details2={
            {"Doctor Name:Arjun","Hospital Name: sangamithra", "Exp: 5yrs","Mobile No:6302352643","600"},
            {"Doctor Name:Keerthy","Hospital Name: Kiims", "Exp: 7yrs","Mobile No:6302352643","900"},
            {"Doctor Name:Anjali", "Hospital Name: abc","Exp: 5yrs","Mobile No:6302352643","300"},
            {"Doctor Name:sai sai Venu","Hospital Name: apollo", "Exp: 6yrs","Mobile No:6302352643","700"},
            {"Doctor Name:Mahesh", "Hospital Name: blosooms","Exp: 8yrs","Mobile No:6302352643","1200"},
    };
    private String[][] doctor_details3={
            {"Doctor Name:Goutham","Hospital Name: sangamithra", "Exp: 5yrs","Mobile No:6302352643","600"},
            {"Doctor Name:Sai sreenivas","Hospital Name: Kiims", "Exp: 7yrs","Mobile No:6302352643","900"},
            {"Doctor Name:Aravindh", "Hospital Name: abc","Exp: 5yrs","Mobile No:6302352643","300"},
            {"Doctor Name:sai vishal","Hospital Name: apollo", "Exp: 6yrs","Mobile No:6302352643","700"},
            {"Doctor Name:sheema", "Hospital Name: blosooms","Exp: 8yrs","Mobile No:6302352643","1200"},
    };
    private String[][] doctor_details4={
            {"Doctor Name:vishnu","Hospital Name: sangamithra", "Exp: 5yrs","Mobile No:6302352643","600"},
            {"Doctor Name:usha","Hospital Name: Kiims", "Exp: 7yrs","Mobile No:6302352643","900"},
            {"Doctor Name:kesary", "Hospital Name: abc","Exp: 5yrs","Mobile No:6302352643","300"},
            {"Doctor Name:sai dhanush","Hospital Name: apollo", "Exp: 6yrs","Mobile No:6302352643","700"},
            {"Doctor Name:Afsha", "Hospital Name: blosooms","Exp: 8yrs","Mobile No:6302352643","1200"},
    };
    private String[][] doctor_details5={
            {"Doctor Name:vishnu","Hospital Name: sangamithra", "Exp: 5yrs","Mobile No:6302352643","600"},
            {"Doctor Name:usha","Hospital Name: Kiims", "Exp: 7yrs","Mobile No:6302352643","900"},
            {"Doctor Name:kesary", "Hospital Name: abc","Exp: 5yrs","Mobile No:6302352643","300"},
            {"Doctor Name:sai dhanush","Hospital Name: apollo", "Exp: 6yrs","Mobile No:6302352643","700"},
            {"Doctor Name:Afsha", "Hospital Name: blosooms","Exp: 8yrs","Mobile No:6302352643","1200"},
    };
    TextView tv;
    Button btn;
    String[][] doctor_details={};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        tv = findViewById(R.id.textViewHATitle);
        btn = findViewById(R.id.buttonHABack);
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);
        if (title.compareTo("Physicians") == 0)
            doctor_details = doctor_details1;
        else if (title.compareTo("Dietician") == 0)
            doctor_details = doctor_details2;
        else if (title.compareTo("Dentist") == 0)
            doctor_details = doctor_details3;
        else if (title.compareTo("Surgeon") == 0)
            doctor_details = doctor_details4;
        else
            doctor_details = doctor_details5;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });
        list = new ArrayList();
        for (int i = 0; i < doctor_details.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", doctor_details[i][3]);
            item.put("line5", "Cons fees:" + doctor_details[i][4] + "/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "Line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d,R.id.line_e}
        );
        ListView lst = findViewById(R.id.listViewHACart);
        lst.setAdapter(sa);

        lst.setOnItemClickListener( new AdapterView.OnItemClickListener() {
          @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
              Intent it= new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
              it.putExtra("text1",title);
              it.putExtra("text2",doctor_details[i][0]);
              it.putExtra("text3",doctor_details[i][1]);
              it.putExtra("text4",doctor_details[i][3]);
              it.putExtra("text5",doctor_details[i][4]);

              startActivity(it);
          }
        });
    }
}