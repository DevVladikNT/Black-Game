package vladiknt.blackgame.maps;

import android.content.Context;
import android.widget.ImageView;

public class Map {

    private MapObject[][] map; // Карта
    private int[] position; // Позиция нашего персонажа
    private MapObject buff = new MapObject("floor"); // Буффер элемента под персонажем, чтобы он не "перемешивал" поле

    // Функция отрисовки участка карты в интерфейсе
    public void render(ImageView[][] renderMap, Context context) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                renderMap[i][j].setImageResource(context.getResources().getIdentifier(map[position[0] - 2 + i][position[1] - 2 + j].getImgSrc(), null, context.getPackageName()));
            }
        }
    }

    public String moveUp() {
        switch (map[position[0] - 1][position[1]].getType()) {
            case "slots":
                return "VNT";
            case "table_r":
                return "R";
            case "table_bj":
                return "BJ";
            case "door":
                return "door" + map[position[0] - 1][position[1]].getDoorId();
        }
        if (canMove(new int[]{position[0] - 1, position[1]})) {
            MapObject buff = map[position[0] - 1][position[1]];
            map[position[0] - 1][position[1]] = map[position[0]][position[1]];
            map[position[0]][position[1]] = this.buff;
            this.buff = buff;
            position[0]--;
        }
        return "";
    }
    public String moveDown() {
        switch (map[position[0] + 1][position[1]].getType()) {
            case "slots":
                return "VNT";
            case "table_r":
                return "R";
            case "table_bj":
                return "BJ";
            case "door":
                return "door" + map[position[0] + 1][position[1]].getDoorId();
        }
        if (canMove(new int[]{position[0] + 1, position[1]})) {
            MapObject buff = map[position[0] + 1][position[1]];
            map[position[0] + 1][position[1]] = map[position[0]][position[1]];
            map[position[0]][position[1]] = this.buff;
            this.buff = buff;
            position[0]++;
        }
        return "";
    }
    public String moveRight() {
        switch (map[position[0]][position[1] + 1].getType()) {
            case "slots":
                return "VNT";
            case "table_r":
                return "R";
            case "table_bj":
                return "BJ";
            case "door":
                return "door" + map[position[0]][position[1] + 1].getDoorId();
        }
        if (canMove(new int[]{position[0], position[1] + 1})) {
            MapObject buff = map[position[0]][position[1] + 1];
            map[position[0]][position[1] + 1] = map[position[0]][position[1]];
            map[position[0]][position[1]] = this.buff;
            this.buff = buff;
            position[1]++;
        }
        return "";
    }
    public String moveLeft() {
        switch (map[position[0]][position[1] - 1].getType()) {
            case "slots":
                return "VNT";
            case "table_r":
                return "R";
            case "table_bj":
                return "BJ";
            case "door":
                return "door" + map[position[0]][position[1] - 1].getDoorId();
        }
        if (canMove(new int[]{position[0], position[1] - 1})) {
            MapObject buff = map[position[0]][position[1] - 1];
            map[position[0]][position[1] - 1] = map[position[0]][position[1]];
            map[position[0]][position[1]] = this.buff;
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
                                new MapObject("floor"), new MapObject("table_bj"), new MapObject("girls1"),
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
                                new MapObject("floor"), new MapObject("floor"), new MapObject("slots"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("door"), new MapObject("me"), // 7
                                new MapObject("floor"), new MapObject("wall"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 8
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("slots"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall")},
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"), // 9
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
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
                map[7][1].setDoorId("3");
                break;
            case 2:
                // Добавится в будущем
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
                        {new MapObject("dark_wall"), new MapObject("door"), new MapObject("me"), // 3
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
                int[][] hidden = new int[][]{{6, 16}, {10, 5}, {14, 12}, {16, 6}, {16, 19}}; // Позиции для скрытой двери
                int[] hiddenPosition = hidden[((int)(Math.random() * 100000)) % 5];
                map[hiddenPosition[0]][hiddenPosition[1]] = new MapObject("door"); // Установили дверь в рандомном месте
                // Биндим id к дверям
                map[3][1].setDoorId("1");
                map[hiddenPosition[0]][hiddenPosition[1]].setDoorId("4");
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
                                new MapObject("floor", "h_floor"), new MapObject("floor", "h_floor"), new MapObject("h_wall"),
                                new MapObject("h_wall"), new MapObject("floor", "h_floor"), new MapObject("floor", "h_floor"),
                                new MapObject("h_wall"), new MapObject("h_dark_wall")},
                        {new MapObject("h_dark_wall"), new MapObject("h_wall"), new MapObject("h_girl1"), // 4
                                new MapObject("h_wall"), new MapObject("floor", "h_floor"), new MapObject("floor", "h_floor"),
                                new MapObject("h_wall"), new MapObject("floor", "h_floor"), new MapObject("floor", "h_floor"),
                                new MapObject("h_wall"), new MapObject("h_dark_wall")},
                        {new MapObject("h_dark_wall"), new MapObject("h_wall"), new MapObject("floor", "h_floor"), // 5
                                new MapObject("floor", "h_floor"), new MapObject("floor", "h_floor"), new MapObject("floor", "h_floor"),
                                new MapObject("h_wall"), new MapObject("floor", "h_floor"), new MapObject("floor", "h_floor"),
                                new MapObject("h_wall"), new MapObject("h_dark_wall")},
                        {new MapObject("h_dark_wall"), new MapObject("h_wall"), new MapObject("h_wall"), // 6
                                new MapObject("h_wall"), new MapObject("h_wall"), new MapObject("h_wall"),
                                new MapObject("h_wall"), new MapObject("floor", "h_floor"), new MapObject("h_wall"),
                                new MapObject("h_wall"), new MapObject("h_dark_wall")},
                        {new MapObject("h_dark_wall"), new MapObject("h_wall"), new MapObject("floor", "h"), // 7
                                new MapObject("floor", "e"), new MapObject("floor", "n"), new MapObject("floor", "t"),
                                new MapObject("floor", "a"), new MapObject("floor", "i"), new MapObject("floor", "tilda"),
                                new MapObject("h_wall"), new MapObject("h_dark_wall")},
                        {new MapObject("h_dark_wall"), new MapObject("h_wall"), new MapObject("floor", "h_floor"), // 8
                                new MapObject("floor", "h_floor"), new MapObject("floor", "h_floor"), new MapObject("h_me"),
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
                // Загружаем в буффер хентайную плиточку, на которой стоит персонаж, чтобы то место не было синим
                buff = new MapObject("floor", "h_floor");
                // Биндим id к дверям
                map[9][5].setDoorId("1");
                break;
        }
    }

}
