package vladiknt.blackgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DeveloperPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.developer_page);
    }

    // Кнопка выхода из активити
    public void backButton(View view) {
        finish();
    }
    // Кнопка соглашения пользователя тестить фичи
    public void secretButton(View view) {
        if(PlayerInfo.counter < 4)
            PlayerInfo.counter++;
        else {
            if(PlayerInfo.counter == 4) {
                PlayerInfo.counter++;
                PlayerInfo.secret = true;
                Toast.makeText(getApplicationContext(), "Вы открыли фичи!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void secretButton2(View view) {
        if(PlayerInfo.counter < 14 && PlayerInfo.counter > 4) {
            PlayerInfo.counter++;
        } else {
            if(PlayerInfo.counter == 14) {
                PlayerInfo.counter++;
                PlayerInfo.secretSex = true;
                Toast.makeText(getApplicationContext(), "Вы открыли картинки 18+!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}