package com.wqd.app.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wqd.app.listener.PromptClickListener;
import com.wqd.app.showdialog.R;
import com.wqd.app.util.ScreenSizeUtils;

/**
 * Created by Queen on 17-1-4.
 * 邮箱：wqd_1994@163.com
 */

public class ShowPromptDialog implements View.OnClickListener{
    private TextView mTitle;
    private TextView mContent;
    private Button mLeftBtn;
    private Button mRightBtn;
    private Button mSingleBtn;
    private TextView mLine;
    private Dialog mDialog;
    private View mDialogView;
    private EditText mEditText;
    private ShowPromptDialog.Builder mBuilder;

    public ShowPromptDialog(ShowPromptDialog.Builder builder) {
        this.mBuilder = builder;
        mDialog = new Dialog(mBuilder.getContext(), R.style.NormalDialogStyle);
        mDialogView = View.inflate(mBuilder.getContext(), R.layout.widget_dialog_prompt, null);
        mTitle = mDialogView.findViewById(R.id.dialog_normal_title);
        mContent = mDialogView.findViewById(R.id.dialog_normal_content);
        mLeftBtn = mDialogView.findViewById(R.id.dialog_normal_leftbtn);
        mRightBtn = mDialogView.findViewById(R.id.dialog_normal_rightbtn);
        mSingleBtn = mDialogView.findViewById(R.id.dialog_normal_midbtn);
        mLine = mDialogView.findViewById(R.id.dialog_normal_line);
        mEditText= mDialogView.findViewById(R.id.dialog_normal_prompt);
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

    private void initDialog(ShowPromptDialog.Builder builder) {
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
        mEditText.setText(builder.getPromptText());
        mEditText.setHint(builder.getHintText());
        mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(builder.getPromptMaxLength())});
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
        mEditText.setSelection(mEditText.getText().length());
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mEditText.setSelection(s.length());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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
            mBuilder.getOnclickListener().clickRightButton(mRightBtn,mEditText.getText().toString());
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
        private String promptText;
        private String hintText;
        private boolean isSingleMode;
        private String singleButtonText;
        private int singleButtonTextColor;
        private String leftButtonText;
        private int leftButtonTextColor;
        private String rightButtonText;
        private int rightButtonTextColor;
        private int buttonTextSize;
        private PromptClickListener onclickListener;
        private View.OnClickListener singleListener;
        private boolean isTitleVisible;
        private boolean isTouchOutside;
        private float height;
        private float width;
        private int maxLength;
        private Context mContext;

