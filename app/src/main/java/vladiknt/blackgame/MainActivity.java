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

/*
 * Если код работает, то его написал
 * Васильев Владислав. А если не работает,
 * то не знаю кто его написал. XD
 */

public class MainActivity extends AppCompatActivity {

    private Deck deck; // Колода (шуз)
    private int myScore, enemyScore; // Сумма очков в игре
    private int my_i, enemy_i; // Куда размещать изображение карты на столе
    private Node hidden; // Скрытая карта противника в начале игры
    private Node card1, card2; // Начальные карты игрока
    private Node ecard; // Первая карта противника
    private int myWins = 0, enemyWins = 0; // Подсчет побед

    private String theme = "Standart";
    private boolean secret = false; // Согласен ли пользователь тестить фичи
    private boolean secretSex = false; // Картинки 18+
    private int counter = 0; // Сколько раз нажали на мою картинку

    private int PAUSE = 3000; // Задержка чтобы успеть посмотреть карты противника
    private String enemyImage; // Картинка противника для загрузки при выходе из меню помощи

    private int currentLayout = 0; // ID текущего layout для корректной работы системной кнопки возвращенияА

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.menu);
        currentLayout = R.layout.menu;
    }

    // Системная кнопка назад
    long backPressed = 0; // Подсчет времени для выхода из приложения
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        // Обработать вызов
        switch(currentLayout) {
            case R.layout.activity_main:
            case R.layout.settings:
                menuButton(findViewById(0));
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
            case R.layout.menu:
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
    // Кнопка возвращения в меню
    public void menuButton(View view) {
        setContentView(R.layout.menu);
        currentLayout = R.layout.menu;
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
            case R.id.KPOPTheme:
                theme = "KPOP";
                Toast.makeText(getApplicationContext(), "Тема \"KPOP\" активирована.", Toast.LENGTH_SHORT).show();
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
                    str = "drawable/anime18_" + ((int)(Math.random()*100000)%16 + 1);
                else if (secret)
                    str = "drawable/anime_" + ((int)(Math.random()*100000)%5 + 1);
                else
                    str = "drawable/anime" + ((int)(Math.random()*100000)%8 + 1);
                break;
            case "Asians":
                if (secretSex)
                    str = "drawable/asian18_" + ((int)(Math.random()*100000)%22 + 1);
                else if (secret)
                    str = "drawable/asian_" + ((int)(Math.random()*100000)%27 + 1);
                else
                    str = "drawable/asian" + ((int)(Math.random()*100000)%13 + 1);
                break;
            case "Boys":
                str = "drawable/boy" + ((int)(Math.random()*100000)%10 + 1);
                break;
            case "Girls":
                if (secretSex)
                    str = "drawable/girl18_" + ((int)(Math.random()*100000)%7 + 1);
                else if (secret)
                    str = "drawable/girl_" + ((int)(Math.random()*100000)%10 + 1);
                else
                    str = "drawable/girl" + ((int)(Math.random()*100000)%10 + 1);
                break;
            case "KPOP":
                str = "drawable/kpop" + ((int)(Math.random()*100000)%19 + 1);
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
                    String score;
                    setContentView(R.layout.menu);
                    currentLayout = R.layout.menu;

                    TextView finish = findViewById(R.id.finish);
                    if(card1.getCardName().equals("a") && card2.getCardName().equals("a")) { // Если в начале игры в руке 2 туза
                        finish.clearComposingText();
                        myWins++;
                        score = "Победа! " + myWins + ":" + enemyWins;
                        finish.setText(score);
                    } else {
                        if (myScore == 21) {
                            finish.clearComposingText();
                            myWins++;
                            score = "Победа! " + myWins + ":" + enemyWins;
                            finish.setText(score);
                        } else if (enemyScore == 21) {
                            finish.clearComposingText();
                            enemyWins++;
                            score = "Поражение. " + myWins + ":" + enemyWins;
                            finish.setText(score);

                        } else if (myScore > 21) {
                            finish.clearComposingText();
                            enemyWins++;
                            score = "Поражение. " + myWins + ":" + enemyWins;
                            finish.setText(score);

                        } else if (enemyScore > 21) {
                            finish.clearComposingText();
                            myWins++;
                            score = "Победа! " + myWins + ":" + enemyWins;
                            finish.setText(score);

                        }
                    }
                }
            }, PAUSE);
            return false;
        }

        //TODO дописать проверки (уникальные случаи типа 7+7+7 и т.п.)
        return true;
    }
    private void finishGame() {
        String score;
        setContentView(R.layout.menu);
        currentLayout = R.layout.menu;

        TextView finish = findViewById(R.id.finish);
        if(myScore == enemyScore) {
            finish.clearComposingText();
            score = "Ничья. " + myWins + ":" + enemyWins;
            finish.setText(score);
        } else if(myScore > enemyScore) {
            finish.clearComposingText();
            myWins++;
            score = "Победа! " + myWins + ":" + enemyWins;
            finish.setText(score);
        } else if(myScore < enemyScore) {
            finish.clearComposingText();
            enemyWins++;
            score = "Поражение. " + myWins + ":" + enemyWins;
            finish.setText(score);
        }
    }

}
