package org.milan.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Milan Rathod
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeRequest {
    private String name;
    private Long salary;
}
