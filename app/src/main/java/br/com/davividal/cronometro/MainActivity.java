package br.com.davividal.cronometro;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Handler cronometro = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View view) {
        TextView timer = (TextView) findViewById(R.id.timer);

        assert timer != null;

        cronometro.postDelayed(new Runnable() {
            public TextView timer;

            @Override
            public void run() {
                Integer actual = Integer.parseInt((String) timer.getText());
                timer.setText(String.format(new Locale("pt", "br"), "%d", ++actual));
            }

            public Runnable init(TextView timer) {
                this.timer = timer;
                return this;
            }
        }.init(timer), 1000);
    }

    public void stop(View view) {
    }
}
