package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.drivers.MacroDriver;
import edu.kis.powp.jobs2d.events.SelectClearMacroListener;
import edu.kis.powp.jobs2d.events.SelectStartMacroListener;
import edu.kis.powp.jobs2d.events.SelectStopMacroListener;

public class MacroFeature {
    private static MacroDriver macroDriver;
    private static Application app;
    private static boolean isRecording;

    public static void setupMacro(Application application) {
        macroDriver = new MacroDriver();
        app = application;
        app.addComponentMenu(MacroFeature.class, "Macro");
        app.addComponentMenuElement(MacroFeature.class, "Start recording", new SelectStartMacroListener());
        //app.addComponentMenuElement(MacroFeature.class, "Stop recording", new SelectStopMacroListener());
        app.addComponentMenuElement(MacroFeature.class, "Clear macro", new SelectClearMacroListener());
    }

    public static MacroDriver getMacroDriver(){
        return macroDriver;
    }

    public static  void setIsRecording(boolean isRecording){
        MacroFeature.isRecording = isRecording;
    }

    public static boolean isRecording(){
        return MacroFeature.isRecording;
    }
}