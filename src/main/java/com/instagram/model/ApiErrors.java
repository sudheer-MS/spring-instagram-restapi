package com.instagram.model;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrors {
	LocalDateTime timestamp;
	String message;
	int status;
	String error;
}
