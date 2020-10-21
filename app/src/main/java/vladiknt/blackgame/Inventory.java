package vladiknt.blackgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import vladiknt.blackgame.maps.Map;

public class Inventory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory);
    }

    // Кнопка смены аватара
    public void avatarButton(View view) {
        switch (view.getId()) {
            case R.id.avatarMe:
                PlayerInfo.avatar = "me";
                break;
            case R.id.avatarBoys1:
                PlayerInfo.avatar = "boys1";
                break;
            case R.id.avatarGirls1:
                PlayerInfo.avatar = "girls1";
                break;
            case R.id.avatarGirls2:
                PlayerInfo.avatar = "girls2";
                break;
            case R.id.avatarPenguin:
                PlayerInfo.avatar = "penguin";
                break;
        }
        PlayerInfo.saveInfo(getFileStreamPath(PlayerInfo.data));
        StartPage.map.renderAvatar();
        // Отсылка для линуксоидов
        if (PlayerInfo.avatar.equals("penguin"))
            Toast.makeText(this, "Любитель Linux обнаружен!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Аватар успешно изменён.", Toast.LENGTH_SHORT).show();
    }
    // Кнопка выхода из активити
    public void backButton(View view) {
        finish();
    }
    // Кнопка возвращения в холл
    public void returnButton(View view) {
        StartPage.setMap(new Map(1));
        Toast.makeText(this, "Вы перемещены в холл.", Toast.LENGTH_SHORT).show();
    }

}