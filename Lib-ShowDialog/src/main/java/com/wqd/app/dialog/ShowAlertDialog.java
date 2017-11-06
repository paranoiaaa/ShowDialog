package com.wqd.app.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.wqd.app.listener.DialogOnClickListener;
import com.wqd.app.util.ScreenSizeUtils;
import com.wqd.app.showdialog.R;

/**
 * Created by Paranoia on 16-12-10.
 */

public class ShowAlertDialog implements View.OnClickListener {
    private TextView mTitle;
    private TextView mContent;
    private Button mLeftBtn;
    private Button mRightBtn;
    private Button mSingleBtn;
    private TextView mLine;
    private Dialog mDialog;
    private View mDialogView;
    private Builder mBuilder;

    public ShowAlertDialog(Builder builder) {

        this.mBuilder = builder;
        mDialog = new Dialog(mBuilder.getContext(), R.style.NormalDialogStyle);
        mDialogView = View.inflate(mBuilder.getContext(), R.layout.widget_dialog_normal, null);
        mTitle = mDialogView.findViewById(R.id.dialog_normal_title);
        mContent = mDialogView.findViewById(R.id.dialog_normal_content);
        mLeftBtn = mDialogView.findViewById(R.id.dialog_normal_leftbtn);
        mRightBtn = mDialogView.findViewById(R.id.dialog_normal_rightbtn);
        mSingleBtn = mDialogView.findViewById(R.id.dialog_normal_midbtn);
        mLine = mDialogView.findViewById(R.id.dialog_normal_line);
        mDialogView.setMinimumHeight((int) (ScreenSizeUtils.getInstance(mBuilder.getContext())
                .getScreenHeight() * builder.getHeight()));
        mDialog.setContentView(mDialogView);

        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenSizeUtils.getInstance(mBuilder.getContext()).getScreenWidth() *
                builder.getWidth());
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
        initDialog(builder);
    }

    private void initDialog(Builder builder) {
        mDialog.setCanceledOnTouchOutside(builder.isTouchOutside());
        mDialog.setCancelable(false);
        if (builder.getTitleVisible()) {
            mTitle.setVisibility(View.VISIBLE);
        } else {
            mTitle.setVisibility(View.GONE);
        }
        if (builder.isSingleMode()) {
            mSingleBtn.setVisibility(View.VISIBLE);
            mLine.setVisibility(View.GONE);
            mLeftBtn.setVisibility(View.GONE);
            mRightBtn.setVisibility(View.GONE);
        }
        mTitle.setText(builder.getTitleText());
        mTitle.setTextColor(builder.getTitleTextColor());
        mTitle.setTextSize(builder.getTitleTextSize());
        mContent.setText(builder.getContentText());
        mContent.setTextColor(builder.getContentTextColor());
        mContent.setTextSize(builder.getContentTextSize());
        mLeftBtn.setText(builder.getLeftButtonText());
        mLeftBtn.setTextColor(builder.getLeftButtonTextColor());
        mLeftBtn.setTextSize(builder.getButtonTextSize());
        mRightBtn.setText(builder.getRightButtonText());
        mRightBtn.setTextColor(builder.getRightButtonTextColor());
        mRightBtn.setTextSize(builder.getButtonTextSize());
        mSingleBtn.setText(builder.getSingleButtonText());
        mSingleBtn.setTextColor(builder.getSingleButtonTextColor());
        mSingleBtn.setTextSize(builder.getButtonTextSize());
        mLeftBtn.setOnClickListener(this);
        mRightBtn.setOnClickListener(this);
        mSingleBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.dialog_normal_leftbtn && mBuilder.getOnclickListener() != null) {
            mBuilder.getOnclickListener().clickLeftButton(mLeftBtn);
            mDialog.dismiss();
            return;
        }
        if (i == R.id.dialog_normal_rightbtn && mBuilder.getOnclickListener() != null) {

            mBuilder.getOnclickListener().clickRightButton(mRightBtn);
            mDialog.dismiss();
            return;
        }
        if (i == R.id.dialog_normal_midbtn && mBuilder.getSingleListener() != null) {

            mBuilder.getSingleListener().onClick(mSingleBtn);
            mDialog.dismiss();
            return;
        }

    }

    public void show() {
        mDialog.show();
    }

    public void dismiss() {
        mDialog.dismiss();
    }

    public static class Builder {

        private String titleText;
        private int titleTextColor;
        private int titleTextSize;
        private String contentText;
        private int contentTextColor;
        private int contentTextSize;
        private boolean isSingleMode;
        private String singleButtonText;
        private int singleButtonTextColor;
        private String leftButtonText;
        private int leftButtonTextColor;
        private String rightButtonText;
        private int rightButtonTextColor;
        private int buttonTextSize;
        private DialogOnClickListener onclickListener;
        private View.OnClickListener singleListener;
        private boolean isTitleVisible;
        private boolean isTouchOutside;
        private float height;
        private float width;
        private Context mContext;

        public Builder(Context context) {
            mContext = context;
            titleText = "温馨提示";
            titleTextColor = ContextCompat.getColor(mContext, R.color.black_light);
            contentText = "";
            contentTextColor = ContextCompat.getColor(mContext, R.color.black_light);
            isSingleMode = false;
            singleButtonText = "确定";
            singleButtonTextColor = ContextCompat.getColor(mContext, R.color.black_light);
            leftButtonText = "取消";
            leftButtonTextColor = ContextCompat.getColor(mContext, R.color.gray);
            rightButtonText = "确定";
            rightButtonTextColor = ContextCompat.getColor(mContext, R.color.themeColor);
            onclickListener = null;
            singleListener = null;
            isTitleVisible = true;
            isTouchOutside = false;
            height = 0.24f;
            width = 0.66f;
            titleTextSize = 16;
            contentTextSize = 14;
            buttonTextSize = 14;
        }

        Context getContext() {

            return mContext;
        }

        String getTitleText() {
            return titleText;
        }

        public Builder setTitleText(String titleText) {
            this.titleText = titleText;
            return this;
        }

        int getTitleTextColor() {
            return titleTextColor;
        }

        public Builder setTitleTextColor(@ColorRes int titleTextColor) {
            this.titleTextColor = ContextCompat.getColor(mContext, titleTextColor);
            return this;
        }

        String getContentText() {
            return contentText;
        }

        public Builder setContentText(String contentText) {
            this.contentText = contentText;
            return this;
        }

        int getContentTextColor() {
            return contentTextColor;
        }

        public Builder setContentTextColor(@ColorRes int contentTextColor) {
            this.contentTextColor = ContextCompat.getColor(mContext, contentTextColor);
            return this;
        }

        boolean isSingleMode() {
            return isSingleMode;
        }

        public Builder setSingleMode(boolean singleMode) {
            isSingleMode = singleMode;
            return this;
        }

        String getSingleButtonText() {
            return singleButtonText;
        }

        public Builder setSingleButtonText(String singleButtonText) {
            this.singleButtonText = singleButtonText;
            return this;
        }

        int getSingleButtonTextColor() {
            return singleButtonTextColor;
        }

        public Builder setSingleButtonTextColor(@ColorRes int singleButtonTextColor) {
            this.singleButtonTextColor = ContextCompat.getColor(mContext, singleButtonTextColor);
            return this;
        }

        String getLeftButtonText() {
            return leftButtonText;
        }

        public Builder setLeftButtonText(String leftButtonText) {
            this.leftButtonText = leftButtonText;
            return this;
        }

        int getLeftButtonTextColor() {
            return leftButtonTextColor;
        }

        public Builder setLeftButtonTextColor(@ColorRes int leftButtonTextColor) {
            this.leftButtonTextColor = ContextCompat.getColor(mContext, leftButtonTextColor);
            return this;
        }

        String getRightButtonText() {
            return rightButtonText;
        }

        public Builder setRightButtonText(String rightButtonText) {
            this.rightButtonText = rightButtonText;
            return this;
        }

        int getRightButtonTextColor() {
            return rightButtonTextColor;
        }

        public Builder setRightButtonTextColor(@ColorRes int rightButtonTextColor) {
            this.rightButtonTextColor = ContextCompat.getColor(mContext, rightButtonTextColor);
            return this;
        }

        DialogOnClickListener getOnclickListener() {
            return onclickListener;
        }

        public Builder setOnclickListener(DialogOnClickListener onclickListener) {
            this.onclickListener = onclickListener;
            return this;
        }

        View.OnClickListener getSingleListener() {
            return singleListener;
        }

        public Builder setSingleListener(View.OnClickListener singleListener) {
            this.singleListener = singleListener;
            return this;
        }

        boolean getTitleVisible() {
            return isTitleVisible;
        }

        public Builder setTitleVisible(boolean isVisible) {
            isTitleVisible = isVisible;
            return this;
        }

        boolean isTouchOutside() {
            return isTouchOutside;
        }

        public Builder setCanceledOnTouchOutside(boolean isTouchOutside) {

            this.isTouchOutside = isTouchOutside;
            return this;
        }

        int getContentTextSize() {
            return contentTextSize;
        }

        public Builder setContentTextSize(int contentTextSize) {
            this.contentTextSize = contentTextSize;
            return this;
        }

        int getTitleTextSize() {
            return titleTextSize;
        }

        public Builder setTitleTextSize(int titleTextSize) {
            this.titleTextSize = titleTextSize;
            return this;
        }

        int getButtonTextSize() {
            return buttonTextSize;
        }

        public Builder setButtonTextSize(int buttonTextSize) {
            this.buttonTextSize = buttonTextSize;
            return this;
        }

        float getHeight() {
            return height;
        }

        public Builder setHeight(float height) {
            this.height = height;
            return this;
        }

        float getWidth() {
            return width;
        }

        public Builder setWidth(float width) {
            this.width = width;
            return this;
        }

        public ShowAlertDialog build() {

            return new ShowAlertDialog(this);
        }
    }
}