        public Builder(Context context) {
            mContext = context;
            titleText = "温馨提示";
            titleTextColor = ContextCompat.getColor(mContext, R.color.black_light);
            contentText = "";
            contentTextColor = ContextCompat.getColor(mContext, R.color.black_light);
            promptText= "";
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
            height = 0.23f;
            width = 0.65f;
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

        public ShowPromptDialog.Builder setTitleText(String titleText) {
            this.titleText = titleText;
            return this;
        }

        int getTitleTextColor() {
            return titleTextColor;
        }

        public ShowPromptDialog.Builder setTitleTextColor(@ColorRes int titleTextColor) {
            this.titleTextColor = ContextCompat.getColor(mContext, titleTextColor);
            return this;
        }

        String getContentText() {
            return contentText;
        }

        public ShowPromptDialog.Builder setContentText(String contentText) {
            this.contentText = contentText;
            return this;
        }

        int getContentTextColor() {
            return contentTextColor;
        }

        public ShowPromptDialog.Builder setContentTextColor(@ColorRes int contentTextColor) {
            this.contentTextColor = ContextCompat.getColor(mContext, contentTextColor);
            return this;
        }

        String getPromptText(){
            return promptText;
        }

        public ShowPromptDialog.Builder setPromptText(String promptText){
            this.promptText=promptText;
            return this;
        }

        String getHintText(){
            return hintText;
        }

        public ShowPromptDialog.Builder setHintText(String hintText){
            this.hintText=hintText;
            return this;
        }

        public ShowPromptDialog.Builder setPromptMaxLength(int maxLength){
            this.maxLength=maxLength;
            return this;
        }

        int getPromptMaxLength(){
            return maxLength;
        }


        boolean isSingleMode() {
            return isSingleMode;
        }

        public ShowPromptDialog.Builder setSingleMode(boolean singleMode) {
            isSingleMode = singleMode;
            return this;
        }

        String getSingleButtonText() {
            return singleButtonText;
        }

        public ShowPromptDialog.Builder setSingleButtonText(String singleButtonText) {
            this.singleButtonText = singleButtonText;
            return this;
        }

        int getSingleButtonTextColor() {
            return singleButtonTextColor;
        }

        public ShowPromptDialog.Builder setSingleButtonTextColor(@ColorRes int singleButtonTextColor) {
            this.singleButtonTextColor = ContextCompat.getColor(mContext, singleButtonTextColor);
            return this;
        }

        String getLeftButtonText() {
            return leftButtonText;
        }

        public ShowPromptDialog.Builder setLeftButtonText(String leftButtonText) {
            this.leftButtonText = leftButtonText;
            return this;
        }

        int getLeftButtonTextColor() {
            return leftButtonTextColor;
        }

        public ShowPromptDialog.Builder setLeftButtonTextColor(@ColorRes int leftButtonTextColor) {
            this.leftButtonTextColor = ContextCompat.getColor(mContext, leftButtonTextColor);
            return this;
        }

        String getRightButtonText() {
            return rightButtonText;
        }

        public ShowPromptDialog.Builder setRightButtonText(String rightButtonText) {
            this.rightButtonText = rightButtonText;
            return this;
        }

        int getRightButtonTextColor() {
            return rightButtonTextColor;
        }

        public ShowPromptDialog.Builder setRightButtonTextColor(@ColorRes int rightButtonTextColor) {
            this.rightButtonTextColor = ContextCompat.getColor(mContext, rightButtonTextColor);
            return this;
        }

        PromptClickListener getOnclickListener() {
            return onclickListener;
        }

        public ShowPromptDialog.Builder setOnclickListener(PromptClickListener onclickListener) {
            this.onclickListener = onclickListener;
            return this;
        }

        View.OnClickListener getSingleListener() {
            return singleListener;
        }

        public ShowPromptDialog.Builder setSingleListener(View.OnClickListener singleListener) {
            this.singleListener = singleListener;
            return this;
        }

        boolean getTitleVisible() {
            return isTitleVisible;
        }

        public ShowPromptDialog.Builder setTitleVisible(boolean isVisible) {
            isTitleVisible = isVisible;
            return this;
        }

        boolean isTouchOutside() {
            return isTouchOutside;
        }

        public ShowPromptDialog.Builder setCanceledOnTouchOutside(boolean isTouchOutside) {
            this.isTouchOutside = isTouchOutside;
            return this;
        }

        int getContentTextSize() {
            return contentTextSize;
        }

        public ShowPromptDialog.Builder setContentTextSize(int contentTextSize) {
            this.contentTextSize = contentTextSize;
            return this;
        }

        int getTitleTextSize() {
            return titleTextSize;
        }

        public ShowPromptDialog.Builder setTitleTextSize(int titleTextSize) {
            this.titleTextSize = titleTextSize;
            return this;
        }

        int getButtonTextSize() {
            return buttonTextSize;
        }

        public ShowPromptDialog.Builder setButtonTextSize(int buttonTextSize) {
            this.buttonTextSize = buttonTextSize;
            return this;
        }

        float getHeight() {
            return height;
        }

        public ShowPromptDialog.Builder setHeight(float height) {
            this.height = height;
            return this;
        }

        float getWidth() {
            return width;
        }

        public ShowPromptDialog.Builder setWidth(float width) {
            this.width = width;
            return this;
        }

        public ShowPromptDialog build() {
            return new ShowPromptDialog(this);
        }
    }
}
