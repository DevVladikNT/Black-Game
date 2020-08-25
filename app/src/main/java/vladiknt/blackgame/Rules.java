package vladiknt.blackgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Rules extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rules);
    }

    // Кнопка выхода из активити
    public void backButton(View view) {
        finish();
    }
    // Кнопка перехода в правила о Black Jack
    public void rulesBJButton(View view) {
        Intent intent = new Intent(Rules.this, RulesBJ.class);
        startActivity(intent);
    }
    // Кнопка перехода в правила о Roulette
    public void rulesRButton(View view) {
        Intent intent = new Intent(Rules.this, RulesRoulette.class);
        startActivity(intent);
    }
    // Кнопка перехода в правила о VNT-Slots
    public void rulesVNTButton(View view) {
        Intent intent = new Intent(Rules.this, RulesVNT.class);
        startActivity(intent);
    }

}