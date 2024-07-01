package objects;

import java.util.ArrayList;
import java.util.HashMap;

public class Plugboard {
    private HashMap<String, String> plug;
    private ArrayList<String> swaps;

    public Plugboard() {
        this.plug = new HashMap<>();
    }

    public Plugboard(String mapOfKeys) {
        String[] duo = mapOfKeys.split(" ");
        this.plug = new HashMap<String, String>();
        this.swaps = new ArrayList<>();

        for (String x: duo){
            String swap1 = x.substring(0, 1).toUpperCase();
            String swap2 = x.substring(1, 2).toUpperCase();
            if (!this.plug.keySet().isEmpty()) {
                boolean conflict = false;
                for (String lettersAlready : this.plug.keySet()) {
                    conflict = false;
                    if (lettersAlready.equalsIgnoreCase(swap1) || lettersAlready.equalsIgnoreCase(swap2)) {
                        conflict = true;
                        break;
                    }
                }
                if (!conflict) {
                    this.plug.put(swap1, swap2);
                    this.plug.put(swap2, swap1);
                    swaps.add(swap1+swap2);
                }else{
                    System.out.println("Set " + swap1 + swap2 + " has been refused!");
                }
        }else{
            this.plug.put(swap1, swap2);
            this.plug.put(swap2, swap1);
            swaps.add(swap1+swap2);
        }

        }
    }

    public String plugboardLetter(String letter){
       String swappedLetter = this.plug.get(letter.toUpperCase());
       if(swappedLetter == null){
           return letter;
       }else{
           return swappedLetter;
       }
    }

    public void printSwaps() {
        for (String x : this.swaps){
            System.out.print(x + " ");
        }
        System.out.println();
    }
}
