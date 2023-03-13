package lk.sltc.healthapp;

public class Goal {
    private final String code;
    private final String name;
    private final String description;

    public Goal(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
