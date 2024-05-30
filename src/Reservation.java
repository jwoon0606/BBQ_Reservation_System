import java.io.Serializable;

public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String phone;
    private String seat;
    private String date;
    private String time;

    public Reservation(String name, String phone, String seat, String date, String time) {
        this.name = name;
        this.phone = phone;
        this.seat = seat;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getSeat() {
        return seat;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", seat='" + seat + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
