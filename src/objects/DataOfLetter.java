package objects;

public class DataOfLetter {
   private String letter;
   private boolean shouldRotate;

   public DataOfLetter(String letter, boolean shouldRotate) {
      this.letter = letter;
      this.shouldRotate = shouldRotate;
   }

   public String getLetter() {
      return letter;
   }

   public void setLetter(String letter) {
      this.letter = letter;
   }

   public boolean isShouldRotate() {
      return shouldRotate;
   }

   public void setShouldRotate(boolean shouldRotate) {
      this.shouldRotate = shouldRotate;
   }
}
