package com.epam.project.model.order;

import java.sql.Timestamp;
import java.util.Objects;

public class BookOrder {
    private Long id;
    private Long bookId;
    private Long userId;
    private Status status;
    private Issue issue;
    private Timestamp orderedDate;
    private Timestamp bookedDate;
    private Timestamp returnedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public Timestamp getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Timestamp orderedDate) {
        this.orderedDate = orderedDate;
    }

    public Timestamp getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(Timestamp bookedDate) {
        this.bookedDate = bookedDate;
    }

    public Timestamp getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Timestamp returnedDate) {
        this.returnedDate = returnedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookOrder bookOrder = (BookOrder) o;
        return id.equals(bookOrder.id) && bookId.equals(bookOrder.bookId) &&
                userId.equals(bookOrder.userId) && status == bookOrder.status &&
                issue == bookOrder.issue && orderedDate.equals(bookOrder.orderedDate) &&
                Objects.equals(bookedDate, bookOrder.bookedDate) &&
                Objects.equals(returnedDate, bookOrder.returnedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookId, userId, status, issue, orderedDate, bookedDate, returnedDate);
    }

    @Override
    public String toString() {
        return "BookOrder{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", userId=" + userId +
                ", status=" + status +
                ", issue=" + issue +
                ", orderedDate=" + orderedDate +
                ", bookedDate=" + bookedDate +
                ", returnedDate=" + returnedDate +
                '}';
    }
}
