package me.pokerman99.serversuite.Windows.staffTrackerWindow;

public class addItemsToColumn {


    public addItemsToColumn(String names, String playtime) {
        this.names = names;
        this.playtime = playtime;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    private String names;

    public String getPlaytime() {
        return playtime;
    }

    public void setPlaytime(String playtime) {
        this.playtime = playtime;
    }

    private String playtime;

}
