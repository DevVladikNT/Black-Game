package vladiknt.blackgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Statistics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics);

        TextView tv = findViewById(R.id.statActivityWinsBJ);
        String str;
        if (PlayerInfo.gamesCounterBJ == 0)
            str = "0%";
        else
            str = "" + (100 * PlayerInfo.winsCounterBJ / PlayerInfo.gamesCounterBJ) + "%";
        tv.clearComposingText();
        tv.setText(str);
        tv = findViewById(R.id.statActivityWinsR);
        if (PlayerInfo.gamesCounterR == 0)
            str = "0%";
        else
            str = "" + (100 * PlayerInfo.winsCounterR / PlayerInfo.gamesCounterR) + "%";
        tv.clearComposingText();
        tv.setText(str);
        tv = findViewById(R.id.statActivityGamesBJ);
        str = "За всё время было сыграно " + PlayerInfo.gamesCounterBJ + " раз(а).";
        tv.clearComposingText();
        tv.setText(str);
        tv = findViewById(R.id.statActivityGamesR);
        str = "За всё время было сыграно " + PlayerInfo.gamesCounterR + " раз(а).";
        tv.clearComposingText();
        tv.setText(str);
    }

    // Кнопка сброса статистики
    long backPressed = 0;
    public void resetStatistics(View view) {
        if (backPressed + 2000 > System.currentTimeMillis()) {
            PlayerInfo.winsCounterBJ = 0;
            PlayerInfo.gamesCounterBJ = 0;
            PlayerInfo.winsCounterR = 0;
            PlayerInfo.gamesCounterR = 0;
            PlayerInfo.saveInfo(getFileStreamPath(PlayerInfo.data));
            finish();
        } else
            Toast.makeText(getBaseContext(), "Нажмите еще раз для сброса.",
                    Toast.LENGTH_SHORT).show();
        backPressed = System.currentTimeMillis();
    }
    // Кнопка выхода из активити
    public void backButton(View view) {
        finish();
    }

}