package Labs.Lab10;

import java.io.Serializable;

class Film implements Serializable {
    private String title;
    private int episodes;
    private int duration;
    private String days; // теперь это строка

    public Film(String title, int episodes, int duration, String days) {
        this.title = title;
        this.episodes = episodes;
        this.duration = duration;
        this.days = days;
    }

    public String getTitle() {
        return title;
    }

    public int getEpisodes() {
        return episodes;
    }

    public int getDuration() {
        return duration;
    }

    public String getDays() {
        return days;
    }

    @Override
    public String toString() {
        return "Фильм: " + getTitle() + ", Серий: " + getEpisodes() +
                ", Длительность: " + getDuration() + " мин, Дни показа: [" + getDays() + "]";
    }
}