package com.br.forum.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusTopic {

	NOT_ANSWERED ("NOT_ANSWERED"),
	UNSOLVED ("UNSOLVED"),
	SOLVED ("SOLVED"),
	CLOSED ("CLOSED");

	private String name;
}
