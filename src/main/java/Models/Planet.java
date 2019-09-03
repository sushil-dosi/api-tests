package Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Planet {
    private String name;
    @JsonProperty("rotation_period")
    private String rotation_period;

    @JsonProperty("orbital_period")
    private String orbital_period;
    private String diameter;
    private String climate;
    private String gravity;
    private String terrain;

    @JsonProperty("surface_water")
    private String surface_water;
    private String population;
    private List<String> residents;
    private List<String> films;
    private String created;
    private String edited;
    private String url;
}
