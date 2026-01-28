package wiseboard.controller;

import wiseboard.input.WiseInput;

public class WiseController {

    private final WiseInput wiseInput;

    public WiseController() {
        this.wiseInput = new WiseInput();
    }

    public void Run() {
        wiseInput.Start();
    }
}