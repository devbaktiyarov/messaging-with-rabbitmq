package com.arsenbaktiyarov.restrabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person implements Serializable {
    private Long id;
    private String name;
}
