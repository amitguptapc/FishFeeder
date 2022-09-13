package com.feeder.fishfeeder.repo;

import com.feeder.fishfeeder.model.FeedStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeederRepo extends CrudRepository<FeedStatus, String> {
}
