package com.dsantano.theseriesapp.models.remote.detail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeasonsSpinnerSerieDetail {
    public Integer seasonNumber;
    public Integer episodeCount;
    public String name;
}
