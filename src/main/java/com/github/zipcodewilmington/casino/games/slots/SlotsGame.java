package com.github.zipcodewilmington.casino.games.slots;

import com.github.zipcodewilmington.casino.BettingGame;
import com.github.zipcodewilmington.casino.Game;
import com.github.zipcodewilmington.casino.Player;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 * Created by leon on 7/21/2020.
 */
public class SlotsGame implements Game, BettingGame {

    SlotsPlayer player;
    int pool;

    @Override
    public void add(Player player) {
        this.player = (SlotsPlayer) player;
    }

    @Override
    public void remove(Player player) {
        if(this.player == player){
            this.player = null;
        }
    }

    @Override
    public ArrayList<Player> getPlayers() {
        ArrayList<Player> player = new ArrayList<>();
        player.add(this.player);
        return player;
    }

    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        String command;
        int val;
        while(true){
            System.out.println("Welcome: would you like to spin the slots?\n1: spin\n2: leave");
            command = in.next().trim();
            if(command.equals("1")){
                try{
                    val = in.nextInt();
                }catch(NumberFormatException e){
                    System.out.println("Please enter a number");
                    continue;
                }
                bet(this.player, val, 1);
                //this.player.getAccount().setBalance()+= resolve(pull()); //I am aware this is wrong, but its colseish
            } else if(command.equals("2")){
                remove(this.player);
                break;
            }
        }
    }

    @Override
    public boolean bet(Player player, int amount, int minimum) {
        if(amount < minimum){
            return false;
        }else if(amount>player.getAccount().getBalance()){
            return false;
        }else{
            player.getAccount().setBalance(player.getAccount().getBalance()-amount);
            this.pool = amount;
            return true;
        }
    }

    public int resolve(int[] slots) {
        if(slots[0] == slots[1] && slots[1] == slots[2]){ //all numbers the same
            return slots[0];
        } else if(slots[2]%slots[0] == 0 && slots[1]%slots[0] == 0){ //2,4,6 or 3,6,9
            return slots[2] * 2;
        }else if(slots[0]%slots[2] == 0 && slots[1]%slots[2] == 0){ //reverse of above
            return slots[0] * 2;
        }else if(slots[0]+1 == slots[1] && slots[1]+1 == slots[2]){ //counting sequence
            return slots[0]+1;
        }
        return 0;
    }

    public int[] pull() {
        Supplier<Integer> slot = () ->{
            return (int) (Math.random() * (10-1) + 1);
        };
        int[] slots = new int[3];
        try {
            for (int i = 35; i <= 100; i++) {
                Thread.sleep(80);
                slots[0] = (i<66) ? slot.get() : slots[0];
                slots[1] = (i<80) ? slot.get() : slots[1];
                slots[2] = slot.get();
                System.out.printf("[%s][%s][%s]\r", slots[0], slots[1], slots[2]);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return slots;
    }
}


