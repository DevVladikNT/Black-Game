package vladiknt.blackgame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }

    // Кнопка начала новой игры
    public void newGame(View view) {
        Intent intent = new Intent(Menu.this, MainActivity.class);
        startActivityForResult(intent, 1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 1) {
            String score = data.getStringExtra("score");
            TextView finish = findViewById(R.id.finish);
            finish.clearComposingText();
            finish.setText(score);
        }
    }
    // Кнопка выхода из активити
    public void backButton(View view) {
        finish();
    }

}