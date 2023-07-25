package com.satyam.taskmanager.specification;

import com.satyam.taskmanager.model.TaskModel;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecification {

        public static Specification<TaskModel> isCompleted(boolean isCompleted) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isCompleted"), isCompleted);
        }

}
