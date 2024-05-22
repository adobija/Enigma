package Objects;

public class DataOfLetter {
    private String letter;
    private int indexOfNextToCode;

    private Boolean shouldRotate;

    public DataOfLetter(String letter, int indexOfLetter) {
        this.letter = letter;
        this.indexOfNextToCode = indexOfLetter;
        this.shouldRotate = false;
    }

    public Boolean getShouldRotate() {
        return shouldRotate;
    }

    public void setShouldRotate(Boolean shouldRotate) {
        this.shouldRotate = shouldRotate;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public int getIndexOfNextToCode() {
        return indexOfNextToCode;
    }

    public void setIndexOfNextToCode(int indexOfNextToCode) {
        this.indexOfNextToCode = indexOfNextToCode;
    }
}
