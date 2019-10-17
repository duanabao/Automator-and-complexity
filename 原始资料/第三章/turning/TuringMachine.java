// Applet Turing Machine Simulator
// Suzanne Skinner
// 1996, All Rights Reserved

import java.awt.*;

class Transition {
  int  currentState;
  char currentChar;
  int  newState;
  char newChar;
  int  direction;

  public Transition(int cs, char cc, int ns, char nc, int dir) {
    currentState = cs;
    currentChar = cc;
    newState = ns;
    newChar = nc;
    direction = dir;
  }
}

class PresetTM {
  public static final String[] names =
    { "<New>",
      "Subtracter",
      "Palindrome Detector",
      "5-State B.B. (501)",
      "5-State B.B. (1915)",
      "5-State B.B. (4098)",
      "6-State Busy Beaver",
    };
  int initPos;
  String initChars;
  String programming;
  String comment;

  public PresetTM(String desc) {
    if (desc.indexOf("Busy Beaver") > -1 || desc.indexOf("B.B.") > -1) {
      initPos = TM.TAPESIZE/2;
      initChars = "";
      comment = "";
      if (desc.indexOf("501") > -1) {
        programming = "1,_  2,1,>\n1,1  3,_,<\n" +
                      "2,_  3,1,>\n2,1  4,1,>\n" +
                      "3,_  1,1,<\n3,1  2,_,>\n" +
                      "4,_  5,_,>\n4,1  H,1,>\n" +
                      "5,_  3,1,<\n5,1  1,1,>";
      }
      else if (desc.indexOf("1915") > -1) {
        programming = "1,_  2,1,>\n1,1  3,1,<\n" +
                      "2,_  1,_,<\n2,1  4,_,<\n" +
                      "3,_  1,1,<\n3,1  H,1,<\n" +
                      "4,_  2,1,<\n4,1  5,1,>\n" +
                      "5,_  4,_,>\n5,1  2,_,>";
      }
      else if (desc.indexOf("4098") > -1) {
        programming = "1,_  2,1,<\n1,1  3,1,>\n" +
                      "2,_  3,1,<\n2,1  2,1,<\n" +
                      "3,_  4,1,<\n3,1  5,_,>\n" +
                      "4,_  1,1,>\n4,1  4,1,>\n" +
                      "5,_  H,1,<\n5,1  1,_,>";
      }
      else if (desc.indexOf("6-State") > -1) {
        programming = "1,_  2,1,<\n1,1  1,1,<\n" +
                      "2,_  3,1,>\n2,1  2,1,>\n" +
                      "3,_  6,_,>\n3,1  4,1,>\n" +
                      "4,_  1,1,<\n4,1  5,_,>\n" +
                      "5,_  1,_,<\n5,1  3,1,>\n" +
                      "6,_  5,1,<\n6,1  H,1,<";
      }
      else
        initPos = -1;
    }
    else if (desc.equals("Subtracter")) {
      initPos = 0;
      initChars = "111111-1111=";
      comment = "Subtracts numbers in unary.\nInput format: '<num>-<num>='";
      programming = "1,_  1,_,>\n1,1  1,1,>\n1,-  1,-,>\n1,=  2,_,<\n" +
                    "2,1  3,=,<\n2,-  H,_,<\n" +
                    "3,1  3,1,<\n3,-  4,-,<\n" +
                    "4,_  4,_,<\n4,1  1,_,>";
    }
    else if (desc.equals("Palindrome Detector")) {
      initPos = 0;
      initChars = " BABBBAABBBAB";
      comment = "Determines whether a given string of\n"+
                "A's and B's is a palindrome.\n"+
                "Leave one blank space before the string.";
      programming = "1,_ 2,#,>\n" +
                    "2,A 3,_,>\n2,B 4,_,>\n2,_ 7,_,<\n" +
                    "3,A 3,A,>\n3,B 3,B,>\n3,_ 5,_,<\n" +
		    "4,A 4,A,>\n4,B 4,B,>\n4,_ 6,_,<\n" +
		    "5,A 11,_,<\n5,B 12,_,<\n5,_ 7,_,<\n" +
		    "6,A 12,_,<\n6,B 11,_,<\n6,_ 7,_,<\n" +
		    "7,_ 7,_,<\n7,# 8,_,>\n" +
		    "8,_ 9,Y,>\n" +
		    "9,_ 10,E,>\n" +
		    "10,_ H,S,>\n" +
		    "11,A 11,A,<\n11,B 11,B,<\n11,_ 2,_,>\n" +
		    "12,A 12,_,<\n12,B 12,_,<\n12,_ 12,_,<\n12,# 13,_,>\n" +
		    "13,_ 14,N,>\n" +
		    "14,_ H,O,>\n";
    }
    else if (desc.equals("<New>")) {
      initPos = 0;
      initChars = "";
      programming = "";
    }
    else
      initPos = -1;
  }
}

