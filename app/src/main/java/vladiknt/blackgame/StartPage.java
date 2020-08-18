package vladiknt.blackgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/*
 * Если код работает, то его написал
 * Васильев Владислав. А если не работает,
 * то не знаю кто его написал. XD
 */

public class StartPage extends AppCompatActivity {

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.start_page);

        PlayerInfo.loadInfo(getFileStreamPath(PlayerInfo.data));
    }

    // Кнопка входа в настройки
    public void settingsButton(View view) {
        Intent intent = new Intent(StartPage.this, Settings.class);
        startActivity(intent);
    }
    // Кнопка входа в меню блекджека
    public void menuButton(View view) {
        Intent intent = new Intent(StartPage.this, Menu.class);
        startActivity(intent);
    }
    // Кнопка входа в меню рулетки
    public void menuRouletteButton(View view) {
        Intent intent = new Intent(StartPage.this, MenuRoulette.class);
        startActivity(intent);
    }

    // Системная кнопка назад
    long backPressed = 0; // Подсчет времени для выхода из приложения
    @Override
    public void onBackPressed() {
        // выход из приложения
        if (backPressed + 2000 > System.currentTimeMillis())
            super.onBackPressed();
        else
            Toast.makeText(getBaseContext(), "Нажмите еще раз для выхода.",
                    Toast.LENGTH_SHORT).show();
        backPressed = System.currentTimeMillis();
    }

}