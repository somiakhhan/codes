import java.util.Objects;
public class Time24 implements Comparable<Time24> {
    private int hour, minute, second;
    public Time24(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
    public int getHour() {
        return hour;
    }
    public int getMinute() {
        return minute;
    }
    public int getSecond() {
        return second;
    }
    public static Time24 parse(String timeString) {
        String[] parts = timeString.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        int second = Integer.parseInt(parts[2]);
        return new Time24(hour, minute, second);
    }
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof Time24)) return false;
        Time24 other = (Time24) obj;
        return hour == other.hour && minute == other.minute && second == other.second;
    }
    @Override
    public int hashCode() {
        return Objects.hash(hour, second, minute);
    }
    @Override
    public int compareTo(Time24 other) {
        int hourComparison = Integer.compare(this.hour, other.hour);
        if(hourComparison != 0) {
            return hourComparison;
        }
        int minuteComparison = Integer.compare(this.minute, other.minute);
        if(minuteComparison != 0) {
            return minuteComparison;
        }
        int secondComparison = Integer.compare(this.second, other.second);
        return secondComparison;
    }
    @Override
    public String toString() {
        String result = "";

        if(hour < 10) {
            result += "0";
        }
        result += hour + ":";

        if(minute < 10) {
            result += "0";
        }
        result += minute + ":";
        
        if(second < 10) {
            result += "0";
        }
        result += second;
        
        return result;
    }
    
}
