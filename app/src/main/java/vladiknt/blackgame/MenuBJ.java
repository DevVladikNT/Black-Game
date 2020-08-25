package vladiknt.blackgame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MenuBJ extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_bj);
        TextView tv = findViewById(R.id.finish);
        tv.clearComposingText();
        tv.setText("Ваш баланс: " + PlayerInfo.money);

        RadioButton rb;
        switch (PlayerInfo.bet) {
            case 5:
                rb = findViewById(R.id.bet5);
                rb.setChecked(true);
                break;
            case 10:
                rb = findViewById(R.id.bet10);
                rb.setChecked(true);
                break;
            case 50:
                rb = findViewById(R.id.bet50);
                rb.setChecked(true);
                break;
            case 100:
                rb = findViewById(R.id.bet100);
                rb.setChecked(true);
                break;
        }
    }

    // Кнопка начала новой игры
    public void newGame(View view) {
        if (PlayerInfo.money - PlayerInfo.bet >= 0) {
            PlayerInfo.money -= PlayerInfo.bet;
            PlayerInfo.saveInfo(getFileStreamPath(PlayerInfo.data));
            Intent intent = new Intent(MenuBJ.this, MainActivityBJ.class);
            startActivityForResult(intent, 1);
        } else
            Toast.makeText(this, "Недостаточно монет.", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 1) { // Условие, которое не даст выпасть NullPointerException
            String score = data.getStringExtra("score");
            score += " Ваш баланс: ";
            score += PlayerInfo.money;
            TextView tv = findViewById(R.id.finish);
            tv.clearComposingText();
            tv.setText(score);
        }
    }
    // Кнопка выхода из активити
    public void backButton(View view) {
        finish();
    }
    // Кнопка выбора ставки
    public void betButton(View view) {
        switch (view.getId()) {
            case R.id.bet5:
                PlayerInfo.bet = 5;
                break;
            case R.id.bet10:
                PlayerInfo.bet = 10;
                break;
            case R.id.bet50:
                PlayerInfo.bet = 50;
                break;
            case R.id.bet100:
                PlayerInfo.bet = 100;
                break;
        }
    }

}