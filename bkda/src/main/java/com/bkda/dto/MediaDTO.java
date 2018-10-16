package com.bkda.dto;

import java.io.Serializable;

import com.bkda.model.Media;

public class MediaDTO implements Serializable {
	private byte[] content;
	private Media media;
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	public Media getMedia() {
		return media;
	}
	public void setMedia(Media media) {
		this.media = media;
	}
}
