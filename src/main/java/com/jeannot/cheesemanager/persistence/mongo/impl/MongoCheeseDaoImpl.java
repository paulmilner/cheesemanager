package com.jeannot.cheesemanager.persistence.mongo.impl;

import java.time.Instant;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.jeannot.cheesemanager.openapi.model.Cheese;
import com.jeannot.cheesemanager.persistence.CheeseDao;

/**
 * @inheritDoc
 */
@Service
public class MongoCheeseDaoImpl implements CheeseDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(MongoCheeseDaoImpl.class);

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public Cheese create(Cheese cheese) {
		cheese.setBy("UserNotYetAvailable"); // TODO
		cheese.setCreated(Instant.now().toString()); // add UTC timestamp as String
		return mongoTemplate.save(cheese);
	}

	@Override
	public List<Cheese> getAll() {
		List<Cheese> cheeses = mongoTemplate.findAll(Cheese.class);

		LOGGER.debug("getAll() returns {}", cheeses);

		return cheeses;
	}

	@Override
	public Cheese get(String id) {
		Cheese cheese = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(id)),
				Cheese.class);

		LOGGER.debug("get({}) returns {}", id, cheese);

		return cheese;
	}

	@Override
	public Cheese update(String id, Cheese cheese) {
		cheese.setBy("UserNotYetAvailable"); // TODO
		cheese.setUpdated(Instant.now().toString()); // add UTC timestamp as String
		return mongoTemplate.save(cheese);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

}
