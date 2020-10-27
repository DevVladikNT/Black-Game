package vladiknt.blackgame.maps;

import android.content.Context;
import android.widget.ImageView;

import vladiknt.blackgame.PlayerInfo;

public class Map {

    private MapObject[][] map; // Карта
    private int[] position; // Позиция нашего персонажа
    private MapObject buff = new MapObject("floor"); // Буффер элемента под персонажем, чтобы он не "перемешивал" поле
    private int id; // id карты

    // Функция отрисовки участка карты в интерфейсе
    public void render(ImageView[][] renderMap, Context context) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                renderMap[i][j].setImageResource(context.getResources().getIdentifier(map[position[0] - 2 + i][position[1] - 2 + j].getImgSrc(), null, context.getPackageName()));
            }
        }
    }
    // Для добавления аватара на карту в точку position
    public void renderAvatar() {
        // Если у игрока хентай-аватар, но он сейчас не там, меняем аватар обратно
        if (!PlayerInfo.lastAvatar.equals("") && this.id != 4) {
            PlayerInfo.avatar = PlayerInfo.lastAvatar;
            PlayerInfo.lastAvatar = "";
        }
        // Устанавливаем аватар в нашу позицию
        map[position[0]][position[1]] = new MapObject(PlayerInfo.avatar);
    }

    public String moveUp() {
        switch (map[position[0] - 1][position[1]].getType()) {
            case "character":
            case "info":
                return "text" + map[position[0] - 1][position[1]].getText();
            case "slots":
                return "VNT";
            case "table_r":
                return "R";
            case "table_bj":
                return "BJ";
            case "h_table_bj":
                return "h_BJ";
            case "table_zonk":
                return "Zonk";
            case "tv":
                return "Dance";
            case "door":
                return "door" + map[position[0] - 1][position[1]].getDoorId();
        }
        if (canMove(new int[]{position[0] - 1, position[1]})) {
            MapObject buff = map[position[0] - 1][position[1]];
            map[position[0] - 1][position[1]] = map[position[0]][position[1]];
            map[position[0]][position[1]] = this.buff;
            // Если лабиринт, то пройденные клеточки заменяются на стены
            if (id == 3)
                map[position[0]][position[1]] = new MapObject("wall");
            this.buff = buff;
            position[0]--;
        }
        return "";
    }
    public String moveDown() {
        switch (map[position[0] + 1][position[1]].getType()) {
            case "character":
            case "info":
                return "text" + map[position[0] + 1][position[1]].getText();
            case "slots":
                return "VNT";
            case "table_r":
                return "R";
            case "table_bj":
                return "BJ";
            case "h_table_bj":
                return "h_BJ";
            case "table_zonk":
                return "Zonk";
            case "tv":
                return "Dance";
            case "door":
                return "door" + map[position[0] + 1][position[1]].getDoorId();
        }
        if (canMove(new int[]{position[0] + 1, position[1]})) {
            MapObject buff = map[position[0] + 1][position[1]];
            map[position[0] + 1][position[1]] = map[position[0]][position[1]];
            map[position[0]][position[1]] = this.buff;
            // Если лабиринт, то пройденные клеточки заменяются на стены
            if (id == 3)
                map[position[0]][position[1]] = new MapObject("wall");
            this.buff = buff;
            position[0]++;
        }
        return "";
    }
    public String moveRight() {
        switch (map[position[0]][position[1] + 1].getType()) {
            case "character":
            case "info":
                return "text" + map[position[0]][position[1] + 1].getText();
            case "slots":
                return "VNT";
            case "table_r":
                return "R";
            case "table_bj":
                return "BJ";
            case "h_table_bj":
                return "h_BJ";
            case "table_zonk":
                return "Zonk";
            case "tv":
                return "Dance";
            case "door":
                return "door" + map[position[0]][position[1] + 1].getDoorId();
        }
        if (canMove(new int[]{position[0], position[1] + 1})) {
            MapObject buff = map[position[0]][position[1] + 1];
            map[position[0]][position[1] + 1] = map[position[0]][position[1]];
            map[position[0]][position[1]] = this.buff;
            // Если лабиринт, то пройденные клеточки заменяются на стены
            if (id == 3)
                map[position[0]][position[1]] = new MapObject("wall");
            this.buff = buff;
            position[1]++;
        }
        return "";
    }
    public String moveLeft() {
        switch (map[position[0]][position[1] - 1].getType()) {
            case "character":
            case "info":
                return "text" + map[position[0]][position[1] - 1].getText();
            case "slots":
                return "VNT";
            case "table_r":
                return "R";
            case "table_bj":
                return "BJ";
            case "h_table_bj":
                return "h_BJ";
            case "table_zonk":
                return "Zonk";
            case "tv":
                return "Dance";
            case "door":
                return "door" + map[position[0]][position[1] - 1].getDoorId();
        }
        if (canMove(new int[]{position[0], position[1] - 1})) {
            MapObject buff = map[position[0]][position[1] - 1];
            map[position[0]][position[1] - 1] = map[position[0]][position[1]];
            map[position[0]][position[1]] = this.buff;
            // Если лабиринт, то пройденные клеточки заменяются на стены
            if (id == 3)
                map[position[0]][position[1]] = new MapObject("wall");
            this.buff = buff;
            position[1]--;
        }
        return "";
    }
    private boolean canMove(int[] position) {
        return map[position[0]][position[1]].getType().equals("floor");
    }

    // Конструктор заранее сохраненных карт по номеру карты
    public Map(int id) {
        this.id = id;
        switch (id) {
            case 1:
                // Стартовая комната
                position = new int[]{7, 2};
                map = new MapObject[][]{
                        {new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"), // 0
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("wall"), // 1
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 2
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 3
                                new MapObject("floor"), new MapObject("table_bj"), new MapObject("character", "girls1"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 4
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 5
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 6
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("character", "boys1"), new MapObject("slots"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("door"), new MapObject("floor"), // 7
                                new MapObject("floor"), new MapObject("info"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 8
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("slots"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 9
                                new MapObject("character", "girls2"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 10
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 11
                                new MapObject("floor"), new MapObject("table_r"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 12
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("wall"), // 13
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"), // 14
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall")}
                };
                // Биндим id к дверям
                map[7][1].setDoorId("2");
                // Устанавливаем текст для табличек
                map[7][4].setText("Добро пожаловать в казино Black Game! Хоть в нашем названии и упоминается черный цвет, " +
                        "мы не пытаемся Вас обмануть. Все правила описаны в соответствующем разделе в меню (кнопка сверху слева). " +
                        "Мы не \"подкручиваем\" ботов и не пытаемся скрыть принцип работы режимов. Соответствующую статистику по играм " +
                        "Вы так же можете посмотреть в меню. В случае обнаружения каких-либо ошибок в приложении, убедительная просьба " +
                        "связаться с автором приложения, контакты которого указаны в разделе меню \"О разработчике\". Благодарим за " +
                        "понимание. Приятной игры!\n" +
                        "Если у вас закончились монеты и вы больше не можете делать ставки в играх, используйте промокод MONEY2020.");
                // Устанавливаем фразы для персонажей
                map[3][5].setText("Давай сыграем в Black Jack?) Уверена, ты сможешь много заработать на этом. " +
                        "Если не умеешь играть, можешь почитать правила, которые тебе должны были выдать на входе.");
                map[6][7].setText("Не мешай мне! Не видишь я играю? (Вы выдите как собеседнику выпадают 3 кота)");
                map[9][3].setText("Добро пожаловать в наше казино. В этой комнате есть несколько интересных игр: Black Jack, " +
                        "Roulette и VNT-Slots. Мне очень нравятся VNT-Slots, но я еще ни разу не выбила джекпот((. " +
                        "Остальные игры можешь поискать в других комнатах ^-^");
                break;
            case 2:
                // Комната с окнами и секреткой
                position = new int[]{9, 6};
                map = new MapObject[][]{
                        {new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"), // 0
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"), // 1
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"), // 2
                                new MapObject("dark_wall"), new MapObject("door", "dark_wall"), new MapObject("floor", "dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("wall"), // 3
                                new MapObject("wall"), new MapObject("wall"), new MapObject("floor", "wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 4
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("window"), new MapObject("floor"), // 5
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 6
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("character", "penguin"), new MapObject("wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("window"), new MapObject("floor"), // 7
                                new MapObject("floor"), new MapObject("wall"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 8
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("window"), new MapObject("floor"), // 9
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("door"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 10
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("window"), new MapObject("floor"), // 11
                                new MapObject("floor"), new MapObject("wall"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 12
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("window"), new MapObject("floor"), // 13
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("door", "downstairs"), // 14
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("wall"), // 15
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"), // 16
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall")},
                };
                // Биндим id к дверям
                map[9][7].setDoorId("1");
                map[2][4].setDoorId("3");
                map[14][2].setDoorId("5");
                // Устанавливаем фразы для персонажей
                map[6][6].setText("Не обращай внимание на то, что я пингвин...\n" +
                        "Кстати, я тут в баре услышал, что где-то в этом казино есть секретная комната. " +
                        "Говорят, там прекрасные девушки...\n" +
                        "Не знаешь, случайно, где она?");
                break;
            case 3:
                // Лабиринт
                position = new int[]{3, 2};
                map = new MapObject[][]{
                        {new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"), // 0
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("wall"), // 1
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 2
                                new MapObject("floor"), new MapObject("floor"), new MapObject("wall"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("wall"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("wall"),
                                new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("door"), new MapObject("floor"), // 3
                                new MapObject("floor"), new MapObject("floor"), new MapObject("wall"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("floor"), new MapObject("wall"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 4
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("wall"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("wall"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("wall"),
                                new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("wall"), // 5
                                new MapObject("floor"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("floor"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("floor"), new MapObject("wall"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 6
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("wall"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("wall"), new MapObject("floor"), new MapObject("wall"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("wall"),
                                new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 7
                                new MapObject("wall"), new MapObject("wall"), new MapObject("floor"),
                                new MapObject("wall"), new MapObject("floor"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("floor"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("floor"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 8
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("wall"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("floor"),
                                new MapObject("wall"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("wall"),
                                new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 9
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("floor"), new MapObject("wall"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("floor"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 10
                                new MapObject("floor"), new MapObject("wall"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("wall"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("wall"),
                                new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("wall"), // 11
                                new MapObject("floor"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("floor"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("floor"),
                                new MapObject("wall"), new MapObject("floor"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 12
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("wall"), new MapObject("floor"), new MapObject("wall"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("wall"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("wall"),
                                new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 13
                                new MapObject("wall"), new MapObject("floor"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("floor"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("floor"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("floor"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 14
                                new MapObject("wall"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("wall"),
                                new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("wall"), // 15
                                new MapObject("wall"), new MapObject("floor"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 16
                                new MapObject("floor"), new MapObject("floor"), new MapObject("wall"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("wall"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("wall"),
                                new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("wall"), // 17
                                new MapObject("floor"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("floor"), new MapObject("wall"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 18
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("wall"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 19
                                new MapObject("wall"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("wall"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("wall"), // 20
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"), // 21
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall")}
                };
                // Установка двери в хентай-комнату
                int[][] hidden = new int[][]{{6, 16}, {10, 5}, {14, 12}, {16, 6}, {16, 19}}; // Позиции для скрытой двери
                int[] hiddenPosition = hidden[((int)(Math.random() * 100000)) % 5];
                map[hiddenPosition[0]][hiddenPosition[1]] = new MapObject("door"); // Установили дверь в рандомном месте
                map[hiddenPosition[0]][hiddenPosition[1]].setDoorId("4");
                // Биндим id к дверям
                map[3][1].setDoorId("2");
                break;
            case 4:
                // Хентай-комната
                position = new int[]{8, 5};
                map = new MapObject[][]{
                        {new MapObject("h_dark_wall"), new MapObject("h_dark_wall"), new MapObject("h_dark_wall"), // 0
                                new MapObject("h_dark_wall"), new MapObject("h_dark_wall"), new MapObject("h_dark_wall"),
                                new MapObject("h_dark_wall"), new MapObject("h_dark_wall"), new MapObject("h_dark_wall"),
                                new MapObject("h_dark_wall"), new MapObject("h_dark_wall")},
                        {new MapObject("h_dark_wall"), new MapObject("h_wall"), new MapObject("h_wall"), // 1
                                new MapObject("h_wall"), new MapObject("h_wall"), new MapObject("h_wall"),
                                new MapObject("h_wall"), new MapObject("h_wall"), new MapObject("h_wall"),
                                new MapObject("h_wall"), new MapObject("h_dark_wall")},
                        {new MapObject("h_dark_wall"), new MapObject("h_wall"), new MapObject("floor", "h_floor"), // 2
                                new MapObject("floor", "h_floor"), new MapObject("floor", "h_floor"), new MapObject("floor", "h_floor"),
                                new MapObject("floor", "h_floor"), new MapObject("floor", "h_floor"), new MapObject("floor", "h_floor"),
                                new MapObject("h_wall"), new MapObject("h_dark_wall")},
                        {new MapObject("h_dark_wall"), new MapObject("h_wall"), new MapObject("floor", "h_floor"), // 3
                                new MapObject("floor", "h_floor"), new MapObject("floor", "h_floor"), new MapObject("character", "h_girls3"),
                                new MapObject("h_wall"), new MapObject("floor", "h_floor"), new MapObject("floor", "h_floor"),
                                new MapObject("h_wall"), new MapObject("h_dark_wall")},
                        {new MapObject("h_dark_wall"), new MapObject("h_wall"), new MapObject("character", "h_girls1"), // 4
                                new MapObject("h_table_bj"), new MapObject("floor", "h_floor"), new MapObject("floor", "h_floor"),
                                new MapObject("h_wall"), new MapObject("floor", "h_floor"), new MapObject("floor", "h_floor"),
                                new MapObject("h_wall"), new MapObject("h_dark_wall")},
                        {new MapObject("h_dark_wall"), new MapObject("h_wall"), new MapObject("floor", "h_floor"), // 5
                                new MapObject("floor", "h_floor"), new MapObject("floor", "h_floor"), new MapObject("floor", "h_floor"),
                                new MapObject("h_wall"), new MapObject("floor", "h_floor"), new MapObject("character", "h_girls2"),
                                new MapObject("h_wall"), new MapObject("h_dark_wall")},
                        {new MapObject("h_dark_wall"), new MapObject("h_wall"), new MapObject("h_wall"), // 6
                                new MapObject("h_wall"), new MapObject("h_wall"), new MapObject("h_wall"),
                                new MapObject("h_wall"), new MapObject("floor", "h_floor"), new MapObject("h_wall"),
                                new MapObject("h_wall"), new MapObject("h_dark_wall")},
                        {new MapObject("h_dark_wall"), new MapObject("h_wall"), new MapObject("floor", "h"), // 7
                                new MapObject("floor", "e"), new MapObject("floor", "n"), new MapObject("floor", "t"),
                                new MapObject("floor", "a"), new MapObject("floor", "i"), new MapObject("floor", "tilda"),
                                new MapObject("h_wall"), new MapObject("h_dark_wall")},
                        {new MapObject("h_dark_wall"), new MapObject("h_wall"), new MapObject("character", "h_girls5"), // 8
                                new MapObject("character", "h_girls4"), new MapObject("floor", "h_floor"), new MapObject("floor", "h_floor"),
                                new MapObject("floor", "h_floor"), new MapObject("floor", "h_floor"), new MapObject("floor", "h_floor"),
                                new MapObject("h_wall"), new MapObject("h_dark_wall")},
                        {new MapObject("h_dark_wall"), new MapObject("h_wall"), new MapObject("h_wall"), // 9
                                new MapObject("h_wall"), new MapObject("h_wall"), new MapObject("door", "h_door"),
                                new MapObject("h_wall"), new MapObject("h_wall"), new MapObject("h_wall"),
                                new MapObject("h_wall"), new MapObject("h_dark_wall")},
                        {new MapObject("h_dark_wall"), new MapObject("h_dark_wall"), new MapObject("h_dark_wall"), // 10
                                new MapObject("h_dark_wall"), new MapObject("h_dark_wall"), new MapObject("h_dark_wall"),
                                new MapObject("h_dark_wall"), new MapObject("h_dark_wall"), new MapObject("h_dark_wall"),
                                new MapObject("h_dark_wall"), new MapObject("h_dark_wall")},
                };
                // Меняем аватара, чтобы он подходил под текстуры хентай-комнаты
                PlayerInfo.lastAvatar = PlayerInfo.avatar;
                PlayerInfo.avatar = "h_me";
                // Загружаем в буффер хентайную плиточку, на которой стоит персонаж, чтобы то место не было синим
                buff = new MapObject("floor", "h_floor");
                // Биндим id к дверям
                map[9][5].setDoorId("2");
                // Устанавливаем фразы для персонажей
                map[3][5].setText("Проходи, не стесняйся ^-^\n" +
                        "У нас тут весело. Только вот мы смогли сюда принести всего один стол и несколько колод карт, " +
                        "но ты ведь любишь играть в Black Jack?");
                map[4][2].setText("Хочешь поиграть с кошечкой?");
                map[5][8].setText("Наруто, это ты?..\n" +
                        "Кажется, нет...\n" +
                        "Ох, после той таблетки всё так размыто...\n" +
                        "Зачем я её взяла т_т");
                map[8][2].setText("Привет, давненько к нам никто не заходил. Хочешь присоединиться?");
                map[8][3].setText("Ааааах, как же хорошо...");
                break;
            case 5:
                // Коридор к бару
                position = new int[]{2, 3};
                map = new MapObject[][]{
                        {new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"), // 0
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("wall"), // 1
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("door", "upstairs"), // 2
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("door"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("wall"), // 3
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"), // 4
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall")}
                };
                // Биндим id к дверям
                map[2][2].setDoorId("2");
                map[2][7].setDoorId("6");
                break;
            case 6:
                // Бар
                position = new int[]{12, 2};
                map = new MapObject[][]{
                        {new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"), // 0
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("wall"), // 1
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 2
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall", "door"), new MapObject("floor"), // 3
                                new MapObject("character", "me"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("bar_table"), // 4
                                new MapObject("table_zonk"), new MapObject("bar_table"), new MapObject("bar_table"),
                                new MapObject("floor", "bar_door"), new MapObject("wall"), new MapObject("tv"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("character", "boys1"), // 5
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 6
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor", "dance_floor"), new MapObject("floor", "dance_floor"),
                                new MapObject("floor", "dance_floor"), new MapObject("wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 7
                                new MapObject("floor"), new MapObject("table"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor", "dance_floor"), new MapObject("floor", "dance_floor"),
                                new MapObject("floor", "dance_floor"), new MapObject("wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 8
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor", "dance_floor"), new MapObject("floor", "dance_floor"),
                                new MapObject("floor", "dance_floor"), new MapObject("wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 9
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 10
                                new MapObject("floor"), new MapObject("table"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 11
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("door"), new MapObject("floor"), // 12
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("wall"), // 13
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"), // 14
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall")}
                };
                // Биндим id к дверям
                // map[3][1]; поменять тип объекта на карте
                map[12][1].setDoorId("5");
                // Устанавливаем фразы для персонажей
                map[3][3].setText("Эй! Как ты сюда пробрался??");
                map[5][2].setText("Не хочешь сыграть со мной в Зонк?");
        }
        renderAvatar();
    }

}
