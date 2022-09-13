package com.feeder.fishfeeder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedStatusDTO {
    private String type;
    private int count;
    private String time;
}
