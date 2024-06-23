package com.easytravel.easytravel.mdbspringboot.repository;

public interface TravelRouteRepositoryCustom {
    String updatePriorityById(Integer id, String priority);
    String updateVisibilityById(Integer id);
}
