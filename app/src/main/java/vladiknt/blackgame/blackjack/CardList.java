package vladiknt.blackgame.blackjack;

import vladiknt.blackgame.blackjack.Node;

public class CardList {

    private Node first; // Первый элемент списка
    private Node last; // Последний элемент списка
    private int length = 0;

    public void addCard(Node card) {
        if(first == null) {
            first = card;
            last = card;
            length++;
        } else {
            last.setNext(card);
            last = card;
            length++;
        }
    }

    public Node returnCard(int i) {
        int k = 1;
        Node current = first;
        while(k != i) {
            current = current.getNext();
            k++;
        }
        return current;
    }

    public Node getBefore(Node card) {
        Node current = first.getNext();
        Node preCurrent = first;
        if(card == first)
            return null;
        else {
            while(current != card) {
                preCurrent = current;
                current = current.getNext();
            }
            return preCurrent;
        }
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setFirst(Node first) {
        this.first = first;
    }

}
