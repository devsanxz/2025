package LeGrimoire;
public class Info {
    private String key;
    private String value;

    // Construtor
    public Info(String key, String value) {
        this.key = key;
        this.value = value;
    }

    // Getter para key
    public String getKey() {
        return key;
    }

    // Setter para key
    public void setKey(String key) {
        this.key = key;
    }

    // Getter para value
    public String getValue() {
        return value;
    }

    // Setter para value
    public void setValue(String value) {
        this.value = value;
    }
}