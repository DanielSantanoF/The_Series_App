package com.dsantano.theseriesapp.models.remote.recomendations;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogoRecomended {
    @SerializedName("path")
    @Expose
    public String path;
    @SerializedName("aspect_ratio")
    @Expose
    public Double aspectRatio;
}
