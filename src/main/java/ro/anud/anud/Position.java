package ro.anud.anud;

import java.util.Objects;

public class Position {
    private Integer x;
    private Integer y;


    public Position() {
        x = 0;
        y = 0;
    }

    public Position(final Integer x, final Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Position setX(final Integer x) {
        this.x = x;
        return this;
    }

    public Integer getY() {
        return y;
    }

    public Position setY(final Integer y) {
        this.y = y;
        return this;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        final Position position = (Position) o;
        return Objects.equals(getX(), position.getX()) &&
                Objects.equals(getY(), position.getY());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
