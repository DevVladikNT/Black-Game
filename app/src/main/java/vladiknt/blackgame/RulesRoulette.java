package vladiknt.blackgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class RulesRoulette extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rules_roulette);
    }

    // Кнопка выхода из активити
    public void backButton(View view) {
        finish();
    }

}