package me.mineland.core.player.ranking;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ThePitRanking {

    Initial("§e", "I✯", "-", 1000),
    Level1("§e", "1✯", "I", 2000),
    Level2("§e", "2✯", "II", 3000),
    Level3("§e", "3✯", "III", 3500),
    Level4("§e", "4✯", "IV", 4000);

    private String color, symbol, name;
    private int experience;

    public static ThePitRanking getRanking(String ligaNome) {
        ThePitRanking liga = ThePitRanking.Initial;

        for (ThePitRanking rank : values()) {
            if (rank.getName().equalsIgnoreCase(ligaNome)) {
                liga = rank;
                break;
            }
        }
        return liga;
    }

    public static ThePitRanking getRanking(int xp) {
        if (xp >= Level3.getExperience()) return Level4;
        if (xp < Level1.getExperience()) return Initial;

        ThePitRanking liga = Initial;
        for (ThePitRanking rank : values()) {
            if (xp <= rank.experience && xp > rank.getMin()) {
                liga = rank;
            }
        }
        return liga;
    }

    public int getMin() {
        int min = 0;

        if (this.ordinal() > 0) min = ThePitRanking.values()[this.ordinal() - 1].getExperience();

        return min;
    }

    public ThePitRanking getPreviousThePitRanking() {
        return this == Initial ? Initial : ThePitRanking.values()[ordinal() - 1];
    }

    public ThePitRanking getNextThePitRanking() {
        return this == Level3 ? Level3 : ThePitRanking.values()[ordinal() + 1];
    }

}
