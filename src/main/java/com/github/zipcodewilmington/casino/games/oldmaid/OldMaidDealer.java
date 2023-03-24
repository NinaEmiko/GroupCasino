package com.github.zipcodewilmington.casino.games.oldmaid;

import com.github.zipcodewilmington.casino.Card;
import com.github.zipcodewilmington.casino.Deck;
import com.github.zipcodewilmington.casino.Number;
import com.github.zipcodewilmington.casino.Player;
import com.github.zipcodewilmington.casino.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.github.zipcodewilmington.casino.Number.*;

public class OldMaidDealer {
    static ArrayList<Card> newDeck = new ArrayList<>();

    public ArrayList<Card> makeDeck(){
        ArrayList<Card> Deck = new ArrayList<>();
        Deck.add(new Card(Suit.HEARTS, ACE));
        Deck.add(new Card(Suit.HEARTS, TWO));
        Deck.add(new Card(Suit.HEARTS, THREE));
        Deck.add(new Card(Suit.HEARTS, FOUR));
        Deck.add(new Card(Suit.HEARTS, FIVE));
        Deck.add(new Card(Suit.HEARTS, SIX));
        Deck.add(new Card(Suit.HEARTS, SEVEN));
        Deck.add(new Card(Suit.HEARTS, EIGHT));
        Deck.add(new Card(Suit.HEARTS, NINE));
        Deck.add(new Card(Suit.HEARTS, TEN));
        Deck.add(new Card(Suit.SPADES, ACE));
        Deck.add(new Card(Suit.SPADES, TWO));
        Deck.add(new Card(Suit.SPADES, THREE));
        Deck.add(new Card(Suit.SPADES, FOUR));
        Deck.add(new Card(Suit.SPADES, FIVE));
        Deck.add(new Card(Suit.SPADES, SIX));
        Deck.add(new Card(Suit.SPADES, SEVEN));
        Deck.add(new Card(Suit.SPADES, EIGHT));
        Deck.add(new Card(Suit.SPADES, NINE));
        Deck.add(new Card(Suit.SPADES, TEN));
        Deck.add(new Card(Suit.DIAMONDS, ACE));
        Deck.add(new Card(Suit.DIAMONDS, TWO));
        Deck.add(new Card(Suit.DIAMONDS, THREE));
        Deck.add(new Card(Suit.DIAMONDS, FOUR));
        Deck.add(new Card(Suit.DIAMONDS, FIVE));
        Deck.add(new Card(Suit.DIAMONDS, SIX));
        Deck.add(new Card(Suit.DIAMONDS, SEVEN));
        Deck.add(new Card(Suit.DIAMONDS, EIGHT));
        Deck.add(new Card(Suit.DIAMONDS, NINE));
        Deck.add(new Card(Suit.DIAMONDS, TEN));
        Deck.add(new Card(Suit.CLUBS, ACE));
        Deck.add(new Card(Suit.CLUBS, TWO));
        Deck.add(new Card(Suit.CLUBS, THREE));
        Deck.add(new Card(Suit.CLUBS, FOUR));
        Deck.add(new Card(Suit.CLUBS, FIVE));
        Deck.add(new Card(Suit.CLUBS, SIX));
        Deck.add(new Card(Suit.CLUBS, SEVEN));
        Deck.add(new Card(Suit.CLUBS, EIGHT));
        Deck.add(new Card(Suit.CLUBS, NINE));
        Deck.add(new Card(Suit.CLUBS, TEN));
        Deck.add(new Card(Suit.SPADES, QUEEN));
        return Deck;
    }

    public static void deckShuffle(){
        OldMaidDealer oldMaidDealer = new OldMaidDealer();
        ArrayList<Card> newDeck = new ArrayList<>();
        newDeck = oldMaidDealer.makeDeck();
        Collections.shuffle(newDeck);
    }
    public static void dealCards(){
        OldMaidDealer.deckShuffle();
        List<Card> dealerHand = newDeck.subList(0, 22);
        List<Card> playerHand = newDeck.subList(22, 40);
    }

    public static ArrayList<Card> setDealerHand(){
        OldMaidDealer.dealCards();
        List<Card> dealerHand = newDeck.subList(0, 22);
        return (ArrayList<Card>) dealerHand;
    }

    public static ArrayList<Card> setPlayerHand(){
        OldMaidDealer.dealCards();
        List<Card> playerHand = newDeck.subList(22, 40);
        return (ArrayList<Card>) playerHand;
    }

    public static Card pickCard(ArrayList<Card> Hand) {
        Random random = new Random();
        int randomIndex = random.nextInt(Hand.size());
        return Hand.get(randomIndex);
    }

    public static ArrayList<Card> matchCard(ArrayList<Card> Hand) {
        ArrayList<Card> removedMatches = new ArrayList<>();
        for (int i = 0; i < Hand.size() - 1; i++) {
            Card card1 = Hand.get(i);
            com.github.zipcodewilmington.casino.Number rank1 = card1.getRank();
            Card card2 = Hand.get(i + 1);
            Number rank2 = card2.getRank();
            if (rank1 == rank2) {
                removedMatches.add(card1);
                removedMatches.add(card2);
                i++;
            }
        }
        Hand.removeAll(removedMatches);
        ArrayList<Card> remainingCards = new ArrayList<>(Hand);
        return remainingCards;
    }

}
