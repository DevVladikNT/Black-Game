package vladiknt.blackgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivityRoulette extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_roulette);
    }

    private int answer;
    private int number;
    private boolean isNumber, red, black, odd, even, n1st12, n2nd12, n3rd12, n118, n1936, n121, n221, n321;

    public void setAnswer(View view) {
        isNumber = false;
        red = false;
        black = false;
        odd = false;
        even = false;
        n1st12 = false;
        n2nd12 = false;
        n3rd12 = false;
        n118 = false;
        n1936 = false;
        n121 = false;
        n221 = false;
        n321 = false;
        switch (view.getId()) {
            case R.id.n0:
                isNumber = true;
                number = 0;
                break;
            case R.id.n1:
                isNumber = true;
                number = 1;
                break;
            case R.id.n2:
                isNumber = true;
                number = 2;
                break;
            case R.id.n3:
                isNumber = true;
                number = 3;
                break;
            case R.id.n4:
                isNumber = true;
                number = 4;
                break;
            case R.id.n5:
                isNumber = true;
                number = 5;
                break;
            case R.id.n6:
                isNumber = true;
                number = 6;
                break;
            case R.id.n7:
                isNumber = true;
                number = 7;
                break;
            case R.id.n8:
                isNumber = true;
                number = 8;
                break;
            case R.id.n9:
                isNumber = true;
                number = 9;
                break;
            case R.id.n10:
                isNumber = true;
                number = 10;
                break;
            case R.id.n11:
                isNumber = true;
                number = 11;
                break;
            case R.id.n12:
                isNumber = true;
                number = 12;
                break;
            case R.id.n13:
                isNumber = true;
                number = 13;
                break;
            case R.id.n14:
                isNumber = true;
                number = 14;
                break;
            case R.id.n15:
                isNumber = true;
                number = 15;
                break;
            case R.id.n16:
                isNumber = true;
                number = 16;
                break;
            case R.id.n17:
                isNumber = true;
                number = 17;
                break;
            case R.id.n18:
                isNumber = true;
                number = 18;
                break;
            case R.id.n19:
                isNumber = true;
                number = 19;
                break;
            case R.id.n20:
                isNumber = true;
                number = 20;
                break;
            case R.id.n21:
                isNumber = true;
                number = 21;
                break;
            case R.id.n22:
                isNumber = true;
                number = 22;
                break;
            case R.id.n23:
                isNumber = true;
                number = 23;
                break;
            case R.id.n24:
                isNumber = true;
                number = 24;
                break;
            case R.id.n25:
                isNumber = true;
                number = 25;
                break;
            case R.id.n26:
                isNumber = true;
                number = 26;
                break;
            case R.id.n27:
                isNumber = true;
                number = 27;
                break;
            case R.id.n28:
                isNumber = true;
                number = 28;
                break;
            case R.id.n29:
                isNumber = true;
                number = 29;
                break;
            case R.id.n30:
                isNumber = true;
                number = 30;
                break;
            case R.id.n31:
                isNumber = true;
                number = 31;
                break;
            case R.id.n32:
                isNumber = true;
                number = 32;
                break;
            case R.id.n33:
                isNumber = true;
                number = 33;
                break;
            case R.id.n34:
                isNumber = true;
                number = 34;
                break;
            case R.id.n35:
                isNumber = true;
                number = 35;
                break;
            case R.id.n36:
                isNumber = true;
                number = 36;
                break;
            case R.id.nBlack:
                black = true;
                break;
            case R.id.nRed:
                red = true;
                break;
            case R.id.nEven:
                even = true;
                break;
            case R.id.nOdd:
                odd = true;
                break;
            case R.id.n118:
                n118 = true;
                break;
            case R.id.n1936:
                n1936 = true;
                break;
            case R.id.n112:
                n1st12 = true;
                break;
            case R.id.n212:
                n2nd12 = true;
                break;
            case R.id.n312:
                n3rd12 = true;
                break;
            case R.id.n121:
                n121 = true;
                break;
            case R.id.n221:
                n221 = true;
                break;
            case R.id.n321:
                n321 = true;
                break;
        }
        Toast.makeText(getApplicationContext(), "Ставка принята.", Toast.LENGTH_SHORT).show();
    }
    public void acceptAnswer(View view) {
        // Если выбран один из вариантов, иначе не запускаем рулетку
        if (isNumber || red || black || odd || even || n1st12 || n2nd12 || n3rd12 || n118 || n1936 || n121 || n221 || n321) {
            answer = ((int) (Math.random() * 100000)) % 37;

            // Блокируем кнопки
            findViewById(R.id.mainRouletteActivityAccept).setClickable(false);
            findViewById(R.id.n0).setClickable(false);
            findViewById(R.id.n1).setClickable(false);
            findViewById(R.id.n2).setClickable(false);
            findViewById(R.id.n3).setClickable(false);
            findViewById(R.id.n4).setClickable(false);
            findViewById(R.id.n5).setClickable(false);
            findViewById(R.id.n6).setClickable(false);
            findViewById(R.id.n7).setClickable(false);
            findViewById(R.id.n8).setClickable(false);
            findViewById(R.id.n9).setClickable(false);
            findViewById(R.id.n10).setClickable(false);
            findViewById(R.id.n11).setClickable(false);
            findViewById(R.id.n12).setClickable(false);
            findViewById(R.id.n13).setClickable(false);
            findViewById(R.id.n14).setClickable(false);
            findViewById(R.id.n15).setClickable(false);
            findViewById(R.id.n16).setClickable(false);
            findViewById(R.id.n17).setClickable(false);
            findViewById(R.id.n18).setClickable(false);
            findViewById(R.id.n19).setClickable(false);
            findViewById(R.id.n20).setClickable(false);
            findViewById(R.id.n21).setClickable(false);
            findViewById(R.id.n22).setClickable(false);
            findViewById(R.id.n23).setClickable(false);
            findViewById(R.id.n24).setClickable(false);
            findViewById(R.id.n25).setClickable(false);
            findViewById(R.id.n26).setClickable(false);
            findViewById(R.id.n27).setClickable(false);
            findViewById(R.id.n28).setClickable(false);
            findViewById(R.id.n29).setClickable(false);
            findViewById(R.id.n30).setClickable(false);
            findViewById(R.id.n31).setClickable(false);
            findViewById(R.id.n32).setClickable(false);
            findViewById(R.id.n33).setClickable(false);
            findViewById(R.id.n34).setClickable(false);
            findViewById(R.id.n35).setClickable(false);
            findViewById(R.id.n36).setClickable(false);
            findViewById(R.id.nRed).setClickable(false);
            findViewById(R.id.nBlack).setClickable(false);
            findViewById(R.id.nOdd).setClickable(false);
            findViewById(R.id.nEven).setClickable(false);
            findViewById(R.id.n112).setClickable(false);
            findViewById(R.id.n212).setClickable(false);
            findViewById(R.id.n312).setClickable(false);
            findViewById(R.id.n118).setClickable(false);
            findViewById(R.id.n1936).setClickable(false);
            findViewById(R.id.n321).setClickable(false);
            findViewById(R.id.n221).setClickable(false);
            findViewById(R.id.n121).setClickable(false);

            // Анимация прокрутки шара
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r32", null, getApplicationContext().getPackageName()));
                }
            }, 54);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r15", null, getApplicationContext().getPackageName()));
                }
            }, 108);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r19", null, getApplicationContext().getPackageName()));
                }
            }, 162);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r4", null, getApplicationContext().getPackageName()));
                }
            }, 216);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r21", null, getApplicationContext().getPackageName()));
                }
            }, 270);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r2", null, getApplicationContext().getPackageName()));
                }
            }, 324);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r25", null, getApplicationContext().getPackageName()));
                }
            }, 378);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r17", null, getApplicationContext().getPackageName()));
                }
            }, 432);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r34", null, getApplicationContext().getPackageName()));
                }
            }, 486);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r6", null, getApplicationContext().getPackageName()));
                }
            }, 540);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r27", null, getApplicationContext().getPackageName()));
                }
            }, 594);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r13", null, getApplicationContext().getPackageName()));
                }
            }, 648);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r36", null, getApplicationContext().getPackageName()));
                }
            }, 702);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r11", null, getApplicationContext().getPackageName()));
                }
            }, 756);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r30", null, getApplicationContext().getPackageName()));
                }
            }, 810);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r8", null, getApplicationContext().getPackageName()));
                }
            }, 864);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r23", null, getApplicationContext().getPackageName()));
                }
            }, 918);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r10", null, getApplicationContext().getPackageName()));
                }
            }, 972);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r5", null, getApplicationContext().getPackageName()));
                }
            }, 1026);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r24", null, getApplicationContext().getPackageName()));
                }
            }, 1080);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r16", null, getApplicationContext().getPackageName()));
                }
            }, 1134);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r33", null, getApplicationContext().getPackageName()));
                }
            }, 1188);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r1", null, getApplicationContext().getPackageName()));
                }
            }, 1242);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r20", null, getApplicationContext().getPackageName()));
                }
            }, 1296);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r14", null, getApplicationContext().getPackageName()));
                }
            }, 1350);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r31", null, getApplicationContext().getPackageName()));
                }
            }, 1404);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r9", null, getApplicationContext().getPackageName()));
                }
            }, 1458);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r22", null, getApplicationContext().getPackageName()));
                }
            }, 1512);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r18", null, getApplicationContext().getPackageName()));
                }
            }, 1566);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r29", null, getApplicationContext().getPackageName()));
                }
            }, 1620);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r7", null, getApplicationContext().getPackageName()));
                }
            }, 1674);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r28", null, getApplicationContext().getPackageName()));
                }
            }, 1728);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r12", null, getApplicationContext().getPackageName()));
                }
            }, 1782);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r35", null, getApplicationContext().getPackageName()));
                }
            }, 1836);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r3", null, getApplicationContext().getPackageName()));
                }
            }, 1890);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r26", null, getApplicationContext().getPackageName()));
                }
            }, 1944);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r0", null, getApplicationContext().getPackageName()));
                }
            }, 1998);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageView iv = findViewById(R.id.rouletteAnswer);
                    String str = "drawable/r" + answer;
                    iv.setImageResource(getApplicationContext().getResources().getIdentifier(str, null, getApplicationContext().getPackageName()));
                }
            }, 2052);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    getAnswer();
                }
            }, 4500);
        } else
            Toast.makeText(this, "Вы не выбрали ни одно число.", Toast.LENGTH_SHORT).show();
    }
    public void getAnswer() {
        String result = "";
        if (isNumber) {
            if (answer == number)
                result = "Победа!";
            else
                result = "Поражение.";
        } else if (odd || even) {
            if (answer % 2 == 0 && answer != 0 && even)
                result = "Победа!";
            else if (answer % 2 == 1 && odd)
                result = "Победа!";
            else
                result = "Поражение.";
        } else {
            switch (answer) {
                case 0:
                    result = "Поражение.";
                    break;
                case 1:
                case 7:
                    if (n118 || red || n1st12 || n121)
                        result = "Победа!";
                    else
                        result = "Поражение.";
                    break;
                case 2:
                case 8:
                case 11:
                    if (n118 || black || n1st12 || n221)
                        result = "Победа!";
                    else
                        result = "Поражение.";
                    break;
                case 3:
                case 9:
                case 12:
                    if (n118 || red || n1st12 || n321)
                        result = "Победа!";
                    else
                        result = "Поражение.";
                    break;
                case 4:
                case 10:
                    if (n118 || black || n1st12 || n121)
                        result = "Победа!";
                    else
                        result = "Поражение.";
                    break;
                case 5:
                    if (n118 || red || n1st12 || n221)
                        result = "Победа!";
                    else
                        result = "Поражение.";
                    break;
                case 6:
                    if (n118 || black || n1st12 || n321)
                        result = "Победа!";
                    else
                        result = "Поражение.";
                    break;
                case 13:
                    if (n118 || black || n2nd12 || n121)
                        result = "Победа!";
                    else
                        result = "Поражение.";
                    break;
                case 14:
                    if (n118 || red || n2nd12 || n221)
                        result = "Победа!";
                    else
                        result = "Поражение.";
                    break;
                case 15:
                    if (n118 || black || n2nd12 || n321)
                        result = "Победа!";
                    else
                        result = "Поражение.";
                    break;
                case 16:
                    if (n118 || red || n2nd12 || n121)
                        result = "Победа!";
                    else
                        result = "Поражение.";
                    break;
                case 17:
                    if (n118 || black || n2nd12 || n221)
                        result = "Победа!";
                    else
                        result = "Поражение.";
                    break;
                case 18:
                    if (n118 || red || n2nd12 || n321)
                        result = "Победа!";
                    else
                        result = "Поражение.";
                    break;
                case 19:
                    if (n1936 || red || n2nd12 || n121)
                        result = "Победа!";
                    else
                        result = "Поражение.";
                    break;
                case 20:
                    if (n1936 || black || n2nd12 || n221)
                        result = "Победа!";
                    else
                        result = "Поражение.";
                    break;
                case 21:
                    if (n1936 || red || n2nd12 || n321)
                        result = "Победа!";
                    else
                        result = "Поражение.";
                    break;
                case 22:
                    if (n1936 || black || n2nd12 || n121)
                        result = "Победа!";
                    else
                        result = "Поражение.";
                    break;
                case 23:
                    if (n1936 || red || n2nd12 || n221)
                        result = "Победа!";
                    else
                        result = "Поражение.";
                    break;
                case 24:
                    if (n1936 || black || n2nd12 || n321)
                        result = "Победа!";
                    else
                        result = "Поражение.";
                    break;
                case 25:
                case 34:
                    if (n1936 || red || n3rd12 || n121)
                        result = "Победа!";
                    else
                        result = "Поражение.";
                    break;
                case 26:
                case 29:
                case 35:
                    if (n1936 || black || n3rd12 || n221)
                        result = "Победа!";
                    else
                        result = "Поражение.";
                    break;
                case 27:
                case 30:
                case 36:
                    if (n1936 || red || n3rd12 || n321)
                        result = "Победа!";
                    else
                        result = "Поражение.";
                    break;
                case 28:
                case 31:
                    if (n1936 || black || n3rd12 || n121)
                        result = "Победа!";
                    else
                        result = "Поражение.";
                    break;
                case 32:
                    if (n1936 || red || n3rd12 || n221)
                        result = "Победа!";
                    else
                        result = "Поражение.";
                    break;
                case 33:
                    if (n1936 || black || n3rd12 || n321)
                        result = "Победа!";
                    else
                        result = "Поражение.";
                    break;
            }
        }

        if (result.equals("Победа!")) {
            PlayerInfo.winsCounterR++;
            if (isNumber)
                PlayerInfo.reward = PlayerInfo.bet * 36; // 36 * начальную ставку как выигрыш, начальная ставка не возвращается
            else if (red)
                PlayerInfo.reward = PlayerInfo.bet * 2;
            else if (black)
                PlayerInfo.reward = PlayerInfo.bet * 2;
            else if (odd)
                PlayerInfo.reward = PlayerInfo.bet * 2;
            else if (even)
                PlayerInfo.reward = PlayerInfo.bet * 2;
            else if (n1st12)
                PlayerInfo.reward = PlayerInfo.bet * 3;
            else if (n2nd12)
                PlayerInfo.reward = PlayerInfo.bet * 3;
            else if (n3rd12)
                PlayerInfo.reward = PlayerInfo.bet * 3;
            else if (n118)
                PlayerInfo.reward = PlayerInfo.bet * 2;
            else if (n1936)
                PlayerInfo.reward = PlayerInfo.bet * 2;
            else if (n121)
                PlayerInfo.reward = PlayerInfo.bet * 3;
            else if (n221)
                PlayerInfo.reward = PlayerInfo.bet * 3;
            else if (n321)
                PlayerInfo.reward = PlayerInfo.bet * 3;
        } else
            PlayerInfo.reward = 0;

        PlayerInfo.gamesCounterR++;
        PlayerInfo.money += PlayerInfo.reward;
        PlayerInfo.saveInfo(getFileStreamPath(PlayerInfo.data));
        Intent intent = new Intent();
        intent.putExtra("result", result);
        setResult(1, intent);
        finish();
    }

}