package vladiknt.blackgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import vladiknt.blackgame.blackjack.Deck;
import vladiknt.blackgame.blackjack.Node;

/*
 * Если код работает, то его написал
 * Васильев Владислав. А если не работает,
 * то не знаю кто его написал. XD
 */

public class MainActivity extends AppCompatActivity {

    private String theme = "Standart";
    private boolean secret = false; // Согласен ли пользователь тестить фичи
    private boolean secretSex = false; // Картинки 18+
    private int counter = 0; // Сколько раз нажали на мою картинку

    private int currentLayout = 0; // ID текущего layout для корректной работы системной кнопки возвращения

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.start_page);
        currentLayout = R.layout.start_page;
    }

    // Системная кнопка назад
    long backPressed = 0; // Подсчет времени для выхода из приложения
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        // Обработать вызов
        switch(currentLayout) {
            case R.layout.activity_main:
                menuButton(findViewById(0));
                break;
            case R.layout.activity_main_roulette:
                menuRouletteButton(findViewById(0));
                break;
            case R.layout.settings:
            case R.layout.menu:
            case R.layout.menu_roulette:
                startPageButton(findViewById(0));
                break;
            case R.layout.themes:
            case R.layout.changes:
            case R.layout.developer_page:
            case R.layout.rules:
                settingsButton(findViewById(0));
                break;
            case R.layout.help_page:
                returnToGame(findViewById(0));
                break;
            case R.layout.start_page:
                // выход из приложения
                if (backPressed + 2000 > System.currentTimeMillis())
                    super.onBackPressed();
                else
                    Toast.makeText(getBaseContext(), "Нажмите еще раз для выхода.",
                            Toast.LENGTH_SHORT).show();
                backPressed = System.currentTimeMillis();
                break;
        }
    }
    // Кнопка возвращения в игру
    public void returnToGame(View view) {
        setContentView(R.layout.activity_main);
        currentLayout = R.layout.activity_main;
        ImageView drawing = findViewById(R.id.drawCard);
        drawing.setClickable(true);
        Button stopping = findViewById(R.id.stopDraw);
        stopping.setClickable(true);
        TextView help = findViewById(R.id.helpButton);
        help.setClickable(true);

        // Возвращаем поле с картами как было
        ImageView ev = findViewById(R.id.enemyImage);
        ImageView mc1 = getMyView(0);
        ImageView mc2 = getMyView(1);
        ImageView ec1 = getEnemyView(0);
        ImageView ec2 = getEnemyView(1);
        ev.setImageResource(getApplicationContext().getResources().getIdentifier(enemyImage, null, getApplicationContext().getPackageName()));
        mc1.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/" + card1.getCardSuit() + card1.getCardName(), null, getApplicationContext().getPackageName()));
        mc2.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/" + card2.getCardSuit() + card2.getCardName(), null, getApplicationContext().getPackageName()));
        ec1.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/" + ecard.getCardSuit() + ecard.getCardName(), null, getApplicationContext().getPackageName()));
        ec2.setImageResource(getApplicationContext().getResources().getIdentifier("drawable/shirt", null, getApplicationContext().getPackageName()));
        check();
    }
    // Кнопка помощи во время игры
    public void helpButton(View view) {
        setContentView(R.layout.help_page);
        currentLayout = R.layout.help_page;
    }
    // Кнопка входа в настройки
    public void settingsButton(View view) {
        setContentView(R.layout.settings);
        currentLayout = R.layout.settings;
    }
    // Кнопка входа в раздел "о разработчике"
    public void devButton(View view) {
        setContentView(R.layout.developer_page);
        currentLayout = R.layout.developer_page;
    }
    // Кнопка входа в меню блекджека
    public void menuButton(View view) {
        setContentView(R.layout.menu);
        currentLayout = R.layout.menu;
    }
    // Кнопка входа в меню рулетки
    public void menuRouletteButton(View view) {
        setContentView(R.layout.menu_roulette);
        currentLayout = R.layout.menu_roulette;
    }
    // Кнопка входа в правила игры
    public void rulesButton(View view) {
        setContentView(R.layout.rules);
        currentLayout = R.layout.rules;
    }
    // Кнопка входа в раздел изменений новой версии
    public void changesButton(View view) {
        setContentView(R.layout.changes);
        currentLayout = R.layout.changes;
    }
    // Кнопка входа в раздел кастомизации
    public void themeButton(View view) {
        setContentView(R.layout.themes);
        currentLayout = R.layout.themes;
    }
    // Кнопка выхода на начальную страничку
    public void startPageButton(View view) {
        setContentView(R.layout.start_page);
        currentLayout = R.layout.start_page;
    }

    public void changeTheme(View view) {
        switch (view.getId()) {
            case R.id.BoysTheme:
                theme = "Boys";
                Toast.makeText(getApplicationContext(), "Тема \"Boys\" активирована.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.GirlsTheme:
                theme = "Girls";
                Toast.makeText(getApplicationContext(), "Тема \"Girls\" активирована.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.AnimeTheme:
                theme = "Anime";
                Toast.makeText(getApplicationContext(), "Тема \"Anime\" активирована.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.AsiansTheme:
                theme = "Asians";
                Toast.makeText(getApplicationContext(), "Тема \"Asians\" активирована.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.BWTheme:
                theme = "BW";
                Toast.makeText(getApplicationContext(), "Тема \"BlackWhite\" активирована.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.StandartTheme:
                theme = "Standart";
                Toast.makeText(getApplicationContext(), "Тема \"Standart\" активирована.", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    // Кнопка соглашения пользователя тестить фичи
    public void secretButton(View view) {
        if(counter < 4)
            counter++;
        else {
            if(counter == 4) {
                counter++;
                secret = true;
                Toast.makeText(getApplicationContext(), "Вы открыли фичи!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void secretButton2(View view) {
        if(counter < 14 && counter > 4) {
            counter++;
        } else {
            if(counter == 14) {
                counter++;
                secretSex = true;
                Toast.makeText(getApplicationContext(), "Вы открыли картинки 18+!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /*
     * BLACK JACK
     */

    private Deck deck; // Колода (шуз)
    private int myScore, enemyScore; // Сумма очков в игре
    private int my_i, enemy_i; // Куда размещать изображение карты на столе
    private Node hidden; // Скрытая карта противника в начале игры
    private Node card1, card2; // Начальные карты игрока
    private Node ecard; // Первая карта противника
    private int myWins = 0, enemyWins = 0; // Подсчет побед

    private int PAUSE = 3000; // Задержка чтобы успеть посмотреть карты противника
    private String enemyImage; // Картинка противника для загрузки при выходе из меню помощи

    public void newGame(View view) {
        setContentView(R.layout.activity_main);
        currentLayout = R.layout.activity_main;
        myScore = 0;
        enemyScore = 0;
        my_i = 0;
        enemy_i = 0;
        deck = new Deck();
        card1 = null;
        card2 = null;
        ecard = null;

        // Установка аватарки противника
        ImageView image = findViewById(R.id.enemyImage);
        String str = "";
        switch (theme) {
            case "Standart":
                str = "drawable/standart" + ((int)(Math.random()*100000)%7 + 1);
                break;
            case "Anime":
                if (secretSex)
                    str = "drawable/anime18_" + ((int)(Math.random()*100000)%17 + 1);
                else if (secret)
                    str = "drawable/anime_" + ((int)(Math.random()*100000)%5 + 1);
                else
                    str = "drawable/anime" + ((int)(Math.random()*100000)%9 + 1);
                break;
            case "Asians":
                if (secretSex)
                    str = "drawable/asian18_" + ((int)(Math.random()*100000)%22 + 1);
                else if (secret)
                    str = "drawable/asian_" + ((int)(Math.random()*100000)%32 + 1);
                else
                    str = "drawable/asian" + ((int)(Math.random()*100000)%30 + 1);
                break;
            case "Boys":
                str = "drawable/boy" + ((int)(Math.random()*100000)%10 + 1);
                break;
            case "Girls":
                if (secretSex)
                    str = "drawable/girl18_" + ((int)(Math.random()*100000)%7 + 1);
                else if (secret)
                    str = "drawable/girl_" + ((int)(Math.random()*100000)%12 + 1);
                else
                    str = "drawable/girl" + ((int)(Math.random()*100000)%12 + 1);
                break;
            case "BW":
                if (secretSex)
                    str = "drawable/bw18_" + ((int)(Math.random()*100000)%14 + 1);
                else if (secret)
                    str = "drawable/bw_" + ((int)(Math.random()*100000)%15 + 1);
                else
                    str = "drawable/bw" + ((int)(Math.random()*100000)%10 + 1);
                break;
        }
        enemyImage = str; // Запомнили аватарку противника
        image.setImageResource(getApplicationContext().getResources().getIdentifier(str, null, getApplicationContext().getPackageName()));

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getCard();
            }
        }, 500);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getCard();
            }
        }, 1000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                enemyGetCard();
            }
        }, 1500);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                enemyGetCardHidden();
                check();
            }
        }, 2000);

        ImageView drawing = findViewById(R.id.drawCard);
        drawing.setClickable(true);
        Button stopping = findViewById(R.id.stopDraw);
        stopping.setClickable(true);
        TextView help = findViewById(R.id.helpButton);
        help.setClickable(true);
    }
    public void getCardButton(View view) {
        getCard();
        check();
    }
    public void stopButton(View view) {
        ImageView drawing = findViewById(R.id.drawCard);
        drawing.setClickable(false);
        Button stopping = findViewById(R.id.stopDraw);
        stopping.setClickable(false);
        TextView help = findViewById(R.id.helpButton);
        help.setClickable(false);

        // Бот вскрывает спрятанную карту
        ImageView iv = getEnemyView(1);
        String str = "drawable/";
        str += hidden.getCardSuit();
        str += hidden.getCardName();
        Context c = getApplicationContext();
        int imageId = c.getResources().getIdentifier(str, null, c.getPackageName());
        iv.setImageResource(imageId);
        hidden = null;
        check();

        // Бот решает брать или остановиться
        Handler handler = new Handler();
        double prob = 0;
        while (check()) {
            if (enemyScore == 20) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finishGame();
                    }
                }, PAUSE);
                break;
            }
            else if (enemyScore == 19) {
                prob = 48.0 / 208;
            } else if (enemyScore == 18) {
                prob = 80.0 / 208;
            } else if (enemyScore == 17) {
                prob = 112.0 / 208;
            } else if (enemyScore == 16) {
                prob = 128.0 / 208;
            } else if (enemyScore == 15) {
                prob = 144.0 / 208;
            } else if (enemyScore == 14) {
                prob = 160.0 / 208;
            } else if (enemyScore == 13) {
                prob = 176.0 / 208;
            } else if (enemyScore == 12) {
                prob = 192.0 / 208;
            } else if (enemyScore <= 11) {
                prob = 1;
            }
            if (Math.random() > prob) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finishGame();
                    }
                }, PAUSE);
                break;
            }

            enemyGetCard();
        }
    }

    private void getCard() {
        String str = "drawable/";
        ImageView iv = getMyView(my_i);
        my_i++;
        Node card = deck.getCard();
        myScore += getScore(card.getCardName(), true);
        str += card.getCardSuit();
        str += card.getCardName();

        // Запоминаем карты
        if(card1 == null)
            card1 = card;
        else if(card2 == null)
            card2 = card;

        // Установка картинки по названию
        iv.setImageResource(getApplicationContext().getResources().getIdentifier(str, null, getApplicationContext().getPackageName()));
    }
    private void enemyGetCard() {
        String str = "drawable/";
        ImageView iv = getEnemyView(enemy_i);
        enemy_i++;
        Node card = deck.getCard();
        enemyScore += getScore(card.getCardName(), false);
        str += card.getCardSuit();
        str += card.getCardName();

        // Запоминаем первую карту противника
        if(ecard == null)
            ecard = card;

        // Установка картинки по названию
        iv.setImageResource(getApplicationContext().getResources().getIdentifier(str, null, getApplicationContext().getPackageName()));
    }
    private void enemyGetCardHidden() {
        String str = "drawable/shirt";
        ImageView iv = getEnemyView(enemy_i);
        enemy_i++;
        Node card = deck.getCard();
        hidden = card;
        enemyScore += getScore(card.getCardName(), false);

        // Установка картинки по названию
        iv.setImageResource(getApplicationContext().getResources().getIdentifier(str, null, getApplicationContext().getPackageName()));
    }

    private ImageView getMyView(int a) {
        switch(a%5) {
            case 0:
                return (ImageView) findViewById(R.id.card0);
            case 1:
                return (ImageView) findViewById(R.id.card1);
            case 2:
                return (ImageView) findViewById(R.id.card2);
            case 3:
                return (ImageView) findViewById(R.id.card3);
            case 4:
                return (ImageView) findViewById(R.id.card4);
        }
        return (ImageView) findViewById(R.id.card0);
    }
    private ImageView getEnemyView(int a) {
        switch(a%5) {
            case 0:
                return (ImageView) findViewById(R.id.eCard0);
            case 1:
                return (ImageView) findViewById(R.id.eCard1);
            case 2:
                return (ImageView) findViewById(R.id.eCard2);
            case 3:
                return (ImageView) findViewById(R.id.eCard3);
            case 4:
                return (ImageView) findViewById(R.id.eCard4);
        }
        return (ImageView) findViewById(R.id.eCard0);
    }

    // Функция рассчёта получаемых очков по названию карты, второй параметр: true - игрок, false - бот
    private int getScore(String name, boolean player) {
        switch(name) {
            case "2":
            case "j":
                return 2;
            case "3":
            case "q":
                return 3;
            case "4":
            case "k":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "10":
                return 10;
            case "a":
                if(player) {
                    if (myScore + 11 > 21)
                        if(myScore == 11 && card2 == null)
                            return 11;
                        else
                            return 1;
                    else
                        return 11;
                } else {
                    if(enemyScore + 11 > 21)
                        if(hidden != null)
                            return 11;
                        else
                            return 1;
                    else
                        return 11;
                }
        }
        return 0;
    }
    private boolean check() {
        TextView tv = findViewById(R.id.my_score);
        TextView etv = findViewById(R.id.enemy_score);
        String str = "You: " + myScore;
        String estr = "Enemy: " + enemyScore;
        if(hidden != null)
            estr = "Enemy: ?";
        tv.setText(str);
        etv.setText(estr);

        Handler handler = new Handler();
        if(myScore >= 21 || enemyScore >= 21) {
            ImageView drawing = findViewById(R.id.drawCard);
            drawing.setClickable(false);
            Button stopping = findViewById(R.id.stopDraw);
            stopping.setClickable(false);
            TextView help = findViewById(R.id.helpButton);
            help.setClickable(false);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    String score = "";
                    setContentView(R.layout.menu);
                    currentLayout = R.layout.menu;

                    TextView finish = findViewById(R.id.finish);
                    finish.clearComposingText();
                    if(card1.getCardName().equals("a") && card2.getCardName().equals("a")) { // Если в начале игры в руке 2 туза
                        myWins++;
                        score = "Победа! " + myWins + ":" + enemyWins;
                    } else {
                        if (myScore == 21) {
                            myWins++;
                            score = "Победа! " + myWins + ":" + enemyWins;
                        } else if (enemyScore == 21) {
                            enemyWins++;
                            score = "Поражение. " + myWins + ":" + enemyWins;
                        } else if (myScore > 21) {
                            enemyWins++;
                            score = "Поражение. " + myWins + ":" + enemyWins;
                        } else if (enemyScore > 21) {
                            myWins++;
                            score = "Победа! " + myWins + ":" + enemyWins;
                        }
                    }
                    finish.setText(score);
                }
            }, PAUSE);
            return false;
        }

        //TODO дописать проверки (уникальные случаи типа 7+7+7 и т.п.)
        return true;
    }
    private void finishGame() {
        String score = "";
        setContentView(R.layout.menu);
        currentLayout = R.layout.menu;

        TextView finish = findViewById(R.id.finish);
        finish.clearComposingText();
        if(myScore == enemyScore) {
            score = "Ничья. " + myWins + ":" + enemyWins;
        } else if(myScore > enemyScore) {
            myWins++;
            score = "Победа! " + myWins + ":" + enemyWins;
        } else if(myScore < enemyScore) {
            enemyWins++;
            score = "Поражение. " + myWins + ":" + enemyWins;
        }
        finish.setText(score);
    }

    /*
     * ROULETTE
     */

    private int answer;
    private int number;
    private boolean isNumber, red, black, odd, even, n1st12, n2nd12, n3rd12, n118, n1936, n121, n221, n321;

    public void newSpin(View view) {
        setContentView(R.layout.activity_main_roulette);
        currentLayout = R.layout.activity_main_roulette;
    }
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
        setContentView(R.layout.menu_roulette);
        currentLayout = R.layout.menu_roulette;
        TextView tv = findViewById(R.id.finishRoulette);
        tv.clearComposingText();
        if (isNumber) {
            if (answer == number)
                tv.setText("Победа!");
            else
                tv.setText("Поражение.");
        } else if (odd || even) {
            if (answer % 2 == 0 && answer != 0 && even)
                tv.setText("Победа!");
            else if (answer % 2 == 1 && odd)
                tv.setText("Победа!");
            else
                tv.setText("Поражение.");
        } else {
            switch (answer) {
                case 0:
                    tv.setText("Поражение.");
                    break;
                case 1:
                case 7:
                    if (n118 || red || n1st12 || n121)
                        tv.setText("Победа!");
                    else
                        tv.setText("Поражение.");
                    break;
                case 2:
                case 8:
                case 11:
                    if (n118 || black || n1st12 || n221)
                        tv.setText("Победа!");
                    else
                        tv.setText("Поражение.");
                    break;
                case 3:
                case 9:
                case 12:
                    if (n118 || red || n1st12 || n321)
                        tv.setText("Победа!");
                    else
                        tv.setText("Поражение.");
                    break;
                case 4:
                case 10:
                    if (n118 || black || n1st12 || n121)
                        tv.setText("Победа!");
                    else
                        tv.setText("Поражение.");
                    break;
                case 5:
                    if (n118 || red || n1st12 || n221)
                        tv.setText("Победа!");
                    else
                        tv.setText("Поражение.");
                    break;
                case 6:
                    if (n118 || black || n1st12 || n321)
                        tv.setText("Победа!");
                    else
                        tv.setText("Поражение.");
                    break;
                case 13:
                    if (n118 || black || n2nd12 || n121)
                        tv.setText("Победа!");
                    else
                        tv.setText("Поражение.");
                    break;
                case 14:
                    if (n118 || red || n2nd12 || n221)
                        tv.setText("Победа!");
                    else
                        tv.setText("Поражение.");
                    break;
                case 15:
                    if (n118 || black || n2nd12 || n321)
                        tv.setText("Победа!");
                    else
                        tv.setText("Поражение.");
                    break;
                case 16:
                    if (n118 || red || n2nd12 || n121)
                        tv.setText("Победа!");
                    else
                        tv.setText("Поражение.");
                    break;
                case 17:
                    if (n118 || black || n2nd12 || n221)
                        tv.setText("Победа!");
                    else
                        tv.setText("Поражение.");
                    break;
                case 18:
                    if (n118 || red || n2nd12 || n321)
                        tv.setText("Победа!");
                    else
                        tv.setText("Поражение.");
                    break;
                case 19:
                    if (n1936 || red || n2nd12 || n121)
                        tv.setText("Победа!");
                    else
                        tv.setText("Поражение.");
                    break;
                case 20:
                    if (n1936 || black || n2nd12 || n221)
                        tv.setText("Победа!");
                    else
                        tv.setText("Поражение.");
                    break;
                case 21:
                    if (n1936 || red || n2nd12 || n321)
                        tv.setText("Победа!");
                    else
                        tv.setText("Поражение.");
                    break;
                case 22:
                    if (n1936 || black || n2nd12 || n121)
                        tv.setText("Победа!");
                    else
                        tv.setText("Поражение.");
                    break;
                case 23:
                    if (n1936 || red || n2nd12 || n221)
                        tv.setText("Победа!");
                    else
                        tv.setText("Поражение.");
                    break;
                case 24:
                    if (n1936 || black || n2nd12 || n321)
                        tv.setText("Победа!");
                    else
                        tv.setText("Поражение.");
                    break;
                case 25:
                case 34:
                    if (n1936 || red || n3rd12 || n121)
                        tv.setText("Победа!");
                    else
                        tv.setText("Поражение.");
                    break;
                case 26:
                case 29:
                case 35:
                    if (n1936 || black || n3rd12 || n221)
                        tv.setText("Победа!");
                    else
                        tv.setText("Поражение.");
                    break;
                case 27:
                case 30:
                case 36:
                    if (n1936 || red || n3rd12 || n321)
                        tv.setText("Победа!");
                    else
                        tv.setText("Поражение.");
                    break;
                case 28:
                case 31:
                    if (n1936 || black || n3rd12 || n121)
                        tv.setText("Победа!");
                    else
                        tv.setText("Поражение.");
                    break;
                case 32:
                    if (n1936 || red || n3rd12 || n221)
                        tv.setText("Победа!");
                    else
                        tv.setText("Поражение.");
                    break;
                case 33:
                    if (n1936 || black || n3rd12 || n321)
                        tv.setText("Победа!");
                    else
                        tv.setText("Поражение.");
                    break;
            }
        }
    }

}
