package org.itss.backtoschool.deskops.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class TrafficIncidentResponse {
    private List<Incident> incidents;
}

