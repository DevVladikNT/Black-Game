package vladiknt.blackgame;

public class Node {

    private String cardName; // Название
    private String cardSuit; // Масть
    private Node next; // Следующий элемент

    public Node(String cardName, String cardSuit) {
        this.cardName = cardName;
        this.cardSuit = cardSuit;
        next = null;
    }

    public String getCardName() {
        return cardName;
    }

    public String getCardSuit() {
        return cardSuit;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

}
