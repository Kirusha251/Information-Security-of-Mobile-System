package com.example.kirill.linux15api;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import static android.R.id.input;
import static java.lang.System.out;

public class MainActivity extends AppCompatActivity {


    EditText input;
    Button btn;
    TextView out;
    String command;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (EditText)findViewById(R.id.txt);
        btn = (Button)findViewById(R.id.btn);
        out = (TextView)findViewById(R.id.out);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                ShellExecuter exe = new ShellExecuter();
                command = input.getText().toString();
                String outp = exe.Executer(command);

                Log.d("Output", outp);
                //runCommand(echo("3darova"));

            }
        });

    }

//    public void runCommand(final String command) {
//
//        // Чтобы не вис интерфейс, запускаем в другом потоке
//        new Thread(new Runnable() {
//            public void run() {
//                OutputStream out = null;
//                InputStream in = null;
//                try {
//                    // Отправляем скрипт в рантайм процесс
//                    Process child = Runtime.getRuntime().exec(new String[] { "su", "-c", "system/bin/sh" });
//                    DataOutputStream stdin = new DataOutputStream(child.getOutputStream());
////Скрипт
//                    stdin.writeBytes(command);
//                    // Выходной и входной потоки
//                    out = child.getOutputStream();
//                    in = child.getInputStream();
//
//                    //Входной поток может что-нибудь вернуть
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
//                    String line;
//                    String result = "";
//                    while ((line = bufferedReader.readLine()) != null)
//                        result += line;
//
//                    //Обработка того, что он вернул
//                    handleBashCommandsResult(result);
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } finally {
//                    if (in != null) {
//                        try {
//                            in.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    if (out != null) {
//                        try {
//                            out.flush();
//                            out.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }).start();
//    }
//    private void handleBashCommandsResult(String result) {
//
//        if (result.contains("SCRIPT_FINISHED")) {
//            //Здесь делаем всё что хотели сделать после завершение скрипта
//            out.setText(result);
//        } else if (true){
//            //А вот здесь можно сделать что-нибудь после другого скрипта
//        } else {
//            out.setText(result);
//            //А вот здесь можно сделать всё остальное
//        }
//    }
//
//    public static String doSleep(int i) {
//        return "adb shell 'sleep " + i + "' ;";
//    }
//
//
//    public static String doSwipe(int x1, int y1, int x2, int y2) {
//        return "adb shell input swipe " + x1 + " " + y1 + " " + x2 + " " + y2 + " ;";
//    }
//
//
//    public static String doTap(int x, int y) {
//        return "adb shell input tap " + x + " " + y + " ;";
//    }
//
//    public static String doInputText(String text) {
//        return "adb shell input text " + text + " ;";
//    }
//
//
//    public static String doInputKeyevent(int keycode) {
//        return "adb shell input keyevent " + keycode + " ;";
//    }
//
//
//    public static String echo(String message) {
//        return "echo '" + message + "' ;";
//    }
}
