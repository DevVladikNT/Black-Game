package vladiknt.blackgame.blackjack;

public class Deck {

    private CardList deck = new CardList();

    public Deck() {
        String[] names = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10", "j", "q", "k", "a", };
        String[] suits = new String[]{"hearts", "diamonds", "clubs", "spades"};
        for(int i = 0; i < names.length; i++) {
            for(int j = 0; j < suits.length; j++) {
                deck.addCard(new Node(names[i], suits[j]));
                deck.addCard(new Node(names[i], suits[j]));
                deck.addCard(new Node(names[i], suits[j]));
                deck.addCard(new Node(names[i], suits[j]));
            }
        }
    }

    public Node getCard() { // Достаёт случайную карту из колоды
        if(deck.getLength() != 0) {
            Node card = deck.returnCard(((int)(Math.random()*1000)%deck.getLength())+1);
            if(deck.getBefore(card) != null)
                deck.getBefore(card).setNext(card.getNext());
            else
                deck.setFirst(card.getNext());
            deck.setLength(deck.getLength() - 1);
            return card;
        }
        return null;
    }

}
