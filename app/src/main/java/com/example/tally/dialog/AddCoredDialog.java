package com.example.tally.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import com.example.tally.AppDatabase;
import com.example.tally.R;
import com.example.tally.bean.Expenditure;
import com.example.tally.listen.ExpenditureListListener;
import com.example.tally.thread.TallyThread;
import com.scwang.smart.refresh.layout.api.RefreshLayout;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 添加支出的弹框
 */

public class AddCoredDialog extends Dialog {

    private TextView mTVSubmit;
    private Context mContext;
    private EditText textInputMoney;
    private RadioGroup typeGroup;
    private int typeNum = -1;//类型编号1 2 3 4 5

    private ExpenditureListListener listListener;
    private RefreshLayout mRefreshLayout;

    public AddCoredDialog(int themeResId, Activity activity, ExpenditureListListener listListener) {
        super(activity, themeResId);

        this.listListener = listListener;

        Log.d("AddCoredDialog","初始化");
        super.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        super.setContentView(R.layout.fragment_add_record);
        super.setCanceledOnTouchOutside(true); // 外部点击取消
        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = super.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.height = activity.getWindowManager().getDefaultDisplay().getHeight() * 2/ 3;
        window.setAttributes(lp);
        initView(this);
        this.show();
        mContext = activity;
    }

    private void initView(Dialog dialog) {
        mTVSubmit = (TextView) dialog.findViewById(R.id.tvBtn);
        textInputMoney = (EditText) dialog.findViewById(R.id.textInputMoney);
        typeGroup = (RadioGroup) dialog.findViewById(R.id.typeGroup);

        setListener();
    }

    private void setListener() {

        // 设置类型单选监听器
        typeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                typeNum = checkedId;
            }
        });
        mTVSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strContext = textInputMoney.getText().toString();
                if(typeNum == -1){
                    Toast.makeText(mContext, "请选择记账类型！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(strContext.trim().isEmpty()){
                    Toast.makeText(mContext, "请输入金额！", Toast.LENGTH_SHORT).show();
                    return;
                }
                //匹配非0-9字符
                String pattern = "\\D";
                Pattern r = Pattern.compile(pattern);
                //不包含小数点
                if(!strContext.contains(".")){
                    Matcher m = r.matcher(strContext);
                    Log.d("AddCoredDialog","不包含小数点");
                    if(m.find()){
                        Toast.makeText(mContext, "输入金额有误！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }else{
                    //有小数点
                    //切割内容
                    String[] temCon = strContext.split(".");
                    Matcher temp1 = r.matcher(temCon[0]);
                    Matcher temp2 = r.matcher(temCon[1]);
                    Log.d("AddCoredDialog","包含小数点");
                    if(temp1.find() && temp2.find()){
                        Toast.makeText(mContext, "输入金额有误！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                Log.d("AddCoredDialog","数据库添加记录");
                Calendar calendar= Calendar.getInstance();
                SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                Expenditure expenditure = new Expenditure();
                expenditure.setTime(dateFormat.format(calendar.getTime()));
                expenditure.setNum(Integer.parseInt(strContext));
                expenditure.setType(typeNum);
                Log.d("AddCoredDialog",
                        String.format("记录-->time: %s, num: %f, type: %d",
                                expenditure.getTime(),expenditure.getNum(),expenditure.getType()));
                //开启新线程插入数据
                TallyThread thread = new TallyThread(expenditure,mContext);
                new Thread(thread).start();
                Log.d("AddCoredDialog","刷新记录列表");
                //列表自动刷新
                listListener.autoRefreshList();
                // 关闭
                dismiss();
            }
        });
    }

}
