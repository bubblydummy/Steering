package com.example.steering;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private static final String SERVER_IP = "192.168.191.16"; // 서버의 IP 주소
    private static final int SERVER_PORT = 10123; // 서버가 listening 중인 포트 번호

    private TextView textView;
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket(SERVER_IP, SERVER_PORT);

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Socket connected!", Toast.LENGTH_SHORT).show();
                            textView.setText("Connected");
                        }
                    });

                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    final String receivedMessage = reader.readLine();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText("Received Message: " + receivedMessage);
                        }
                    });

                    Thread.sleep(3000); // 3초 대기

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText("3 Seconds Wait Complete");
                        }
                    });


                    socket.close();



                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                    Log.e("error", "An error occurred:", e);
                }
            }
        }).start();
    }
}