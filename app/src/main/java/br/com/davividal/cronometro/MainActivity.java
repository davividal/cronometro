package br.com.davividal.cronometro;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Handler cronometro = new Handler();
    private Runnable timedTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View view) {
        TextView timer = (TextView) findViewById(R.id.timer);
        Button start = (Button) findViewById(R.id.start);
        Button stop = (Button) findViewById(R.id.stop);

        assert null != start && null != stop && timer != null;

        timedTask = new Runnable() {
            public TextView timer;
            private Handler cronometro;

            @Override
            public void run() {
                Integer actual = Integer.parseInt((String) timer.getText());
                timer.setText(String.format(new Locale("pt", "br"), "%d", ++actual));
                cronometro.postDelayed(timedTask, 1000);
            }

            public Runnable init(TextView timer, Handler cronometro) {
                this.timer = timer;
                this.cronometro = cronometro;
                return this;
            }
        }.init(timer, cronometro);

        cronometro.postDelayed(timedTask, 1000);

        start.setEnabled(false);
        stop.setEnabled(true);
    }

    public void stop(View view) {
        Button start = (Button) findViewById(R.id.start);
        Button stop = (Button) findViewById(R.id.stop);

        assert null != start && null != stop;

        cronometro.removeCallbacks(timedTask);

        start.setEnabled(true);
        stop.setEnabled(false);
    }
}
