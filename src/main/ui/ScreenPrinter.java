package ui;

import exceptions.LogException;
import model.EventLog;

import javax.swing.*;

/**
 * Represents a screen printer for printing event log to screen.
 */

public class ScreenPrinter extends JInternalFrame implements LogPrinter {

    @Override
    public void printLog(EventLog el) throws LogException {
        //stub
    }
}
