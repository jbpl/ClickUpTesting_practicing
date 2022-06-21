package pl.jb.dto.request.task;

import lombok.Data;

@Data
public class CreateTaskRequestDto {

    private String name;
    private String description;
    private String status;
    private String priority;
    private String parent;
    private String time_estimate;
    private String assignees;
    private boolean archived;
}
