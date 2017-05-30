package awvillager.ui.util;

import java.awt.EventQueue;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.swing.JTextArea;

public class TextAreaOutputStream  extends OutputStream {

    private final ByteArrayOutputStream buf = new ByteArrayOutputStream();
    private final JTextArea textArea;
    public TextAreaOutputStream(JTextArea textArea) {
      super();
      this.textArea = textArea;
    }
    @Override public void flush() throws IOException {

        if (EventQueue.isDispatchThread()) {

            textArea.append(buf.toString("UTF-8"));
            textArea.setCaretPosition(textArea.getText().length());
            buf.reset();

        } else {

            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        textArea.append(buf.toString("UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    textArea.setCaretPosition(textArea.getText().length());
                    buf.reset();
                }
            });

        }

    }
    @Override public void write(int b) throws IOException {
      buf.write(b);
    }
  }

