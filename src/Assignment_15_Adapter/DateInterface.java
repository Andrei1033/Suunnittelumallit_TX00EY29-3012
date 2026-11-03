package Assignment_15_Adapter;

public interface DateInterface {
    void setDay(int day);
    void setMonth(int month);
    void setYear(int year);

    int getDay();
    int getMonth();
    int getYear();

    void advanceDays(int days);
}
