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
        answer = ((int) (Math.random() * 100000)) % 37;

        ImageView iv = findViewById(R.id.rouletteAnswer);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView iv = findViewById(R.id.rouletteAnswer);
                iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r32", null, getApplicationContext().getPackageName()));
            }
        }, 110);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView iv = findViewById(R.id.rouletteAnswer);
                iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r19", null, getApplicationContext().getPackageName()));
            }
        }, 220);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView iv = findViewById(R.id.rouletteAnswer);
                iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r21", null, getApplicationContext().getPackageName()));
            }
        }, 335);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView iv = findViewById(R.id.rouletteAnswer);
                iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r25", null, getApplicationContext().getPackageName()));
            }
        }, 440);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView iv = findViewById(R.id.rouletteAnswer);
                iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r6", null, getApplicationContext().getPackageName()));
            }
        }, 550);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView iv = findViewById(R.id.rouletteAnswer);
                iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r13", null, getApplicationContext().getPackageName()));
            }
        }, 660);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView iv = findViewById(R.id.rouletteAnswer);
                iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r11", null, getApplicationContext().getPackageName()));
            }
        }, 770);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView iv = findViewById(R.id.rouletteAnswer);
                iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r8", null, getApplicationContext().getPackageName()));
            }
        }, 880);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView iv = findViewById(R.id.rouletteAnswer);
                iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r5", null, getApplicationContext().getPackageName()));
            }
        }, 990);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView iv = findViewById(R.id.rouletteAnswer);
                iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r16", null, getApplicationContext().getPackageName()));
            }
        }, 1100);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView iv = findViewById(R.id.rouletteAnswer);
                iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r1", null, getApplicationContext().getPackageName()));
            }
        }, 1210);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView iv = findViewById(R.id.rouletteAnswer);
                iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r14", null, getApplicationContext().getPackageName()));
            }
        }, 1320);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView iv = findViewById(R.id.rouletteAnswer);
                iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r22", null, getApplicationContext().getPackageName()));
            }
        }, 1430);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView iv = findViewById(R.id.rouletteAnswer);
                iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r29", null, getApplicationContext().getPackageName()));
            }
        }, 1540);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView iv = findViewById(R.id.rouletteAnswer);
                iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r28", null, getApplicationContext().getPackageName()));
            }
        }, 1650);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView iv = findViewById(R.id.rouletteAnswer);
                iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r35", null, getApplicationContext().getPackageName()));
            }
        }, 1760);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView iv = findViewById(R.id.rouletteAnswer);
                iv.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/r0", null, getApplicationContext().getPackageName()));
            }
        }, 1870);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView iv = findViewById(R.id.rouletteAnswer);
                String str = "drawable/r" + answer;
                iv.setImageResource(getApplicationContext().getResources().getIdentifier(str, null, getApplicationContext().getPackageName()));
            }
        }, 1980);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getAnswer();
            }
        }, 4000);
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
        Intent intent = new Intent();
        intent.putExtra("result", result);
        setResult(1, intent);
        finish();
    }

}