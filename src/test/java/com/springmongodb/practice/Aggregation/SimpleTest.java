package com.springmongodb.practice.Aggregation;

import com.springmongodb.practice.Entities.StatusPartition;
import com.springmongodb.practice.Helper.ValueFactory;
import com.springmongodb.practice.Model.Lead;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Before
    public void setUpTest() {
        mongoTemplate.dropCollection(Lead.class);
        mongoTemplate.createCollection(Lead.class);
        mongoTemplate.insert(ValueFactory.lead1);
        mongoTemplate.insert(ValueFactory.lead2);
    }

    @Test
    public void basicTest() {
        List<Lead> leadList = mongoTemplate.findAll(Lead.class);
        assertEquals(2,leadList.size());
    }

    @Test
    public void basicMongoTemplateTest() {
        mongoTemplate.insert(ValueFactory.lead3);

        Query query = new Query();
        query.addCriteria(Criteria.where("status").is("ACTIVE"));
        List<Lead> leads = mongoTemplate.find(query, Lead.class);

        assertEquals(2, leads.size());
    }

    @Test
    public void basicAggregationTest() {
       Aggregation aggregation = newAggregation(
               match(Criteria.where("status").is("ACTIVE")),
               group("partition").count().as("total")
       );
       AggregationResults<StatusPartition> statusPartitions = mongoTemplate.aggregate(aggregation, Lead.class, StatusPartition.class);
       List<StatusPartition> statusPartitionList = statusPartitions.getMappedResults();
       assertEquals(2, statusPartitionList.size());
    }
}
