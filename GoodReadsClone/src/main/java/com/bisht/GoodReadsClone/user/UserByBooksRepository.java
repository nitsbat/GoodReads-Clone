package com.bisht.GoodReadsClone.user;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
public interface UserByBooksRepository extends CassandraRepository<UserByBooks, String> {
    Slice<UserByBooks> findAllById(String id, Pageable pageable);
}
