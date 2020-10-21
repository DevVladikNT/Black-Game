package vladiknt.blackgame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MenuBJ_h extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_bj_h);
        TextView tv = findViewById(R.id.finish);
        tv.clearComposingText();
        tv.setText("Ваш баланс: " + PlayerInfo.money);

        RadioButton rb;
        switch (PlayerInfo.hBet) {
            case 5:
                rb = findViewById(R.id.hbet5);
                rb.setChecked(true);
                break;
            case 10:
                rb = findViewById(R.id.hbet10);
                rb.setChecked(true);
                break;
            case 50:
                rb = findViewById(R.id.hbet50);
                rb.setChecked(true);
                break;
            case 100:
                rb = findViewById(R.id.hbet100);
                rb.setChecked(true);
                break;
        }
    }

    // Кнопка начала новой игры
    public void newGame(View view) {
        if (PlayerInfo.hBet != 0) {
            if (PlayerInfo.money >= 500) {
                if (PlayerInfo.money - PlayerInfo.hentaiBet >= 0) {
                    PlayerInfo.money -= PlayerInfo.hentaiBet;
                    PlayerInfo.saveInfo(getFileStreamPath(PlayerInfo.data));
                    Intent intent = new Intent(MenuBJ_h.this, MainActivityBJ_h.class);
                    startActivityForResult(intent, 1);
                } else
                    Toast.makeText(this, "Недостаточно монет.", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, "Эти девочки не интересуются неудачниками.", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Сначала выберите ставку.", Toast.LENGTH_SHORT).show();
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
            case R.id.hbet5:
                PlayerInfo.hBet = 5;
                PlayerInfo.hentaiBet = (int)(PlayerInfo.money * 0.05);
                break;
            case R.id.hbet10:
                PlayerInfo.hBet = 10;
                PlayerInfo.hentaiBet = (int)(PlayerInfo.money * 0.1);
                break;
            case R.id.hbet50:
                PlayerInfo.hBet = 50;
                PlayerInfo.hentaiBet = (int)(PlayerInfo.money * 0.5);
                break;
            case R.id.hbet100:
                PlayerInfo.hBet = 100;
                PlayerInfo.hentaiBet = PlayerInfo.money;
                break;
        }
    }

}