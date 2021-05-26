package com.ebs.datacredito.util;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SwaggerPageable {

    @ApiModelProperty(notes = "Number of records per page", example = "20")
    private Integer size;

    @ApiModelProperty(notes = "Results page you want to retrieve (0..N)", example = "0")
    private Integer page;

    @ApiModelProperty(notes = "Sorting criteria in the format: property(,asc|desc).")
    private String sort;

}

