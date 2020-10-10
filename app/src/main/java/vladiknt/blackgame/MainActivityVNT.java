package vladiknt.blackgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityVNT extends AppCompatActivity {

    int[] wheel1 = new int[]{5, 1, 4, 1, 3, 0, 2, 0, 1, 6}; // Первое колесо
    int[] wheel2 = new int[]{5, 1, 4, 0, 3, 0, 2, 0, 1, 7}; // Второе колесо
    int[] wheel3 = new int[]{5, 0, 4, 0, 3, 0, 1, 0, 1, 8}; // Третье колесо
    int bonus = 0; // Кол-во оставшихся бонусных игр

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_vnt);
    }

    public void newSpin(View view) {
        final TextView tv1 = findViewById(R.id.slots_result1);
        final TextView tv2 = findViewById(R.id.slots_result2);
        final TextView tv3 = findViewById(R.id.slots_result3);
        final ImageView iv11 = findViewById(R.id.slots1_1);
        final ImageView iv12 = findViewById(R.id.slots1_2);
        final ImageView iv13 = findViewById(R.id.slots1_3);
        final ImageView iv21 = findViewById(R.id.slots2_1);
        final ImageView iv22 = findViewById(R.id.slots2_2);
        final ImageView iv23 = findViewById(R.id.slots2_3);
        final ImageView iv31 = findViewById(R.id.slots3_1);
        final ImageView iv32 = findViewById(R.id.slots3_2);
        final ImageView iv33 = findViewById(R.id.slots3_3);
        findViewById(R.id.VNTButton).setClickable(false);

        final int firstRand = (int)(Math.random() * 1000) % 10;
        final int secondRand = (int)(Math.random() * 1000) % 10;
        final int thirdRand = (int)(Math.random() * 1000) % 10;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iv11.setImageResource(R.drawable.slots_default);
                iv12.setImageResource(R.drawable.slots_default);
                iv13.setImageResource(R.drawable.slots_default);
                tv1.clearComposingText();
                tv1.setText("?");
            }
        }, 500);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iv21.setImageResource(R.drawable.slots_default);
                iv22.setImageResource(R.drawable.slots_default);
                iv23.setImageResource(R.drawable.slots_default);
                tv2.clearComposingText();
                tv2.setText("?");
            }
        }, 750);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iv31.setImageResource(R.drawable.slots_default);
                iv32.setImageResource(R.drawable.slots_default);
                iv33.setImageResource(R.drawable.slots_default);
                tv3.clearComposingText();
                tv3.setText("?");
            }
        }, 1000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iv11.setImageResource(getApplicationContext().getResources().getIdentifier(getSrc((10 + firstRand - 1) % 10, 1), null, getApplicationContext().getPackageName()));
                iv12.setImageResource(getApplicationContext().getResources().getIdentifier(getSrc((10 + firstRand) % 10, 1), null, getApplicationContext().getPackageName()));
                iv13.setImageResource(getApplicationContext().getResources().getIdentifier(getSrc((10 + firstRand + 1) % 10, 1), null, getApplicationContext().getPackageName()));
                tv1.clearComposingText();
                tv1.setText("" + getK(wheel1[firstRand]));
            }
        }, 2500);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iv21.setImageResource(getApplicationContext().getResources().getIdentifier(getSrc((10 + secondRand - 1) % 10, 2), null, getApplicationContext().getPackageName()));
                iv22.setImageResource(getApplicationContext().getResources().getIdentifier(getSrc((10 + secondRand) % 10, 2), null, getApplicationContext().getPackageName()));
                iv23.setImageResource(getApplicationContext().getResources().getIdentifier(getSrc((10 + secondRand + 1) % 10, 2), null, getApplicationContext().getPackageName()));
                tv2.clearComposingText();
                tv2.setText("" + getK(wheel2[secondRand]));
            }
        }, 4000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iv31.setImageResource(getApplicationContext().getResources().getIdentifier(getSrc((10 + thirdRand - 1) % 10, 3), null, getApplicationContext().getPackageName()));
                iv32.setImageResource(getApplicationContext().getResources().getIdentifier(getSrc((10 + thirdRand) % 10, 3), null, getApplicationContext().getPackageName()));
                iv33.setImageResource(getApplicationContext().getResources().getIdentifier(getSrc((10 + thirdRand + 1) % 10, 3), null, getApplicationContext().getPackageName()));
                tv3.clearComposingText();
                tv3.setText("" + getK(wheel3[thirdRand]));
            }
        }, 5500);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                double k = 1; // Итоговый коэффициент
                String result = "Заработано: "; // Результат игры

                if (wheel1[firstRand] == 6 && wheel2[secondRand] == 7 && wheel3[thirdRand] == 8) {
                    Toast.makeText(MainActivityVNT.this, "VNT EPIC! Получено 5 бонусных игр.", Toast.LENGTH_SHORT).show();
                    bonus += 5;
                    k = 1;
                } else if ((wheel1[firstRand] == 6 && wheel2[secondRand] == 7) || (wheel1[firstRand] == 6 && wheel3[thirdRand] == 8)
                        || (wheel2[secondRand] == 7 && wheel3[thirdRand] == 8)) {
                    Toast.makeText(MainActivityVNT.this, "VNT Bonus! Получена 1 бонусная игра.", Toast.LENGTH_SHORT).show();
                    bonus++;
                    k = getK(wheel1[firstRand]) * getK(wheel2[secondRand]) * getK(wheel3[thirdRand]);
                } else if (wheel1[firstRand] == wheel2[secondRand] && wheel2[secondRand] == wheel3[thirdRand]) {
                    switch (wheel1[firstRand]) {
                        case 0:
                            Toast.makeText(MainActivityVNT.this, "Lose combo. x0", Toast.LENGTH_SHORT).show();
                            k = 0;
                            break;
                        case 1:
                            Toast.makeText(MainActivityVNT.this, "Block combo. x1", Toast.LENGTH_SHORT).show();
                            k = 1;
                            break;
                        case 3:
                            Toast.makeText(MainActivityVNT.this, "Good combo! x4", Toast.LENGTH_SHORT).show();
                            k = 4;
                            break;
                        case 4:
                            Toast.makeText(MainActivityVNT.this, "Lucky combo! x8", Toast.LENGTH_SHORT).show();
                            k = 8;
                            break;
                        case 5:
                            Toast.makeText(MainActivityVNT.this, "JackPot!!! x32", Toast.LENGTH_SHORT).show();
                            k = 32;
                            break;
                    }
                } else {
                    k = getK(wheel1[firstRand]) * getK(wheel2[secondRand]) * getK(wheel3[thirdRand]);
                }
                PlayerInfo.reward = (int)(PlayerInfo.bet * k);
                result += PlayerInfo.reward; // Записываем сколько монет получили
                PlayerInfo.money += PlayerInfo.reward;
                PlayerInfo.saveInfo(getFileStreamPath(PlayerInfo.data));
                if (bonus != 0) {
                    result += "\nБонусных игр осталось: ";
                    result += bonus;
                    Toast.makeText(MainActivityVNT.this, result, Toast.LENGTH_SHORT).show();
                    bonus--;
                    findViewById(R.id.VNTButton).setClickable(true);
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("result", result);
                    setResult(1, intent);
                    finish();
                }
            }
        }, 8500);
    }

    // Возвращает значение коэффициента по номеру картинки
    public double getK(int image) {
        switch (image) {
            case 0:
                return 0.5;
            case 1:
                return 0.8;
            case 2:
            case 6:
            case 7:
            case 8:
                return 1;
            case 3:
                return 1.2;
            case 4:
                return 1.6;
            case 5:
                return 1.9;
        }
        return 1;
    }

    // Возвращает название картинки по случайному значению и номеру колеса
    public String getSrc(int rand, int wheel) {
        String src = "drawable/slots";
        if (wheel == 1) {
            src += wheel1[rand];
        } else if (wheel == 2) {
            src += wheel2[rand];
        } else {
            src += wheel3[rand];
        }
        return src;
    }

}