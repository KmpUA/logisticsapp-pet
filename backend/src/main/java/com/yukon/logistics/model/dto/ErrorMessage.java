package com.yukon.logistics.model.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

/**
 * Class for response object of any Exception in our app.
 */
@EqualsAndHashCode
@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorMessage {
    int status;
    Date date;
    String description;
    String url;
}
