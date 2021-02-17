package hr.fer.oprpp1.hw08.jnotepadpp;
/**
 * @author Tomislav Lovrencic
 *
 * This interface represents single document listener.
 */
public interface SingleDocumentListener {
	/**
	 * This method is called when the document is modified.
	 * @param model
	 */
	void documentModifyStatusUpdated(SingleDocumentModel model);
	/**
	 * This method is called when the document file path is updated.
	 * @param model
	 */
	void documentFilePathUpdated(SingleDocumentModel model);
}
