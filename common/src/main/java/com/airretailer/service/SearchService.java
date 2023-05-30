package com.airretailer.service;

import com.airretailer.dto.SearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.*;

@RequiredArgsConstructor
@Component
public class SearchService {

    Set<String> availableFields = Set.of("firstName", "lastName");


    final EntityManager em;

    List<?> search(SearchDto dto) {

        Map<String, String> request = new HashMap<>();
        request.put("firstName", dto.getFirstName());
        request.put("lastName", dto.getLastName());
        request.put("middleName", dto.getMiddleName());

        Query q = new Query();
        for (String x : availableFields) {
            if (request.get(x) != null) {
                q.addCriteria(Criteria.where(x).is(request.get(x)));
            }
        }


        return Collections.emptyList();
    }
}
