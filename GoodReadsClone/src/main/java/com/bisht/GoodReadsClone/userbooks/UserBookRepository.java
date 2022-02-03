package com.bisht.GoodReadsClone.userbooks;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserBookRepository extends CassandraRepository<UserBookEntity, UserBookKey> {
}
