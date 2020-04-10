/**
 * SYST 17796 Project Winter 2020 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package javafxapplication18;

import java.util.Arrays;
import java.util.List;
import javafx.scene.image.Image;

/**
 * A class to be used as the base Card class for the project. Must be general
 * enough to be instantiated for any Card game. Students wishing to add to the
 * code should remember to add themselves as a modifier.
 *
 * @author dancye
 * @modified Gursimar Singh Hehar-April 2020
 */
public class Card {

//Variables for card suit, rank and image
    private int rank;
    private String suit;
    private Image imgCard;

    public Card(int rank, String suit) {
        //Setting rank and suit in constructor will automatically assign
        //the values to our card image
        setRank(rank);
        setSuit(suit);
        
        //Location of the card image
        //Here we import card images using our valid rank and suit names
        imgCard = new Image("javafxapplication18/Cards/" +rank+"_" +"of"+"_"+suit +".png");
    }

    /**
     * This method returns the list of ranks which are valid for a card object
     * We will be using 11,12,13,14 instead of jack,queen,king and ace
     * This is because it will make us easier for us to compare the cards
     * also changed the card images names to respective rank names used here.
     *
     */
    public static List<Integer> getValidRank() {
        return Arrays.asList(2,3,4,5,6,7,8,9,10,11,12,13,14);
    }

    /**
     * This method returns the list of suits that are vaild for a card object.
     *
     */
    public static List<String> getValidSuit() {
        return Arrays.asList("spades", "hearts", "diamonds", "clubs");
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        //Only if rank name is valid we set it to card object
        List<Integer> ranks = getValidRank();
        if (ranks.contains(rank)) {
            this.rank = rank;
        }
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        //Only if suit name is valid we set it to card object
        List<String> suits = getValidSuit();
        if (suits.contains(suit)) {
            this.suit = suit;
        }
    }

    public Image getImgCard() {
        return imgCard;
    }

    public void setImgCard(Image imgCard) {
        this.imgCard = imgCard;
    }

}
