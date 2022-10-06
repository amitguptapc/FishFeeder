package com.feeder.fishfeeder.service;

import com.feeder.fishfeeder.dto.FeedStatusDTO;
import com.feeder.fishfeeder.model.FeedStatus;
import com.feeder.fishfeeder.repo.FeederRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FeederService {

    @Autowired
    FeederRepo feederRepo;

    public String feed(String type) {
        try {
            log.info("Starting to trigger RPi's servo motor");
            Runtime.getRuntime().exec("python /home/pi/feeder.py");
            log.info("Triggered RPi's servo motor");
        } catch (Exception e) {
            log.info("Unable to feed");
            return "Unable to feed";
        }
        int count = 0;
        String time = "";
        Optional<FeedStatus> feedStatusDB = feederRepo.findById(type);
        if (feedStatusDB.isPresent()) {
            count = feedStatusDB.get().getCount();
            time = feedStatusDB.get().getTime() + "\n";
        }
        String localTime = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
        FeedStatus feedStatus = new FeedStatus(type, count + 1, time + localTime);
        feederRepo.save(feedStatus);
        log.info("Fed successfully at " + localTime);
        return "Fed successfully at " + localTime;
    }

    public List<FeedStatusDTO> getFeedInfo() {
        List<FeedStatusDTO> list = new ArrayList<>();
        Iterable<FeedStatus> feedStatusIterable = feederRepo.findAll();
        for (FeedStatus feedStatus : feedStatusIterable) {
            list.add(new FeedStatusDTO(feedStatus.getType(), feedStatus.getCount(), feedStatus.getTime()));
        }
        log.info("Feed status fetched successfully");
        return list;
    }

    @Scheduled(cron = "0 */5 5 * * ?")
    public void autoFeedMorning() {
        Optional<FeedStatus> feedStatus = feederRepo.findById("morningAuto");
        if (feedStatus.isPresent() && feedStatus.get().getCount() > 0)
            return;
        log.info("Morning auto-feed process started");
        feed("morningAuto");
    }

    @Scheduled(cron = "0 */5 17 * * ?")
    public void autoFeedEvening() {
        Optional<FeedStatus> feedStatus = feederRepo.findById("eveningAuto");
        if (feedStatus.isPresent() && feedStatus.get().getCount() > 0)
            return;
        log.info("Evening auto-feed process started");
        feed("eveningAuto");
    }

    @Scheduled(cron = "0 */5 0,4 * * ?")
    public void clearDB() {
        if (feederRepo.count() <= 0)
            return;
        feederRepo.deleteAll();
        log.info("DB cleared successfully");
    }
}
