package mergeSort;

public class Contestant implements Comparable<Contestant> {
    int id;
    int score;

    public Contestant(int id, int score) {
        this.id = id;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Contestant " + id + ": " + score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contestant that = (Contestant) o;
        return id == that.id &&
                score == that.score;
    }

    @Override
    public int compareTo(Contestant o) {
        return Integer.compare(this.getScore(),o.getScore());
    }
}
