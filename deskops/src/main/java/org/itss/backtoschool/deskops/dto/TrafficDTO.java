package org.itss.backtoschool.deskops.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrafficDTO {
    private Integer travelTimeMinutes;
    private Integer delayMinutes;
    private Boolean workFromHomeRecommendation;
    private LocalDateTime fetchedAt;
}