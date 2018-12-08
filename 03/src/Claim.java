public class Claim {
    private int inchesFromLeft;
    private int inchesFromTop;
    private int width;
    private int height;
    private int id;

    @Override
    public String toString() {
        return "#" + id + " @ " + inchesFromLeft + "," + inchesFromTop + ": " + width + "x" + height;
    }

    public int getInchesFromLeft() {
        return inchesFromLeft;
    }

    public void setInchesFromLeft(int inchesFromLeft) {
        this.inchesFromLeft = inchesFromLeft;
    }

    public int getInchesFromTop() {
        return inchesFromTop;
    }

    public void setInchesFromTop(int inchesFromTop) {
        this.inchesFromTop = inchesFromTop;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
