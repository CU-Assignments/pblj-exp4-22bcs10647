import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

class Card {
    private String symbol;
    private String value;

    public Card(String symbol, String value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return value + " of " + symbol;
    }
}

public class CardCollection {
    public static void main(String[] args) {
        Collection<Card> cards = new ArrayList<>();
        initializeDeck(cards);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the symbol you want to search for (Hearts, Diamonds, Clubs, Spades):");
        String symbol = scanner.nextLine();

        findCardsBySymbol(cards, symbol);
    }

    private static void initializeDeck(Collection<Card> cards) {
        String[] symbols = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        for (String symbol : symbols) {
            for (String value : values) {
                cards.add(new Card(symbol, value));
            }
        }
    }

    private static void findCardsBySymbol(Collection<Card> cards, String symbol) {
        System.out.println("Cards of symbol " + symbol + ":");
        for (Card card : cards) {
            if (card.getSymbol().equalsIgnoreCase(symbol)) {
                System.out.println(card);
            }
        }
    }
}
