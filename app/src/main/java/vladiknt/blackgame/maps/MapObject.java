package vladiknt.blackgame.maps;

public class MapObject {

    private String type;
    private String imgSrc;

    public MapObject(String type) {
        this.type = type;
        imgSrc = "drawable/" + type;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public String getType() {
        return type;
    }

}