class miscUtil {

  public static boolean isLetterOrDigit(char ch) {     // for Java 1.0
    String list = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz" +
                  "0123456789";
    return (list.indexOf(ch) > -1);
  }

  public static String[] split(String text, String splitChars) {
  // Perl-ish split function
  // Splits text into an array of Strings, with the split points at any
  // characters within splitChars (these are not included in the new strings).
  // Discards any empty entries where two splitters occur together or one
  // occurs at the beginning or end of the string.

    int pos = -1;
    int totalSplitters = 0;
    while (++pos < text.length())
      if (splitChars.indexOf(text.charAt(pos)) > -1)
        totalSplitters++;
    int resultSize = totalSplitters+1;
    String[] result = new String[resultSize];
    pos = -1;
    int resultPos = 0;
    int subStart = 0;
    while (++pos < text.length()) {
      if (splitChars.indexOf(text.charAt(pos)) > -1) {
        if (pos > subStart)
          result[resultPos++] = text.substring(subStart, pos);
        subStart = pos+1;
      }
    }
    if (pos > subStart)   // if the last character is not a splitter
      result[resultPos++] = text.substring(subStart, pos);
    if (resultPos < resultSize) {
      // if the array was over-allocated (there were padding separators),
      // trim it down
      resultSize = resultPos;
      String[] paredResult = new String[resultSize];
      for (int i=0; i < resultSize; i++)
        paredResult[i] = result[i];
      return paredResult;
    }
    else
      return result;
  }
}

class TM implements Runnable {

   // transition/program results
  public static final int SUCCESS = 0, HALTED = -1, NOTFOUND = -2,
                          ABNORMAL = -3, NOPROG = -4, USERINT = -5;
  // speeds
  public static final int SLOW = 0, FAST = 1, VFAST = 2, COMPUTE = 3;
  // special states
  public static final int HALT = -1, RUNNING = -2, NA = -3;
  // special chars
  public static final char NUL = 0;
  // directions
  public static final int LEFT = 0, RIGHT = 1, STAY = 2;
  // defaults
  public static final int TAPESIZE = 30000;

  TuringMachine app;
  Component display;
  boolean programmed = false;
  int speed = SLOW;
  Transition[] transTable;
  int tableSize;

  char[] tape;
  int state;
  int tapePos = TAPESIZE/2;
  int initPos;
  String initChars;

  int initNonBlanks = 0, nonBlanks;
  int moving = STAY;
  long totalTransitions;

  public TM(TuringMachine daddy, Component disp) {
    tape = new char[TAPESIZE];
    app = daddy;
    display = disp;
    setState(NA);
  }

  public boolean validTapeChar(char ch) {
    return (miscUtil.isLetterOrDigit(ch) || " +/*-!@#$%^&()=,.".indexOf(ch) > -1);
  }

  public void setSpeed(String newSpeed) {
    if (newSpeed.equals("Slow"))
      speed = SLOW;
    else if (newSpeed.equals("Fast"))
      speed = FAST;
    else if (newSpeed.equals("Very Fast"))
      speed = VFAST;
    else
      speed = COMPUTE;
  }

  public void setState(int newState) {
    state = newState;
    if (state == HALT)
      app.tapePanel.currentState.setText("State: H");
    else if (state == RUNNING)
      app.tapePanel.currentState.setText("State: R");
    else if (state == NA)
      app.tapePanel.currentState.setText("State: N");
    else
      app.tapePanel.currentState.setText("State: " + state);
  }

  public boolean initMachine(int initPos, String initChars,
                             StringBuffer errorMsg) {
    char c;

    this.initPos = initPos;
    this.initChars = initChars;
    int numChars = initChars.length();
    for (int i=0; i < TAPESIZE; i++)
      tape[i] = ' ';
    tapePos = initPos;
    if (initPos + numChars >= TAPESIZE)
      numChars = TAPESIZE - initPos - 1;
    initNonBlanks = 0;
    for (int i=0; i < numChars; i++) {
      c = initChars.charAt(i);
      if (c == '_')
        c = ' ';
      if (!validTapeChar(c)) {
        errorMsg.append("Invalid tape character '" + c + "'");
        return false;
      }
      tape[initPos+i] = c;
      if (c != ' ')
        initNonBlanks++;
    }
    nonBlanks = initNonBlanks;
    totalTransitions = 0;
    setState(1);
    display.repaint();
    return true;
  }

