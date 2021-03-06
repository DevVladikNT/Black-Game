package vladiknt.blackgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.StorageReference;

import java.io.InputStream;

import vladiknt.blackgame.maps.Map;

/*
 * Если код работает, то его написал
 * Васильев Владислав. А если не работает,
 * то не знаю кто его написал. XD
 */

public class StartPage extends AppCompatActivity {
    public static Map map;
    public static void setMap(Map newMap) {
        map = newMap;
    }
    private ImageView[][] renderMap = new ImageView[5][5]; // Для рендера участка карты
    private Intent intent; // Для запуска режимов игры

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.start_page);

        renderMap[0][0] = findViewById(R.id.map00);
        renderMap[0][1] = findViewById(R.id.map01);
        renderMap[0][2] = findViewById(R.id.map02);
        renderMap[0][3] = findViewById(R.id.map03);
        renderMap[0][4] = findViewById(R.id.map04);
        renderMap[1][0] = findViewById(R.id.map10);
        renderMap[1][1] = findViewById(R.id.map11);
        renderMap[1][2] = findViewById(R.id.map12);
        renderMap[1][3] = findViewById(R.id.map13);
        renderMap[1][4] = findViewById(R.id.map14);
        renderMap[2][0] = findViewById(R.id.map20);
        renderMap[2][1] = findViewById(R.id.map21);
        renderMap[2][2] = findViewById(R.id.map22);
        renderMap[2][3] = findViewById(R.id.map23);
        renderMap[2][4] = findViewById(R.id.map24);
        renderMap[3][0] = findViewById(R.id.map30);
        renderMap[3][1] = findViewById(R.id.map31);
        renderMap[3][2] = findViewById(R.id.map32);
        renderMap[3][3] = findViewById(R.id.map33);
        renderMap[3][4] = findViewById(R.id.map34);
        renderMap[4][0] = findViewById(R.id.map40);
        renderMap[4][1] = findViewById(R.id.map41);
        renderMap[4][2] = findViewById(R.id.map42);
        renderMap[4][3] = findViewById(R.id.map43);
        renderMap[4][4] = findViewById(R.id.map44);
        PlayerInfo.loadInfo(getFileStreamPath(PlayerInfo.data));
        TextView tv = findViewById(R.id.balance);
        tv.setText("Money: " + PlayerInfo.money);
        // Рендерим карту
        map = new Map(1);
        map.render(renderMap, getApplicationContext());
    }
    // Конструктор вывода текста персонажа/информационной таблички
    private Dialog makeTextWindow(String text) {
        Dialog dialog = new Dialog(StartPage.this);
        dialog.setContentView(R.layout.dialog_window);
        TextView tv = dialog.findViewById(R.id.dialogText);
        tv.setText(text);
        return dialog;
    }

    // Кнопка входа в настройки
    public void settingsButton(View view) {
        Intent intent = new Intent(StartPage.this, Settings.class);
        startActivity(intent);
    }
    // Кнопка перехода в инвентарь
    public void inventoryButton(View view) {
        Intent intent = new Intent(StartPage.this, Inventory.class);
        startActivity(intent);
    }

    // Кнопка вверх
    public void upButton(View view) {
        String output = map.moveUp();
        switch (output) {
            case "VNT":
                intent = new Intent(StartPage.this, MenuVNT.class);
                startActivity(intent);
                break;
            case "R":
                intent = new Intent(StartPage.this, MenuRoulette.class);
                startActivity(intent);
                break;
            case "BJ":
                intent = new Intent(StartPage.this, MenuBJ.class);
                startActivity(intent);
                break;
            case "h_BJ":
                intent = new Intent(StartPage.this, MenuBJ_h.class);
                startActivity(intent);
                break;
            case "Zonk":
                // todo
                break;
            case "Dance":
                intent = new Intent(StartPage.this, MenuDance.class);
                startActivity(intent);
                break;
        }
        // Если это дверь
        if (output.startsWith("door"))
            map = new Map(Integer.parseInt(output.substring(4)));
        // Если нужно создать окно с сообщением
        if (output.startsWith("text"))
            makeTextWindow(output.substring(4)).show();

        TextView tv = findViewById(R.id.balance);
        tv.setText("Money: " + PlayerInfo.money);
        map.render(renderMap, getApplicationContext());
    }
    // Кнопка вниз
    public void downButton(View view) {
        String output = map.moveDown();
        switch (output) {
            case "VNT":
                intent = new Intent(StartPage.this, MenuVNT.class);
                startActivity(intent);
                break;
            case "R":
                intent = new Intent(StartPage.this, MenuRoulette.class);
                startActivity(intent);
                break;
            case "BJ":
                intent = new Intent(StartPage.this, MenuBJ.class);
                startActivity(intent);
                break;
            case "h_BJ":
                intent = new Intent(StartPage.this, MenuBJ_h.class);
                startActivity(intent);
                break;
            case "Zonk":
                // todo
                break;
            case "Dance":
                intent = new Intent(StartPage.this, MenuDance.class);
                startActivity(intent);
                break;
        }
        // Если это дверь
        if (output.startsWith("door"))
            map = new Map(Integer.parseInt(output.substring(4)));
        // Если нужно создать окно с сообщением
        if (output.startsWith("text"))
            makeTextWindow(output.substring(4)).show();

        TextView tv = findViewById(R.id.balance);
        tv.setText("Money: " + PlayerInfo.money);
        map.render(renderMap, getApplicationContext());
    }
    // Кнопка вправо
    public void rightButton(View view) {
        String output = map.moveRight();
        switch (output) {
            case "VNT":
                intent = new Intent(StartPage.this, MenuVNT.class);
                startActivity(intent);
                break;
            case "R":
                intent = new Intent(StartPage.this, MenuRoulette.class);
                startActivity(intent);
                break;
            case "BJ":
                intent = new Intent(StartPage.this, MenuBJ.class);
                startActivity(intent);
                break;
            case "h_BJ":
                intent = new Intent(StartPage.this, MenuBJ_h.class);
                startActivity(intent);
                break;
            case "Zonk":
                // todo
                break;
            case "Dance":
                intent = new Intent(StartPage.this, MenuDance.class);
                startActivity(intent);
                break;
        }
        // Если это дверь
        if (output.startsWith("door"))
            map = new Map(Integer.parseInt(output.substring(4)));
        // Если нужно создать окно с сообщением
        if (output.startsWith("text"))
            makeTextWindow(output.substring(4)).show();

        TextView tv = findViewById(R.id.balance);
        tv.setText("Money: " + PlayerInfo.money);
        map.render(renderMap, getApplicationContext());
    }
    // Кнопка влево
    public void leftButton(View view) {
        String output = map.moveLeft();
        switch (output) {
            case "VNT":
                intent = new Intent(StartPage.this, MenuVNT.class);
                startActivity(intent);
                break;
            case "R":
                intent = new Intent(StartPage.this, MenuRoulette.class);
                startActivity(intent);
                break;
            case "BJ":
                intent = new Intent(StartPage.this, MenuBJ.class);
                startActivity(intent);
                break;
            case "h_BJ":
                intent = new Intent(StartPage.this, MenuBJ_h.class);
                startActivity(intent);
                break;
            case "Zonk":
                // todo
                break;
            case "Dance":
                intent = new Intent(StartPage.this, MenuDance.class);
                startActivity(intent);
                break;
        }
        // Если это дверь
        if (output.startsWith("door"))
            map = new Map(Integer.parseInt(output.substring(4)));
        // Если нужно создать окно с сообщением
        if (output.startsWith("text"))
            makeTextWindow(output.substring(4)).show();

        TextView tv = findViewById(R.id.balance);
        tv.setText("Money: " + PlayerInfo.money);
        map.render(renderMap, getApplicationContext());
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