package com.example.steering;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String SERVER_IP = "192.168.38.16"; // 서버의 IP 주소(라즈베리파이)
    private static final int SERVER_PORT = 10123; // 서버가 listening 중인 포트 번호

    private Map<Button, String> buttonMessageMap;

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 버튼과 메시지 매핑 초기화
        buttonMessageMap = new HashMap<>();
        buttonMessageMap.put(findViewById(R.id.btn_up), "up");
        buttonMessageMap.put(findViewById(R.id.btn_down), "down");
        buttonMessageMap.put(findViewById(R.id.btn_right), "right");
        buttonMessageMap.put(findViewById(R.id.btn_left), "left");
        textView=findViewById(R.id.text1);


        for (final Button button : buttonMessageMap.keySet()) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String message = buttonMessageMap.get(button);
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    SocketThread thread = new SocketThread(message);
                    thread.start();
                }
            });
        }
    }


    class SocketThread extends Thread{

        String message; // 메시지(버튼 종류)


        public SocketThread(String message){
            this.message = message;

        }

        @Override
        public void run() {

            try{


                Socket socket = new Socket(SERVER_IP, SERVER_PORT); // 소켓 열어주기

                //메시지 전송
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(message.getBytes());
                outputStream.flush();


                //소켓 닫기
                socket.close();

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }




}