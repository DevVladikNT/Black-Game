package vladiknt.blackgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import vladiknt.blackgame.blackjack.Deck;
import vladiknt.blackgame.blackjack.Node;

public class MainActivityBJ extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bj);
        ImageView iv = findViewById(R.id.drawCard);
        iv.setClickable(false);
        Button btn = findViewById(R.id.stopDraw);
        btn.setClickable(false);
        newGame();
    }

    // Кнопка помощи во время игры
    public void helpButton(View view) {
        Intent intent = new Intent(MainActivityBJ.this, RulesBJ.class);
        startActivity(intent);
    }

    private Deck deck; // Колода (шуз)
    private int myScore, enemyScore; // Сумма очков в игре
    private int my_i, enemy_i; // Куда размещать изображение карты на столе
    private Node hidden; // Скрытая карта противника в начале игры
    private Node card1, card2; // Начальные карты игрока
    private Node ecard; // Первая карта противника

    private int PAUSE = 3000; // Задержка чтобы успеть посмотреть карты противника

    public void newGame() {
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
        switch (PlayerInfo.theme) {
            case "Standart":
                str = "drawable/standart" + ((int)(Math.random()*100000)%7 + 1);
                break;
            case "Anime":
                if (PlayerInfo.secretSex)
                    str = "drawable/anime18_" + ((int)(Math.random()*100000)%22 + 1);
                else if (PlayerInfo.secret)
                    str = "drawable/anime_" + ((int)(Math.random()*100000)%14 + 1);
                else
                    str = "drawable/anime" + ((int)(Math.random()*100000)%9 + 1);
                break;
            case "Asians":
                if (PlayerInfo.secretSex)
                    str = "drawable/asian18_" + ((int)(Math.random()*100000)%27 + 1);
                else if (PlayerInfo.secret)
                    str = "drawable/asian_" + ((int)(Math.random()*100000)%40 + 1);
                else
                    str = "drawable/asian" + ((int)(Math.random()*100000)%32 + 1);
                break;
            case "Boys":
                str = "drawable/boy" + ((int)(Math.random()*100000)%10 + 1);
                break;
            case "Girls":
                if (PlayerInfo.secretSex)
                    str = "drawable/girl18_" + ((int)(Math.random()*100000)%24 + 1);
                else if (PlayerInfo.secret)
                    str = "drawable/girl_" + ((int)(Math.random()*100000)%32 + 1);
                else
                    str = "drawable/girl" + ((int)(Math.random()*100000)%21 + 1);
                break;
            case "BW":
                if (PlayerInfo.secretSex)
                    str = "drawable/bw18_" + ((int)(Math.random()*100000)%14 + 1);
                else if (PlayerInfo.secret)
                    str = "drawable/bw_" + ((int)(Math.random()*100000)%15 + 1);
                else
                    str = "drawable/bw" + ((int)(Math.random()*100000)%10 + 1);
                break;
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
                ImageView iv = findViewById(R.id.drawCard);
                iv.setClickable(true);
                Button btn = findViewById(R.id.stopDraw);
                btn.setClickable(true);
            }
        }, 2000);
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
                    if(card1.getCardName().equals("a") && card2.getCardName().equals("a")) { // Если в начале игры в руке 2 туза
                        PlayerInfo.winsCounterBJ++;
                        PlayerInfo.reward = PlayerInfo.bet * 3;
                        score = "Победа!";
                    } else {
                        if (myScore == 21) {
                            PlayerInfo.winsCounterBJ++;
                            PlayerInfo.reward = (int)(PlayerInfo.bet * 2.5);
                            score = "Победа!";
                        } else if (enemyScore == 21) {
                            PlayerInfo.reward = 0;
                            score = "Поражение.";
                        } else if (myScore > 21) {
                            PlayerInfo.reward = 0;
                            score = "Поражение.";
                        } else if (enemyScore > 21) {
                            PlayerInfo.winsCounterBJ++;
                            PlayerInfo.reward = PlayerInfo.bet * 2;
                            score = "Победа!";
                        }
                    }

                    PlayerInfo.gamesCounterBJ++;
                    PlayerInfo.money += PlayerInfo.reward;
                    PlayerInfo.saveInfo(getFileStreamPath(PlayerInfo.data));
                    Intent intent = new Intent();
                    intent.putExtra("score", score);
                    setResult(1, intent);
                    finish();
                }
            }, PAUSE);
            return false;
        }

        //TODO дописать проверки (уникальные случаи типа 7+7+7 и т.п.)
        return true;
    }
    private void finishGame() {
        String score = "";
        if(myScore == enemyScore) {
            PlayerInfo.reward = PlayerInfo.bet;
            score = "Ничья.";
        } else if(myScore > enemyScore) {
            PlayerInfo.winsCounterBJ++;
            PlayerInfo.reward = PlayerInfo.bet * 2;
            score = "Победа!";
        } else if(myScore < enemyScore) {
            PlayerInfo.reward = 0;
            score = "Поражение.";
        }

        PlayerInfo.gamesCounterBJ++;
        PlayerInfo.money += PlayerInfo.reward;
        PlayerInfo.saveInfo(getFileStreamPath(PlayerInfo.data));
        Intent intent = new Intent();
        intent.putExtra("score", score);
        setResult(1, intent);
        finish();
    }

}
