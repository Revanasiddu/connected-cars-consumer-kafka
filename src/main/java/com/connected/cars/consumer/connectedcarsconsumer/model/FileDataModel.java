package com.connected.cars.consumer.connectedcarsconsumer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Revanasidd Namadev.
 * User: rnamade1
 * Date: 9/26/2021
 * Time: 4:23
 */

@Data
@Valid
@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
public class FileDataModel {

    @JsonProperty("name")
    @NotEmpty(message = "name is required.")
    @Size(min = 2, max = 30, message = "The length of name must be between 2 and 30 characters.")
    private String name;

    @JsonProperty("dob")
    @NotNull(message = "The date of birth is required.")
    private String dob;

    @JsonProperty("salary")
    @NotNull(message = "The salary is required.")
    private String salary;

    @JsonProperty("age")
    @NotNull(message = "Age is required.")
    @Min(18)
    private Integer age;

}
