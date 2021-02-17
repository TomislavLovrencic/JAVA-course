package hr.fer.oprpp1.hw08.jnotepadpp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Collator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.Document;
import javax.swing.text.Element;

import hr.fer.oprpp1.hw08.jnotepadpp.local.FormLocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.local.ILocalizationListener;
import hr.fer.oprpp1.hw08.jnotepadpp.local.LJLabel;
import hr.fer.oprpp1.hw08.jnotepadpp.local.LJMenu;
import hr.fer.oprpp1.hw08.jnotepadpp.local.LocalizableAction;
import hr.fer.oprpp1.hw08.jnotepadpp.local.LocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.local.StringLocal;

/**
 * @author Tomislav Lovrencic
 *
 * This class represents a notepad.
 *
 */
public class JNotepadPP extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * This is a form localization provider for this notepad.
	 */
	private FormLocalizationProvider flp = new FormLocalizationProvider(LocalizationProvider.getInstance(), this);

	/**
	 * This is a instance of {@link DefaultMultipleDocumentModel}.
	 */
	private DefaultMultipleDocumentModel model;

	/**
	 * This is a label for status bar length.
	 */
	private JLabel length = new JLabel("0");
	/**
	 * This is a label for status bar Ln.
	 */
	private JLabel ln = new JLabel("1");
	/**
	 * This is a label for status bar Col.
	 */
	private JLabel col = new JLabel("1");
	/**
	 * This is a label for status bar Sel.
	 */
	private JLabel sel = new JLabel("0");
	/**
	 * This is a changeCase menu.
	 */
	private JMenu changeCase;
	/**
	 * This is a sort menu.
	 */
	private JMenu sort;
	/**
	 * This is a unique menu.
	 */
	private JMenuItem unique;

	/**
	 * This is an instance of DateFormat.
	 */
	final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	/**
	 * This is a label dateTime used in status bar.
	 */
	private JLabel dateTime = new JLabel(dateFormat.format(System.currentTimeMillis()));

	/**
	 * This is a basic constructor.
	 */
	public JNotepadPP() {
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		model = new DefaultMultipleDocumentModel(flp);
		setSize(800, 600);
		setTitle("JNotepad++");
		initGui();

	}

	/**
	 * This method is used to initialize gui components.
	 */
	public void initGui() {
		getContentPane().setLayout(new BorderLayout());

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Calendar now = Calendar.getInstance();
						Thread.sleep(500);
						dateTime.setText(dateFormat.format(now.getTime()));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		});

		t.setDaemon(true);
		t.start();

		flp.addLocalizationListener(new ILocalizationListener() {

			@Override
			public void localizationChanged() {
				createActionsDescription();

			}

		});

		model.addMultipleDocumentListener(new MultipleDocumentListener() {

			@Override
			public void documentRemoved(SingleDocumentModel model) {

			}

			@Override
			public void documentAdded(SingleDocumentModel s) {
				if (s.getFilePath() != null) {
					model.getPane().setToolTipTextAt(model.getDocument(s), s.getFilePath().toString());

				} else {
					model.getPane().setToolTipTextAt(model.getDocument(s), "unnamed");
				}
				updateStatusBar(s);

				s.getTextComponent().addCaretListener(new CaretListener() {

					@Override
					public void caretUpdate(CaretEvent e) {
						String a = s.getTextComponent().getSelectedText();
						if (a == null) {

							unique.setEnabled(false);
							sort.setEnabled(false);
							changeCase.setEnabled(false);
						} else {
							unique.setEnabled(true);
							sort.setEnabled(true);
							changeCase.setEnabled(true);
						}
						updateStatusBar(s);

					}
				});

			}

			@Override
			public void currentDocumentChanged(SingleDocumentModel previousModel, SingleDocumentModel currentModel) {
				int index = model.getPane().getSelectedIndex();
				if (index != -1) {
					String name = "unnamed";
					if (model.getDocument(index).getFilePath() != null) {
						name = model.getDocument(index).getFilePath() + "";
					}
					setTitle("(" + name + ")" + " - JNotepad++");
					length.setText(Integer.toString(currentModel.getTextComponent().getText().getBytes().length));

				} else {
					setTitle("JNotepadd++");

				}
				if (index != -1 && index < model.getNumberOfDocuments()) {
					updateStatusBar(model.getDocument(index));

				}

			}
		});

		WindowListener wind = new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				boolean cancel = checkForModification();
				if (!cancel) {
					dispose();
				}

			}
		};

		this.addWindowListener(wind);

		initToolbar();

		initMenu();

		createActions();

		initStatusBar();

		getContentPane().add(model.getPane(), BorderLayout.CENTER);
	}

	/**
	 * This method is used to initialize status bar.
	 */
	private void initStatusBar() {
		JPanel statusBar = new JPanel();

		statusBar.setLayout(new GridLayout(1, 3));
		statusBar.setBorder(new LineBorder(Color.DARK_GRAY));
		JPanel helper = new JPanel();
		helper.setLayout(new FlowLayout(FlowLayout.LEFT));
		helper.add(new LJLabel("length", flp));
		helper.add(new JLabel(":"));
		helper.add(length);
		helper.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.gray));
		statusBar.add(helper);
		JPanel helper2 = new JPanel();
		helper2.setLayout(new FlowLayout(FlowLayout.LEFT));
		helper2.add(new LJLabel("Ln", flp));
		helper2.add(new JLabel(":"));
		helper2.add(ln);
		helper2.add(new LJLabel("Col", flp));
		helper2.add(new JLabel(":"));
		helper2.add(col);
		helper2.add(new LJLabel("Sel", flp));
		helper2.add(new JLabel(":"));
		helper2.add(sel);
		helper2.add(new JLabel());
		statusBar.add(helper2);
		JPanel time = new JPanel();
		time.setLayout(new FlowLayout(FlowLayout.RIGHT));
		time.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.gray));
		time.add(dateTime);
		statusBar.add(time);

		this.add(statusBar, BorderLayout.PAGE_END);

	}

	/**
	 * This is an action used to open document.
	 */
	private Action openDocumentAction = new LocalizableAction("open", flp) {
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser();
			fc.setDialogTitle(new StringLocal("openfile", flp).getText());
			if (fc.showOpenDialog(JNotepadPP.this) != JFileChooser.APPROVE_OPTION) {
				return;
			}

			File file = fc.getSelectedFile();
			if (!Files.isReadable(file.toPath())) {

				messageDialog(new StringLocal("fileNotReadable", flp).getText(),
						new StringLocal("warning", flp).getText(), file.getAbsolutePath(), "error.png");
				return;
			}

			model.loadDocument(file.toPath());

		}
	};

	/**
	 * This is an action used to save document.
	 */
	private Action saveDocumentAction = new LocalizableAction("save", flp) {

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (model.getCurrentDocument() == null)
				return;

			if (model.getCurrentDocument().getFilePath() == null) {
				JFileChooser jfc = new JFileChooser();
				jfc.setDialogTitle(new StringLocal("saveDocument", flp).getText());
				if (jfc.showSaveDialog(JNotepadPP.this) != JFileChooser.APPROVE_OPTION) {
					messageDialog(new StringLocal("fileNotSaved", flp).getText(),
							new StringLocal("warning", flp).getText(), null, "error.png");
					return;
				}

				saveAscheck(jfc.getSelectedFile().toPath());
			} else {
				File file = new File(model.getCurrentDocument().getFilePath().toString());

				if (!Files.isReadable(file.toPath())) {
					messageDialog(new StringLocal("fileNotFile", flp).getText(),
							new StringLocal("warning", flp).getText(), file.getAbsolutePath(), "error.png");
					return;
				}
				if (checkForPath(file.toPath()))
					return;
				model.saveDocument(model.getCurrentDocument(), file.toPath());
			}

		}
	};

	/**
	 * This is an action used to close selected tab.
	 */
	private Action closeTabAction = new LocalizableAction("closeTab", flp) {

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			int answer = -1;
			boolean cancel = false;
			if (model.getCurrentDocument() != null && model.getCurrentDocument().isModified()) {
				answer = optionDialog(new StringLocal("saveThisFile", flp).getText(),
						new StringLocal("unsavedFiles", flp).getText(),
						new String[] { new StringLocal("yes", flp).getText(), new StringLocal("no", flp).getText(),
								new StringLocal("cancel", flp).getText() },
						null, "error.png");

				if (answer == 2 || answer == -1) {
					cancel = true;
				}

				else if (answer == 0) {
					Action a = saveDocumentAction;
					a.actionPerformed(null);
				}
			}
			if (!cancel)
				model.closeDocument(model.getCurrentDocument());

		}
	};

	/**
	 * This is an Action used to cut selected text.
	 */
	private Action cutAction = new LocalizableAction("cut", flp) {

		private static final long serialVersionUID = 1L;
		Action a = new DefaultEditorKit.CutAction();

		@Override
		public void actionPerformed(ActionEvent e) {
			a.actionPerformed(e);

		}
	};

	/**
	 * This is an action used to copy selected text.
	 */
	private Action copyAction = new LocalizableAction("copy", flp) {

		private static final long serialVersionUID = 1L;

		Action a = new DefaultEditorKit.CopyAction();

		@Override
		public void actionPerformed(ActionEvent e) {
			a.actionPerformed(e);

		}
	};
	/**
	 * This is an action used to paste cut or copied text.
	 */
	private Action pasteAction = new LocalizableAction("paste", flp) {

		private static final long serialVersionUID = 1L;

		Action a = new DefaultEditorKit.PasteAction();

		@Override
		public void actionPerformed(ActionEvent e) {
			a.actionPerformed(e);

		}
	};
	/**
	 * This is an action used to show statistical info about document.
	 */
	private Action statAction = new LocalizableAction("statistic", flp) {

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			DefaultSingleDocumentModel df = (DefaultSingleDocumentModel) model.getCurrentDocument();
			if (df == null)
				return;

			int numberOfChars = df.getTextComponent().getText().length();
			int numberOfNonBlanks = df.getTextComponent().getText().replaceAll("\\s+", "").length();
			int numberOfLines = df.getTextComponent().getLineCount();

			String message = new StringLocal("numOfChar", flp).getText() + numberOfChars + "\n"
					+ new StringLocal("numOfNonBlank", flp).getText() + numberOfNonBlanks + "\n"
					+ new StringLocal("numOfLines", flp).getText() + numberOfLines;

			messageDialog(message, new StringLocal("statInfo", flp).getText(), null, "info.png");

		}
	};
	/**
	 * This is an action used to exit notepad.
	 */
	private Action exitAction = new LocalizableAction("exit", flp) {

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean cancel = checkForModification();
			if (!cancel) {
				dispose();
			}
		}

	};

	/**
	 * This is an action used to create new document.
	 */
	private Action createNewAction = new LocalizableAction("create", flp) {

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			model.createNewDocument();

		}
	};

	/**
	 * This is an action used to save as document.
	 */
	private Action saveAsDocumentAction = new LocalizableAction("saveAs", flp) {

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (model.getCurrentDocument() == null)
				return;
			JFileChooser jfc = new JFileChooser();
			jfc.setDialogTitle("Save document");
			if (jfc.showSaveDialog(JNotepadPP.this) != JFileChooser.APPROVE_OPTION) {
				messageDialog(new StringLocal("fileNotSaved", flp).getText(), new StringLocal("warning", flp).getText(),
						null, "error.png");
				return;
			}
			saveAscheck(jfc.getSelectedFile().toPath());

		}
	};

	/**
	 * This is an action used to set language to croatian.
	 */
	private Action hrvAction = new LocalizableAction("croatian", flp) {

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			LocalizationProvider.getInstance().setLanguage("hr");
		};
	};

	/**
	 * This is an action used to set language to English.
	 */
	private Action engAction = new LocalizableAction("english", flp) {

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			LocalizationProvider.getInstance().setLanguage("en");

		};
	};

	/**
	 * This is an action used to set language to german.
	 */
	private Action deAction = new LocalizableAction("german", flp) {

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			LocalizationProvider.getInstance().setLanguage("de");

		};
	};

	/**
	 * This is an action used to change case to upper for selected text.
	 */
	private Action upperCaseAction = new LocalizableAction("uppercase", flp) {
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			String a = model.getCurrentDocument().getTextComponent().getSelectedText();
			if (a != null)
				model.getCurrentDocument().getTextComponent().replaceSelection(a.toUpperCase());
		};
	};

	/**
	 * This is an action used to change case to lower for selected text.
	 */
	private Action lowerCaseAction = new LocalizableAction("lowercase", flp) {
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			String a = model.getCurrentDocument().getTextComponent().getSelectedText();
			if (a != null)
				model.getCurrentDocument().getTextComponent().replaceSelection(a.toLowerCase());

		};
	};

	/**
	 * This is an action used to invert case for selected text.
	 */
	private Action invertCaseAction = new LocalizableAction("invertcase", flp) {
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			String a = model.getCurrentDocument().getTextComponent().getSelectedText();
			if (a == null)
				return;
			String b = "";
			for (int i = 0; i < a.length(); i++) {

				if (Character.isUpperCase(a.charAt(i))) {
					b += Character.toLowerCase(a.charAt(i));
				} else if (Character.isLowerCase(a.charAt(i))) {
					b += Character.toUpperCase(a.charAt(i));
				}
			}
			model.getCurrentDocument().getTextComponent().replaceSelection(b);

		};
	};

	/**
	 * This is an action used to sort selected text in ascending way.
	 */
	private Action ascendingAction = new LocalizableAction("ascending", flp) {
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			uniqueAscAndDesc(1);
		}

	};
	/**
	 * This is an action used to sort selected text in descending way.
	 */
	private Action descendingAction = new LocalizableAction("descending", flp) {
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			uniqueAscAndDesc(2);
		};
	};

	/**
	 * This is an action used to delete lines that repeat in selected text.
	 */
	private Action uniqueAction = new LocalizableAction("unique", flp) {
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			uniqueAscAndDesc(0);

		};
	};

	/**
	 * This method is used to sort text or to remove duplicate lines depending on
	 * given value.
	 * 
	 * @param value
	 */
	public void uniqueAscAndDesc(int value) {
		if (model.getCurrentDocument() == null)
			return;

		Locale locale = new Locale(LocalizationProvider.getInstance().getLanguage());
		Collator collator = Collator.getInstance(locale);

		JTextArea editor = model.getCurrentDocument().getTextComponent();
		Document doc = editor.getDocument();

		if (editor.getSelectedText() == null)
			return;

		int line1 = 0;
		int line2 = 0;
		String selectedText = null;
		try {
			Element root = doc.getDefaultRootElement();
			line1 = root.getElementIndex(editor.getCaret().getMark());
			line2 = root.getElementIndex(editor.getCaret().getDot());

			int offset1 = root.getElement(line1 > line2 ? line2 : line1).getStartOffset();
			int offset2 = root.getElement(line1 > line2 ? line1 : line2).getEndOffset();

			selectedText = doc.getText(offset1, offset2 - offset1);

			String[] l = selectedText.split("\n");
			List<String> lines = new ArrayList<>();
			for (String elem : l)
				lines.add(elem);

			String result = "";

			if (value == 0) {
				for (int i = 0; i < lines.size(); i++) {
					for (int j = lines.size() - 1; j >= i + 1; j--) {
						if (lines.get(i).trim().equals(lines.get(j).trim())) {
							lines.remove(j);
						}
					}
				}
			} else {
				if(offset2 == doc.getEndPosition().getOffset()) {
					doc.remove(offset1, (offset2-1) - offset1);
				}
				else {
					doc.remove(offset1, offset2 - offset1);
				}
				doc.insertString(offset1, sorting(selectedText, collator, value == 1 ? true : false), null);
				return;
			}

			for (String elem : lines) {
				result += elem + "\n";
			}

			if(offset2 == doc.getEndPosition().getOffset()) {
				doc.remove(offset1, (offset2-1) - offset1);
			}
			else {
				doc.remove(offset1, offset2 - offset1);
			}
			doc.insertString(offset1, result, null);

		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}

	};

	/**
	 * This method is used to sort selected text in ascending or descending way
	 * depending on given asc value.
	 * 
	 * @param selectedText
	 * @param collator
	 * @param asc
	 * @return
	 */
	public String sorting(String selectedText, Collator collator, boolean asc) {
		String[] words = selectedText.split("\\s+");
		String[] lines = selectedText.split("\n");
		Integer[] numOfWords = new Integer[lines.length];

		for (int i = 0; i < lines.length; i++) {
			numOfWords[i] = findNumber(lines[i]);
		}

		List<String> words1 = Arrays.asList(words);
		if (asc) {
			words1.sort(collator);
		} else {
			words1.sort(collator.reversed());
		}

		String result = "";
		int brojac = 0;
		for (int i = 0; i < lines.length; i++) {
			for (int j = 0; j < numOfWords[i]; j++) {
				result += words1.get(brojac) + " ";
				brojac++;
			}
			result += "\n";
		}
		return result;
	}


	/**
	 * This method is used to find number of words in given string.
	 * 
	 * @param word
	 * @return
	 */
	public int findNumber(String word) {
		int index = 0;
		int num = 0;
		while (index < word.length()) {

			if (word.charAt(index) != ' ') {
				while (word.charAt(index) != ' ') {
					index++;
					if (index == word.length())
						break;

				}
				num++;
			} else {
				index++;
			}

			if (index == word.length())
				break;

		}
		return num;
	}

	/**
	 * This method is used to save this document.
	 * 
	 * @param path
	 */
	public void saveAscheck(Path path) {

		if (checkForPath(path))
			return;

		int answer = -2;
		if (Files.isReadable(path)) {
			answer = optionDialog(new StringLocal("overwriteThisFile", flp).getText(),
					new StringLocal("fileAlreadyExists", flp).getText(),
					new String[] { new StringLocal("yes", flp).getText(), new StringLocal("no", flp).getText(),
							new StringLocal("cancel", flp).getText() },
					null, "error.png");

		}
		if (answer == 0 || answer == -2) {
			model.saveDocument(model.getCurrentDocument(), path);
		}
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ImageIcon(bytes);
	}

	/**
	 * This method is used to check for modification before closing the program.
	 * 
	 * @return
	 */
	public boolean checkForModification() {
		for (SingleDocumentModel elem : model) {
			int answer = -1;
			if (elem.isModified()) {
				String fil = elem.getFilePath() == null ? "unnamed" : elem.getFilePath().getFileName().toString();
				answer = optionDialog(new StringLocal("saveThisFile", flp).getText(),
						new StringLocal("unsavedFiles", flp).getText(),
						new String[] { new StringLocal("yes", flp).getText(), new StringLocal("no", flp).getText(),
								new StringLocal("cancel", flp).getText() },
						fil, "error.png");
				if (answer == 2 || answer == -1) {
					return true;
				}

				if (answer == 0) {
					Action a = saveDocumentAction;
					a.actionPerformed(null);
				}

			}
		}
		return false;

	}

	/**
	 * This method is used to check if the document is already opened.
	 * 
	 * @param path
	 * @return
	 */
	public boolean checkForPath(Path path) {
		for (SingleDocumentModel elem : model) {
			if (elem.getFilePath() != null && !elem.equals(model.getCurrentDocument())
					&& elem.getFilePath().equals(path)) {
				messageDialog(new StringLocal("fileAlreadyOpened", flp).getText(),
						new StringLocal("warning", flp).getText(), null, "error.png");
				return true;
			}
		}
		return false;
	}

	/**
	 * This method is used to create and name actions.
	 * 
	 */
	private void createActions() {

		openDocumentAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control O"));
		openDocumentAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_O);

		saveDocumentAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control S"));
		saveDocumentAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);

		cutAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control X"));
		cutAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_X);

		copyAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control C"));
		copyAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_C);

		pasteAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control V"));
		pasteAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_V);

		createNewAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control N"));
		createNewAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_N);

		saveAsDocumentAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control shift S"));
		saveAsDocumentAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_F1);

		closeTabAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control W"));
		closeTabAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_W);

		statAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("F1"));
		statAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_F1);

		exitAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control Q"));
		exitAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_Q);

		createActionsDescription();

	}

	/**
	 * This method is used to create action descriptions.
	 */
	private void createActionsDescription() {
		openDocumentAction.putValue(Action.SHORT_DESCRIPTION, flp.getString("openDocumentAction"));
		saveDocumentAction.putValue(Action.SHORT_DESCRIPTION, flp.getString("saveDocumentAction"));
		cutAction.putValue(Action.SHORT_DESCRIPTION, flp.getString("cutAction"));
		copyAction.putValue(Action.SHORT_DESCRIPTION, flp.getString("copyAction"));
		pasteAction.putValue(Action.SHORT_DESCRIPTION, flp.getString("pasteAction"));
		createNewAction.putValue(Action.SHORT_DESCRIPTION, flp.getString("createNewAction"));
		saveAsDocumentAction.putValue(Action.SHORT_DESCRIPTION, flp.getString("saveAsDocumentAction"));
		closeTabAction.putValue(Action.SHORT_DESCRIPTION, flp.getString("closeTabAction"));
		statAction.putValue(Action.SHORT_DESCRIPTION, flp.getString("statAction"));
		exitAction.putValue(Action.SHORT_DESCRIPTION, flp.getString("exitAction"));
		hrvAction.putValue(Action.SHORT_DESCRIPTION, flp.getString("hrvAction"));
		engAction.putValue(Action.SHORT_DESCRIPTION, flp.getString("engAction"));
		deAction.putValue(Action.SHORT_DESCRIPTION, flp.getString("deAction"));
		upperCaseAction.putValue(Action.SHORT_DESCRIPTION, flp.getString("upperCaseAction"));
		lowerCaseAction.putValue(Action.SHORT_DESCRIPTION, flp.getString("lowerCaseAction"));
		invertCaseAction.putValue(Action.SHORT_DESCRIPTION, flp.getString("invertCaseAction"));
		ascendingAction.putValue(Action.SHORT_DESCRIPTION, flp.getString("ascendingAction"));
		descendingAction.putValue(Action.SHORT_DESCRIPTION, flp.getString("descendingAction"));
		uniqueAction.putValue(Action.SHORT_DESCRIPTION, flp.getString("uniqueAction"));

	}

	/**
	 * This method is used to update status bar.
	 * 
	 * @param s
	 */
	private void updateStatusBar(SingleDocumentModel s) {
		length.setText(Integer.toString(s.getTextComponent().getText().getBytes().length));

		int pos = s.getTextComponent().getCaretPosition();

		String sText = s.getTextComponent().getSelectedText();
		int b = sText == null ? 0 : sText.length();

		try {
			int line = s.getTextComponent().getLineOfOffset(pos);
			ln.setText(Integer.toString(line + 1));
			col.setText(Integer.toString(1 + (pos - s.getTextComponent().getLineStartOffset(line))));
			sel.setText(Integer.toString(b));
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}

	}

	/**
	 * This method is used to show localized message dialog.
	 * 
	 * @param a
	 * @param b
	 * @param file
	 * @param image
	 */
	public void messageDialog(String a, String b, String file, String image) {
		JOptionPane.showMessageDialog(getContentPane(), file != null ? a + "(" + file + ")" : a, b,
				JOptionPane.INFORMATION_MESSAGE, getImageIcon(image));
	}

	/**
	 * This method is used to show localized option dialog.
	 * 
	 * @param a
	 * @param b
	 * @param opt
	 * @param file
	 * @param image
	 * @return
	 */
	public int optionDialog(String a, String b, String[] opt, String file, String image) {
		String[] options = opt;

		int x = JOptionPane.showOptionDialog(getContentPane(), file != null ? a + "(" + file + ")" : a, b,
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, getImageIcon(image), options, options[0]);
		return x;
	}

	/**
	 * This method is used to initialize menu.
	 * 
	 */
	private void initMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new LJMenu("file", flp);
		fileMenu.add(new JMenuItem(createNewAction));
		fileMenu.add(new JMenuItem(openDocumentAction));
		fileMenu.add(new JMenuItem(saveDocumentAction));
		fileMenu.add(new JMenuItem(saveAsDocumentAction));
		fileMenu.add(new JMenuItem(closeTabAction));
		fileMenu.add(new JMenuItem(statAction));
		fileMenu.addSeparator();
		fileMenu.add(new JMenuItem(exitAction));
		menuBar.add(fileMenu);

		JMenu editMenu = new LJMenu("edit", flp);
		editMenu.add(new JMenuItem(cutAction));
		editMenu.add(new JMenuItem(copyAction));
		editMenu.add(new JMenuItem(pasteAction));
		menuBar.add(editMenu);

		JMenu localizationMenu = new LJMenu("language", flp);
		localizationMenu.add(new JMenuItem(hrvAction));
		localizationMenu.add(new JMenuItem(engAction));
		localizationMenu.add(new JMenuItem(deAction));
		menuBar.add(localizationMenu);

		JMenu tools = new LJMenu("tools", flp);
		changeCase = new LJMenu("changeCase", flp);
		changeCase.setEnabled(false);
		changeCase.add(new JMenuItem(upperCaseAction));
		changeCase.add(new JMenuItem(lowerCaseAction));
		changeCase.add(new JMenuItem(invertCaseAction));
		tools.add(changeCase);

		sort = new LJMenu("sort", flp);
		sort.setEnabled(false);
		sort.add(new JMenuItem(ascendingAction));
		sort.add(new JMenuItem(descendingAction));
		tools.add(sort);
		unique = new JMenuItem(uniqueAction);
		unique.setEnabled(false);
		tools.add(unique);
		menuBar.add(tools);

		this.setJMenuBar(menuBar);

	}

	/**
	 * This method is used to initialize tool bar.
	 * 
	 */
	private void initToolbar() {
		JToolBar toolbar = new JToolBar();
		toolbar.add(new JButton(createNewAction));
		toolbar.add(new JButton(openDocumentAction));
		toolbar.add(new JButton(saveDocumentAction));
		toolbar.add(new JButton(saveAsDocumentAction));
		toolbar.add(new JButton(closeTabAction));
		toolbar.add(new JButton(cutAction));
		toolbar.add(new JButton(copyAction));
		toolbar.add(new JButton(pasteAction));
		toolbar.add(new JButton(statAction));
		toolbar.add(new JButton(exitAction));

		getContentPane().add(toolbar, BorderLayout.PAGE_START);

	}

	/**
	 * This is a main method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				LocalizationProvider.getInstance().setLanguage("hr");
				JNotepadPP notepad = new JNotepadPP();
				notepad.setVisible(true);
			}
		});
	}
}
