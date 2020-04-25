package vladiknt.blackgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Deck deck; // Колода (шуз)
    private int myScore, enemyScore; // Сумма очков в игре
    private int my_i, enemy_i; // Куда размещать изображение карты на столе
    private Node hidden; // Скрытая карта противника в начале игры
    private Node card1, card2; // Начальные карты игрока
    private int myWins = 0, enemyWins = 0; // Подсчет побед

    private boolean secret = false; // Согласен ли пользователь тестить фичи
    private boolean secretSex = false; // Картинки 18+
    private int counter = 0; // Сколько раз нажали на мою картинку

    private int PAUSE = 3000; // Задержка чтобы успеть посмотреть карты противника

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }

    // Кнопка входа в настройки
    public void settingsButton(View view) {
        setContentView(R.layout.settings);
    }

    // Кнопка входа в раздел "о разработчике"
    public void devButton(View view) {
        setContentView(R.layout.developer_page);
    }

    // Кнопка возвращения в меню
    public void menuButton(View view) {
        setContentView(R.layout.menu);
        ImageView start = findViewById(R.id.startImage);
        if(secretSex)
            start.setImageResource(getApplicationContext().getResources().getIdentifier("@drawable/start_sex", null, getApplicationContext().getPackageName()));
        else if(secret)
            start.setImageResource(getApplicationContext().getResources().getIdentifier("@drawable/start_secret", null, getApplicationContext().getPackageName()));
    }

    // Кнопка входа в правила игры
    public void rulesButton(View view) {
        setContentView(R.layout.rules);
    }

    // Кнопка соглашения пользователя тестить фичи
    public void secretButton(View view) {
        if(counter < 4)
            counter++;
        else {
            if(counter < 14) {
                if(counter == 4) {
                    counter++;
                    secret = true;
                    Toast notification = Toast.makeText(getApplicationContext(), "Вы открыли секретные фичи!", Toast.LENGTH_SHORT);
                    notification.show();
                } else {
                    counter++;
                }
            } else {
                if(counter == 14) {
                    counter++;
                    secretSex = true;
                    Toast notification = Toast.makeText(getApplicationContext(), "Вы открыли фичи 18+!", Toast.LENGTH_SHORT);
                    notification.show();
                }
            }
        }
    }

    public void newGame(View view) {
        setContentView(R.layout.activity_main);
        myScore = 0;
        enemyScore = 0;
        my_i = 0;
        enemy_i = 0;
        deck = new Deck();
        card1 = null;
        card2 = null;

        // Установка аватарки противника
        ImageView image = findViewById(R.id.enemyImage);
        String str = "";
        if(secret) {
            if((int) (Math.random() * 100) == 50)
                str = "drawable/admin";
            else {
                if(secretSex) {
                    switch ((int) (Math.random() * 1000) % 21) {
                        case 0:
                            str = "drawable/girl_sex";
                            break;
                        case 1:
                            str = "drawable/girl_sex2";
                            break;
                        case 2:
                            str = "drawable/girl_sex3";
                            break;
                        case 3:
                            str = "drawable/girl_sex4";
                            break;
                        case 4:
                            str = "drawable/girl_sex5";
                            break;
                        case 5:
                            str = "drawable/girl_sex6";
                            break;
                        case 6:
                            str = "drawable/girl_sex7";
                            break;
                        case 7:
                            str = "drawable/girl_sex8";
                            break;
                        case 8:
                            str = "drawable/girl_sex9";
                            break;
                        case 9:
                            str = "drawable/girl_sex10";
                            break;
                        case 10:
                            str = "drawable/girl_sex11";
                            break;
                        case 11:
                            str = "drawable/girl_sex12";
                            break;
                        case 12:
                            str = "drawable/girl_sex13";
                            break;
                        case 13:
                            str = "drawable/girl_sex14";
                            break;
                        case 14:
                            str = "drawable/girl_sex15";
                            break;
                        case 15:
                            str = "drawable/girl_sex16";
                            break;
                        case 16:
                            str = "drawable/girl_sex17";
                            break;
                        case 17:
                            str = "drawable/girl_sex18";
                            break;
                        case 18:
                            str = "drawable/girl_sex19";
                            break;
                        case 19:
                            str = "drawable/girl_sex20";
                            break;
                        case 20:
                            str = "drawable/girl_sex21";
                            break;
                    }
                } else {
                    switch ((int) (Math.random() * 1000) % 46) {
                        case 0:
                            str = "drawable/anime";
                            break;
                        case 1:
                            str = "drawable/asian";
                            break;
                        case 2:
                            str = "drawable/asian2";
                            break;
                        case 3:
                            str = "drawable/asian3";
                            break;
                        case 4:
                            str = "drawable/asian4";
                            break;
                        case 5:
                            str = "drawable/asian5";
                            break;
                        case 6:
                            str = "drawable/asian6";
                            break;
                        case 7:
                            str = "drawable/asian7";
                            break;
                        case 8:
                            str = "drawable/asian8";
                            break;
                        case 9:
                            str = "drawable/asian9";
                            break;
                        case 10:
                            str = "drawable/asian10";
                            break;
                        case 11:
                            str = "drawable/asian11";
                            break;
                        case 12:
                            str = "drawable/asian12";
                            break;
                        case 13:
                            str = "drawable/asian13";
                            break;
                        case 14:
                            str = "drawable/asian14";
                            break;
                        case 15:
                            str = "drawable/asian15";
                            break;
                        case 16:
                            str = "drawable/asian16";
                            break;
                        case 17:
                            str = "drawable/asian17";
                            break;
                        case 18:
                            str = "drawable/asian18";
                            break;
                        case 19:
                            str = "drawable/asian19";
                            break;
                        case 20:
                            str = "drawable/asian20";
                            break;
                        case 21:
                            str = "drawable/asian21";
                            break;
                        case 22:
                            str = "drawable/asian22";
                            break;
                        case 23:
                            str = "drawable/asian23";
                            break;
                        case 24:
                            str = "drawable/asian24";
                            break;
                        case 25:
                            str = "drawable/asian25";
                            break;
                        case 26:
                            str = "drawable/asian26";
                            break;
                        case 27:
                            str = "drawable/asian27";
                            break;
                        case 28:
                            str = "drawable/asian28";
                            break;
                        case 29:
                            str = "drawable/asian29";
                            break;
                        case 30:
                            str = "drawable/asian30";
                            break;
                        case 31:
                            str = "drawable/asian31";
                            break;
                        case 32:
                            str = "drawable/asian32";
                            break;
                        case 33:
                            str = "drawable/asian33";
                            break;
                        case 34:
                            str = "drawable/asian34";
                            break;
                        case 35:
                            str = "drawable/asian35";
                            break;
                        case 36:
                            str = "drawable/girl";
                            break;
                        case 37:
                            str = "drawable/girl2";
                            break;
                        case 38:
                            str = "drawable/girl3";
                            break;
                        case 39:
                            str = "drawable/girl4";
                            break;
                        case 40:
                            str = "drawable/girl5";
                            break;
                        case 41:
                            str = "drawable/girl6";
                            break;
                        case 42:
                            str = "drawable/girl7";
                            break;
                        case 43:
                            str = "drawable/girl8";
                            break;
                        case 44:
                            str = "drawable/girl9";
                            break;
                        case 45:
                            str = "drawable/girl10";
                            break;
                    }
                }
            }
        } else {
            switch ((int) (Math.random() * 1000) % 9) {
                case 0:
                    str = "drawable/anime_girl";
                    break;
                case 1:
                    str = "drawable/anime_girl2";
                    break;
                case 2:
                    str = "drawable/anime_girl3";
                    break;
                case 3:
                    str = "drawable/anime_girl4";
                    break;
                case 4:
                    str = "drawable/anime_girl5";
                    break;
                case 5:
                    str = "drawable/anime_girl6";
                    break;
                case 6:
                    str = "drawable/anime_girl7";
                    break;
                case 7:
                    str = "drawable/anime_girl8";
                    break;
                case 8:
                    str = "drawable/anime_girl9";
                    break;
            }
        }
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
                prob = 32.0 / 208;
            } else if (enemyScore == 18) {
                prob = 64.0 / 208;
            } else if (enemyScore == 17) {
                prob = 96.0 / 208;
            } else if (enemyScore == 16) {
                prob = 112.0 / 208;
            } else if (enemyScore == 15) {
                prob = 128.0 / 208;
            } else if (enemyScore == 14) {
                prob = 144.0 / 208;
            } else if (enemyScore == 13) {
                prob = 160.0 / 208;
            } else if (enemyScore == 12) {
                prob = 176.0 / 208;
            } else if (enemyScore == 11) {
                prob = 192.0 / 208;
            } else if (enemyScore <= 10) {
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
                        return 1;
                    else
                        return 11;
                } else {
                    if(enemyScore + 11 > 21)
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
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    String score;
                    setContentView(R.layout.menu);
                    ImageView start = findViewById(R.id.startImage);
                    if(secretSex)
                        start.setImageResource(getApplicationContext().getResources().getIdentifier("@drawable/start_sex", null, getApplicationContext().getPackageName()));
                    else if(secret)
                        start.setImageResource(getApplicationContext().getResources().getIdentifier("@drawable/start_secret", null, getApplicationContext().getPackageName()));

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
        ImageView start = findViewById(R.id.startImage);
        if(secretSex)
            start.setImageResource(getApplicationContext().getResources().getIdentifier("@drawable/start_sex", null, getApplicationContext().getPackageName()));
        else if(secret)
            start.setImageResource(getApplicationContext().getResources().getIdentifier("@drawable/start_secret", null, getApplicationContext().getPackageName()));

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
