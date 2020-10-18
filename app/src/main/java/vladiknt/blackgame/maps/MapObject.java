package vladiknt.blackgame.maps;

public class MapObject {

    private String type; // Тип элемента (основные: door, floor, wall, dark_wall)
    private String imgSrc; // Путь к картинке для отрисовки данного элемента в интерфейсе
    private String doorId; // Для дверей

    public MapObject(String type) {
        this.type = type;
        imgSrc = "drawable/" + type;
    }
    public MapObject(String type, String imgSrc) {
        // Конструктор для хентай-комнаты
        this.type = type;
        this.imgSrc = "drawable/" + imgSrc;
    }

    public String getImgSrc() {
        return imgSrc;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void setDoorId(String doorId) {
        this.doorId = doorId;
    }
    public String getDoorId() {
        return doorId;
    }

}