  public void initMachine() {
    char c;

    int numChars = initChars.length();
    for (int i=0; i < TAPESIZE; i++)
      tape[i] = ' ';
    tapePos = initPos;
    if (initPos + numChars >= TAPESIZE)
      numChars = TAPESIZE - initPos - 1;
    for (int i=0; i < numChars; i++) {
      c = initChars.charAt(i);
      tape[initPos+i] = c;
    }
    nonBlanks = initNonBlanks;
    totalTransitions = 0;
    setState(1);
    display.repaint();
  }

  public boolean scrollTape(int dir) {
    if (dir == LEFT) {
      if (tapePos == 0)
        return false;
      else
        tapePos--;
    }
    else if (dir == RIGHT) {
      if (tapePos == TAPESIZE-1)
        return false;
      else
        tapePos++;
    }
    return true;
  }

  public void run() {
    int result = SUCCESS;

    if (speed != COMPUTE)
      display.repaint();
    while (result == SUCCESS) {
      if (speed == SLOW)
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {}
      else if (speed == FAST)
        try {
          Thread.sleep(200);
        } catch (InterruptedException e) {}
      else if (speed == VFAST)
        try {
          Thread.sleep(50);
        } catch (InterruptedException e) {}
      result = transition();
    }
    moving = STAY;
    display.repaint();
    app.controlPanel.showResults(result);
  }

  public boolean program(int initPos, String initChars, String programming,
                         StringBuffer errorMsg) {
    char c;

    if (!initMachine(initPos, initChars, errorMsg)) {
      setState(NA);
      return false;
    }
    String[] codeLines = miscUtil.split(programming, "\n");
    if (codeLines.length < 1) {
        errorMsg.append("No programming entered");
        setState(NA);
        return false;
    }
    tableSize = codeLines.length;
    transTable = new Transition[tableSize];
    int  currentState;
    char currentChar;
    int  newState;
    char newChar;
    int  direction;
    for (int i=1; i <= tableSize; i++) {
      String[] nextTrans = miscUtil.split(codeLines[i-1], ", ");
      if (nextTrans.length < 4 || nextTrans.length > 5) {
        errorMsg.append("In line " + i + ": bad format");
        setState(NA);
        return false;
      }
      if (nextTrans[0].equals("H")) {
        errorMsg.append("In line " + i +
                        ": cannot transition from halt state");
        setState(NA);
        return false;
      }
      try {
        currentState = Integer.parseInt(nextTrans[0]);
      }
      catch (NumberFormatException e) {
        errorMsg.append("In line " + i + ": invalid state '" +
                        nextTrans[0] + "'");
        setState(NA);
        return false;
      }
      if (currentState < 1 || currentState > 99) {
        errorMsg.append("In line " + i + ": invalid state '" +
                        currentState + "'");
        setState(NA);
        return false;
      }
      if (nextTrans[1].length() > 1) {
        errorMsg.append("In line " + i + ": bad format");
        setState(NA);
        return false;
      }
      currentChar = nextTrans[1].charAt(0);
      if (currentChar == '_')
        currentChar = ' ';
      if (!validTapeChar(currentChar)) {
        errorMsg.append("In line " + i + ": Invalid tape character '" +
                        currentChar + "'");
        setState(NA);
        return false;
      }
      if (nextTrans[2].equals("H"))
        newState = HALT;
      else {
        try {
          newState = Integer.parseInt(nextTrans[2]);
        }
        catch (NumberFormatException e) {
          errorMsg.append("In line " + i + ": invalid state '" +
                          nextTrans[2] + "'");
         setState(NA);
          return false;
        }
        if (newState < 1 || newState > 99) {
          errorMsg.append("In line " + i + ": invalid state '" +
                          newState + "'");
          setState(NA);
          return false;
        }
      }
      if (nextTrans[3].length() > 1) {
        errorMsg.append("In line " + i + ": bad format");
        setState(NA);
        return false;
      }
      if (nextTrans.length == 5) {
        if (nextTrans[4].length() > 1) {
          errorMsg.append("In line " + i + ": bad format");
          setState(NA);
          return false;
        }
      }
      c = nextTrans[3].charAt(0);
      if (c == '<' || c == '>') {
        direction = (c == '<') ? LEFT : RIGHT;
        if (nextTrans.length == 4)
          newChar = NUL;
        else {
          newChar = nextTrans[4].charAt(0);
          if (newChar == '_')
            newChar = ' ';
          if (!validTapeChar(newChar)) {
            errorMsg.append("In line " + i + ": Invalid tape character '" +
                            newChar + "'");
            setState(NA);
            return false;
          }
        }
      }
      else {
        newChar = nextTrans[3].charAt(0);
        if (newChar == '_')
          newChar = ' ';
        if (!validTapeChar(newChar)) {
          errorMsg.append("In line " + i + ": Invalid tape character '" +
                          newChar + "'");
          setState(NA);
          return false;
        }
        if (nextTrans.length == 4)
          direction = STAY;
        else {
          c = nextTrans[4].charAt(0);
          if (c != '<' && c != '>') {
            errorMsg.append("In line " + i + ": bad format");
            setState(NA);
            return false;
          }
          direction = (c == '<') ? LEFT : RIGHT;
        }
      }
      transTable[i-1] = new Transition(currentState, currentChar,
                                       newState, newChar, direction);
    }
    programmed = true;
    setState(1);
    return true;
  }

