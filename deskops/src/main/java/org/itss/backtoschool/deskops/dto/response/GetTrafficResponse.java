package org.itss.backtoschool.deskops.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetTrafficResponse {
    private boolean WorkFromHome;
}
