package vladiknt.blackgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
    // Кнопка входа в раздел изменений новой версии
    public void changesButton(View view) {
        Intent intent = new Intent(Settings.this, Changes.class);
        startActivity(intent);
    }
    // Кнопка входа в раздел "о разработчике"
    public void devButton(View view) {
        Intent intent = new Intent(Settings.this, DeveloperPage.class);
        startActivity(intent);
    }

}