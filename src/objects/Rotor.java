package objects;

public class Rotor {
   private int position;

   private String[][] scrambledLetters;

   private int notchToRotateNext;

   private String lettersToCypher;

    public Rotor(int position, String lettersToCypher, int notchToRotateNext) {
        if(position > 25 || position < 0){
            throw new ArithmeticException("Please input valid position!");
        }
        if(notchToRotateNext > 25 || notchToRotateNext < 0){
            throw new ArithmeticException("Please input valid notch!");
        }
        this.position = position;
        this.notchToRotateNext = notchToRotateNext;
        this.scrambledLetters = new String[26][2];
        this.lettersToCypher = lettersToCypher;
        generateScrablerToPosition(position);
    }
    private String getLettersToCypher(){
        return this.lettersToCypher;
    }

    public void generateScrablerToPosition(int position){
        String[] arrayOfScrambledLetters = getLettersToCypher().toUpperCase().split("");
        String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
        int counter = 0;
        while(counter <= 25){
//            System.out.println("adding to row " + counter +  " elements " + alphabet[position] + ", " + arrayOfScrambledLetters[position]);
            this.scrambledLetters[position] = new String[]{alphabet[counter], arrayOfScrambledLetters[counter]};
            if(position < 25){
                position++;
            }else{
                position = 0;
            }
            counter++;
        }
    }

    public void printScrambler(){
        for (String[] x: scrambledLetters){
            System.out.println("{"+x[0] + ", " + x[1]+"}");
        }
    }
    public DataOfLetter getOutputIndexIn(DataOfLetter letter){
        int positionOnCall = getPosition();
        if(letter.ShouldRotate()){
            rotate();
        }
        int indexOfInputLetter = letter.getIndexOfNextLetter();
        String foundLetter = this.scrambledLetters[indexOfInputLetter][1];
        int indexOfLetterInAlphabet = 0;
        for(String[] pairs: this.scrambledLetters){
            if(pairs[0].equalsIgnoreCase(String.valueOf(foundLetter))){
                break;
            }else{
                indexOfLetterInAlphabet++;
            }
        }
        boolean shouldRotateNext = false;
        int positionAtEnd = getPosition();
        if(positionOnCall == positionAtEnd-1 && getNotchToRotateNext() == positionOnCall){
            shouldRotateNext = true;
        }
        DataOfLetter outputLetter = new DataOfLetter(foundLetter, shouldRotateNext);
        outputLetter.setIndexOfNextLetter(indexOfLetterInAlphabet);
        return outputLetter;
    }

    public DataOfLetter getOutputIndexOut(DataOfLetter letter){
        int indexOfInputLetter = letter.getIndexOfNextLetter();

        String foundLetter = this.scrambledLetters[indexOfInputLetter][0];

        int indexOfLetterInRotorsAlphabet = 0;

        for(String[] pairs: this.scrambledLetters){
            if(pairs[1].equalsIgnoreCase(String.valueOf(foundLetter))){
                break;
            }else{
                indexOfLetterInRotorsAlphabet++;
            }
        }
        DataOfLetter outputLetter = new DataOfLetter(foundLetter, false);
        outputLetter.setIndexOfNextLetter(indexOfLetterInRotorsAlphabet);
        return outputLetter;
    }

    public void rotate(){
        int prevPosition = getPosition();
        this.position++;
        if(getPosition() >= 26){
            this.position = 0;
        }
        generateScrablerToPosition(getPosition());
        System.out.println("Rotating from " + prevPosition + " to " + getPosition()+ " !");
    }
    public int getPosition(){
        return this.position;
    }

    public int getNotchToRotateNext() {
        return notchToRotateNext;
    }

    public String[][] getScrambledLetters() {
        return scrambledLetters;
    }

}
