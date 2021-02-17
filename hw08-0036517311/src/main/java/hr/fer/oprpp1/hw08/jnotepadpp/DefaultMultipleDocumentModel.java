package hr.fer.oprpp1.hw08.jnotepadpp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import hr.fer.oprpp1.hw08.jnotepadpp.local.FormLocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.local.StringLocal;

/**
 * @author Tomislav Lovrencic
 *
 * This class is a implementation of {@link MultipleDocumentModel}.
 *
 */
public class DefaultMultipleDocumentModel extends JTabbedPane implements MultipleDocumentModel {

	/**
	 * List of {@link MultipleDocumentListener}.
	 */
	private List<MultipleDocumentListener> listeners;

	/**
	 * List of {@link DefaultSingleDocumentModel}.
	 */
	private List<DefaultSingleDocumentModel> documents;
	/**
	 * Index of document.
	 */
	private int indexOfDoc;
	/**
	 * JTabbedPane used for this model.
	 */
	private JTabbedPane pane;
	/**
	 * ImageIcon for elements in pane.
	 */
	private ImageIcon icon;
	/**
	 * Form localization provider for this model.
	 */
	private FormLocalizationProvider flp;

	private static final long serialVersionUID = 1L;

	/**
	 * This is a basic constructor for this model.
	 * 
	 * @param flp
	 */
	public DefaultMultipleDocumentModel(FormLocalizationProvider flp) {
		this.flp = flp;
		this.listeners = new ArrayList<>();
		this.documents = new ArrayList<>();
		this.pane = new JTabbedPane();
		this.indexOfDoc = 0;
		this.icon = getImageIcon("greenD.png");
		this.pane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				notifyListenersDocumentChanged(getCurrentDocument());

			}
		});

	}

	/**
	 * This is a getter for JTabbedPane.
	 * 
	 * @return
	 */
	public JTabbedPane getPane() {
		return this.pane;
	}

	/**
	 * This method is used to get index of this given document.
	 * 
	 * @param s
	 * @return
	 */
	public int getDocument(SingleDocumentModel s) {
		int index = 0;
		for (SingleDocumentModel elem : documents) {
			if (s.equals(elem)) {
				return index;
			}
			index++;
		}
		return index;
	}

	/**
	 * This method is used to notify all listeners that document has been added.
	 * 
	 * @param s
	 */
	public void notifyListenersDocumentAdded(SingleDocumentModel s) {
		for (MultipleDocumentListener listener : listeners) {
			listener.documentAdded(s);
		}
	}

	/**
	 * This method is used to notify all listeners that document has been changed.
	 * 
	 * @param s
	 */
	public void notifyListenersDocumentChanged(SingleDocumentModel s) {
		for (MultipleDocumentListener listener : listeners) {
			listener.currentDocumentChanged(null, s);
		}
	}

	@Override
	public Iterator<SingleDocumentModel> iterator() {
		this.indexOfDoc = 0;

		Iterator<SingleDocumentModel> iterator = new Iterator<>() {

			@Override
			public boolean hasNext() {
				return indexOfDoc < documents.size();

			}

			@Override
			public SingleDocumentModel next() {
				SingleDocumentModel doc = documents.get(indexOfDoc);
				indexOfDoc++;
				return doc;
			}

		};
		return iterator;
	}

	@Override
	public SingleDocumentModel createNewDocument() {
		DefaultSingleDocumentModel model = new DefaultSingleDocumentModel(null, "");
		documents.add(model);
		JScrollPane scroll = new JScrollPane(model.getTextComponent());
		icon = getImageIcon("redD.png");
		model.setModified(true);
		pane.addTab("unnamed", icon, scroll);
		icon = getImageIcon("greenD.png");
		pane.setSelectedIndex(getDocument(model));
		notifyListenersDocumentAdded(model);
		addListener(model);
		return model;
	}

	@Override
	public SingleDocumentModel getCurrentDocument() {
		int sel = pane.getSelectedIndex();
		if (sel != -1) {
			return documents.get(sel);
		}
		return null;
	}

	/**
	 * This method is used to get index of selected element in JTabbedPane.
	 * 
	 * @return
	 */
	public int getDoc() {
		return this.pane.getSelectedIndex();
	}

	@Override
	public SingleDocumentModel loadDocument(Path path) {
		DefaultSingleDocumentModel m = null;

		File file = new File(path.toString());
		for (SingleDocumentModel elem : this) {
			if (elem.getFilePath() != null && elem.getFilePath().equals(path)) {
				m = (DefaultSingleDocumentModel) elem;
				this.pane.setSelectedIndex(getDocument(m));
				return m;
			}
		}

		byte[] okteti = null;
		try {
			okteti = Files.readAllBytes(file.toPath());
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(this,
					new StringLocal("somethingWentWrong", flp).getText() + "(" + file.getAbsolutePath() + ")",
					new StringLocal("error", flp).getText(), JOptionPane.ERROR_MESSAGE, getImageIcon("error.png"));
		}

		String tekst = new String(okteti, StandardCharsets.UTF_8);
		m = new DefaultSingleDocumentModel(path, tekst);
		JScrollPane scroll = new JScrollPane(m.getTextComponent());
		documents.add(m);
		pane.addTab(m.getFilePath().getFileName().toString(), icon, scroll);
		pane.setSelectedIndex(getDocument(m));

		notifyListenersDocumentAdded(m);
		addListener(m);
		return m;
	}

	/**
	 * This method is used to add a listener to given model.
	 * 
	 * @param m
	 */
	private void addListener(DefaultSingleDocumentModel m) {

		m.addSingleDocumentListener(new SingleDocumentListener() {

			@Override
			public void documentModifyStatusUpdated(SingleDocumentModel model) {
				if (m.isModified()) {
					pane.setIconAt(pane.getSelectedIndex(), getImageIcon("redD.png"));
				} else {
					pane.setIconAt(pane.getSelectedIndex(), getImageIcon("greenD.png"));
				}
			}

			@Override
			public void documentFilePathUpdated(SingleDocumentModel model) {
				pane.setToolTipTextAt(getDocument(model), model.getFilePath().toString());
				pane.setTitleAt(getDocument(model), model.getFilePath().getFileName().toString());
			}
		});

	}

	@Override
	public void saveDocument(SingleDocumentModel model, Path newPath) {
		JTextArea editor = model.getTextComponent();

		byte[] okteti = editor.getText().getBytes();

		if (newPath == null) {
			return;
		}
		try {
			Files.write(newPath, okteti);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(this,
					new StringLocal("somethingWentWrong", flp).getText() + "(" + newPath + ")",
					new StringLocal("error", flp).getText(), JOptionPane.ERROR_MESSAGE, getImageIcon("error.png"));
		}

		model.setModified(false);
		model.setFilePath(newPath);
		notifyListenersDocumentChanged(model);

		JOptionPane.showMessageDialog(this, new StringLocal("fileSuccessfullySaved", flp).getText(),
				new StringLocal("Success", flp).getText(), JOptionPane.INFORMATION_MESSAGE,
				getImageIcon("success.png"));
	}

	@Override
	public void closeDocument(SingleDocumentModel model) {
		int i = pane.getSelectedIndex();
		if (i != -1) {
			pane.remove(i);
			documents.remove(i);

		}

	}

	@Override
	public void addMultipleDocumentListener(MultipleDocumentListener l) {
		this.listeners.add(l);

	}

	@Override
	public void removeMultipleDocumentListener(MultipleDocumentListener l) {
		this.listeners.remove(l);

	}

	@Override
	public int getNumberOfDocuments() {
		return documents.size();
	}

	@Override
	public SingleDocumentModel getDocument(int index) {
		return documents.get(index);
	}

	/**
	 * This method is used to get image icon.
	 * 
	 * @param a
	 * @return
	 */
	public ImageIcon getImageIcon(String a) {
		InputStream is = this.getClass().getResourceAsStream("icons/" + a);
		if (is == null) {
			System.out.println("File has not been found!");
			System.exit(1);
		}
		byte[] bytes = null;
		try {
			bytes = is.readAllBytes();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ImageIcon(bytes);
	}

}
