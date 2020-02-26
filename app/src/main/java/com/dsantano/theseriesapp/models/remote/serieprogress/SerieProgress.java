package com.dsantano.theseriesapp.models.remote.serieprogress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SerieProgress {
    private String episode;
    private String posterPath;
    private String season;
    private String serieId;
    private String serieName;
}
