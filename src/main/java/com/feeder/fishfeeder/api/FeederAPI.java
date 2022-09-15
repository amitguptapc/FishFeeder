package com.feeder.fishfeeder.api;

import com.feeder.fishfeeder.dto.FeedStatusDTO;
import com.feeder.fishfeeder.service.FeederService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feeder")
public class FeederAPI {
    @Autowired
    FeederService feederService;

    @GetMapping("/manualFeed")
    public String feed() {
        return feederService.feed("manualFeed");
    }

    @GetMapping("/feedInfo")
    public List<FeedStatusDTO> getFeedInfo() {
        return feederService.getFeedInfo();
    }
}