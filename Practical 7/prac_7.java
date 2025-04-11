import java.util.*;

public class prac_7 {

    static Map<String, Set<String>> firstSets = new HashMap<>();
    static Map<String, Set<String>> followSets = new HashMap<>();
    static Map<String, List<List<String>>> grammar = new HashMap<>();
    static String startSymbol = "S";

    public static void main(String[] args) {
        initializeGrammar();
        computeFirstSets();
        computeFollowSets();
        printSets("First", firstSets);
        printSets("Follow", followSets);
    }

    static void initializeGrammar() {
        grammar.put("S", Arrays.asList(Arrays.asList("A", "B", "C"), Arrays.asList("D")));
        grammar.put("A", Arrays.asList(Arrays.asList("a"), Arrays.asList("ε")));
        grammar.put("B", Arrays.asList(Arrays.asList("b"), Arrays.asList("ε")));
        grammar.put("C", Arrays.asList(Arrays.asList("(", "S", ")"), Arrays.asList("c")));
        grammar.put("D", Arrays.asList(Arrays.asList("A", "C")));

        for (String nonTerminal : grammar.keySet()) {
            firstSets.put(nonTerminal, new HashSet<>());
            followSets.put(nonTerminal, new HashSet<>());
        }
    }

    static void computeFirstSets() {
        for (String nonTerminal : grammar.keySet()) {
            computeFirst(nonTerminal);
        }
    }

    static Set<String> computeFirst(String symbol) {
        if (firstSets.get(symbol).size() > 0) return firstSets.get(symbol);
        Set<String> first = new HashSet<>();

        for (List<String> production : grammar.get(symbol)) {
            for (String token : production) {
                if (!grammar.containsKey(token)) { 
                    first.add(token);
                    break;
                }
                Set<String> subFirst = computeFirst(token);
                first.addAll(subFirst);
                if (!subFirst.contains("ε")) break;
            }
        }
        firstSets.get(symbol).addAll(first);
        return first;
    }

    static void computeFollowSets() {
        followSets.get(startSymbol).add("$");

        boolean changed;
        do {
            changed = false;
            for (String nonTerminal : grammar.keySet()) {
                for (List<String> production : grammar.get(nonTerminal)) {
                    Set<String> trailer = new HashSet<>(followSets.get(nonTerminal));

                    for (int i = production.size() - 1; i >= 0; i--) {
                        String symbol = production.get(i);

                        if (grammar.containsKey(symbol)) {
                            if (followSets.get(symbol).addAll(trailer)) changed = true;
                            if (firstSets.get(symbol).contains("ε")) {
                                trailer.addAll(firstSets.get(symbol));
                                trailer.remove("ε");
                            } else {
                                trailer = new HashSet<>(firstSets.get(symbol));
                            }
                        } else {
                            trailer.clear();
                            trailer.add(symbol);
                        }
                    }
                }
            }
        } while (changed);
    }

    static void printSets(String name, Map<String, Set<String>> sets) {
        System.out.println(name + " Sets:");
        for (Map.Entry<String, Set<String>> entry : sets.entrySet()) {
            System.out.println(name + "(" + entry.getKey() + ") = " + entry.getValue());
        }
        System.out.println();
    }
}
