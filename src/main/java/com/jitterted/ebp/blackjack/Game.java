package com.jitterted.ebp.blackjack;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public class Game {

    public static final int NUM_OF_INITIAL_CARDS_PER_PLAYER = 2;
    public static final int DEALER_STOPPING_VALUE = 16;
    private final Deck deck;

    private final Hand dealerHand = new Hand();
    private final Hand playerHand = new Hand();

    public static void main(String[] args) {
        Game game = new Game();

        AnsiConsole.systemInstall();
        System.out.println(ansi()
                                   .bgBright(Ansi.Color.WHITE)
                                   .eraseScreen()
                                   .cursor(1, 1)
                                   .fgGreen().a("Welcome to")
                                   .fgRed().a(" JitterTed's")
                                   .fgBlack().a(" BlackJack game"));
        System.out.println(ansi()
                                   .cursor(3, 1)
                                   .fgBrightBlack().a("Hit [ENTER] to start..."));

        System.console().readLine();


        game.initialDeal();
        game.play();

        System.out.println(ansi().reset());
    }

    public Game() {
        deck = new Deck();
    }

    public void initialDeal() {
        for (int i = 0; i < NUM_OF_INITIAL_CARDS_PER_PLAYER; i++) {
            playerHand.dealOneRound(deck);
            dealerHand.dealOneRound(deck);
        }
    }

    public void play() {
        boolean playerBusted = playerPhase();

        // Dealer makes its choice automatically based on a simple heuristic (<=16, hit, 17>=stand)
        if (!playerBusted) {
            dealerPhase();
        }

        displayFinalGameState();
        displayGameResults(playerBusted);
    }

    public boolean playerPhase() {
        // get Player's decision: hit until they stand, then they're done (or they go bust)
        boolean playerBusted = false;
        while (!playerBusted) {
            displayGameState();
            String playerChoice = inputFromPlayer().toLowerCase();
            if (playerChoice.startsWith("s")) {
                break;
            }
            if (playerChoice.startsWith("h")) {
                playerHand.dealOneRound(deck);
                playerBusted = playerHand.isBust();
            } else {
                System.out.println("You need to [H]it or [S]tand");
            }
        }

        return playerBusted;
    }

    private void dealerPhase() {
        while (dealerHand.shouldDealerHit(this)) {
            dealerHand.dealOneRound(deck);
        }
    }

    private String inputFromPlayer() {
        System.out.println("[H]it or [S]tand?");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void displayGameResults(boolean playerBusted) {
        if (playerBusted) {
            System.out.println("You Busted, so you lose.  💸");
        } else if (dealerHand.isBust()) {
            System.out.println("Dealer went BUST, Player wins! Yay for you!! 💵");
        } else if (playerHand.compareTo(playerHand) == 1) {
            System.out.println("You beat the Dealer! 💵");
        } else if (dealerHand.compareTo(playerHand) == 0) {
            System.out.println("Push: You tie with the Dealer. 💸");
        } else {
            System.out.println("You lost to the Dealer. 💸");
        }
    }

    private void displayGameState() {
        System.out.print(ansi().eraseScreen().cursor(1, 1));
        System.out.println("Dealer has: ");
        dealerHand.printFirstCard();

        // second card is the hole card, which is hidden
        displayBackOfCard();

        System.out.println();
        playerHand.displayHandFormatted("Player");
    }

    private void displayBackOfCard() {
        System.out.print(
                ansi()
                        .cursorUp(7)
                        .cursorRight(12)
                        .a("┌─────────┐").cursorDown(1).cursorLeft(11)
                        .a("│░░░░░░░░░│").cursorDown(1).cursorLeft(11)
                        .a("│░ J I T ░│").cursorDown(1).cursorLeft(11)
                        .a("│░ T E R ░│").cursorDown(1).cursorLeft(11)
                        .a("│░ T E D ░│").cursorDown(1).cursorLeft(11)
                        .a("│░░░░░░░░░│").cursorDown(1).cursorLeft(11)
                        .a("└─────────┘"));
    }

    private void displayFinalGameState() {
        System.out.print(ansi().eraseScreen().cursor(1, 1));
        dealerHand.displayHandFormatted("Dealer");
        System.out.println();
        playerHand.displayHandFormatted("Player");
    }
}
