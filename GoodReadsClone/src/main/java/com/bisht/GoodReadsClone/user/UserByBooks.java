package com.bisht.GoodReadsClone.user;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Table(value = "books_by_user")
public class UserByBooks {

    @PrimaryKeyColumn(name = "user_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String id;

    @PrimaryKeyColumn(name = "book_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    @CassandraType(type = Name.TEXT)
    private String bookId;

    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    @CassandraType(type = Name.TIMEUUID)
    private UUID timeUuid;

    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, ordering = Ordering.ASCENDING)
    @CassandraType(type = Name.TEXT)
    private String readingStatus;

    @Column("book_name")
    @CassandraType(type = Name.TEXT)
    private String bookName;

    @Column("author_name")
    @CassandraType(type = Name.LIST, typeArguments = Name.TEXT)
    private List<String> authorNames;

    @Column("cover_id")
    @CassandraType(type = Name.TEXT)
    private String coverId;

    @Column("rating")
    @CassandraType(type = Name.INT)
    private int rating;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public UUID getTimeUuid() {
        return timeUuid;
    }

    public void setTimeUuid(UUID timeUuid) {
        this.timeUuid = timeUuid;
    }

    public String getReadingStatus() {
        return readingStatus;
    }

    public void setReadingStatus(String readingStatus) {
        this.readingStatus = readingStatus;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public List<String> getAuthorNames() {
        return authorNames;
    }

    public void setAuthorNames(List<String> authorNames) {
        this.authorNames = authorNames;
    }

    public String getCoverId() {
        return coverId;
    }

    public void setCoverId(String coverId) {
        this.coverId = coverId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserByBooks that = (UserByBooks) o;
        return getId().equals(that.getId()) &&
                getBookId().equals(that.getBookId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBookId());
    }
}
