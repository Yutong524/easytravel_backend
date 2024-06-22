package com.easytravel.easytravel.mdbspringboot.repository;

import com.easytravel.easytravel.mdbspringboot.model.TravelRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class TravelRouteRepositoryCustomImpl implements TravelRouteRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String updatePriorityById(Integer id, String priority) {
        Query query = new Query(Criteria.where("routeId").is(id));
        Update update = new Update().set("priority", priority);
        mongoTemplate.updateFirst(query, update, TravelRoute.class);
        return "Priority has been changed!";
    }

    @Override
    public String updateVisibilityById(Integer id) {
        Query query = new Query(Criteria.where("routeId").is(id));
        TravelRoute route = mongoTemplate.findOne(query, TravelRoute.class);

        assert route != null;
        boolean newVisibility = !route.getVisibility();
        Update update = new Update().set("visibility", newVisibility);
        mongoTemplate.updateFirst(query, update, TravelRoute.class);
        return "Visibility has been changed!";
    }
}
