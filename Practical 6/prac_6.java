
class RDP {
    private String input;
    private int index;

    public RDP(String input) {
        this.input = input.replaceAll("\s", "");
        this.index = 0;
    }

    private boolean match(char expected) {
        if (index < input.length() && input.charAt(index) == expected) {
            index++;
            return true;
        }

        return false;
    }

    private boolean S() {
        if (match('a')) {
            return true;
        } else if (match('(')) {
            if (L() && match(')')) {
                return true;
            }
        }
        return false;
    }

    private boolean L() {
        if (S()) {
            return LPrime();
        }
        return false;
    }

    private boolean LPrime() {
        if (match(',')) {
            if (S()) {
                return LPrime();
            }
            return false;
        }
        return true;
    }
    
    public boolean parse() {
        return S() && index == input.length();
    }
}

public class prac_6 {
    public static void main(String[] args) {
        String[] testCases = {"a", "(a)", "(a,a)", "(a,(a,a),a)", "(a,a),(a,a)", "a)", "a,a", "a,", "(a,a),a"};
        for (String test : testCases) {
            RDP parser = new RDP(test);
            System.out.println(test + " -> " + (parser.parse() ? "Valid string" : "Invalid string"));
        }
    }
}