package Resources;

import java.time.LocalDate;
import java.util.Date;

public class ReserveAndBurrow {
    private Long id;
    private Long userId;
    private Long bookId;
    private Boolean reserved;
    private Boolean isIssued;
    private LocalDate issueDate;
    private LocalDate returnDate;

    public ReserveAndBurrow() {
    }

    public ReserveAndBurrow(Long id, Long userId, Long bookId, Boolean reserved, Boolean isIssued, LocalDate issueDate, LocalDate returnDate) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.reserved = reserved;
        this.isIssued = isIssued;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

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

    public Boolean getReserved() {
        return reserved;
    }

    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
    }

    public Boolean getIsIssued() {
        return isIssued;
    }

    public void setIsIssued(Boolean booked) {
        isIssued = booked;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}