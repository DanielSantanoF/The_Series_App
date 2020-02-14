package com.dsantano.theseriesapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedBySerieDetail {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("credit_id")
    @Expose
    public String creditId;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("gender")
    @Expose
    public Integer gender;
    @SerializedName("profile_path")
    @Expose
    public String profilePath;
}
