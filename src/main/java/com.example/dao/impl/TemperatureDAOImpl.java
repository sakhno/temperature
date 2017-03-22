package com.example.dao.impl;

import com.example.dao.TemperatureDAO;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.IndexOptions;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.mongodb.client.model.Filters.*;

/**
 * Created by antonsakhno on 21.03.17.
 */
@Repository
public class TemperatureDAOImpl implements TemperatureDAO {
    private static final String DB_NAME = "temperatureDB";
    private static final String COLLECTION_NAME = "temperatureCollection";
    private static final Long ENTRY_LIVE_TIME = 60L;
    private static final String EXPIRE_AT = "expireAt";
    private static final String FIELD_TIME = "timestamp";
    private static final String FIELD_TEMPERATURE = "temperature";

    private MongoClient mongoClient;

    @Autowired
    public TemperatureDAOImpl(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
        getTemperatureCollection().createIndex(new Document(EXPIRE_AT, 1),
                new IndexOptions().expireAfter(ENTRY_LIVE_TIME, TimeUnit.MINUTES));
    }

    @Override
    public List<Double> readTemperatureForPeriod(LocalDateTime timeFrom, LocalDateTime timeTo) {
        List<Double> result = new ArrayList<>();
        for (Document doc: getTemperatureCollection()
                .find(and(gte(FIELD_TIME, timeFrom.toString()), lte(FIELD_TIME, timeTo.toString())))) {
            result.add(doc.get(FIELD_TEMPERATURE, Double.class));
        }
        return result;
    }

    @Override
    public void saveCurrentTemperature(double temperature) {
        Document temperatureDoc = new Document()
                .append(FIELD_TEMPERATURE, temperature)
                .append(FIELD_TIME, LocalDateTime.now().toString());
        getTemperatureCollection().insertOne(temperatureDoc);
    }

    private MongoCollection<Document> getTemperatureCollection() {
        return mongoClient.getDatabase(DB_NAME).getCollection(COLLECTION_NAME);
    }
}
