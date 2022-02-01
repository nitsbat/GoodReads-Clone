package com.bisht.GoodReadsClone.userbooks;


import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.time.LocalDate;

public class UserBookEntity {

    @Id
    @PrimaryKeyColumn(name = "user_book_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private UserBookKey userBookKey;

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
}
