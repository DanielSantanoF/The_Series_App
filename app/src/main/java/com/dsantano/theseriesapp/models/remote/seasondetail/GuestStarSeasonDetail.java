package com.dsantano.theseriesapp.models.remote.seasondetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestStarSeasonDetail {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("credit_id")
    @Expose
    public String creditId;
    @SerializedName("character")
    @Expose
    public String character;
    @SerializedName("order")
    @Expose
    public Integer order;
    @SerializedName("gender")
    @Expose
    public Integer gender;
    @SerializedName("profile_path")
    @Expose
    public String profilePath;
}
