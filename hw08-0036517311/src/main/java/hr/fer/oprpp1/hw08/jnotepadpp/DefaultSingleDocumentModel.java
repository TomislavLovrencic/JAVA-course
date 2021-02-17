package hr.fer.oprpp1.hw08.jnotepadpp;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

/**
 * @author Tomislav Lovrencic
 *
 * This class represents implementation of {@link SingleDocumentModel}.
 */
public class DefaultSingleDocumentModel implements SingleDocumentModel {

	/**
	 * Path of file.
	 */
	private Path filepath;
	/**
	 * Text of documents text component.
	 */
	private String textContent;
	/**
	 * Document's text component.
	 */
	private JTextArea textArea;

	/**
	 * Document's indicator of modification.
	 */
	private boolean modified;
	/**
	 * List of {@link SingleDocumentListener}.
	 */
	private List<SingleDocumentListener> listeners;

	/**
	 * This is a basic constructor.
	 * 
	 * @param filepath
	 * @param text
	 */
	public DefaultSingleDocumentModel(Path filepath, String text) {
		this.filepath = filepath;
		this.textContent = text;
		this.modified = false;
		this.listeners = new ArrayList<>();
		this.textArea = new JTextArea(textContent);
		Document doc = this.textArea.getDocument();
		doc.addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				setModified(true);

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				setModified(true);

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				setModified(true);
			}
		});
	}

	/**
	 * This method is used to notify all listeners that documents has been modified.
	 */
	public void notifyAllListenersStatusUpdated() {
		for (SingleDocumentListener l : listeners) {
			l.documentModifyStatusUpdated(this);
		}
	}

	/**
	 * This method is used to notify all listeners that documents file path has been
	 * updated.
	 */
	public void notifyAllListeners() {
		for (SingleDocumentListener l : listeners) {
			l.documentFilePathUpdated(this);
		}
	}

	@Override
	public JTextArea getTextComponent() {
		return textArea;
	}

	@Override
	public Path getFilePath() {
		return filepath;
	}

	@Override
	public void setFilePath(Path path) {
		this.filepath = path;
		notifyAllListeners();

	}

	@Override
	public boolean isModified() {
		return modified;
	}

	@Override
	public void setModified(boolean modified) {
		this.modified = modified;
		notifyAllListenersStatusUpdated();
	}

	@Override
	public void addSingleDocumentListener(SingleDocumentListener l) {
		this.listeners.add(l);

	}

	@Override
	public void removeSingleDocumentListener(SingleDocumentListener l) {
		this.listeners.remove(l);

	}

}
