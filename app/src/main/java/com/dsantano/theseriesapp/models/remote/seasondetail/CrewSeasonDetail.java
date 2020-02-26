package com.dsantano.theseriesapp.models.remote.seasondetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrewSeasonDetail {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("credit_id")
    @Expose
    public String creditId;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("department")
    @Expose
    public String department;
    @SerializedName("job")
    @Expose
    public String job;
    @SerializedName("gender")
    @Expose
    public Integer gender;
    @SerializedName("profile_path")
    @Expose
    public String profilePath;
}
