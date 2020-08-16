package vladiknt.blackgame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MenuRoulette extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_roulette);
    }

    // Кнопка начала новой игры
    public void newSpin(View view) {
        Intent intent = new Intent(MenuRoulette.this, MainActivityRoulette.class);
        startActivityForResult(intent, 1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 1) {
            String result = data.getStringExtra("result");
            TextView tv = findViewById(R.id.finishRoulette);
            tv.clearComposingText();
            tv.setText(result);
        }
    }
    // Кнопка выхода из активити
    public void backButton(View view) {
        finish();
    }

}