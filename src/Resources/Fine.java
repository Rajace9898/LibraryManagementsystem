package Resources;

public class Fine {
    private Long id;
    private Long userId;
    private Long bookId;
    private Integer totalAmount;
    private Integer finePerDay;
    private Integer overDue;
    private Boolean isPaid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getFinePerDay() {
        return finePerDay;
    }

    public void setFinePerDay(Integer finePerDay) {
        this.finePerDay = finePerDay;
    }

    public Fine() {
    }

    public Integer getOverDue() {
        return overDue;
    }

    public void setOverDue(Integer overDue) {
        this.overDue = overDue;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public Fine(Long id, Long userId, Long bookId, Integer totalAmount, Integer finePerDay) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.totalAmount = totalAmount;
        this.finePerDay = finePerDay;
    }

}
