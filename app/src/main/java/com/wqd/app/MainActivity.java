package com.wqd.app;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wqd.app.dialog.ShowAlertDialog;
import com.wqd.app.dialog.ShowPromptDialog;
import com.wqd.app.dialog.ShowSelectionDialog;
import com.wqd.app.listener.DialogOnClickListener;
import com.wqd.app.listener.DialogOnItemClickListener;
import com.wqd.app.listener.PromptClickListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button mButton1, mButton2, mButton3, mButton4;
    private ShowSelectionDialog dialog1;
    private ShowAlertDialog dialog2;
    private ShowAlertDialog dialog3;
    private ShowPromptDialog mDialog4;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);
        mButton1 = findViewById(R.id.button1);
        mButton2 = findViewById(R.id.button2);
        mButton3 = findViewById(R.id.button3);
        mButton4 = findViewById(R.id.button4);
        mButton1.setOnClickListener(view -> dialog1.show());
        mButton2.setOnClickListener(view -> dialog2.show());
        mButton3.setOnClickListener(view -> dialog3.show());
        mButton4.setOnClickListener(view -> mDialog4.show());
        initBottomDialog();
        initNormalDialog();
        initNormalDialog2();
        iniPromptDialog();
    }

    private void iniPromptDialog() {
        mDialog4 = new ShowPromptDialog.Builder(this)
                .setContentText("是否关闭对话框？")
                .setHintText("初始化输入文字")
                .setPromptMaxLength(12)
                .setContentText("请输入新的群组名称,最长12位")
                .setOnclickListener(new PromptClickListener() {
                    @Override
                    public void clickLeftButton(View view) {

                    }

                    @Override
                    public void clickRightButton(View view, String result) {
                        Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                    }
                })
                .build();
    }

    private void initBottomDialog() {
        final ArrayList<String> s = new ArrayList<>();
        s.add("拍照");
        s.add("相册");
        dialog1=new ShowSelectionDialog.Builder(this)
                .setlTitleVisible(false)
                .setTitleText("please select")
                .setCancleButtonText("取消")
                .setOnItemListener((button, position) -> {
                    Toast.makeText(MainActivity.this, s.get(position), Toast.LENGTH_SHORT).show();
                })
                .setCanceledOnTouchOutside(true)
                .build();
        dialog1.setDataList(s);
    }

    private void initNormalDialog() {
        dialog2 = new ShowAlertDialog.Builder(this)
                .setContentText("是否关闭对话框？")
                .setOnclickListener(new DialogOnClickListener() {
                    @Override
                    public void clickLeftButton(View view) {
                    }

                    @Override
                    public void clickRightButton(View view) {

                    }
                })
                .build();
    }

    private void initNormalDialog2() {
        dialog3 = new ShowAlertDialog.Builder(this)
                .setContentText("是否关闭对话框？")
                .setSingleMode(true)
                .setSingleButtonTextColor(R.color.colorAccent)
                .setCanceledOnTouchOutside(true)
                .setSingleListener(view -> {
                })
                .build();
    }
}
