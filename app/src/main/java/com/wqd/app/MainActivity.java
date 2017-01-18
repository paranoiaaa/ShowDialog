package com.wqd.app;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton1= (Button) findViewById(R.id.button1);
        mButton2= (Button) findViewById(R.id.button2);
        mButton3= (Button) findViewById(R.id.button3);
        mButton4= (Button) findViewById(R.id.button4);

        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.show();

            }
        });
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2.show();

            }
        });
        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog3.show();

            }
        });
        mButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog4.show();

            }
        });

        initBottomDialog();
        initNormalDialog();
        initNormalDialog2();
        iniPromptDialog();

    }

    private void iniPromptDialog() {
        mDialog4 = new ShowPromptDialog.Builder(MainActivity.this)
                .setHeight(0.23f)  //屏幕高度*0.23
                .setWidth(0.65f)  //屏幕宽度*0.65
                .setTitleVisible(true)
                .setTitleText("温馨提示")
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
                .setlTitleVisible(false)   //设置是否显示标题
                .setTitleHeight(65)   //设置标题高度
                .setTitleText("please select")  //设置标题提示文本
                .setTitleTextSize(14) //设置标题字体大小 sp
                .setTitleTextColor(R.color.colorPrimary) //设置标题文本颜色
                .setItemHeight(40)  //设置item的高度
                .setItemWidth(0.9f)  //屏幕宽度*0.9
                .setItemTextColor(R.color.colorPrimaryDark)  //设置item字体颜色
                .setItemTextSize(14)  //设置item字体大小
                .setCancleButtonText("取消")  //设置最底部“取消”按钮文本
                .setOnItemListener(new DialogOnItemClickListener() {  //监听item点击事件
                    @Override
                    public void onItemClick(Button button, int position) {

                        dialog1.dismiss();
                        Toast.makeText(MainActivity.this, s.get(position), Toast.LENGTH_SHORT).show();
                    }
                })
                .setCanceledOnTouchOutside(true)  //设置是否可点击其他地方取消dialog
                .build();


        dialog1.setDataList(s);


    }

    private void initNormalDialog(){

        dialog2 = new ShowAlertDialog.Builder(MainActivity.this)
                .setHeight(0.23f)  //屏幕高度*0.23
                .setWidth(0.65f)  //屏幕宽度*0.65
                .setTitleVisible(true)
                .setTitleText("温馨提示")
                .setTitleTextColor(R.color.black_light)
                .setContentText("是否关闭对话框？")
                .setContentTextColor(R.color.black_light)
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

        dialog3 = new ShowAlertDialog.Builder(MainActivity.this)
                .setHeight(0.23f)  //屏幕高度*0.23
                .setWidth(0.65f)  //屏幕宽度*0.65
                .setTitleVisible(true)
                .setTitleText("温馨提示")
                .setTitleTextColor(R.color.colorPrimary)
                .setContentText("是否关闭对话框？")
                .setContentTextColor(R.color.colorPrimaryDark)
                .setSingleMode(true)
                .setSingleButtonText("关闭")
                .setSingleButtonTextColor(R.color.colorAccent)
                .setCanceledOnTouchOutside(true)
                .setSingleListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog3.dismiss();
                    }
                })
                .build();

    }
}
