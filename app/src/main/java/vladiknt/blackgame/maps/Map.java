package vladiknt.blackgame.maps;

import android.content.Context;
import android.widget.ImageView;

public class Map {

    private MapObject[][] map;
    private int[] position;
    private MapObject buff = new MapObject("floor");

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

    public Map(int id) {
        switch (id) {
            case 1:
                position = new int[]{7, 2};
                map = new MapObject[][]{
                        {new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"), },
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"), },
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"), },
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("table_bj"), new MapObject("girls1"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"), },
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("dark_wall"), },
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall"), },
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("slots"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall"), },
                        {new MapObject("dark_wall"), new MapObject("door"), new MapObject("me"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall"), },
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("slots"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall"), },
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall"), },
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("dark_wall"), },
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("table_r"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"), },
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("floor"), new MapObject("floor"),
                                new MapObject("floor"), new MapObject("wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"), },
                        {new MapObject("dark_wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("wall"),
                                new MapObject("wall"), new MapObject("wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"), },
                        {new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"),
                                new MapObject("dark_wall"), new MapObject("dark_wall"), new MapObject("dark_wall"), }
                };
                break;
            case 2:
                // добавится в будущих обновлениях
                break;
        }
    }

}
