package vladiknt.blackgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Queue;
import java.util.Stack;

public class MainActivityDance extends AppCompatActivity {

    long time = System.currentTimeMillis();
    Stack<String> st = new Stack<>();
    TextView tv1 = findViewById(R.id.firstElement);
    TextView tv2 = findViewById(R.id.secondElement);
    TextView tv3 = findViewById(R.id.thirdElement);
    TextView tv4 = findViewById(R.id.fourthElement);
    ImageView iv = findViewById(R.id.danceImage);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dance);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadStack();
                Toast.makeText(MainActivityDance.this, "Начали!", Toast.LENGTH_SHORT).show();
                MainActivityDance.this.tv1.setText(MainActivityDance.this.st.pop());
                MainActivityDance.this.tv2.setText(MainActivityDance.this.st.pop());
                MainActivityDance.this.tv3.setText(MainActivityDance.this.st.pop());
                MainActivityDance.this.tv4.setText(MainActivityDance.this.st.pop());
            }
        }, 2000);
    }

    // При завершении партии
    private void finishGame(boolean win) {
        double k; // Коэффициент награды
        String result;
        if (win) {
            k = 10.0 / (System.currentTimeMillis() - time);
            result = "Заработано: "; // Результат игры
            PlayerInfo.reward = (int)(PlayerInfo.bet * k);
            result += PlayerInfo.reward; // Записываем сколько монет получили
            PlayerInfo.money += PlayerInfo.reward;
            PlayerInfo.saveInfo(getFileStreamPath(PlayerInfo.data));
        } else {
            result = "Поражение.";
        }

        Intent intent = new Intent();
        intent.putExtra("result", result);
        setResult(1, intent);
        finish();
    }

    // Кнопки игрока
    public void danceButton(View view) {
        String result = "";
        switch (view.getId()) {
            case R.id.danceUp:
                result = "▲";
                iv.setImageResource(R.drawable.dance_sprite1);
                break;
            case R.id.danceDown:
                result = "▼";
                iv.setImageResource(R.drawable.dance_sprite3);
                break;
            case R.id.danceLeft:
                result = "◄";
                iv.setImageResource(R.drawable.dance_sprite2);
                break;
            case R.id.danceRight:
                result = "►";
                iv.setImageResource(R.drawable.dance_sprite2);
                break;
        }
        if (result.equals(tv1.getText().toString())) {
            tv1.clearComposingText();
            tv1.setText(tv2.getText());
            tv2.clearComposingText();
            tv2.setText(tv3.getText());
            tv3.clearComposingText();
            tv3.setText(tv4.getText());
            tv4.clearComposingText();
            if (st.empty())
                tv4.setText("");
            else
                tv4.setText(st.pop());
            if (tv1.getText().toString().equals(""))
                finishGame(true);
        } else {
            finishGame(false);
        }
    }

    // Заполняем стек стрелочками
    private void loadStack() {
        for (int i = 0; i < 20; i++) {
            switch ((int)(Math.random() * 100000) % 4) {
                case 0:
                    st.push("▲");
                    break;
                case 1:
                    st.push("▼");
                    break;
                case 2:
                    st.push("◄");
                    break;
                case 3:
                    st.push("►");
                    break;
            }
        }
    }

}