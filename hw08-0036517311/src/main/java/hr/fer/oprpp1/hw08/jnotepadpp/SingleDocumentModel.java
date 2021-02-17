package hr.fer.oprpp1.hw08.jnotepadpp;

import java.nio.file.Path;

import javax.swing.JTextArea;

/**
 * @author Tomislav Lovrencic
 *
 * This interface represents single document model functionality.
 */
public interface SingleDocumentModel {
	/**
	 * This method is used to get text component of a document.
	 * @return
	 */
	JTextArea getTextComponent();
	/**
	 * This method is used to get file path of this document.
	 * @return
	 */
	Path getFilePath();
	
	/**
	 * This method is used to set file path.
	 * @param path
	 */
	void setFilePath(Path path);
	/**
	 * This method is used to check if the document is modified.
	 * @return
	 */
	boolean isModified();
	/**
	 * This method is used to set modification of this document.
	 * @param modified
	 */
	void setModified(boolean modified);
	/**
	 * This method is used to add {@link SingleDocumentListener}.
	 * @param l
	 */
	void addSingleDocumentListener(SingleDocumentListener l);
	/**
	 * This method is used to remove {@link SingleDocumentListener}.
	 * @param l
	 */
	void removeSingleDocumentListener(SingleDocumentListener l);
}