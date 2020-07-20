package com.example.tally;


import androidx.annotation.CallSuper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.graphics.drawable.Drawable;

import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.tally.dialog.AddCoredDialog;
import com.example.tally.fragment.ExpenditureListFragment;
import com.example.tally.listen.ExpenditureListListener;
import com.scwang.smart.refresh.layout.api.RefreshLayout;

/**
 * 主类
 */

public class MainActivity extends AppCompatActivity implements ExpenditureListListener {

    private RadioButton mRbHome,mRbPond,mRbPerson,mRbMsg;
    private RadioGroup mRbGroup;
    private ImageView mIvAdd;
    ExpenditureListFragment expenditureListFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListener();
        mRbHome.setChecked(true);
        expenditureListFragment = (ExpenditureListFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_expen_list);
    }

    /**
     * 底部选项点击监听
     */
    private void initListener() {
        mRbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_home:
                        break;
                    case R.id.rb_me:
                        break;
                    case R.id.rb_message:
                        break;
                    case R.id.rb_pond:
                        break;
                    default:
                        break;
                }
            }
        });
        mIvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 显示弹窗
                AddCoredDialog dialog = new AddCoredDialog(
                        R.style.MyDialog,
                        MainActivity.this,MainActivity.this);
            }
        });
    }

    /**
     * 初始化底部导航栏
     */
    private void initViews() {
        mRbHome = findViewById(R.id.rb_home);
        mRbPond = findViewById(R.id.rb_pond);
        mRbMsg = findViewById(R.id.rb_message);
        mRbPerson = findViewById(R.id.rb_me);
        mIvAdd = findViewById(R.id.rbAdd);
        mRbGroup = findViewById(R.id.radioGroup);

        Drawable dbHome = getResources().getDrawable(R.drawable.selector_home);
        dbHome.setBounds(0, 0, UIUtils.dipTopx(this,40), UIUtils.dipTopx(this,40));
        mRbHome.setCompoundDrawables(null, dbHome, null, null);

        Drawable dbPond = getResources().getDrawable(R.drawable.selector_pond);
        dbPond.setBounds(0, 0, 40, 40);
        mRbPond.setCompoundDrawables(null, dbPond, null, null);

        Drawable dbMsg = getResources().getDrawable(R.drawable.selector_message);
        dbMsg.setBounds(0, 0, 40, 40);
        mRbMsg.setCompoundDrawables(null, dbMsg, null, null);

        Drawable dbMe = getResources().getDrawable(R.drawable.selector_person);
        dbMe.setBounds(0, 0, 40, 40);
        mRbPerson.setCompoundDrawables(null, dbMe, null, null);
    }

    @Override
    public void autoRefreshList() {
        expenditureListFragment.autoRreshLayout();
    }
}
