package com.dsantano.theseriesapp.models.remote.recomendations;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NetworkRecomended {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("logo")
    @Expose
    public LogoRecomended logo;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("origin_country")
    @Expose
    public String originCountry;
}
