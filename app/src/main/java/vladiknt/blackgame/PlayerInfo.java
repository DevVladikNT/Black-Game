package vladiknt.blackgame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class PlayerInfo {

    // Данные об игроке
    static int money; // Баланс игрока
    static int winsCounterBJ; // Кол-во побед в Black Jack
    static int gamesCounterBJ; // Кол-во игр в Black Jack
    static int winsCounterR; // Кол-во побед в Roulette
    static int gamesCounterR; // Кол-во игр в Roulette

    // Данные для отображения кастомизации в приложении
    static String theme = "Standart"; // Название выбранной темы для отображения
    static boolean secret = false; // Согласен ли пользователь тестить фичи
    static boolean secretSex = false; // Картинки 18+
    static int counter = 0; // Сколько раз нажали на мою картинку

    static int bet = 0; // Текущая ставка
    static int reward = 0; // Размер выплаты после игры в случае победы

    // Методы загрузки/записи данных об игроке
    static final String data = "data.txt";
    static void loadInfo(File file) {
        if (!file.exists()) {
            money = 1000;
            winsCounterBJ = 0;
            gamesCounterBJ = 0;
            winsCounterR = 0;
            gamesCounterR = 0;
            saveInfo(file);
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String buff;
                int i = 0;
                while (true) {
                    buff = br.readLine();
                    if (buff.equals("%"))
                        break;
                    else {
                        switch (i) {
                            case 0:
                                money = Integer.parseInt(buff);
                                break;
                            case 1:
                                winsCounterBJ = Integer.parseInt(buff);
                                break;
                            case 2:
                                gamesCounterBJ = Integer.parseInt(buff);
                                break;
                            case 3:
                                winsCounterR = Integer.parseInt(buff);
                                break;
                            case 4:
                                gamesCounterR = Integer.parseInt(buff);
                                break;
                            case 5:
                                theme = buff;
                        }
                    }
                    i++;
                }
            } catch (Exception e) {
                //
            }
        }
    }
    static void saveInfo(File file) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("" + money);
            bw.newLine();
            bw.append("" + winsCounterBJ);
            bw.newLine();
            bw.append("" + gamesCounterBJ);
            bw.newLine();
            bw.append("" + winsCounterR);
            bw.newLine();
            bw.append("" + gamesCounterR);
            bw.newLine();
            bw.append(theme);
            bw.newLine();
            bw.append("%");
        } catch (Exception e) {
            //
        }
    }

}
