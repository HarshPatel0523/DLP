import java.util.*;

public class DFA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of Input Symbols: ");
        int noInputSymbols = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Enter Input Symbols: ");
        String[] inputSymbols = scanner.nextLine().split(" ");

        System.out.print("Enter number of states: ");
        int noStates = scanner.nextInt();

        System.out.print("Enter Initial state: ");
        int initialState = scanner.nextInt();

        System.out.print("Enter Final state: ");
        scanner.nextLine(); 
        String[] finalStatesInput = scanner.nextLine().split(" ");
        Set<Integer> finalStates = new HashSet<>();
        for (String state : finalStatesInput) {
            finalStates.add(Integer.parseInt(state));
        }

        Map<Integer, Map<String, Integer>> transitions = new HashMap<>();
        for (int state = 0; state < noStates; state++) {
            transitions.put(state, new HashMap<>());
            for (String symbol : inputSymbols) {
                System.out.print("Enter the next state for state " + state + " on input symbol '" + symbol + "': ");
                int nextState = scanner.nextInt();
                transitions.get(state).put(symbol, nextState);
            }
        }

        System.out.print("Enter the input string: ");
        scanner.nextLine(); 
        String inputString = scanner.nextLine();

        int currentState = initialState;

        for (char ch : inputString.toCharArray()) {
            String symbol = String.valueOf(ch);

            if (!Arrays.asList(inputSymbols).contains(symbol)) {
                System.out.println("Error: Symbol '" + symbol + "' not in the input alphabet.");
                return;
            }

            if (!transitions.containsKey(currentState) || !transitions.get(currentState).containsKey(symbol)) {
                System.out.println("Error: No transition for state " + currentState + " on symbol '" + symbol + "'.");
                return;
            }

            currentState = transitions.get(currentState).get(symbol);
        }

        if (finalStates.contains(currentState)) {
            System.out.println("String accepted!");
        } else {
            System.out.println("String rejected.");
        }

        scanner.close();
    }
}