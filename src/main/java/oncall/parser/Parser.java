package oncall.parser;

public interface Parser<T> {
    T parse(String input);
}
