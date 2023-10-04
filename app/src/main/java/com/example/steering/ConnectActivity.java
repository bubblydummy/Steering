package com.example.steering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ConnectActivity extends AppCompatActivity {

    Button btn_connect;
    EditText rasp_ip,rasp_port;
    String str_rasp_ip,str_rasp_port;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        btn_connect=findViewById(R.id.btn_connect);
// ----------------<연결버튼 클릭>
        btn_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rasp_ip=findViewById(R.id.edit_ip);
                rasp_port=findViewById(R.id.edit_port);
                str_rasp_ip=rasp_ip.getText().toString();
                str_rasp_port=rasp_port.getText().toString();
                Intent intent = new Intent(ConnectActivity.this, MainActivity.class);

                intent.putExtra("rasp_ip", str_rasp_ip);//id값 rasp_ip
                intent.putExtra("rasp_port", str_rasp_port);//id값 rasp_port

                startActivity(intent);
            }
        });
    }


}