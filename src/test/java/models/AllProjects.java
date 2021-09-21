package models;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AllProjects {
    @Expose
    int offset;
    @Expose
    int limit;
    @Expose
    int size;
    @Expose
    List<String> links;
    @Expose
    List<Project> projects;
}