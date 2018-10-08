package com.bkda.security;

public interface TokenExtractor {
	String extract(String payload);
}
