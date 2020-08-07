package com.stackroute.keepnote.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/*
 * The class "Note" will be acting as the data model for the Note data in the ArrayList.
 */
public class Note implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8936443632490905193L;
	/*
	 * This class should have five fields (noteId, noteTitle, noteContent,
	 * noteStatus and createdAt). This class should also contain the getters and
	 * setters for the fields. The value of createdAt should not be accepted from
	 * the user but should be always initialized with the system date
	 */
	private int noteId;
	private String noteTitle;
	private String noteContent;
	private String noteStatus;
	private LocalDateTime createdAt;

	
	/* All the getters/setters definition should be implemented here */

	public int getNoteId() {
		return this.noteId;

	}

	public void setNoteId(final int intid) {
		this.noteId = intid;
	}

	public String getNoteTitle() {
		return this.noteTitle;
	}

	public void setNoteTitle(final String string) {
		this.noteTitle = string;
	}

	public String getNoteContent() {
		return this.noteContent;
	}

	public void setNoteContent(final String string) {
		this.noteContent =string;
	}

	public String getNoteStatus() {
		return this.noteStatus;
	}

	public void setNoteStatus(final String string) {
		this.noteStatus = string;
	}

	public LocalDateTime getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(final LocalDateTime localdatetime) {
		this.createdAt = localdatetime;  
	}

	/* Override the toString() method */

	@Override
	public String toString() {
		
		return String.format("Note [noteId=%s, noteTitle=%s, noteContent=%s, noteStatus=%s, createdAt=%s]", noteId,
				noteTitle, noteContent, noteStatus, createdAt);
	}
}