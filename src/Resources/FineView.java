package Resources;

public class FineView {
    private String name;
    private String title;
    private String author;
    private Integer overDue;
    private Integer finePerDay;
    private Integer totalAmount;
    private Boolean isPaid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getOverDue() {
        return overDue;
    }

    public void setOverDue(Integer overDue) {
        this.overDue = overDue;
    }

    public Integer getFinePerDay() {
        return finePerDay;
    }

    public void setFinePerDay(Integer finePerDay) {
        this.finePerDay = finePerDay;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    @Override
    public String toString() {
        return
                "{"+
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", overDue=" + overDue +
                ", finePerDay=Rs." + finePerDay +
                ", totalAmount=Rs." + totalAmount +
                '}';
    }

    public String toString2() {
        return "{" +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", overDue=" + overDue +
                ", finePerDay=Rs." + finePerDay +
                ", totalAmount=Rs." + totalAmount +
                '}';
    }
}
