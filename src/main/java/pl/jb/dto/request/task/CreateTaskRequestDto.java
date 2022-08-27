package pl.jb.dto.request.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateTaskRequestDto {

    private String name;
    private String description;
    private String status;
    private String priority;
    private String parent;
    @JsonProperty("time_estimate")
    private String timeEstimate;
    private String assignees;
    private boolean archived;
}
