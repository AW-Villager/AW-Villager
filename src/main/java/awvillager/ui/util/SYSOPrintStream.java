package awvillager.ui.util;

import java.io.PrintStream;

import org.apache.logging.log4j.Logger;

public class SYSOPrintStream extends PrintStream {

    private Logger logger;

    public SYSOPrintStream(Logger logger, PrintStream ps) {
        super(ps);
        this.logger = logger;
    }

    @Override
    public void println(Object o) {
        logger.info(getPrefix() + o);
    }

    @Override
    public void println(String s) {
        logger.info(getPrefix() + s);
    }

    private String getPrefix() {
        StackTraceElement[] elems = Thread.currentThread().getStackTrace();
        StackTraceElement elem = elems[3];
        if (elem.getClassName().startsWith("java.lang.Throwable")) {
            elem = elems[3 + 4];
        }
        return "[" + elem.getClassName() + ":" + elem.getMethodName() + ":" + elem.getLineNumber() + "]: ";
    }

}
