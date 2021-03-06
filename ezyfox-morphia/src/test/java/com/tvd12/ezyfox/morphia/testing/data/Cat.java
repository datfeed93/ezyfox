package com.tvd12.ezyfox.morphia.testing.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import xyz.morphia.annotations.Entity;
import xyz.morphia.annotations.Id;

@Setter
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Entity(value = "ezyfox.mongodb.testing.cat", noClassnameStored = true)
public class Cat {
	@Id
	private Long id = (long) ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
	private String name = "cat#" + ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
	private String fixedValue = "fixedValue";
	private Set<String> valueSet = new HashSet<>();
	public int age = 10;
	private List<Kitty> kitties = new ArrayList<>();
	private List<String> valueList = new ArrayList<>();
	private int free;
	private String unset = "100";
	
	public Cat(Long id) {
		this.id = id;
	}
}
