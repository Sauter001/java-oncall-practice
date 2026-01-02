package oncall.domain;

import oncall.error.DomainNotExistException;

import java.util.stream.Stream;

public enum Weekday {
    MON("월"),
    TUE("화"),
    WED("수"),
    THU("목"),
    FRI("금"),
    SAT("토"),
    SUN("일");

    private static final String DOMAIN_TYPE = "요일";
    private final String name;

    Weekday(String name) {
        this.name = name;
    }

    public static Weekday from(String name) {
        return Stream.of(values())
                .filter(w -> w.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new DomainNotExistException(DOMAIN_TYPE));
    }

    public String getName() {
        return name;
    }
}
