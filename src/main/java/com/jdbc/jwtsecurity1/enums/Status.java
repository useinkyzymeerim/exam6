package com.jdbc.jwtsecurity1.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status {
    Active("Active"),
    Removed("Removed"),
    Inactive("Inactive");
    final String description;
}
