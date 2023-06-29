package com.example.supermarket.app.admin;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchForm {
	@NotBlank
	private String userid;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//	private LocalDateTime firstDate;
	private LocalDate firstDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//	private LocalDateTime lastDate;
	private LocalDate lastDate;
}