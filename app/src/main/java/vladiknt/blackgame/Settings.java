package vladiknt.blackgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
    }

    // Кнопка выхода из активити
    public void backButton(View view) {
        finish();
    }
    // Кнопка входа в правила игры
    public void rulesButton(View view) {
        Intent intent = new Intent(Settings.this, Rules.class);
        startActivity(intent);
    }
    // Кнопка входа в раздел кастомизации
    public void themeButton(View view) {
        Intent intent = new Intent(Settings.this, Themes.class);
        startActivity(intent);
    }
    // Кнопка входа в раздел статистики
    public void statisticsButton(View view) {
        Intent intent = new Intent(Settings.this, Statistics.class);
        startActivity(intent);
    }
    // Кнопка входа в раздел изменений новой версии
    public void changesButton(View view) {
        Intent intent = new Intent(Settings.this, Changes.class);
        startActivity(intent);
    }
    // Кнопка входа в раздел о разработчике
    public void devButton(View view) {
        Intent intent = new Intent(Settings.this, DeveloperPage.class);
        startActivity(intent);
    }
    // Кнопка для ввода промокода
    public void giftCodeButton(View view) {
        EditText et = findViewById(R.id.giftCode);
        switch (et.getText().toString()) {
            case "vladiknt20":
                PlayerInfo.money += 1000;
                PlayerInfo.saveInfo(getFileStreamPath(PlayerInfo.data));
                Toast.makeText(this, "Вам начислено 1000 монет на счёт.", Toast.LENGTH_SHORT).show();
                break;
            case "freePlay":
                PlayerInfo.bet = 0;
                Toast.makeText(this, "Теперь вы можете играть бесплатно.", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "Промокод недействителен.", Toast.LENGTH_SHORT).show();
        }
    }

}