  public int transition() {
    Transition currentTrans;

    if (!programmed)
      return NOPROG;
    else if (state == HALT)
      return HALTED;
    else {
      for (int i=0; i < tableSize; i++) {
        currentTrans = transTable[i];
        if (currentTrans.currentState == state &&
            currentTrans.currentChar == tape[tapePos]) {
          if (currentTrans.newChar != NUL) {
            if (currentTrans.newChar == ' ' && tape[tapePos] != ' ')
              nonBlanks--;
            else if (currentTrans.newChar != ' ' && tape[tapePos] == ' ')
              nonBlanks++;
            tape[tapePos] = currentTrans.newChar;
          }
          boolean scrResult = scrollTape(currentTrans.direction);
          if (!scrResult) {
            setState(HALT);
            return ABNORMAL;
          }
          if (speed == COMPUTE)
            state = currentTrans.newState;
          else
            setState(currentTrans.newState);
          totalTransitions++;
          if (moving != currentTrans.direction)
            moving = currentTrans.direction;
          if (speed != COMPUTE)
            display.repaint();
          return SUCCESS;
        }
      }
      setState(HALT);
      return NOTFOUND;
    }
  }
}

class TapeDisplay extends Canvas implements Runnable {
  static final int MINW = 200, MINH = 50,
                   CELLHEIGHT = 20, CELLWIDTH = 20;

  int[] leftTriXs, rightTriXs, triYs;
  Dimension minSize;
  TuringMachine app;
  Thread scroller = new Thread(this);
  int scrollDir;
  int width, height, xpos, ypos, numCells;
  Font tapeFont;
  Color darkYellow = new Color(189, 193, 0);

  public TapeDisplay(TuringMachine daddy) {
    minSize = new Dimension(MINW, MINH);
    tapeFont = new Font("Helvetica", Font.BOLD, 12);
    app = daddy;
  }

  public void initGraphics() {
    width = size().width; height = size().height;
    ypos = height/2 - CELLHEIGHT/2;
    numCells = width/CELLWIDTH - 3;
    xpos = (width - numCells*CELLWIDTH) / 2;
    int[] ltx = {20, 4, 20, 20};
    leftTriXs = ltx;
    int[] rtx = {width-21, width-5, width-21, width-21};
    rightTriXs = rtx;
    int[] ty = {ypos, height/2, ypos+CELLHEIGHT, ypos};
    triYs = ty;
  }

