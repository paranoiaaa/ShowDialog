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
    private Button mButton1,mButton2,mButton3,mButton4;
    private ShowSelectionDialog dialog1;
    private ShowAlertDialog dialog2;
    private ShowAlertDialog dialog3;
    private ShowPromptDialog mDialog4;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        setContentView(R.layout.activity_main);
        mButton1= findViewById(R.id.button1);
        mButton2= findViewById(R.id.button2);
        mButton3= findViewById(R.id.button3);
        mButton4= findViewById(R.id.button4);
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
                .setTitleTextColor(R.color.black_light)
                .setContentText("是否关闭对话框？")
                .setContentTextColor(R.color.black_light)
                .setLeftButtonText("关闭")
                .setLeftButtonTextColor(R.color.gray)
                .setRightButtonText("不关闭")
                .setRightButtonTextColor(R.color.black_light)
                .setHintText("初始化输入文字")
                .setPromptMaxLength(5)
                .setContentText("请输入新的群组名称,最长12位")
                .setOnclickListener(new PromptClickListener() {
                    @Override
                    public void clickLeftButton(View view) {mDialog4.dismiss();
                    }

                    @Override
                    public void clickRightButton(View view,String result) {
                        Toast.makeText(MainActivity.this,result,Toast.LENGTH_SHORT).show();
                        mDialog4.dismiss();
                    }
                })
                .build();
    }

    private void initBottomDialog() {
        final ArrayList<String> s = new ArrayList<>();
        s.add("拍照");
        s.add("相册");
        dialog1 = new ShowSelectionDialog.Builder(this)
                .setlTitleVisible(false)
                .setTitleHeight(65)
                .setTitleText("please select")
                .setTitleTextSize(14)
                .setTitleTextColor(R.color.colorPrimary)
                .setItemHeight(40)
                .setItemWidth(0.9f)
                .setItemTextColor(R.color.colorPrimaryDark)
                .setItemTextSize(14)
                .setCancleButtonText("取消")
                .setOnItemListener((button, position) -> {
                    dialog1.dismiss();
                    Toast.makeText(MainActivity.this, s.get(position), Toast.LENGTH_SHORT).show();
                })
                .setCanceledOnTouchOutside(true)
                .build();
        dialog1.setDataList(s);
    }

    private void initNormalDialog(){
        dialog2 = new ShowAlertDialog.Builder(this)
                .setContentText("是否关闭对话框？")
                .setLeftButtonText("关闭")
                .setLeftButtonTextColor(R.color.gray)
                .setRightButtonText("不关闭")
                .setRightButtonTextColor(R.color.black_light)
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

    private void initNormalDialog2(){
        dialog3 = new ShowAlertDialog.Builder(this)
                .setTitleTextColor(R.color.colorPrimary)
                .setContentText("是否关闭对话框？")
                .setContentTextColor(R.color.colorPrimaryDark)
                .setSingleMode(true)
                .setSingleButtonText("关闭")
                .setSingleButtonTextColor(R.color.colorAccent)
                .setCanceledOnTouchOutside(true)
                .setSingleListener(view -> dialog3.dismiss())
                .build();
    }
}
