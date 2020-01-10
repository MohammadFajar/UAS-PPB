package com.mohammadfajar.stiki.uas_transbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

public class tentangActivity extends Activity {
    private Activity activity = this;
    Button btnBack;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang);

        Button btnBack = (Button) this.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new klik());

    }

    class klik implements Button.OnClickListener
    {
        public void onClick (View v)
        {
            Intent i = new Intent(activity, MainActivity.class );
            startActivity(i);
        }
    }
}