package hr.fer.oprpp1.hw08.jnotepadpp;
/**
 * @author Tomislav Lovrencic
 *
 * This interface represents multiple document listener.
 */
public interface MultipleDocumentListener {
	/**
	 * This method is called when the current document is changed.
	 * @param previousModel
	 * @param currentModel
	 */
	void currentDocumentChanged(SingleDocumentModel previousModel,
	SingleDocumentModel currentModel);
	/**
	 * This method is called when the document is added.
	 * @param model
	 */
	void documentAdded(SingleDocumentModel model);
	
	/**
	 * This method is called when the document is removed.
	 * @param model
	 */
	void documentRemoved(SingleDocumentModel model);
}