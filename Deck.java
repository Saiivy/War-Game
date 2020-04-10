package javafxapplication18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author gursimar singh hehar This class represents a deck of card
 */
public class Deck {

    //Arraylist to store our card objects and create a deck
    private ArrayList<Card> deck;

    public Deck() {
        //Directly calling methods from Card class as they are static
        List<String> suits = Card.getValidSuit();
        List<Integer> ranks = Card.getValidRank();

        //New Arraylist
        deck = new ArrayList<>();
        //Now we loop through both of the lists to create a whole deck
        for (String suit : suits) {
            for (int rank : ranks) {
                deck.add(new Card(rank, suit));

            }
        }

    }
    

    /**
     * This method will shuffle the deck of card objects
     */
    public void shuffle(){
        Collections.shuffle(deck);
    }

    /**
     * Using this method we will get top-most card from each deck
     */
    public Card getTopCard() {
            return deck.remove(0);
    }
    

}
