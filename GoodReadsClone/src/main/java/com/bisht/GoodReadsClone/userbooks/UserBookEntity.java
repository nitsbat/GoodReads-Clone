package com.bisht.GoodReadsClone.userbooks;


import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDate;

@Table(value = "user_book_by_id")
public class UserBookEntity {

    @Id
    @PrimaryKeyColumn(name = "user_book_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private UserBookKey key;

    @Column(value = "started_date")
    @CassandraType(type = CassandraType.Name.DATE)
    private LocalDate startedDate;

    @Column(value = "completed_date")
    @CassandraType(type = CassandraType.Name.DATE)
    private LocalDate completedDate;

    @Column(value = "status")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String status;

    @Column(value = "rating")
    @CassandraType(type = CassandraType.Name.INT)
    private int rating;

    public UserBookKey getKey() {
        return key;
    }

    public void setKey(UserBookKey key) {
        this.key = key;
    }

    public LocalDate getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(LocalDate startedDate) {
        this.startedDate = startedDate;
    }

    public LocalDate getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(LocalDate completedDate) {
        this.completedDate = completedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
