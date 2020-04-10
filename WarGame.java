package javafxapplication18;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author gursimar singh hehar
 */
public class WarGame extends Application {

    //Global variables to use across different method in this class
    //make them private so other classes wont have access to them
    private ImageView viewPlayer1Deck;
    private ImageView viewPlayer2Deck;
    private Button btnHit;
    private Label lblPlayerOneScore;
    private Label lblPlayerTwoScore;
    private ArrayList<Card> deck1;
    private ArrayList<Card> deck2;
    private Deck deck;
    private int Player1score = 0;
    private int Player2score = 0;
    private Card player1Card;
    private Card player2Card;
    private Media media;
    private MediaPlayer player;
    private Text txtWar;
    private ImageView viewWarCard1;
    private ImageView viewWarCard2;
    ArrayList<Card> pile;
    ArrayList<Card> pile2;

    @Override
    public void start(Stage stage) {
        play();
        //Now we add imageview to hbox with  padding
        HBox imageBox = new HBox(400);
        imageBox.setAlignment(Pos.CENTER);
        imageBox.setPadding(new Insets(40, 40, 40, 40));
        imageBox.getChildren().addAll(viewPlayer1Deck, viewPlayer2Deck);

        //Now we add imageview to hbox with  padding
        HBox scoreBox = new HBox(800);
        scoreBox.setAlignment(Pos.CENTER);
        scoreBox.setPadding(new Insets(5, 5, 5, 5));
        scoreBox.getChildren().addAll(lblPlayerOneScore, lblPlayerTwoScore);

        //Adding the button to vbox
        VBox btnBox = new VBox();
        btnBox.getChildren().add(btnHit);
        btnBox.setAlignment(Pos.CENTER);

        //Creating a root vbox which will have all of our nodes
        VBox root = new VBox(15);
        root.getChildren().addAll(imageBox, scoreBox, btnBox);

        //Creating scene and adding stylesheets
        Scene scene = new Scene(root, 1336, 768);
        scene.getStylesheets().add("javafxapplication18/styles.css");

        //Creating stage with title
        stage.setTitle("War");

        //loading app icon
        stage.getIcons().add(new Image("javafxapplication18/Cards/logo.jpeg"));

        //settting scene on stage
        stage.setScene(scene);

        //Maximizing the stage to fit on every screen
        stage.setMaximized(true);

        //Show the stage
        stage.show();
    }

    private void play() {
        //Create two ImageView to set our decks on
        //one for player one and other for player two...
        //Initially we set back of card image..
        viewPlayer1Deck = new ImageView(new Image("javafxapplication18/Cards/back.png"));
        viewPlayer2Deck = new ImageView(new Image("javafxapplication18/Cards/back.png"));

        //Create a new deck of cards..
        deck = new Deck();
        //Shuffling the deck..
        deck.shuffle();

        //Here we will split the main deck into two decks of equal sizes
        //Because we want both players to have same amount of cards..
        deck1 = new ArrayList();
        deck2 = new ArrayList();
        for (int i = 1; i <= 52; i++) {
            if (i % 2 == 0) {
                deck1.add(deck.getTopCard());
            } else {
                deck2.add(deck.getTopCard());
            }
        }

        //Score labels
        lblPlayerOneScore = new Label("Score: " + Player1score);
        lblPlayerOneScore.setId("label");
        lblPlayerTwoScore = new Label("Score: " + Player2score);
        lblPlayerTwoScore.setId("label1");

        //A button when pressed puts the cards on screen
        btnHit = new Button("HIT");
        btnHit.setId("button");
        btnHit.setOnAction((event) -> {
            btnHit.setDefaultButton(true);

            //Now lets begin the logic of the game
            //As soon as player presses the button
            //Two cards are thrown
            player1Card = deck1.remove(0);
            player2Card = deck2.remove(0);
            //Setting the images of those cards..
            viewPlayer1Deck.setImage(player1Card.getImgCard());
            viewPlayer2Deck.setImage(player2Card.getImgCard());

            //Setting score
            lblPlayerOneScore.setText("Score: " + Player1score +"\n" + "Cards Left: " +deck1.size());

            //Setting score
            lblPlayerTwoScore.setText("Score: " + Player2score +"\n" + "Cards Left: "+deck2.size());

            //Now, if the rank of playerone's card is greater than the other
            if (player1Card.getRank() > player2Card.getRank()) {
                //The score of playerone increases
                Player1score++;
                //And finally we give the card to player1 and take from player2
                deck1.add(player2Card);
                deck1.add(player1Card);

            } //Now, if the rank of player Two's card is greater than the other player
            else if (player2Card.getRank() > player1Card.getRank()) {
                //The score of playertwo increases
                Player2score++;
                //And finally we give the card to player2
                deck2.add(player1Card);
                deck2.add(player2Card);
                
            } //if both card have same rank, both player go into war..         
            else {
               war();
            }
            displayWinner(deck1, deck2);

        });
    }
/**
 * This method makes the game logic when both players throws the card with same rank
 */
   public void war(){
       displayScore("Both Players Had the Same Card" +"\n" +"War Is Happening!");
       //Each player draws a card from deck..
       Card warCard = deck1.get(0);
       Card warCard2 = deck2.get(0);
       //Each player throws a second card as well,which determines who gets 
       // All cards.
       Card warCard3 = deck1.get(0);
       Card warCard4 = deck2.get(0);
       if(warCard4.getRank()>warCard3.getRank()){
       deck2.add(warCard3);
       deck2.add(warCard4);
       deck2.add(warCard2);
       deck2.add(warCard);
       Player2score++;
       }
       else if(warCard4.getRank()<warCard3.getRank()){
       deck1.add(warCard3);
       deck1.add(warCard4);
       deck1.add(warCard2);
       deck1.add(warCard);
       Player1score++;
               
       
       }
       else{
       war();
       }

  }
    /**
     * Method that specifies the winning player
     * @param deck1
     * @param deck2
     */
    public void displayWinner(ArrayList<Card> deck1, ArrayList<Card> deck2) {

        if (isEmpty(deck1, deck2) == true) {
            //We set the button to disable so players know game is over..
            btnHit.setDisable(true);
            //Now we display the scores of players..
            if(Player1score>Player2score){
                int score = Player1score-Player2score-1;
            displayScore("Player 1 Won By: " +score + " Scores"  );
            
            }
           
            else if(Player2score>Player1score){
                int score = Player2score-Player1score-1;
            displayScore("Player 2 Won By: " +score +" Scores" );
           
            }
        }

    }
      /**
     * @param deck1
     * @param deck2
     * @return true
     * @return false
     * This method returns true if deck1 size=1 otherwise returns false
     */
    private boolean isEmpty(ArrayList<Card> deck1, ArrayList<Card> deck2) {
      //We set it to 1 instead of zero. Coz,we need atlest one card to throw to play the game
        if (deck1.size() == 1 || deck2.size() == 1) {
            return true;
        }
        return false;

    }
    /**
     * This method display an alert box which show the winning player
     */
   public void displayScore(String winner) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(winner);
        alert.showAndWait();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    

}
