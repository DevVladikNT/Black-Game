package vladiknt.blackgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import vladiknt.blackgame.maps.Map;

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
            case "MONEY2020":
                if (PlayerInfo.money < 5) {
                    PlayerInfo.money += 100;
                    PlayerInfo.saveInfo(getFileStreamPath(PlayerInfo.data));
                    Toast.makeText(this, "Вам начислено 100 монет на счёт.", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(this, "У вас достаточно денег.", Toast.LENGTH_SHORT).show();
                break;
            case "vladiknt20":
                PlayerInfo.money += 1000;
                PlayerInfo.saveInfo(getFileStreamPath(PlayerInfo.data));
                Toast.makeText(this, "Вам начислено 1000 монет на счёт.", Toast.LENGTH_SHORT).show();
                break;
            case "freePlay":
                PlayerInfo.bet = 0;
                Toast.makeText(this, "Теперь вы можете играть бесплатно.", Toast.LENGTH_SHORT).show();
                break;
            case "hentaiRoom":
                StartPage.setMap(new Map(4));
                Toast.makeText(this, "Вы перемещены в хентай-комнату.", Toast.LENGTH_SHORT).show();
                break;
            case "getHentaiGirl":
                if (PlayerInfo.secret) {
                    PlayerInfo.avatar = "secret_girl";
                    PlayerInfo.saveInfo(getFileStreamPath(PlayerInfo.data));
                    StartPage.map.renderAvatar();
                    Toast.makeText(this, "Теперь можно дрочить.", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(this, "Сначала отдай дань уважения разработчику.", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "Промокод недействителен.", Toast.LENGTH_SHORT).show();
        }
    }

}