  public void paint(Graphics g) {
    g.setFont(tapeFont);
    g.setColor(new Color(192, 0, 0));
    g.drawRect(0, 0, width-1, height-1);
    g.drawRect(1, 1, width-3, height-3);
    g.fillPolygon(new int[]{2, 2, 15, 2}, new int[]{15, 2, 2, 15}, 4);
    g.fillPolygon(new int[]{2, 2, 15, 2},
                  new int[]{height-15, height-2, height-2, height-15}, 4);
    g.fillPolygon(new int[]{width-2, width-2, width-15, width-2},
                  new int[]{15, 2, 2, 15}, 4);
    g.fillPolygon(new int[]{width-2, width-2, width-15, width-2},
                  new int[]{height-15, height-2, height-2, height-15}, 4);
    if (app.machine.moving == TM.LEFT)
      g.setColor(Color.yellow);
    else
      g.setColor(darkYellow);
    g.fillPolygon(leftTriXs, triYs, 4);
    if (app.machine.moving == TM.RIGHT)
      g.setColor(Color.yellow);
    else
      g.setColor(darkYellow);
    g.fillPolygon(rightTriXs, triYs, 4);
    g.setColor(Color.black);
    g.drawPolygon(leftTriXs, triYs, 4);
    g.drawPolygon(rightTriXs, triYs, 4);

    g.setColor(Color.black);
    int actualNumCells = numCells;
    if (!app.machine.programmed) {
      for (int x=xpos, count=0; count < numCells; x+=CELLWIDTH, count++) {
        if (count == numCells/2) {
          g.setColor(Color.red);
          g.drawRect(x, ypos, CELLWIDTH, CELLHEIGHT);
          g.drawRect(x-1, ypos-1, CELLWIDTH+2, CELLHEIGHT+2);
          g.setColor(Color.black);
        }
        else
          g.drawRect(x, ypos, CELLWIDTH, CELLHEIGHT);
        if (count == numCells/2 + 1) {
          g.setColor(Color.red);
          g.drawLine(x, ypos, x, ypos+CELLHEIGHT);
          g.setColor(Color.black);
        }
      }
    }
    else {
      char[] tape = app.machine.tape;
      int tapePos = app.machine.tapePos;
      int centerCell = numCells/2;
      int centerCellPos = xpos + CELLWIDTH*centerCell;
      int charsToLeft = numCells/2;
      int charsToRight = (numCells%2 == 0) ? numCells/2-1: numCells/2;
      int firstCell, lastCell, startx;
      if (tapePos < charsToLeft) {
        firstCell = 0;
        startx = xpos + CELLWIDTH * (charsToLeft - tapePos);
        actualNumCells -= (charsToLeft - tapePos);
      }
      else {
        firstCell = tapePos - charsToLeft;
        startx = xpos;
      }
      if (tapePos + charsToRight >= TM.TAPESIZE)
        actualNumCells -= ((tapePos+charsToRight+1) - TM.TAPESIZE);
      lastCell = firstCell + actualNumCells - 1;
      for (int x=startx, tpos = firstCell; tpos <= lastCell;
           x+=CELLWIDTH, tpos++) {
        if (x == centerCellPos) {
          g.setColor(Color.red);
          g.drawRect(x, ypos, CELLWIDTH, CELLHEIGHT);
          g.drawRect(x-1, ypos-1, CELLWIDTH+2, CELLHEIGHT+2);
          g.setColor(Color.black);
        }
        else
          g.drawRect(x, ypos, CELLWIDTH, CELLHEIGHT);
        if (x == centerCellPos + CELLWIDTH) {
          g.setColor(Color.red);
          g.drawLine(x, ypos, x, ypos+CELLHEIGHT);
          g.setColor(Color.black);
        }
        if (tape[tpos] != ' ')
          g.drawString("" + tape[tpos], x+CELLWIDTH/2-4, ypos+CELLHEIGHT*3/4);
      }
    }
  }

  public boolean mouseDown(Event evt, int x, int y) {
    if (!app.execution.isAlive() && !scroller.isAlive()) {
      if (x >= leftTriXs[1] && x <= leftTriXs[0] &&
          y >= triYs[0] && y <= triYs[2]) {
        scrollDir = TM.LEFT;
        if (app.machine.scrollTape(scrollDir)) {
          app.machine.moving = scrollDir;
          this.repaint();
          scroller = new Thread(this);
          scroller.start();
        }
      }
      else if (x >= rightTriXs[0] && x <= rightTriXs[1] &&
               y >= triYs[0] && y <= triYs[2]) {
        scrollDir = TM.RIGHT;
        if (app.machine.scrollTape(scrollDir)) {
          app.machine.moving = scrollDir;
          this.repaint();
          scroller = new Thread(this);
          scroller.start();
        }
      }
    }
    return true;
  }

  public boolean mouseUp(Event evt, int x, int y) {
    if (scroller.isAlive()) {
      scroller.stop();
      app.machine.moving = TM.STAY;
      repaint();
    }
    return true;
  }

  public void run() {
    boolean offTape = false;
    while (!offTape) {
      try {
        Thread.sleep(100);
      }
      catch (InterruptedException e) {}
      offTape = !app.machine.scrollTape(scrollDir);
      this.repaint();
    }
    app.machine.moving = TM.STAY;
    repaint();
  }

  public Dimension minimumSize() {
    return new Dimension(MINW, MINH);
  }

  public Dimension preferredSize() {
    return new Dimension(MINW, MINH);
  }
}

class TapePanel extends Panel {
  Label currentState;
  Choice speed;
  TapeDisplay tapeDisplay;
  TuringMachine app;

