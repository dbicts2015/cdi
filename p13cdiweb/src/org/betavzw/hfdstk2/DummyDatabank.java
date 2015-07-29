package org.betavzw.hfdstk2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DummyDatabank {
	private final List<String> cursussen= new ArrayList<>();
	public DummyDatabank() {
		cursussen.add("Java inleiding");
		cursussen.add("Java vervolmaking");
	}
	public List<String> getCursussen() {
		return Collections.unmodifiableList(cursussen);
	}
}
