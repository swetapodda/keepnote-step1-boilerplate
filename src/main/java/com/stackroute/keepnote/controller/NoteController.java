package com.stackroute.keepnote.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stackroute.keepnote.model.Note;
import com.stackroute.keepnote.repository.NoteRepository;

/*Annotate the class with @Controller annotation. @Controller annotation is used to mark 
 * any POJO class as a controller so that Spring can recognize this class as a Controller
 * */
@Controller
public class NoteController {
	/*
	 * From the problem statement, we can understand that the application
	 * requires us to implement the following functionalities.
	 * 
	 * 1. display the list of existing notes from the collection. Each note 
	 *    should contain Note Id, title, content, status and created date.
	 * 2. Add a new note which should contain the note id, title, content and status.
	 * 3. Delete an existing note.
	 * 4. Update an existing note.
	 */
	
	/* 
	 * Get the application context from resources/beans.xml file using ClassPathXmlApplicationContext() class.
	 * Retrieve the Note object from the context.
	 * Retrieve the NoteRepository object from the context.
	 */
	public static ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

	private Note note = ctx.getBean("note", Note.class);
	private NoteRepository noteRepository = ctx.getBean("noteRepository", NoteRepository.class);
	
	/*Define a handler method to read the existing notes by calling the getAllNotes() method 
	 * of the NoteRepository class and add it to the ModelMap which is an implementation of Map 
	 * for use when building model data for use with views. it should map to the default URL i.e. "/" */
	
	@GetMapping("/")
	public String getNotes(final ModelMap modelMap) {
		modelMap.addAttribute("notesList", this.noteRepository.getAllNotes());
		return "index";
	}
	/*Define a handler method which will read the Note data from request parameters and
	 * save the note by calling the addNote() method of NoteRepository class. Please note 
	 * that the createdAt field should always be auto populated with system time and should not be accepted 
	 * from the user. Also, after saving the note, it should show the same along with existing 
	 * notes. Hence, reading notes has to be done here again and the retrieved notes object 
	 * should be sent back to the view using ModelMap.
	 * This handler method should map to the URL "/saveNote". 
	*/
	
	@RequestMapping("/saveNote")
	public String saveNote(@ModelAttribute("note") final Note note, final ModelMap modelMap) {
		if (null != note) {
			if (note.getNoteId() > 0 && null != note.getNoteTitle() && note.getNoteTitle().trim().length() > 0
					&& null != note.getNoteContent() && note.getNoteContent().trim().length() > 0
					&& null != note.getNoteStatus() && note.getNoteStatus().trim().length() > 0) {

				if (this.noteRepository.exists(note.getNoteId())) {
					modelMap.addAttribute("error", "Note ID already exists");
				} else {
					this.noteRepository.addNote(note);
				}
			} else {
				modelMap.addAttribute("error", "Please fill out the required fields");
			}
		}
		modelMap.addAttribute("notesList", this.noteRepository.getAllNotes());
		
		return "index";
	}
	/* Define a handler method to delete an existing note by calling the deleteNote() method 
	 * deleteNote() method of the NoteRepository class This handler method should
	 * map to the URL "/deleteNote"
	 */
	@RequestMapping("/deleteNote")
	public String deleteNote(@RequestParam final int noteId,final ModelMap modelMap) {
		this.noteRepository.deleteNote(noteId);
		return "redirect:/";
	}

	
}