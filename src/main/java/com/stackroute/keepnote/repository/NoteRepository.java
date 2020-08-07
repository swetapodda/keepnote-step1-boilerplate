package com.stackroute.keepnote.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.stackroute.keepnote.model.Note;

/*
 * This class contains the code for data storage interactions and methods 
 * of this class will be used by other parts of the applications such
 * as Controllers and Test Cases
 * */

public class NoteRepository {

	/* Declare a variable called "list" to store all the notes. */
	List<Note> notesList;

	public NoteRepository() {
		this.notesList = new ArrayList<Note>();
		/* Initialize the variable using proper data type */
	}

	/* This method should return all the notes in the list */

	public List<Note> getList() {
		return this.notesList;
	}

	/* This method should set the list variable with new list of notes */

	public void setList(final List<Note> list) {
		this.notesList = list;
	}

	/*
	 * This method should Note object as argument and add the new note object into
	 * list
	 */

	public void addNote(final Note note) {
		note.setCreatedAt(LocalDateTime.now());
		this.notesList.add(note);
	}

	/* This method should deleted a specified note from the list */

	public boolean deleteNote(final int noteId) {
		/* Use list iterator to find matching note id and remove it from the list */
		Note deleteNote = null;
		boolean flag = false;
		for (Note note : notesList) {
			if (note.getNoteId() == noteId) {
				deleteNote = note;
				break;
			}
		}
		if(null != deleteNote) {
			this.notesList.remove(deleteNote);
			flag=  true;
		}
		return flag;
	}

	/* This method should return the list of notes */

	public List<Note> getAllNotes() {
		return this.notesList;
	}

	/*
	 * This method should check if the matching note id present in the list or not.
	 * Return true if note id exists in the list or return false if note id does not
	 * exists in the list
	 */

	public boolean exists(final int noteId) {
		boolean flag = false;
		for (Note note : notesList) {
			if (note.getNoteId() == noteId) {
				flag =  true;
			}
		}
		return flag;
	}
}