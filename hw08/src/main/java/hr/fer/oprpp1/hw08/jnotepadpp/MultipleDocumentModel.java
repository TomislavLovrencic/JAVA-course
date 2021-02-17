package hr.fer.oprpp1.hw08.jnotepadpp;

import java.nio.file.Path;

/**
 * @author Tomislav Lovrencic
 *
 * This interface represents multiple document model functionality.
 */
public interface MultipleDocumentModel extends Iterable<SingleDocumentModel> {
	/**
	 * This method is used to create new document.
	 * @return
	 */
	SingleDocumentModel createNewDocument();
	/**
	 * This method is used to get current document.
	 * @return
	 */
	SingleDocumentModel getCurrentDocument();
	/**
	 * This method is used to load a document.
	 * @param path
	 * @return
	 */
	SingleDocumentModel loadDocument(Path path);
	/**
	 * This method is used to save a document.
	 * @param model
	 * @param newPath
	 */
	void saveDocument(SingleDocumentModel model, Path newPath);
	/**
	 * This method is used to close a document.
	 * @param model
	 */
	void closeDocument(SingleDocumentModel model);
	/**
	 * This method is used to add {@link MultipleDocumentListener}.
	 * @param l
	 */
	void addMultipleDocumentListener(MultipleDocumentListener l);
	/**
	 * This method is used to remove {@link MultipleDocumentListener}
	 * @param l
	 */
	void removeMultipleDocumentListener(MultipleDocumentListener l);
	/**
	 * This method is used to get number of documents.
	 * @return
	 */
	int getNumberOfDocuments();
	/**
	 * This method is used to get a document on given index.
	 * @param index
	 * @return
	 */
	SingleDocumentModel getDocument(int index);
}