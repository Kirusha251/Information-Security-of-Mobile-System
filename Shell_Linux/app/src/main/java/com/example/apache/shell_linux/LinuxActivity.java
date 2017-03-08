package com.example.apache.shell_linux;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LinuxActivity extends AppCompatActivity {

    EditText edCommand;
    Button execute;
    TextView info;
    String[] command;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linux);

        edCommand = (EditText)findViewById(R.id.editCommand);
        execute = (Button)findViewById(R.id.executeButton);
        info = (TextView)findViewById(R.id.infoView);

        execute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ShellExecuter shellExecuter = new ShellExecuter();
                    command[0] = edCommand.getText().toString();
                    if (command.equals("cls")) {
                        info.setText("");
                    }
                    //command = new String[]{"cat", "/proc/net/arp"};
                    //command = new String[]{ "cat", "/system/etc/permissions/handheld_core_hardware.xml" };
                    String output = shellExecuter.Executer(command);
                    info.setText(info.getText() + "\n" + output + "\n" + "------------------------");
                    Log.d("Output", output);
                    edCommand.setText("");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
}
