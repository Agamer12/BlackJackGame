package com.ari.game;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import javafx.scene.control.TextField;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Random;

import java.util.Random;

public class Controller {
    Locale usa = new Locale("en", "US");
    // Create a Currency instance for the Locale
    Currency dollars = Currency.getInstance(usa);
    // Create a formatter given the Locale
    NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(usa);

    Random rand = new Random();
    private double money = 0;
    private double bet = 0;

    @FXML
    ImageView c1, c2, c3, c4, c5, p1, p2, p3, p4, p5;
    @FXML
    Text cash, playerText, dealerText, betText, roundText;
    @FXML
    TextField betAmount;

    Object[][] deck = new Object[54][2];

    Object[][] playerHand = new Object[5][2];
    Object[][] dealerHand = new Object[5][2];

    int playerHandCount;
    int dealerHandCount;

    int round;

    public void initialize() {
        int count = 0;

        for (int i = 2; i <= 10; i++) {
            deck[count][0] = i + "_of_clubs.jpg";
            deck[count][1] = i;
            count++;
        }
        deck[count][0] = "jack_of_clubs.jpg";
        deck[count][1] = 10;
        count++;
        deck[count][0] = "queen_of_clubs.jpg";
        deck[count][1] = 10;
        count++;
        deck[count][0] = "king_of_clubs.jpg";
        deck[count][1] = 10;
        count++;
        deck[count][0] = "ace_of_clubs.jpg";
        deck[count][1] = 11;
        count++;

        for (int i = 2; i <= 10; i++) {
            deck[count][0] = i + "_of_spades.jpg";
            deck[count][1] = i;
            count++;
        }
        deck[count][0] = "jack_of_spades.jpg";
        deck[count][1] = 10;
        count++;
        deck[count][0] = "queen_of_spades.jpg";
        deck[count][1] = 10;
        count++;
        deck[count][0] = "king_of_spades.jpg";
        deck[count][1] = 10;
        count++;
        deck[count][0] = "ace_of_spades.jpg";
        deck[count][1] = 11;
        count++;

        for (int i = 2; i <= 10; i++) {
            deck[count][0] = i + "_of_diamonds.jpg";
            deck[count][1] = i;
            count++;
        }
        deck[count][0] = "jack_of_diamonds.jpg";
        deck[count][1] = 10;
        count++;
        deck[count][0] = "queen_of_diamonds.jpg";
        deck[count][1] = 10;
        count++;
        deck[count][0] = "king_of_diamonds.jpg";
        deck[count][1] = 10;
        count++;
        deck[count][0] = "ace_of_diamonds.jpg";
        deck[count][1] = 11;
        count++;

        for (int i = 2; i <= 10; i++) {
            deck[count][0] = i + "_of_hearts.jpg";
            deck[count][1] = i;
            count++;
        }
        deck[count][0] = "jack_of_hearts.jpg";
        deck[count][1] = 10;
        count++;
        deck[count][0] = "queen_of_hearts.jpg";
        deck[count][1] = 10;
        count++;
        deck[count][0] = "king_of_hearts.jpg";
        deck[count][1] = 10;
        count++;
        deck[count][0] = "ace_of_hearts.jpg";
        deck[count][1] = 11;
        count++;
        deck[count][0] = "red_joker.jpg";
        deck[count][1] = 0;
        count++;
        deck[count][0] = "black_joker.jpg";
        deck[count][1] = 0;
        count++;

        reset();

        money = 0;
        setCash(10000);


    }
    private void reset(){
        playerText.setFill(javafx.scene.paint.Color.BLACK);
        dealerText.setFill(javafx.scene.paint.Color.BLACK);

        displayCard(deck[53], c1);
        displayCard(deck[53], c2);
        displayCard(deck[53], c3);
        displayCard(deck[53], c4);
        displayCard(deck[53], c5);

        displayCard(deck[52], p1);
        displayCard(deck[52], p2);
        displayCard(deck[52], p3);
        displayCard(deck[52], p4);
        displayCard(deck[52], p5);

        betText.setText("Bet: $0.0");
        roundText.setText("Round: 1");

        playerText.setText("Player: 0");
        dealerText.setText("Dealer: 0");

        betAmount.setText("");

        bet = 0;
        round = 1;

        playerHand = new Object[5][2];
        dealerHand = new Object[5][2];

        playerHandCount = 0;
        dealerHandCount = 0;

    }
    public void placeBet() {

        if(round == 1) {

            try {
                bet = Integer.parseInt(betAmount.getText());
            } catch (Exception e) {
                bet = 0;
            }
            if (bet > money) {
                bet = money;
            }
            setCash(-bet);

            setPlayerHand(drawCard(), 0);
            setPlayerHand(drawCard(), 1);
            setDealerHand(drawCard(), 0);
            round++;
        } else if (round == 11) {
            hit();
        }else{
            betAmount.setText("");
        }
        betText.setText("Bet: " + dollarFormat.format(bet));
        betAmount.setText("");

        roundText.setText("Round: " + round);
    }

