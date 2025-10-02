package org.itss.backtoschool.course.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.itss.backtoschool.course.dto.TrafficRouteOption;
import org.itss.backtoschool.course.dto.TrafficIncident;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrafficRouteResponse {
    private List<TrafficRouteOption> routes;
    private List<TrafficIncident> trafficIncidents;
    private String mapImageUrl;
}
