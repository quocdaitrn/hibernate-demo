package vn.self.training.hibernate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GroupDto {
    private String groupLeader;
    private List<Long> projects = new ArrayList<>();
}
