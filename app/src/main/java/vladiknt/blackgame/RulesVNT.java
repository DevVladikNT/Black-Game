package vladiknt.blackgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class RulesVNT extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rules_vnt);
    }

    // Кнопка выхода из активити
    public void backButton(View view) {
        finish();
    }

}