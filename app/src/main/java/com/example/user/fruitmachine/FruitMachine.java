package com.example.user.fruitmachine;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by user on 03/11/2017.
 */

public class FruitMachine {
    private String name;
    private int machineFunds;
    private int playerFunds;
    private ArrayList<Symbol> symbols;
    private ArrayList<Symbol> results;
    private Ui ui;

    public FruitMachine(String name, int machineFunds, int playerFunds) {
        this.name = name;
        this.machineFunds = machineFunds;
        this.playerFunds = playerFunds;
        this.symbols = new ArrayList<>();
        this.results = new ArrayList<>();
        this.generateSymbols();
    }

    public void generateSymbols(){
        for (Symbol symbol : Symbol.values()){
            symbols.add(symbol);
        }
    }

    public ArrayList<Symbol> getResults() {
        return results;
    }

    public int getFunds() {
        return machineFunds;
    }

    public ArrayList<Symbol> getSymbols() {
        return symbols;
    }

    public void setMachineFunds(int newFunds) {
        this.machineFunds = newFunds;
    }

    public void setPlayerFunds(int playerFunds) {
        this.playerFunds = playerFunds;
    }

    public Symbol generateSingleRandomFruit() {
        //implement try catch to find out error if fruit machine empty
        Random rand = new Random();

        //returns a random enum from fruit machine symbols arraylist
        Symbol randomSymbol = symbols.get(rand.nextInt(symbols.size()));
        return randomSymbol;
    }

    public void spin(){
        Symbol firstSymbol = generateSingleRandomFruit();
        Symbol secondSymbol = generateSingleRandomFruit();
        Symbol thirdSymbol = generateSingleRandomFruit();

        this.results.add(firstSymbol);
        this.results.add(secondSymbol);
        this.results.add(thirdSymbol);
    }

    public int compareSymbolsReturnWinnings(){
        int total = 0;

        //compares getValue of results array - if they == then prize
        if (this.results.get(0).getValue() ==  this.results.get(1).getValue() && this.results.get(0).getValue() ==                      this.results.get(2).getValue()){
            total = this.results.get(0).getValue() * this.results.size();
        }
        return total;
    }

    public void payout(Integer winnings){
        this.setMachineFunds(this.machineFunds - winnings);
        this.setPlayerFunds(this.playerFunds + winnings);
    }

    public String describeSymbols(){
        String resultsText = "";

        for (Symbol symbol: this.results){
            resultsText += symbol.name() + ", ";
        }

        return resultsText;
    }

    public void play() {
        String response = ui.askUserIfTheyWantToPlay().toUpperCase();
        if (response.equals("Y") && playerFunds > 0 && machineFunds > 0){
            this.spin();
            int winnings = this.compareSymbolsReturnWinnings();
            this.payout(winnings);
            setPlayerFunds(playerFunds - 1);
            ui.showUserSymbols(describeSymbols());
        }
    }
}


