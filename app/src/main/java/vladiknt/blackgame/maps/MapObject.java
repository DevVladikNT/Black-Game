package vladiknt.blackgame.maps;

public class MapObject {

    private String type; // Тип элемента (основные: door, floor, wall, dark_wall)
    private String imgSrc; // Путь к картинке для отрисовки данного элемента в интерфейсе
    private String doorId; // Для дверей

    // Конструктор по умолчанию, устанавливающий сразу и тип, и путь до картинки
    public MapObject(String type) {
        this.type = type;
        imgSrc = "drawable/" + type;
    }
    // Конструктор для хентай-комнаты
    public MapObject(String type, String imgSrc) {
        this.type = type;
        this.imgSrc = "drawable/" + imgSrc;
    }

    public String getImgSrc() {
        return imgSrc;
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
