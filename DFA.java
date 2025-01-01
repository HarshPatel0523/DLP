import java.util.*;

public class DFA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of Input Symbols: ");
        sc.nextInt();
        
        System.out.print("Enter Input Symbols: ");
        sc.nextLine(); 
        String[] inputSymbols = sc.nextLine().split(" ");

        System.out.print("Enter number of states: ");
        int noStates = sc.nextInt();

        System.out.print("Enter Initial state: ");
        int initialState = sc.nextInt();

        System.out.print("Enter Final state: ");
        sc.nextLine(); 
        String[] finalStatesInput = sc.nextLine().split(" ");

        Set<Integer> finalStates = new HashSet<>();
        
        for (String state : finalStatesInput) {
            finalStates.add(Integer.parseInt(state));
        }

        Map<Integer, Map<String, Integer>> transitions = new HashMap<>();
        
        for (int state = 0; state < noStates; state++) {
            transitions.put(state, new HashMap<>());
            for (String symbol : inputSymbols) {
                System.out.print("Enter the next state for state " + state + " on input symbol '" + symbol + "': ");
                int nextState = sc.nextInt();
                transitions.get(state).put(symbol, nextState);
            }
        }

        System.out.print("Enter the input string: ");
        sc.nextLine(); 
        String inputString = sc.nextLine();

        int currentState = initialState;

        for (char ch : inputString.toCharArray()) {
            String symbol = String.valueOf(ch);

            if (!Arrays.asList(inputSymbols).contains(symbol)) {
                System.out.println("Error!!!: Symbol '" + symbol + "'is not there in the input alphabet.");
                return;
            }

            if (!transitions.containsKey(currentState) || !transitions.get(currentState).containsKey(symbol)) {
                System.out.println("Error!!!: No transition for the state " + currentState + " on symbol '" + symbol + "'.");
                return;
            }

            currentState = transitions.get(currentState).get(symbol);
        }

        if (finalStates.contains(currentState)) {
            System.out.println("String accepted!");
        } else {
            System.out.println("String rejected.");
        }

        sc.close();
    }
}