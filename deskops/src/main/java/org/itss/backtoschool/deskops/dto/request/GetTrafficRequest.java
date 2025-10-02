package org.itss.backtoschool.deskops.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTrafficRequest {
    private String Street;
    private int StreetNumber;
    private String City;
}
