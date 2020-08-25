package vladiknt.blackgame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MenuVNT extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_vnt);
        TextView tv = findViewById(R.id.finish);
        tv.clearComposingText();
        tv.setText("Ваш баланс: " + PlayerInfo.money);

        RadioButton rb;
        switch (PlayerInfo.bet) {
            case 5:
                rb = findViewById(R.id.bet10);
                rb.setChecked(true);
                PlayerInfo.bet = 10;
                break;
            case 10:
                rb = findViewById(R.id.bet10);
                rb.setChecked(true);
                break;
            case 50:
                rb = findViewById(R.id.bet100);
                rb.setChecked(true);
                PlayerInfo.bet = 100;
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
            Intent intent = new Intent(MenuVNT.this, MainActivityVNT.class);
            startActivityForResult(intent, 1);
        } else
            Toast.makeText(this, "Недостаточно монет.", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 1) { // Условие, которое не даст выпасть NullPointerException
            String result = data.getStringExtra("result");
            result += " Ваш баланс: ";
            result += PlayerInfo.money;
            TextView tv = findViewById(R.id.finish);
            tv.clearComposingText();
            tv.setText(result);
        }
    }

    // Кнопка выхода из активити
    public void backButton(View view) {
        finish();
    }
    // Кнопка выбора ставки
    public void betButton(View view) {
        switch (view.getId()) {
            case R.id.bet10:
                PlayerInfo.bet = 10;
                break;
            case R.id.bet100:
                PlayerInfo.bet = 100;
                break;
        }
    }

}