package com.danielburgnerjr.jokelibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Joke {
    private List<String> jokeList;

    public Joke() {
        this.jokeList = new ArrayList<>();
        this.jokeList.add("When you notice George Washington squint at the sunlight when you pull a dollar out of your wallet.");
        this.jokeList.add("When, you're shopping with the kids, the three-year-old asks for something and the seven-year-old tells him, \"It's not in the budget.\"");
        this.jokeList.add("When you're reading this in your father-in-law’s driveway because you have to glom off of his wi-fi.");
        this.jokeList.add("When you use a coupon for a free hamburger from Mc Donald's and then come home and put your own piece of cheese on it!");
        this.jokeList.add("Software engineer (n.)\nA machine that turns pizza into code");
        this.jokeList.add("Lottery (n.)\nA tax on people who are bad at math");
    }

    public String getJoke() {
        Random random = new Random();
        return jokeList.get(random.nextInt(jokeList.size() - 1));
    }

}