package com.chengxiang.pwmdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.things.pio.PeripheralManagerService;
import com.google.android.things.pio.Pwm;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    //PWM输出名称
    private static final String PWN_NAME = "PWM0";
    //PWM输出
    private Pwm mPwn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PeripheralManagerService service = new PeripheralManagerService();
        try {
            //打开并设置PWM,通过设置不同的占空比来控制旋转的角度
            mPwn = service.openPwm(PWN_NAME);
            mPwn.setPwmFrequencyHz(50);
            mPwn.setPwmDutyCycle(4);
            mPwn.setEnabled(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPwn != null) {
            try {
                //关闭连接
                mPwn.close();
                mPwn = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