  public TapePanel(TuringMachine daddy) {
    app = daddy;
    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints gbcon = new GridBagConstraints();

    currentState = new Label("State: H", Label.CENTER);
    Button start = new Button("Start"),
           stop = new Button("Stop"),
           resume = new Button("Resume"),
           step = new Button("Step");
    Label speedLabel = new Label("Speed:", Label.RIGHT);
    speed = new Choice();
    speed.addItem("Slow");
    speed.addItem("Fast");
    speed.addItem("Very Fast");
    speed.addItem("Compute");
    tapeDisplay = new TapeDisplay(app);

    setLayout(gbl);

    // add current state label
    gbcon.gridx = 0; gbcon.gridy = 0;
    gbcon.gridwidth = 6;
    gbcon.anchor = GridBagConstraints.NORTH;
    gbcon.weighty = 0.1;
    gbl.setConstraints(currentState, gbcon);
    add(currentState);

    // add start, stop, and step control buttons
    gbcon.gridy = 1;
    gbcon.gridwidth = 1;
    gbcon.insets = new Insets(0,0,0,40);
    gbcon.weightx = 0.2; gbcon.weighty = 0.1;
    gbl.setConstraints(start, gbcon);
    add(start);
    gbcon.gridx = 1;
    gbl.setConstraints(stop, gbcon);
    add(stop);
    gbcon.gridx = 2;
    gbl.setConstraints(resume, gbcon);
    add(resume);
    gbcon.gridx = 3;
    gbl.setConstraints(step, gbcon);
    add(step);

    // add speed pull-down list control
    gbcon.gridx = 4;
    gbcon.insets = new Insets(0,0,0,5);
    gbl.setConstraints(speedLabel, gbcon);
    add(speedLabel);
    gbcon.gridx = 5;
    gbl.setConstraints(speed, gbcon);
    add(speed);
    
    // add turing tape display canvas
    gbcon.gridx = 0; gbcon.gridy = 2;
    gbcon.insets = new Insets(0,0,5,0);
    gbcon.gridwidth = 6;
    gbcon.fill = GridBagConstraints.BOTH;
    gbcon.anchor = GridBagConstraints.SOUTH;
    gbcon.weightx = 0; gbcon.weighty = 0.8;
    gbl.setConstraints(tapeDisplay, gbcon);
    add(tapeDisplay);
  }

  public boolean action(Event evt, Object arg) {
    if (evt.target instanceof Button) {
      String command = (String)arg;
      int result;
      TM machine = app.machine;
      ControlPanel cp = app.controlPanel;
      SpecsPanel sp = app.specsPanel;
      if (command.equals("Step")) {
        if (machine.state == TM.HALT) {
          cp.addMessage("Restarting...");
          machine.initMachine();
        }
        result = machine.transition();
        machine.moving = TM.STAY;
        tapeDisplay.repaint();
        if (result == TM.HALTED)
          cp.addMessage("Machine is halted");
        else if (result == TM.ABNORMAL)
          cp.addMessage("The machine has run\noff the end of the tape");
        else if (result == TM.NOTFOUND)
          cp.addMessage("No applicable transition found");
        else if (result == TM.NOPROG)
          cp.addMessage("No program entered");
        else if (machine.state == TM.HALT)
          cp.addMessage("Halt state reached");
        return true;
      }
      else if (command.equals("Start") || command.equals("Resume")) {
        if (!app.machine.programmed)
          cp.addMessage("Cannot start: No program entered");
        else if (app.execution.isAlive())
          cp.addMessage("Already running");
        else {
          StringBuffer errorMsg = new StringBuffer(50);
          boolean success = true;
          if (command.equals("Start"))
            success = app.machine.initMachine(sp.initPosBar.getValue(),
                      sp.initChars.getText(), errorMsg);
          if (success) {
            app.machine.setSpeed(speed.getSelectedItem());
            cp.addMessage("Running...");
            app.execution = new Thread(app.machine);
            app.execution.start();
          }
          else
            cp.addMessage("Error initializing machine:\n"+errorMsg);
        }
      }
      else if (command.equals("Stop")) {
        if (app.execution.isAlive()) {
          app.execution.stop();
          if (app.machine.speed == TM.COMPUTE)
            app.machine.setState(app.machine.state);
          app.machine.moving = TM.STAY;
          tapeDisplay.repaint();
          cp.showResults(TM.USERINT);
        }
        else
          cp.addMessage("Machine is not running");
      }
    }
    return false;
  }
}

class SpecsPanel extends Panel {
  TuringMachine app;
  Choice loader;
  TextField machineName;
  Label initPos;
  Scrollbar initPosBar;
  TextField initChars;

