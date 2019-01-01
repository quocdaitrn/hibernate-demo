package vn.self.training.hibernate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjectDto {
    private Long id;
    private Long groupId;
    private int projectNumber;
    private String name;
    private String customer;
    private String employees;
    private String status;
    private String startDate;
    private String endDate;
    private int version;
}
