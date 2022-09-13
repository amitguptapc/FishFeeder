package com.feeder.fishfeeder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Feeder_Table")
public class FeedStatus {
    @Id
    private String type;
    private int count;
    @Lob
    @Column
    private String time;
}