  public SpecsPanel(TuringMachine daddy) {
    app = daddy;
    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints gbcon = new GridBagConstraints();

    Label nameLabel = new Label("Machine name"),
          initPosLabel = new Label("Initial tape position"),
          initCharsLabel = new Label("Initial characters on tape");
    Button loadButton = new Button("Load new program:");
    loader = new Choice();
    for (int i=0; i < PresetTM.names.length; i++)
      loader.addItem(PresetTM.names[i]);
    machineName = new TextField(30);
    initPosBar = new Scrollbar(Scrollbar.HORIZONTAL, TM.TAPESIZE/2, 50,
                               0, TM.TAPESIZE);
    initPos = new Label("" + (TM.TAPESIZE/2), Label.RIGHT);
    initChars = new TextField(30);

    setLayout(gbl);

    gbcon.gridx = 0; gbcon.gridy = 0;
    gbcon.insets = new Insets(0,0,5,5);
    gbcon.anchor = GridBagConstraints.NORTHWEST;
    gbl.setConstraints(loadButton, gbcon);
    add(loadButton);
    gbcon.gridx = 1;
    gbcon.gridwidth = 2;
    gbcon.fill = GridBagConstraints.NONE;
    gbl.setConstraints(loader, gbcon);
    add(loader);

    gbcon.gridx = 0; gbcon.gridy = 1;
    gbcon.fill = GridBagConstraints.NONE;
    gbl.setConstraints(nameLabel, gbcon);
    add(nameLabel);
    gbcon.gridx = 1;
    gbcon.gridwidth = 2;
    gbcon.fill = GridBagConstraints.HORIZONTAL;
    gbl.setConstraints(machineName, gbcon);
    add(machineName);

    gbcon.gridx = 0; gbcon.gridy = 2;
    gbcon.gridwidth = 1;
    gbcon.fill = GridBagConstraints.NONE;
    gbl.setConstraints(initPosLabel, gbcon);
    add(initPosLabel);
    gbcon.gridx = 1;
    gbcon.fill = GridBagConstraints.HORIZONTAL;
    gbcon.weightx = 0.7;
    gbl.setConstraints(initPosBar, gbcon);
    add(initPosBar);
    gbcon.weightx = 0;
    gbcon.gridx = 2;
    gbcon.fill = GridBagConstraints.NONE;
    gbl.setConstraints(initPos, gbcon);
    add(initPos);

    gbcon.gridx = 0; gbcon.gridy = 3;
    gbcon.fill = GridBagConstraints.NONE;
    gbl.setConstraints(initCharsLabel, gbcon);
    add(initCharsLabel);
    gbcon.gridx = 1;
    gbcon.gridwidth = 2;
    gbcon.fill = GridBagConstraints.HORIZONTAL;
    gbl.setConstraints(initChars, gbcon);
    add(initChars);
  }

  public boolean action(Event evt, Object arg) {
    StringBuffer errorMsg = new StringBuffer(50);

    if (!(evt.target instanceof Button) || app.execution.isAlive())
      return true;
    String name = loader.getSelectedItem();
    ControlPanel cp = app.controlPanel;
    PresetTM preset = new PresetTM(name);
    if (preset.initPos < 0) {
      cp.addMessage("Machine unimplemented");
      return true;
    }
    app.machine = new TM(app, app.tapePanel.tapeDisplay);
    if (name.equals("<New>"))
      machineName.setText("");
    else
      machineName.setText(name);
    initPos.setText(""+preset.initPos);
    initPosBar.setValue(preset.initPos);
    initChars.setText(preset.initChars);
    app.programPanel.programming.setText(preset.programming);
    if (!name.equals("<New>")) {
      cp.addMessage("\nEntering program...");
      boolean success = app.machine.program(preset.initPos, preset.initChars,
                                            preset.programming, errorMsg);
      if (success) {
        cp.addMessage("Machine programmed successfully.");
        if (preset.comment.length() > 0)
          cp.addMessage("\nProgram description:\n"+preset.comment+"\n");
      }
      else {
        cp.addMessage("\nError:");
        cp.addMessage(errorMsg.toString());
      }
    }
    app.tapePanel.tapeDisplay.repaint();
    return true;
  }

  public boolean handleEvent(Event evt) {
    if (evt.target instanceof Scrollbar) {
      initPos.setText("" + initPosBar.getValue());
      return true;
    }
    else
      return super.handleEvent(evt);
  }
}

class ProgramPanel extends Panel {
  TextArea programming;

  public ProgramPanel() {
    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints gbcon = new GridBagConstraints();

    Label programmingLabel = new Label("Programming");
    programming = new TextArea(10, 20);

    setLayout(gbl);

    gbcon.gridx = 0; gbcon.gridy = 0;
    gbcon.insets = new Insets(0,0,5,5);
    gbcon.weighty = 0.1;
    gbl.setConstraints(programmingLabel, gbcon);
    add(programmingLabel);
    gbcon.gridy = 1;
    gbcon.fill = GridBagConstraints.BOTH;
    gbcon.anchor = GridBagConstraints.NORTHWEST;
    gbcon.weighty = 0.9;
    gbl.setConstraints(programming, gbcon);
    add(programming);
  }
}

class ControlPanel extends Panel {
  TextArea messages;
  TuringMachine app;

