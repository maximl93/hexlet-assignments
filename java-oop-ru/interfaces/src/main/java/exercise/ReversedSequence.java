package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {

    private String text;

    public ReversedSequence(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        StringBuilder reversed = new StringBuilder();
        for (int i = text.length() - 1; i >= 0; i--) {
            reversed.append(text.charAt(i));
        }
        return reversed.toString();
    }

    @Override
    public int length() {
        return text.length();
    }

    @Override
    public char charAt(int index) {
        return text.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return text.subSequence(start, end);
    }
}
// END
