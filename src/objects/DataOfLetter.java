package objects;

public class DataOfLetter {
   private String letter;
   private boolean shouldRotate;
   private int indexOfNextLetter;

   public DataOfLetter(String letter, boolean shouldRotate) {
      this.letter = letter;
      this.shouldRotate = shouldRotate;
   }

   public boolean isShouldRotate() {
      return shouldRotate;
   }

   public int getIndexOfNextLetter() {
      return indexOfNextLetter;
   }

   public void setIndexOfNextLetter(int indexOfNextLetter) {
      this.indexOfNextLetter = indexOfNextLetter;
   }

   public String getLetter() {
      return letter.toUpperCase();
   }

   public void setLetter(String letter) {
      this.letter = letter;
   }

   public boolean ShouldRotate() {
      return shouldRotate;
   }

   public void setShouldRotate(boolean shouldRotate) {
      this.shouldRotate = shouldRotate;
   }
}
