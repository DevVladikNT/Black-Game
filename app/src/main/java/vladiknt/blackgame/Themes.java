package vladiknt.blackgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Themes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themes);
    }

    public void changeTheme(View view) {
        switch (view.getId()) {
            case R.id.BoysTheme:
                PlayerInfo.theme = "Boys";
                Toast.makeText(getApplicationContext(), "Тема \"Boys\" активирована.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.GirlsTheme:
                PlayerInfo.theme = "Girls";
                Toast.makeText(getApplicationContext(), "Тема \"Girls\" активирована.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.AnimeTheme:
                PlayerInfo.theme = "Anime";
                Toast.makeText(getApplicationContext(), "Тема \"Anime\" активирована.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.AsiansTheme:
                PlayerInfo.theme = "Asians";
                Toast.makeText(getApplicationContext(), "Тема \"Asians\" активирована.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.BWTheme:
                PlayerInfo.theme = "BW";
                Toast.makeText(getApplicationContext(), "Тема \"BlackWhite\" активирована.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.StandartTheme:
                PlayerInfo.theme = "Standart";
                Toast.makeText(getApplicationContext(), "Тема \"Standart\" активирована.", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    // Кнопка выхода из активити
    public void backButton(View view) {
        finish();
    }

}