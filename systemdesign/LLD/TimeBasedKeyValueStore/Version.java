package systemdesign.LLD.TimeBasedKeyValueStore;

public class Version {
    private int timestamp;
    private String value;

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getValue() {
        return value;
    }

    public Version(int timestamp, String value) {
        this.timestamp = timestamp;
        this.value = value;
    }
}
