package com.example.shahar.advancedmobile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.example.shahar.advancedmobile.MyFragmentEvent.SEND_TEXT;
import static com.example.shahar.advancedmobile.MyFragmentEvent.SHOW_TOAST;
import static com.example.shahar.advancedmobile.MyFragmentEvent.SOME_OTHER_COMMAND;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MyEvent event) {
        Fragment fragment;
        switch (event.getEvNum()) {
            case 1:
                SomeFragment frag1 = SomeFragment.newInstance("input");
                fragment = getSupportFragmentManager().findFragmentByTag("inpFrag");
                if (fragment == null) {
/*
                    SomeFragment frag = SomeFragment.newInstance("Hi", "I'm a fragment");
*/
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, frag1, "inpFrag").commitNow();
                    Toast.makeText(this, event.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "input fragment already presence!", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                SomeFragment frag2 = SomeFragment.newInstance("text");
                fragment = getSupportFragmentManager().findFragmentByTag("textFrag");
                if (fragment == null) {

/*
                    AuthenticationExampleFragment frag2 = AuthenticationExampleFragment.newInstance();
*/
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, frag2, "textFrag").commitNow();
                    Toast.makeText(this, event.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "text fragment already presence!", Toast.LENGTH_SHORT).show();
                }
                break;
            case 3:
                fragment = getSupportFragmentManager().findFragmentByTag("loginFrag");
                if (fragment == null) {
                    AuthenticationFragment frag3 = AuthenticationFragment.newInstance();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, frag3, "loginFrag").commitNow();
                    Toast.makeText(this, event.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "login fragment already presence!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventFromFragment(MyFragmentEvent event) {
        switch (event.getCommand()) {
            case SHOW_TOAST:
                Toast.makeText(this, event.getMessage(), Toast.LENGTH_SHORT).show();
                break;
            case SEND_TEXT:
                SomeFragment frag = SomeFragment.newInstance("text");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, frag, "textFrag").commitNow();
                final TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(event.getMessage());
                break;
            case SOME_OTHER_COMMAND:
            default:

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void inpFrag(View view) {
        EventBus.getDefault().post(new MyEvent("Added input Fragment!",1));
    }

    public void textFrag(View view) {
        EventBus.getDefault().post(new MyEvent("Added text Fragment!",2));
    }

    public void loginFrag(View view) {
        EventBus.getDefault().post(new MyEvent("Added login Fragment!",3));
    }
}
