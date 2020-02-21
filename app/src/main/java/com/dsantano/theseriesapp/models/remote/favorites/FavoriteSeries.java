package com.dsantano.theseriesapp.models.remote.favorites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteSeries {
    private String posterPath;
    private String serieId;
    private String serieName;
}