  public ControlPanel(TuringMachine daddy) {
    app = daddy;
    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints gbcon = new GridBagConstraints();

    Button reset = new Button("Clear Program"),
           install = new Button("Install Program");
    messages = new TextArea(5, 30);
    messages.setEditable(false);
    Button clearMsgs = new Button("Clear Message Box");

    setLayout(gbl);

    gbcon.gridx = 0; gbcon.gridy = 0;
    gbcon.insets = new Insets(0,0,5,5);
    gbcon.anchor = GridBagConstraints.NORTHEAST;
    gbcon.weightx = 0.5; gbcon.weighty = 0.1;
    gbl.setConstraints(reset, gbcon);
    add(reset);
    gbcon.gridx = 1;
    gbl.setConstraints(install, gbcon);
    add(install);

    gbcon.gridx = 0; gbcon.gridy = 1;
    gbcon.gridwidth = 2;
    gbcon.fill = GridBagConstraints.BOTH;
    gbcon.anchor = GridBagConstraints.SOUTHEAST;
    gbcon.weightx = 0; gbcon.weighty = 0.8;
    gbl.setConstraints(messages, gbcon);
    add(messages);

    gbcon.gridx = 0; gbcon.gridy = 2;
    gbcon.gridwidth = 2;
    gbcon.fill = GridBagConstraints.NONE;
    gbcon.anchor = GridBagConstraints.CENTER;
    gbcon.weightx = 0; gbcon.weighty = 0.1;
    gbl.setConstraints(clearMsgs, gbcon);
    add(clearMsgs);
  }

  public boolean action(Event evt, Object arg) {
    StringBuffer errorMsg = new StringBuffer(50);

    if (evt.target instanceof Button) {
      String command = (String)arg;
      if (command.equals("Clear Program"))
        app.programPanel.programming.setText("");
      else if (command.equals("Install Program")) {
        int initPos = app.specsPanel.initPosBar.getValue();
        String initChars = app.specsPanel.initChars.getText();
        String programming = app.programPanel.programming.getText();
        app.machine = new TM(app, app.tapePanel.tapeDisplay);
        addMessage("\nEntering program...");
        boolean success = app.machine.program(initPos, initChars, programming,
                                              errorMsg);
        app.tapePanel.tapeDisplay.repaint();
        if (success)
          addMessage("Machine programmed successfully");
        else {
          addMessage("Error:");
          addMessage(errorMsg.toString());
        }
      }
      else if (command.equals("Clear Message Box"))
        messages.setText("");        
    }
    return true;
  }

  public void addMessage(String msg) {
    messages.appendText(msg+"\n");
  }

  public void showResults(int haltReason) {
    addMessage("\nMachine halted:");
    switch (haltReason) {
      case TM.HALTED: addMessage("Halt state reached"); break;
      case TM.ABNORMAL: addMessage("The machine ran off the tape"); break;
      case TM.NOTFOUND: addMessage("No applicable transition found"); break;
      case TM.USERINT: addMessage("User interrupt"); break;
    }
    addMessage("" + app.machine.totalTransitions + " total transitions");
    addMessage("" + app.machine.nonBlanks + " non-blank characters on tape");
  }
}

public class TuringMachine extends java.applet.Applet {
  static final Color BGCOLOR=new Color(192, 192, 192);

  TapePanel tapePanel;
  SpecsPanel specsPanel;
  ProgramPanel programPanel;
  ControlPanel controlPanel;

  TM machine;
  Thread execution;

  public void init() {
    setBackground(BGCOLOR);

    tapePanel = new TapePanel(this);
    specsPanel = new SpecsPanel(this);
    programPanel = new ProgramPanel();
    controlPanel = new ControlPanel(this);    
    execution = new Thread(machine);
    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints gbcon = new GridBagConstraints();
    setLayout(gbl);

    gbcon.gridx = 0; gbcon.gridy = 0;
    gbcon.gridwidth = 2;
    gbcon.fill = GridBagConstraints.BOTH;
    gbcon.insets = new Insets(0,0,0,0);
    gbcon.anchor = GridBagConstraints.NORTHWEST;
    gbcon.weighty = 0.6;
    gbl.setConstraints(tapePanel, gbcon);
    add(tapePanel);
    tapePanel.validate();

    gbcon.gridy = 1;
    gbcon.weighty = 0.2;
    gbl.setConstraints(specsPanel, gbcon);
    add(specsPanel);
    specsPanel.validate();

    gbcon.gridy = 2;
    gbcon.gridwidth = 1;
    gbcon.anchor = GridBagConstraints.SOUTHEAST;
    gbcon.weighty = 0.2;
    gbcon.insets = new Insets(0,0,0,15);
    gbl.setConstraints(programPanel, gbcon);
    add(programPanel);
    programPanel.validate();

    gbcon.gridx = 1;
    gbcon.insets = new Insets(0,15,0,0);
    gbl.setConstraints(controlPanel, gbcon);
    add(controlPanel);
    controlPanel.validate();

    machine = (TM)new TM(this, tapePanel.tapeDisplay); //placeholder
  }

  public void start() {
    tapePanel.tapeDisplay.initGraphics();
  }
}