    private void displayCard(Object[] card, ImageView view) {
        Image image = new Image("file:/Users/arikoro/Documents/Code Projeects/Java/Game/src/main/resources/com/ari/game/cards/" + card[0]);
        view.setImage(image);
    }

    private Object[] drawCard() {
        int card = rand.nextInt(52);
        return deck[card];
    }

    private void setCash(double amount) {
        money += amount;
        cash.setText("Cash: " + dollarFormat.format(money));
    }

    private void setPlayerHand(Object[] card, int pos) {
        playerHand[pos] = card;
        playerHandCount += Integer.parseInt(card[1].toString());

        playerText.setText("Player: " + playerHandCount);

        switch (pos) {
            case 0 -> displayCard(card, p1);
            case 1 -> displayCard(card, p2);
            case 2 -> displayCard(card, p3);
            case 3 -> displayCard(card, p4);
            case 4 -> displayCard(card, p5);
        }
    }
    private void setDealerHand(Object[] card, int pos) {
        dealerHand[pos] = card;
        dealerHandCount += Integer.parseInt(card[1].toString());

        dealerText.setText("Dealer: " + dealerHandCount);

        switch (pos) {
            case 0 -> displayCard(card, c1);
            case 1 -> displayCard(card, c2);
            case 2 -> displayCard(card, c3);
            case 3 -> displayCard(card, c4);
            case 4 -> displayCard(card, c5);
        }
    }

    public void hit() {
        System.out.println(round);
        if (round <= 5 && round != 1) {
            if(playerHandCount > 21) {
                bet = 0;
                setCash(bet);
                round = 5;
                playerHandCount = 0;
                playerText.setFill(javafx.scene.paint.Color.RED);
                betText.setText("Bet: " + dollarFormat.format(bet));
            } else {
                setPlayerHand(drawCard(), round);
                if(playerHandCount > 21) {
                    hit();
                }
            }
            round++;
        } else if (round <= 10) {
            if(dealerHandCount > 21){
                setCash(bet * 2);
                bet = 0;
                round = 11;
                dealerHandCount = 0;
                dealerText.setFill(javafx.scene.paint.Color.RED);

            } else {
                if (dealerHandCount < 17) {
                    setDealerHand(drawCard(), round - 5);
                    round++;
                    hit();
                } else {
                    round = 11;
                }
            }
        } else{
            if(playerHandCount > dealerHandCount) {
                setCash(bet * 2);
            } else if (playerHandCount == dealerHandCount) {
                setCash(bet);
            }
            bet = 0;
            betText.setText("Bet: " + dollarFormat.format(bet));

            reset();
        }
        roundText.setText("Round: " + round);
    }

    public void stand() {
        if (round < 6){
            round = 6;
            hit();
        } else {
            hit();
        }
    }
}
